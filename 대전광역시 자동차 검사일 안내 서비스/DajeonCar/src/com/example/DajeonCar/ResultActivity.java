package com.example.DajeonCar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

import com.example.DajeonCar.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity {

	private MainActivity _mainActivity;
	private Date date;
	     
	
	  @SuppressWarnings({ "deprecation", "static-access" })
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_result);
	       TextView _vehicleView=(TextView)findViewById(R.id.textView1);
	       TextView _inspectionSdate=(TextView)findViewById(R.id.textView2);
	       TextView _inspectionEdate=(TextView)findViewById(R.id.textView3);
	       Intent in=getIntent();
	        
	       String inspectionS=in.getStringExtra("inspectionSdate");
	       String inspectionE=in.getStringExtra("inspectionEdate");
	       char[] inS = new char[15];
	       char[] inE = new char[15];
	       
	       for(int i=0;i<8;i++){
	          inS[i]=inspectionS.charAt(i);
	          inE[i]=inspectionE.charAt(i);

	       }
	       inspectionS="";
	       inspectionE="";
	       for(int i=0;i<4;i++){
	          inspectionS+=inS[i];
	          inspectionE+=inE[i];
	       }
	       inspectionS+="년 ";
	       inspectionE+="년 ";
	       for(int i=4;i<6;i++){
	          inspectionS+=inS[i];
	          inspectionE+=inE[i];
	       }
	       inspectionS+="월 ";
	       inspectionE+="월 ";
	       for(int i=6;i<8;i++){
	          inspectionS+=inS[i];
	          inspectionE+=inE[i];
	       }
	       inspectionS+="일";
	       inspectionE+="일";
	       
	       //jsonParsing함수로 얻은 정보를 전달받음
	       _vehicleView.setText(in.getStringExtra("vehicleName"));
	       _inspectionSdate.setText(inspectionS);
	       _inspectionEdate.setText(inspectionE);
	       

	       //현재 날짜 구하는 코드
	       Date thisDate = new Date();
	      SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
	      String nowDateTime = formater.format(thisDate);
	       
	       
	      //D-Day를 계산함
	      remainedDate(nowDateTime,in.getStringExtra("inspectionEdate"));
	        
	      ImageButton button=(ImageButton)findViewById(R.id.button1);
	           button.setOnClickListener(new OnClickListener(){
	               
	               public void onClick(View v){

	                  Intent intent=new Intent (ResultActivity.this,RepairShopSelectActivity.class);
	                  startActivity(intent);
	               }
	            });
	   }


	//윤년 계산해서 D-Day 구하는 함수 
	public boolean remainedDate(String nowDateTime,String inspectionEdate){
		TextView _D_Date=(TextView)findViewById(R.id.textView4);


		int dY=0;
		int dM=0;
		int reM=0;
		int dD=0;
		int result;
		int[] day=new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};
		//월 별로 일 수를 배열에 집어넣음 
		
		//-48은 아스키코드값 뻬준거 
		if((int)inspectionEdate.charAt(3)-48>=(int)nowDateTime.charAt(3)-48){
			
			dY=((int)inspectionEdate.charAt(3)-48)-((int)nowDateTime.charAt(3)-48);
		
			if(((int)inspectionEdate.charAt(4)-48)*10+((int)inspectionEdate.charAt(5)-48)>=((int)nowDateTime.charAt(4)-48)*10+((int)nowDateTime.charAt(5))-48){
				
				dM=(((int)inspectionEdate.charAt(4)-48)*10+(int)inspectionEdate.charAt(5)-48)-(((int)nowDateTime.charAt(4)-48)*10+(int)nowDateTime.charAt(5)-48);
				
			}else{
				if(dY>0){
					dM=(((int)inspectionEdate.charAt(4)-48)*10+(int)inspectionEdate.charAt(5)-36)-(((int)nowDateTime.charAt(4)-48)*10+(int)nowDateTime.charAt(5)-48);
					dY--;
				}
			}
			if(((int)inspectionEdate.charAt(6)-48)*10+((int)inspectionEdate.charAt(7)-48)>=((int)nowDateTime.charAt(6)-48)*10+((int)nowDateTime.charAt(7)-48) ){
				
				dD=(((int)inspectionEdate.charAt(6)-48)*10+(int)inspectionEdate.charAt(7)-48)-(((int)nowDateTime.charAt(6)-48)*10+(int)nowDateTime.charAt(7)-48);
				
			}else{
				if(dM>0){
					dD=(((int)inspectionEdate.charAt(6)-48)*10+(int)inspectionEdate.charAt(7)-48)+day[((nowDateTime.charAt(4)-48)*10)+(nowDateTime.charAt(5)-48)]-(((int)nowDateTime.charAt(6)-48)*10+((int)nowDateTime.charAt(7)-48));
					dM--;
					reM++;
				}			
			}
		}
		result=dY*365+dD;
		int m=(nowDateTime.charAt(4)-48)*10+(nowDateTime.charAt(5)-48);
		for(int i=0;i<dM;i++){
			if(reM==1){
				m++;
				reM=0;
			}
			result+=day[m];
			m++;
			if(m>12){
				m=1;
			}	
		}
		int leapYear=0;
		//leapYear- 윤년 
		for(int i=0;i<=dY;i++){
			if(((((int)nowDateTime.charAt(2)-48)*10+((int)nowDateTime.charAt(3)-48))+i)%4==0){
				leapYear++;
				if((((int)inspectionEdate.charAt(2)-48)*10+((int)inspectionEdate.charAt(3)-48))%4==0 && (int)inspectionEdate.charAt(5)-48>2){
					leapYear++;
				}
			}
		}
		if((((int)inspectionEdate.charAt(2)-48)*10+((int)inspectionEdate.charAt(3)-48))%100==0){
			leapYear--;
			if((((int)inspectionEdate.charAt(2)-48)*10+((int)inspectionEdate.charAt(3)-48))%400==0)
				leapYear++;
		}
	
		result+=leapYear;
		_D_Date.setText(String.valueOf("        만료일까지 \nD-"+result+"일 남았습니다."));
		return false;
		
	}

}
