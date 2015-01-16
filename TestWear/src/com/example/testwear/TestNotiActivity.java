package com.example.testwear;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestNotiActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iosBtClick();
    }
    
    private void iosBtClick(){
        Button bt = (Button)findViewById(R.id.ios_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
            }
        });
    }

}
