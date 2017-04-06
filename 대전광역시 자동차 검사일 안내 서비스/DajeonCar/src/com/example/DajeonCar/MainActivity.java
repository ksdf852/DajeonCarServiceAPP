package com.example.DajeonCar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.example.DajeonCar.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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




public class MainActivity extends Activity {

	
	String vehicleNameValue=""; 
	String inspectionSdate="";
	String inspectionEdate="";
	
	TextView textView; // 출력할 textView
	String vehicle;
	
	public String getVehicle(){
		return vehicle;
	}
	public void setVehicle(String v){
		this.vehicle=v;
	}
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this,InputNumberActivity.class));
      
        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
 
            @Override
            public void run() {
            }
        }, 5000);	

        startActivity(new Intent(this,LoadingActivity.class));
        StrictMode.enableDefaults();
        
        //검사일 알아보기 버튼을 누르면 다음 activity로 넘어감
        ImageButton button=(ImageButton)findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener(){
         	
         	public void onClick(View v) {
                boolean loop = true;

                try {
                   if (!myClickListener(v)) {
                      Toast.makeText(MainActivity.this, "잘못된 입력입니다.",
                            Toast.LENGTH_LONG).show();
                   } else {

                      Intent intent = new Intent(MainActivity.this,
                            ResultActivity.class);
                      intent.putExtra("vehicleName", vehicleNameValue);
                      intent.putExtra("inspectionSdate", inspectionSdate);
                      intent.putExtra("inspectionEdate", inspectionEdate);
                      setResult(RESULT_OK, intent);

                      startActivity(intent);
                   }
                } catch (IOException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                }
             }
          });

       }

       // 검사일 알아보기 버튼을 누를 시 jsonParsing 함수를 호출함
       public boolean myClickListener(View target) throws IOException {

          EditText et = (EditText) findViewById(R.id.editText1);
          Editable etStr = et.getText();
          setVehicle(etStr.toString());
          boolean check=true;
          
          for(int i=0;i<etStr.length();i++)
             if(etStr.charAt(i)==' ')
                check=false;
          if(etStr.length()<7 || !check)
             return false;
          
          if (jsonParsing(target))
             return true;
          else
             return false;

       }

       // REST API로 받은 json 파일의 결과값을 추출하는 함수
       public boolean jsonParsing(View target) throws IOException {

          char[] vehicleName = new char[10];
          char[] inspectionSdata = new char[10];
          char[] inspectionEdata = new char[10];
          String query = null;

          String _inspectionURL = "http://data.daejeon.go.kr/rest/car/carDataDetailService.json?carNo=";
          String _serviceKey = "&ServiceKey=4qKjTGtn4WtMl%2BOF4AmrLWWmduYIaG8Y3fiDeeg0BZwcrXFqWOJm%2BRlZYls1wbQwTzkEjTZ8UIDCcmmi1TZyXQ%3D%3D";
          String _vehicle = getVehicle();

          // 한글을 UTF-8 변환시킴
          char _forEncode = _vehicle.charAt(2);
          query = URLEncoder.encode(String.valueOf(_forEncode), "utf-8");

          String _resultNumber = String.valueOf(_vehicle.charAt(0))
                + String.valueOf(_vehicle.charAt(1)) + query
                + _vehicle.charAt(3) + _vehicle.charAt(4) + _vehicle.charAt(5)
                + _vehicle.charAt(6);
          String _url = _inspectionURL + _resultNumber + _serviceKey;

          URL url = new URL(_url);

          InputStream jsonP = url.openStream();
          BufferedReader in = new BufferedReader(new InputStreamReader(jsonP,
                "UTF8"));
          String data = in.readLine();

          if (data.charAt(1) == '}')
             return false;
          else {
             // 필요한 데이터들을 추출하여 배열에 집어넣음
             for (int i = 49; i < 56; i++)
                vehicleName[i - 49] = data.charAt(i);
             for (int i = 77; i < 85; i++)
                inspectionSdata[i - 77] = data.charAt(i);
             for (int i = 106; i < 114; i++)
                inspectionEdata[i - 106] = data.charAt(i);

             vehicleNameValue = "";
             inspectionSdate = "";
             inspectionEdate = "";
             for (int i = 0; i < vehicleName.length; i++) {
                vehicleNameValue += vehicleName[i];
             }

             for (int i = 0; i < inspectionSdata.length; i++) {
                inspectionSdate += inspectionSdata[i];
             }

             for (int i = 0; i < inspectionEdata.length; i++) {
                inspectionEdate += inspectionEdata[i];
             }
             return true;
          }

       }
    }