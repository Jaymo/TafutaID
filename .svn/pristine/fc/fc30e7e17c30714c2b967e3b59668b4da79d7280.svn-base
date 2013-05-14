package com.tafuta.android.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.MapView;


public class TafutaSubmit  extends UserLocationMap {
	
	private Button BtnSend;
	private static EditText firstname,middlename,lastname,idnumber,phonenumber;
	private TextView Title ;
	private String mErrorMessage = "";
	private boolean mError = false;
	private Calendar Calendar;
	HttpPost httpPost;
	StringBuffer buffer;
	HttpResponse response;
	InputStream is = null;
	ArrayList<NameValuePair> namevaluepairs;
	String result = "",Y,N;
	static String FIRSTNAME,MIDDLENAME,LASTNAME,IDNUMBER,LATITUDE,LONGITUDE,PHONENUMBER;
	String eFIRSTNAME="",eMIDDLENAME="",eLASTNAME="",eIDNUMBER="",eLATITUDE="",eLONGITUDE="",ePHONENUMBER="";
	static String SETFIRSTNAME,SETMIDDLENAME,SETLASTNAME,SETIDNUMBER;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_found);
        
        firstname=(EditText)findViewById(R.id.edtext_firstname);
        middlename=(EditText)findViewById(R.id.edtext_middlename);
        lastname=(EditText)findViewById(R.id.edtext_lastname);
        idnumber=(EditText)findViewById(R.id.edtext_idnumber);
        phonenumber=(EditText)findViewById(R.id.edtext_phonenumber);
        
        BtnSend = (Button)findViewById(R.id.send);
        
        
        mapView = (MapView)findViewById(R.id.mapview);
		mapController = mapView.getController();
        
        Title = (TextView)findViewById(R.id.lbl_title);
        
        initComponents();
        PopulateFields();
        
	}

	private void initComponents() {
		
		firstname.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (TextUtils.isEmpty(firstname.getText())) {
                    	firstname.setError(getString(R.string.empty_firstname));
                    }
                }

            }

        });
		middlename.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (TextUtils.isEmpty(middlename.getText())) {
                    	middlename.setError(getString(R.string.empty_middlename));
                    }
                }

            }

        });
		lastname.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (TextUtils.isEmpty(lastname.getText())) {
                    	lastname.setError(getString(R.string.empty_lastname));
                    }
                }

            }

        });
		idnumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (TextUtils.isEmpty(idnumber.getText())) {
                    	idnumber.setError(getString(R.string.empty_idnumber));
                    }
                }

            }

        });
		phonenumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (TextUtils.isEmpty(phonenumber.getText())) {
                    	phonenumber.setError(getString(R.string.empty_phonenumber));
                    }
                }

            }

        });
		phonenumber.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (TextUtils.isEmpty(phonenumber.getText().toString())) {
                	phonenumber.setText("+254");
                }

                return false;
            }

        });
		Calendar = java.util.Calendar.getInstance();
        updateDisplay();
		
		BtnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	
            if (Util.isConnected(TafutaSubmit.this)) {
            	ePHONENUMBER =phonenumber.getText().toString();
            	mError = false;
            	
                if (TextUtils.isEmpty(firstname.getText())) {
                    mErrorMessage = getString(R.string.empty_firstname)+"\n";
                    mError = true;
                }
                if (TextUtils.isEmpty(middlename.getText())) {
                    mErrorMessage += getString(R.string.empty_middlename)+"\n";
                    mError = true;
                }
                if (TextUtils.isEmpty(lastname.getText())) {
                    mErrorMessage += getString(R.string.empty_lastname)+"\n";
                    mError = true;
                }
                
                if (TextUtils.isEmpty(idnumber.getText())) {
                    mErrorMessage += getString(R.string.empty_idnumber)+"\n";
                    mError = true;
                }
                if(ePHONENUMBER.length() < 10) 
                { 
            		phonenumber.setError("Error in Phone Number!!");
            		mError = true;
                }
                if (!mError) {
                	new PostID(){
                		public void onPostExecute(String result) {
                			   
                			if(result.equals("Y")){
                	        	this.progressDialog.dismiss();
                			   Toast.makeText(TafutaSubmit.this, " ID Card Sucessfully submited.", Toast.LENGTH_LONG).show();
                			   TafutaSubmit.this.finish();
                			   return;
                	           }
                			if(result.equals("N")){
                	              this.progressDialog.dismiss();
                	        	   Toast.makeText(TafutaSubmit.this, " That ID card seems to have been submitted already", Toast.LENGTH_LONG).show();
                	        	   return;
                	           }
                		}
                	}.execute();
                	
                	
                }
                else {
                    final Toast t = Toast.makeText(TafutaSubmit.this, "Error!\n" + mErrorMessage,
                            Toast.LENGTH_LONG);
                    t.show();
                    mErrorMessage = "";
                  
                }
            	
            }
          else{
        	  Toast.makeText(TafutaSubmit.this, " No internet Connection Detected", Toast.LENGTH_LONG).show();
          }
            }
		
		});

	}
	private void updateDisplay() {
        SimpleDateFormat dispFormat = new SimpleDateFormat("'Submited on 'MMMM dd, yyyy 'at' hh:mm a");
        Date datetime = Calendar.getTime();
        Title.setText(dispFormat.format(datetime));
        
    }
	
	private void PopulateFields() { 
		TafutaSubmitId.Data(this);
		SETFIRSTNAME=TafutaSubmitId.FIRSTNAME;
		SETMIDDLENAME=TafutaSubmitId.MIDDLENAME;
		SETLASTNAME=TafutaSubmitId.LASTNAME;
		SETIDNUMBER=TafutaSubmitId.IDNUMBER;
		
		firstname.setText(SETFIRSTNAME);
		middlename.setText(SETMIDDLENAME);
		lastname.setText(SETLASTNAME);
		idnumber.setText(SETIDNUMBER);
	
		String phone =String.valueOf(TafutaSettings.getphone(getApplicationContext()));
		if (TextUtils.isEmpty(phonenumber.getText().toString())) {
		phonenumber.setText(phone);
	    }
	        
    }
	
	public class PostID 
	extends AsyncTask<String, Integer, String> {
		ProgressDialog progressDialog = new ProgressDialog(TafutaSubmit.this);
		 @Override
		  protected void onPreExecute() {
			 progressDialog.setMessage("Submitting  ID Card....");
		        progressDialog.show();
			  
		}

		@Override
		protected String doInBackground(String... params) {
			
				eFIRSTNAME =firstname.getText().toString();
		    	eMIDDLENAME =middlename.getText().toString();
		    	eLASTNAME =lastname.getText().toString();
		    	eIDNUMBER =idnumber.getText().toString();
		    	ePHONENUMBER =phonenumber.getText().toString();
		    	eLATITUDE=String.valueOf(sLatitude);
		    	eLONGITUDE=String.valueOf(sLongitude);
		    	
		    	try{
		    		HttpClient httpclient = new DefaultHttpClient();
					httpPost = new HttpPost("http://akajaymo.kodingen.com/submitid.php");
					namevaluepairs = new ArrayList<NameValuePair>();
					namevaluepairs.add (new BasicNameValuePair("firstname",eFIRSTNAME));
					namevaluepairs.add (new BasicNameValuePair("middlename",eMIDDLENAME));
					namevaluepairs.add (new BasicNameValuePair("lastname",eLASTNAME));
					namevaluepairs.add (new BasicNameValuePair("idnumber",eIDNUMBER));
					namevaluepairs.add (new BasicNameValuePair("phonenumber",ePHONENUMBER));
					namevaluepairs.add (new BasicNameValuePair("lat",eLATITUDE));
					namevaluepairs.add (new BasicNameValuePair("lon",eLONGITUDE));
					httpPost.setEntity (new UrlEncodedFormEntity(namevaluepairs));
					HttpResponse response = httpclient.execute(httpPost);
		            HttpEntity entity = response.getEntity();
		            is = entity.getContent();
					
					
				 }
					catch (Exception e)
					{
						Log.e("log_tag", "Error converting result "+e.toString());
					}
					try{
						BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			            StringBuilder sb = new StringBuilder();
			            String line = null;
			            while ((line = reader.readLine()) != null) {
			                    sb.append(line + "\n");
			            }
			            is.close();
			            result=sb.toString().trim();
			      }
			      catch(Exception e){
			            Log.e("log_tag", "Error converting result "+e.toString());
			       }
			
	   return result;
		}
	}
		

		
	
	@Override
	protected void updateInterface() {
		
	}

}