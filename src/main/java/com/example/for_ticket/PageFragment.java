package com.example.for_ticket;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;

import static com.example.for_ticket.MainActivity.al;
import static com.example.for_ticket.MainActivity.flag;

public class PageFragment extends Fragment {
    private View mRootView;
    private AppCompatButton testt;
    private AppCompatEditText input_number;
    private PageFragment pageFragment;
    private AppCompatTextView show_number, show_get;
    private String ticket;
    private static String data131, data132, data133;
    private static String data141, data142;
    private int n1 = 0, n2 = 0, n3 = 0, n4 = 0, n5 = 0;
    private String data11, data12, data13, data14;
    private AppCompatButton first_month_button, sec_month_button, third_month_button;
    private AppCompatTextView wait_to_earn;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.dosomething, container, false);
        initailize();
        how_much_button();
        return mRootView;


    }

    @Override
    public void onStart() {
        super.onStart();
        testt.setOnClickListener(v -> methods());
        input_number.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId) {
                case EditorInfo.IME_ACTION_DONE:
                    methods();
                    break;
            }
            return false;
        });
    }

    private void initailize() {
        testt = mRootView.findViewById(R.id.testt);
        input_number = mRootView.findViewById(R.id.input_number);
        show_number = mRootView.findViewById(R.id.show_number);
        show_get = mRootView.findViewById(R.id.show_get);
        first_month_button = mRootView.findViewById(R.id.first_month_button);
        sec_month_button = mRootView.findViewById(R.id.sec_month_button);
        third_month_button = mRootView.findViewById(R.id.third_month_button);
        wait_to_earn = mRootView.findViewById(R.id.wait_to_earn);
    }

    private void methods() {
        ticket = input_number.getText().toString().trim();
        show_number.setText("");
        show_number.setText(ticket);
        //Log.e("flag is", String.valueOf(flag));
        if (flag ==1) {
            analyce();
            Runnable r2 = () -> {
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

                    //Log.e("TESTn1", String.valueOf(n1));
                    //Log.e("TESTn2", String.valueOf(n2));
                    //Log.e("TESTn3", String.valueOf(n3));
                   // Log.e("TESTn4", String.valueOf(n4));
                   // Log.e("TESTn5", String.valueOf(n5));

                    if (number == n1) {
                        show_point_to_textview(0);
                    } else if (number == n2) {
                        show_point_to_textview(0);
                    } else if (number == n3) {
                        show_point_to_textview(0);
                    } else if (number == n4) {
                        show_point_to_textview(0);
                    } else if (number == n5) {
                        show_point_to_textview(0);
                    } else {
                        show_point_to_textview(1);
                    }

                } catch (Exception e) {
                    //Log.e("TEST", e.toString());
                    //Log.e("TEST", String.valueOf(n1));

                }
            };
            r2.run();

        } else {
            //Log.e("TEST", "flag not true");
        }
        input_number.setText("");
    }

    private void show_point_to_textview(int i) {
        switch (i) {
            case 0:
                show_get.setText("可能中獎了!!");
                //Log.e("TEST", show_get.getText().toString());
                break;
            case 1:
                show_get.setText("銘謝惠顧!!");
                break;
            default:
        }
    }

    private void analyce() {

        data11 = al.get(0);
        data12 = al.get(1);
        data13 = al.get(2);
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

    private void how_much_button(){
        String st1 =  "還要" , st2 = "天才能兌獎喔";
        String stn;
        String month_to1, month_to2, month_to3;
        int month1, month2, month3, month4, month5, month6;

        int nowYear, nowMonth, nowDate, needDate, maxDate, nextMonth, nowMaxDate;
        Calendar calendar2 = Calendar.getInstance();
        nowYear = calendar2.get(Calendar.YEAR) - 1911;
        nowMonth = calendar2.get(Calendar.MONTH) +1;
        nowDate = calendar2.get(Calendar.DAY_OF_MONTH);
        /*Log.e("nowYear", String.valueOf(nowYear));
        Log.e("nowMonth", String.valueOf(nowMonth));
        Log.e("nowDate", String.valueOf(nowDate));*/
        if(nowDate != 25){
            if(nowDate < 25){
                needDate = 25 - nowDate;
                stn = st1 + String.valueOf(needDate) + st2;
                Log.e("date", stn);
                wait_to_earn.setText(stn);
            }else if(nowDate > 25){
                //取得當月最大日
                calendar2.set(Calendar.MONTH, nowMonth);
                nowMaxDate = calendar2.getActualMaximum(Calendar.DAY_OF_MONTH);

                needDate = (nowMaxDate - nowDate) + 25;
                if(nowMonth == 1 || nowMonth == 3 || nowMonth == 5 || nowMonth == 7 || nowMonth ==8 || nowMonth == 10 || nowMonth == 12){
                    needDate += 1;
                }
                Log.e("da te", String.valueOf(nowMaxDate));
                stn = st1 + String.valueOf(needDate) + st2;
                wait_to_earn.setText(stn);
            }
        }else {
            wait_to_earn.setText("試試手氣的時候到囉");
        }
        //如果是偶數月
        if((nowMonth % 2) == 0){
            //當月為二月
            if (nowMonth == 2 ){
                month1 = 1;
                month2 = 2;
                month3 = 11;
                month4 = 12;
                month5 = 9;
                month6 = 10;
                month_to1 = month1 + " " + month2 + "月兌獎";
                month_to2 = month3 + " " + month4 + "月兌獎";
                month_to3 = month5 + " " + month6 + "月兌獎";
                first_month_button.setText(month_to1);
                sec_month_button.setText(month_to2);
                third_month_button.setText(month_to3);

            }else if(nowMonth == 4 ){
                month1 = 3;
                month2 = 4;
                month3 = 1;
                month4 = 2;
                month5 = 11;
                month6 = 12;
                month_to1 = month1 + " " + month2 + "月兌獎";
                month_to2 = month3 + " " + month4 + "月兌獎";
                month_to3 = month5 + " " + month6 + "月兌獎";
                first_month_button.setText(month_to1);
                sec_month_button.setText(month_to2);
                third_month_button.setText(month_to3);
            }else{
                month1 = nowMonth -1;
                month2 = nowMonth ;
                month3 = nowMonth -3;
                month4 = nowMonth -2;
                month5 = nowMonth -5;
                month6 = nowMonth -4;
                month_to1 = month1 + " " + month2 + "月兌獎";
                month_to2 = month3 + " " + month4 + "月兌獎";
                month_to3 = month5 + " " + month6 + "月兌獎";
                first_month_button.setText(month_to1);
                sec_month_button.setText(month_to2);
                third_month_button.setText(month_to3);
            }
        //單數月
        }else{
            if (nowMonth == 1){
                month1 = 11;
                month2 = 12;
                month3 = 9;
                month4 = 10;
                month5 = 7;
                month6 = 8;
                month_to1 = month1 + " " + month2 + "月兌獎";
                month_to2 = month3 + " " + month4 + "月兌獎";
                month_to3 = month5 + " " + month6 + "月兌獎";
                first_month_button.setText(month_to1);
                sec_month_button.setText(month_to2);
                third_month_button.setText(month_to3);
            }else if(nowMonth == 3){
                month1 = 2;
                month2 = 1;
                month3 = 11;
                month4 = 12;
                month5 = 9;
                month6 = 10;
                month_to1 = month1 + " " + month2 + "月兌獎";
                month_to2 = month3 + " " + month4 + "月兌獎";
                month_to3 = month5 + " " + month6 + "月兌獎";
                first_month_button.setText(month_to1);
                sec_month_button.setText(month_to2);
                third_month_button.setText(month_to3);

            }else if(nowMonth == 5){
                month1 = 3;
                month2 = 4;
                month3 = 1;
                month4 = 2;
                month5 = 11;
                month6 = 12;
                month_to1 = month1 + " " + month2 + "月兌獎";
                month_to2 = month3 + " " + month4 + "月兌獎";
                month_to3 = month5 + " " + month6 + "月兌獎";
                first_month_button.setText(month_to1);
                sec_month_button.setText(month_to2);
                third_month_button.setTextSize(15);
                third_month_button.setText(month_to3);
            }else {
                month1 = nowMonth - 2;
                month2 = nowMonth - 1;
                month3 = nowMonth -4;
                month4 = nowMonth -3;
                month5 = nowMonth -6;
                month6 = nowMonth -5;
                month_to1 = month1 + " " + month2 + "月兌獎";
                month_to2 = month3 + " " + month4 + "月兌獎";
                month_to3 = month5 + " " + month6 + "月兌獎";
                first_month_button.setText(month_to1);
                sec_month_button.setText(month_to2);
                third_month_button.setText(month_to3);
            }

        }
    }

}
