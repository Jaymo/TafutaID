package com.tafuta.android.app;


import impl.android.com.twitterapime.xauth.ui.WebViewOAuthDialogWrapper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.tafuta.android.app.util.ActionItem;
import com.tafuta.android.app.util.QuickAction;
import com.twitterapime.rest.Credential;
import com.twitterapime.rest.TweetER;
import com.twitterapime.rest.UserAccountManager;
import com.twitterapime.search.Tweet;
import com.twitterapime.xauth.Token;

public class TafutaAbout extends Activity implements com.twitterapime.xauth.ui.OAuthDialogListener {
	 @SuppressWarnings("unused")
	 Facebook mfacebook = new Facebook("394218160599133");
	 private TextView mMessage;
	 private Button mshare;
	 private static final int ID_FACEBOOK = 1;
	 private static final int ID_TWITTER = 2;
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
		setContentView(R.layout.about);
		
		mPrefs = getPreferences(MODE_PRIVATE);
		access_token = mPrefs.getString("access_token", null);
		
		
		 mMessage = (TextView) findViewById(R.id.AboutTextView);
		 
		 mshare=(Button) findViewById(R.id.share);
		 
		 ActionItem facebookItem	= new ActionItem(ID_FACEBOOK, "Facebook", getResources().getDrawable(R.drawable.share));
		 ActionItem twitterItem 	= new ActionItem(ID_TWITTER, "Twitter", getResources().getDrawable(R.drawable.share));
		 final QuickAction quickAction = new QuickAction(this, QuickAction.HORIZONTAL);
		 quickAction.addActionItem(facebookItem);
		 quickAction.addActionItem(twitterItem);
		 
		
			quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {			
				@Override
				public void onItemClick(QuickAction source, int pos, int actionId) {				
					ActionItem actionItem = quickAction.getActionItem(pos);
	                 
					
					if (actionId == ID_FACEBOOK) {
						Facebookclicked();
					} else if (actionId == ID_TWITTER) {
						Twitterclicked();
					} else {
					}
				}
			});
			quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {			
				@Override
				public void onDismiss() {
				}
			});
			mshare.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					 if (Util.isConnected(TafutaAbout.this)) {
					    quickAction.show(v);
					 }
					 else{
						 Toast.makeText(TafutaAbout.this, " No internet Connection Detected", Toast.LENGTH_LONG).show();
					 }
				}
			});
		 
		
		
		 
        
	}
	private void Facebookclicked() {
		
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
    			mfacebook.authorize(TafutaAbout.this,new String[]{"publish_stream","publish_checkins"}, new DialogListener() {
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
    			if(mfacebook.isSessionValid())
    			{
    			try {
    				Responce = mfacebook.request("me");
    				Bundle upDets = new Bundle();
    				upDets.putString("message", "Have you collected or lost a Kenyan National ID card?" +
						"Use TafutaID;Submit or Search the ID!Available on Samsung store and Google Play");
    				upDets.putString(Facebook.TOKEN, access_token);
    				Responce = mfacebook.request("me/feed", upDets, "POST");
    				Toast.makeText(TafutaAbout.this, "App Sucessfully shared on timeline", Toast.LENGTH_LONG).show();
    			} catch (Exception e) {
    				
    				Log.v("fail" ,e.toString());
    				
    				
    			} 
    			
    			}
    			
            	
            	
            }
		
	
	private void Twitterclicked() {

            	CONSUMER_KEY = "hX8G2LBJyIJTbQTDGhqgw";
    			
    			
    			CONSUMER_SECRET = "M5UiO7Ya9i1DswTg69APE323RogwR53PONuqS6w";

    			
    			CALLBACK_URL = "http://www.twitter.com";
    			  
    			  
    			WebView webView = new WebView(TafutaAbout.this);
		        setContentView(webView);
		        webView.requestFocus(View.FOCUS_DOWN);
		        
		        WebViewOAuthDialogWrapper pageWrapper =
		        new WebViewOAuthDialogWrapper(webView);
				pageWrapper.setConsumerKey(CONSUMER_KEY);
				pageWrapper.setConsumerSecret(CONSUMER_SECRET);
				pageWrapper.setCallbackUrl(CALLBACK_URL);
				pageWrapper.setOAuthListener(TafutaAbout.this);
				pageWrapper.login();
    				
    				
            	
            }
		

	@Override
	public void onAccessDenied(String m) {
		showMessage("Acess Denied");
	}
	@Override
	public void onAuthorize(Token accessToken) {
		
		try {
			Credential creds = new Credential(CONSUMER_KEY, CONSUMER_SECRET, accessToken);
			UserAccountManager userman = UserAccountManager.getInstance(creds);
			
			if (userman.verifyCredential()) {
				Tweet twt =new Tweet("Have you collected or lost a Kenyan National ID card?" +
						"Use TafutaID to Submit/Search the ID.Available on Samsung store");
				TweetER ter = TweetER.getInstance(userman);
				twt = ter.post(twt);
				showMessage("Sucessfully shared on your Timeline");
			}
		} catch (Exception e) {
			String error = e.toString();
		      showMessage(error);
		}
	
		
	}
	@Override
	public void onFail(String arg0, String arg1) {
		showMessage("Something went wrong");
		
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
	     mfacebook.extendAccessTokenIfNeeded(TafutaAbout.this, null);
	}


}