package com.tafuta.android.app;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;
import impl.android.com.twitterapime.xauth.ui.WebViewOAuthDialogWrapper;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.twitterapime.rest.Credential;
import com.twitterapime.rest.TweetER;
import com.twitterapime.rest.UserAccountManager;
import com.twitterapime.search.Tweet;
import com.twitterapime.xauth.Token;
public class TafutaIDsocial  extends Activity  implements com.twitterapime.xauth.ui.OAuthDialogListener {
	
	Facebook mfacebook = new Facebook("394218160599133");
	 Button Twitter,facebook;
	 private SharedPreferences mPrefs;
	 String access_token;
	 String Responce;
	 String CONSUMER_KEY;
	 String CONSUMER_SECRET;
	 String CALLBACK_URL;
	 String FILENAME = "AndroidSSO_data";
	 private Token accessToken;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		    requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.social_media);
			
			facebook = (Button)findViewById(R.id.socfb);
			Facebookclicked();
			Twitter = (Button)findViewById(R.id.soctw);
			Twitterclicked();
			mPrefs = getPreferences(MODE_PRIVATE);
			access_token = mPrefs.getString("access_token", null);
			
			
			
			
			
		}
	 @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			mfacebook.authorizeCallback(requestCode, resultCode, data);
		}

	private void Facebookclicked() {
		facebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	long expires = mPrefs.getLong("access_expires", 0);
    			if( access_token !=null){
    				mfacebook.setAccessToken(access_token);
    			}
    			if(expires !=0)
    			{
    				mfacebook.setAccessExpires(expires);
    			}
    			if(!mfacebook.isSessionValid())
    			{
    			mfacebook.authorize(TafutaIDsocial.this,new String[]{"publish_stream","publish_checkins"}, new DialogListener() {
    				@Override
    				public void onFacebookError(FacebookError e) {
    					// TODO Auto-generated method stub
    					
    				}
    				
    				@Override
    				public void onError(DialogError e) {
    					// TODO Auto-generated method stub
    					
    				}
    				
    				@Override
    				public void onComplete(Bundle values) {
    					SharedPreferences.Editor editor = mPrefs.edit();
    					editor.putString("access_token",
    							mfacebook.getAccessToken());
    					editor.putLong("access_expires",
    							mfacebook.getAccessExpires());
    					editor.commit();					
    				}
    				
    				@Override
    				public void onCancel() {
    					// TODO Auto-generated method stub
    					
    				}
    			});
    			}
    			if(mfacebook.isSessionValid())//if the session is  valid...
    			{
    			try {
    				Responce = mfacebook.request("me");
    				Bundle upDets = new Bundle();
    				upDets.putString("message", "this is a test update via TafutaID");
    				//upDets.putString("description", "test test test....can anyone read this?");
    				upDets.putString(Facebook.TOKEN, access_token);
    				Responce = mfacebook.request("me/feed", upDets, "POST");
    				Toast.makeText(TafutaIDsocial.this, "posted to timeline", Toast.LENGTH_LONG).show();
    			} catch (Exception e) {
    				
    				Log.v("fail" ,e.toString());
    				
    				
    			} 
    			
    			}
    			
            	
            	
            }
		
	});
	}

	private void Twitterclicked() {
		facebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	CONSUMER_KEY = "hX8G2LBJyIJTbQTDGhqgw";
    			
    			
    			CONSUMER_SECRET = "M5UiO7Ya9i1DswTg69APE323RogwR53PONuqS6w";

    			
    			CALLBACK_URL = "";
    			  
    			  
    		        WebView webView = new WebView(TafutaIDsocial.this);
    		        setContentView(webView);
    		        webView.requestFocus(View.FOCUS_DOWN);
    		        
    		        WebViewOAuthDialogWrapper pageWrapper =
    		        new WebViewOAuthDialogWrapper(webView);
    				pageWrapper.setConsumerKey(CONSUMER_KEY);
    				pageWrapper.setConsumerSecret(CONSUMER_SECRET);
    				pageWrapper.setCallbackUrl(CALLBACK_URL);
    				//pageWrapper.setOAuthListener(this);
    				pageWrapper.login();
    				
    				try {
    					Credential creds = new Credential(CONSUMER_KEY, CONSUMER_SECRET, accessToken);
    					UserAccountManager userman = UserAccountManager.getInstance(creds);
    					
    					if (userman.verifyCredential()) {
    						Tweet twt =new Tweet("Have you found or lost a Kenyan National ID card? " +
    								"Use TafutaID for Android,Symbian or Samsung to Submit or Search ");
    						TweetER ter = TweetER.getInstance(userman);
    						twt = ter.post(twt);
    						showMessage("Shared on your TimeLine");
    					}
    				} catch (Exception e) {
    					String error = e.toString();
    				      showMessage(error);
    				}
            	
            	
            }
		
	});
	}
	@Override
	public void onAccessDenied(String m) {
		showMessage("Acess Denied");
	}
	@Override
	public void onAuthorize(Token accessToken) {
		showMessage("Acess Granted");
		
	}
	@Override
	public void onFail(String arg0, String arg1) {
		showMessage("Acess Granted");
		
	}


	private void showMessage(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.title_pic);
		builder.setMessage(msg).setCancelable(false)
				.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		
		builder.create().show();
	}
	
	@Override
	protected void onResume() {
		 super.onResume();
	     mfacebook.extendAccessTokenIfNeeded(TafutaIDsocial.this, null);
	}

	

}
