package com.example.for_ticket;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
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




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.dosomething, container, false);
        initailize();
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

    }

    private void methods() {
        ticket = input_number.getText().toString().trim();
        show_number.setText("");
        show_number.setText(ticket);
        Log.e("flag is", String.valueOf(flag));
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

                    Log.e("TESTn1", String.valueOf(n1));
                    Log.e("TESTn2", String.valueOf(n2));
                    Log.e("TESTn3", String.valueOf(n3));
                    Log.e("TESTn4", String.valueOf(n4));
                    Log.e("TESTn5", String.valueOf(n5));

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
                    Log.e("TEST", e.toString());
                    Log.e("TEST", String.valueOf(n1));

                }
            };
            r2.run();

        } else {
            Log.e("TEST", "flag not true");
        }
        input_number.setText("");
    }

    private void show_point_to_textview(int i) {
        switch (i) {
            case 0:
                show_get.setText("可能中獎了!!");
                Log.e("TEST", show_get.getText().toString());
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


}
