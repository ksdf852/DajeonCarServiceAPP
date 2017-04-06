package com.example.DajeonCar;

import com.example.DajeonCar.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_loading);
         
	        Handler hd = new Handler();
	        hd.postDelayed(new Runnable() {
	 
	            @Override
	            public void run() {
	                finish();       // 3 초후 Activity를 닫아버림
	            }
	        }, 3000);		
	}


}