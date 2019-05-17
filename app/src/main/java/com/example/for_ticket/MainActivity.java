package com.example.for_ticket;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int flag = 0;
    String data11, data12, data13, data14;
    private ArrayList<String> al;
    String data131, data132, data133;
    int n1 = 0, n2 = 0, n3 = 0, n4 = 0, n5 = 0;
    String data141, data142;
    EditText input_number;
    private Button testt;
    private TextView show_point, show_number;
    private String ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        process();
        SyncTEST syncTEST = new SyncTEST();
        syncTEST.execute();

        input_number.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId){
                    case EditorInfo.IME_ACTION_DONE:
                        methods();
                }
                return false;
            }
        });
    }


    private void methods(){
        ticket = input_number.getText().toString().trim();

        show_number.setText("");
        show_number.setText(ticket);

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    int number = Integer.parseInt(ticket);
                    n1 = Integer.valueOf(data141);
                    n2 = Integer.valueOf(data142);
                    String d1 = data131.substring(data131.length() - 3);
                    String d2 = data132.substring(data132.length() - 3);
                    String d3 = data133.substring(data133.length() - 3);
                    n3 = Integer.valueOf(d3);
                    n4 = Integer.valueOf(d2);
                    n5 = Integer.valueOf(d1);

                    Log.e("TESTn1", String.valueOf(n1));
                    Log.e("TESTn2", String.valueOf(n2));
                    Log.e("TESTn3", String.valueOf(n3));
                    Log.e("TESTn4", String.valueOf(n4));
                    Log.e("TESTn5", String.valueOf(n5));


                    if (number == n1) {
                        show_point_to_textview(0);
                    } else if(number == n2){
                        show_point_to_textview(0);
                    }else if(number == n3) {
                        show_point_to_textview(0);
                    }else if(number == n4) {
                        show_point_to_textview(0);
                    }else if(number == n5) {
                        show_point_to_textview(0);
                    }else {
                        show_point_to_textview(1);
                    }

                }catch (Exception e){
                    Log.e("TEST", e.toString());
                    Log.e("TEST", String.valueOf(n1));

                }
            }
        };
        if( flag == 1 ){
            Runnable r1 = new Runnable() {
                @Override
                public void run() {
                    try {
                        if (flag == 1){
                            analyce();
                        }else {
                            Log.e("TEST", "在等一下");
                        }
                    } catch (Exception e) {
                        Log.e("TEST", e.toString());
                    }
                }
            };
            r1.run();
            r2.run();
        }else {
            Log.e("TEST", "請在試一次");
        }
        input_number.setText("");
    }



    private void show_point_to_textview(int i) {
        switch (i) {
            case 0:
                show_point.setText("可能中獎了!!");
                break;
            case 1:
                show_point.setText("銘謝惠顧!!");
                break;
            default:
        }
    }

    private void process() {
        input_number = findViewById(R.id.input_number);
        testt = findViewById(R.id.testt);
        show_point = findViewById(R.id.show_point);
        show_number = findViewById(R.id.show_number);
    }

    private void analyce() {


        //本月發票 一千萬
        data11 = al.get(0);
        //本月發票 兩百萬
        data12 = al.get(1);
        //本月發票 20萬 可對末三碼
        data13 = al.get(2);
        //本月發票 200塊
        data14 = al.get(3);

        String[] data13x = data13.split("、");
        data131 = data13x[0];
        data132 = data13x[1];
        data133 = data13x[2];

        String[] data14x = data14.split("、");
        data141 = data14x[0];
        data142 = data14x[1];

        Log.e("TEST", al.toString());
        Log.e("TEST141", data141);
        Log.e("TEST142", data142);
        Log.e("TEST131", data131);
        Log.e("TEST132", data132);
        Log.e("TEST133", data133);
        Log.e("TEST12", data12);
        Log.e("TEST11", data11);

        // 每一個array 佔字元   8/8/26/7
    }

    public void testt(View view) {
        testt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                methods();
            }
        });
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
                Log.e("TEST1", responseData);


                //Jsoup
                Document d = Jsoup.parse(responseData);
                Elements elements = d.select("td[class*=number]");
                Log.e("TEST", elements.toString());
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
                    url1 = url + year + "0" + (month - 4);
                    Log.d("TEST", url1);
                } else {
                    url1 = url + year + (month - 4);
                    Log.d("TEST", url1);
                }

            } else {
                if (month < 10) {
                    url1 = url + year + "0" + (month - 4);
                    Log.d("TEST", url1);
                } else {
                    url1 = url + year + (month - 4);
                    Log.d("TEST", url1);
                }
            }
        /*
        Log.d("TEST", String.valueOf(year) );
        Log.d("TEST", String.valueOf(month) );
        Log.d("TEST", String.valueOf(date) );
    */
            // Log.e("TEST", url1);
            return url1;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            flag = 1;
            Log.e("TEST", String.valueOf(flag));
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = 0;
    }


}
