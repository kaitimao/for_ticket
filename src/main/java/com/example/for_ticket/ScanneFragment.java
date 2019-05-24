package com.example.for_ticket;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanneFragment extends Fragment {
    private View view;
    private Button fortest;
    TextView m_textView_result;

    public ScanneFragment() {
        // Required empty public constructor
    }
    public static IntentIntegrator forSupportFragment(Fragment fragment){
        IntentIntegrator integrator = new IntentIntegrator(fragment.getActivity());
        integrator.forSupportFragment(fragment);
        return integrator;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_scanne, container, false);
        process();

        return view;
    }

    private void process(){
        fortest = view.findViewById(R.id.for_test);
        m_textView_result = view.findViewById(R.id.m_textView_result);
    }


    @Override
    public void onStart() {
        super.onStart();
        fortest.setOnClickListener(v -> toscan());
    }

    private void toscan() {
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        //integrator.setCaptureActivity(ScanActivity.class);
        integrator.setPrompt("Scan");       //底部提示的文字
        integrator.setCameraId(0);          //前面或後面的相機
        integrator.setBeepEnabled(false);    //掃描成功後不發出 BB 聲
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);


        if (result!= null)
        {
            if (result.getContents()==null)
            {
                Log.e("resulr", "result : null");
            }
            else
            {
                String content = result.getContents();
                m_textView_result.setText("Scanner result :" + content);

            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
