package com.example.testwear;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * 
 * @author huiwang
 *
 */
public class IosPairActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ios_pair_activity);
        
    }

    private void pairBtClick(){
        Button pairBt = (Button)findViewById(R.id.ios_bt);
        pairBt.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
            }
        });
    }
}
