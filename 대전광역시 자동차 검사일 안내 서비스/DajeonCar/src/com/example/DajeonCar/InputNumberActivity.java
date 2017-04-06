package com.example.DajeonCar;

import java.io.IOException;

import com.example.DajeonCar.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class InputNumberActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inputnumber);
		 	
		ImageButton button=(ImageButton)findViewById(R.id.Button1);
        button.setOnClickListener(new OnClickListener(){
         	
        	public void onClick(View v){

    	        Handler hd = new Handler();
    	        hd.postDelayed(new Runnable() {
    	 
    	            @Override
    	            public void run() {
    	                finish();       // 직접입력 버튼이 눌러지면 Activity를 닫음
    	            }
    	        },0);		
        	}
        });
	}
}
