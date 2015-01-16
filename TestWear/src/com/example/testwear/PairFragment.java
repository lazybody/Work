package com.example.testwear;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PairFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_welcome_circular, container, false);  
        iosBtClick(view);
        return view;
    }
    
    private void iosBtClick(View view){
        LinearLayout bt = (LinearLayout)view.findViewById(R.id.ios_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent();
                ComponentName componentName = new ComponentName(  
                        "com.mobvoi.ticwear.ios",  
                        "com.mobvoi.ticwear.ios.StartActivity");
                it.setComponent(componentName);
                startActivity(it);
            }
        });
    }

}
