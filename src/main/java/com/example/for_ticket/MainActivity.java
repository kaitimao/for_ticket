package com.example.for_ticket;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mikepenz.materialdrawer.DrawerBuilder;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> al;
    static int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SyncTEST syncTEST = new SyncTEST();
        syncTEST.execute();

        initData();
        initdrawer();

    }



    private void initData() {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.titleA, PageFragment.class)
                .add(R.string.titleB, ScanneFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);


    }

    private void initdrawer() {
        new DrawerBuilder().withActivity(this).build();
    }


    public class SyncTEST extends AsyncTask<Void, Void, Void> {
        String responseData;
        String url, url_end, url1;
        int month, year, date;
        String html;
        private String numbers;

        private String getNumbers() {
            return numbers;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                html = logic();
                Log.e("TESTHTML", html);
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(html).build();
              /*  Log.e("TESTURL", logic());
                Log.e("TEST", request.toString());*/
                Response response = client.newCall(request).execute();
                responseData = response.body().string();
                //Log.e("TEST1", responseData);


                //Jsoup
                Document d = Jsoup.parse(responseData);
                Elements elements = d.select("td[class*=number]");
                //Log.e("TEST", elements.toString());
                al = new ArrayList<>();
                for (Element element : elements) {
                    String atest = element.text().trim();
                    al.add(atest.trim());

                    //postShow += atest;

                    //  Log.e("TEST", element.text());
                }
                numbers = elements.text();
                Log.e("TEST2", d.text());
                Log.e("TEST3", getNumbers());
                    /*for (Element element : elements){
                        al.add(element);
                        Log.e("TEST", al.toString());
                    }*/
            } catch (Exception e) {
                Log.e("TEST4", e.toString());
            }
            return null;
        }

        private String logic() {
            url = "https://www.etax.nat.gov.tw/etw-main/web/ETW183W2_";
            url_end = "/";
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR) - 1911;
            month = calendar.get(Calendar.MONTH) + 1;
            date = calendar.get(Calendar.DATE);

            if ((month % 2) == 0) {
                month -= 1;
                if (month < 10) {
                    url1 = url + year + "0" + (month - 2);
                    Log.d("TEST", url1);
                } else {
                    url1 = url + year + (month - 2);
                    Log.d("TEST", url1);
                }

            } else {
                if (month < 10) {
                    url1 = url + year + "0" + (month - 2);
                    Log.d("TEST", url1);
                } else {
                    url1 = url + year + (month - 2);
                    Log.d("TEST", url1);
                }
            }

            // Log.e("TEST", url1);
            return url1;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            flag = 1;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
