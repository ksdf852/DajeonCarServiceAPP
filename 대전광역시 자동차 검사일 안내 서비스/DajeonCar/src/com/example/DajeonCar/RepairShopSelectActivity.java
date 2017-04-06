package com.example.DajeonCar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class RepairShopSelectActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_repairshopselect);
	    

	    Intent in=getIntent();
	    
		ImageButton button1=(ImageButton)findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener(){
         	
         	public void onClick(View v){
         
          	   Intent intent=new Intent (RepairShopSelectActivity.this,RepairShopActivity.class);
               intent.putExtra("num",1);
               startActivity(intent);
         	}
         });	    
        ImageButton button2=(ImageButton)findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener(){
         	
         	public void onClick(View v){
         
          	   Intent intent=new Intent (RepairShopSelectActivity.this,RepairShopActivity.class);
               intent.putExtra("num",2);
               startActivity(intent);
         	}
         });	    
        ImageButton button3=(ImageButton)findViewById(R.id.button3);
        button3.setOnClickListener(new OnClickListener(){
         	
         	public void onClick(View v){
         
          	   Intent intent=new Intent (RepairShopSelectActivity.this,RepairShopActivity.class);
               intent.putExtra("num",3);
               startActivity(intent);
         	}
         });	    
	}
}
