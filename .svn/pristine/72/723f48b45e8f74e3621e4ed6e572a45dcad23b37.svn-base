package com.tafuta.android.app;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.tafuta.android.app.util.ActionItem;
import com.tafuta.android.app.util.CustomItemizedOverlay;
import com.tafuta.android.app.util.CustomOverlayItem;
import com.tafuta.android.app.util.QuickAction;

public class TafutaDisplayID extends MapActivity{
	private static final int ID_PHONE = 1;
	private static final int ID_SMS   = 2;
	private TextView Tfirstname,Tmiddlename,Tlastname,Tidnumber ;
	private String FIRSTNAME,MIDDLENAME,LASTNAME,IDNUMBER,PHONENUMBER;
	private Button CONTACT;
	static JSONObject json ;
	CustomItemizedOverlay<CustomOverlayItem> itemizedOverlay;
	List<Overlay> mapOverlays;
	Drawable drawable;
	public MapController mapControl;
	MapView mapView;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        setContentView(R.layout.display_id);
        
        getWindow().setFeatureDrawableResource(
        Window.FEATURE_LEFT_ICON, R.drawable.icon_launcher);
        
        Tfirstname = (TextView)findViewById(R.id.firstname);
        Tmiddlename = (TextView)findViewById(R.id.middlename);
        Tlastname = (TextView)findViewById(R.id.lastname);
        Tidnumber = (TextView)findViewById(R.id.id_number);
        
        mapView = (MapView) findViewById(R.id.mapview);
        
     
		
        
        CONTACT = (Button)findViewById(R.id.contact);
        
        mapView.setBuiltInZoomControls(true);
        
        initComponents();
        ButtonStyle();
        
        
	}
	private void ButtonStyle() {
	ActionItem aboutItem 	= new ActionItem(ID_PHONE, "Phone", getResources().getDrawable(R.drawable.phone));
        ActionItem shareItem 	= new ActionItem(ID_SMS, "SMS", getResources().getDrawable(R.drawable.sms));
        final QuickAction quickAction = new QuickAction(this, QuickAction.HORIZONTAL);
        quickAction.addActionItem(aboutItem );
        quickAction.addActionItem(shareItem);
        
     
		quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {			
			@Override
			public void onItemClick(QuickAction source, int pos, int actionId) {				
				@SuppressWarnings("unused")
				ActionItem actionItem = quickAction.getActionItem(pos);  
				
				if (actionId == ID_PHONE) {
					String number ="tel:"+PHONENUMBER.toString().trim();
					Intent call = new Intent (Intent.ACTION_DIAL,Uri.parse(number));
	        		startActivity(call);
					
				} else if (actionId == ID_SMS) {
					Intent sendIntent= new Intent(Intent.ACTION_VIEW);
					sendIntent.putExtra("sms_body", "TafutaID:"+IDNUMBER);
					sendIntent.putExtra("address",PHONENUMBER );
					sendIntent.setType("vnd.android-dir/mms-sms"); 
					startActivity(sendIntent);
				} else {
					
				}
			}
		});
		
		quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {			
			@Override
			public void onDismiss() {
			}
		});
	
        CONTACT.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	quickAction.show(v);
            	 
             } 
            });
		
	}

	private void initComponents() {
		List<Overlay> mapOverlays = mapView.getOverlays();
		try{
	      	
	      	JSONArray  places = json.getJSONArray("results");
	      	
	 	        for(int i=0;i<1;i++){							
	 				JSONObject e = places.getJSONObject(i);
	 				IDNUMBER=  e.get("idnumber").toString();
	 				FIRSTNAME =  e.get("firstname").toString();
	 				MIDDLENAME=  e.get("middlename").toString();
	 				LASTNAME=  e.get("lastname").toString();
	 				PHONENUMBER= e.getString("phone").toString();
	 				
	 				drawable = this.getResources().getDrawable(R.drawable.blue);
	 				
		    	    itemizedOverlay = new CustomItemizedOverlay<CustomOverlayItem>(drawable, mapView);
		    	 
		    	    GeoPoint point = new GeoPoint((int)(e.getDouble("lat")*1E6),(int) (e.getDouble("lon")*1E6));			    	    
		    	    CustomOverlayItem overlayItem = new CustomOverlayItem(point,"Name: "+FIRSTNAME +" " +MIDDLENAME +" "+LASTNAME, "ID Number: "+IDNUMBER+"\n");
		    	    
		    	    itemizedOverlay.addOverlay((CustomOverlayItem) overlayItem);
		    	  //  
		    	    mapOverlays.add(itemizedOverlay);
	 	        }
		}
	 	       catch(JSONException e)        {
	 	       	 Log.e("log_tag", "Error kupitisha JSON "+e.toString());
	 	       }
	 	       
	 	       Tfirstname.setText(FIRSTNAME);
 	    	   Tmiddlename.setText(MIDDLENAME);
 	    	   Tlastname.setText(LASTNAME);
 	    	   Tidnumber.setText(IDNUMBER);
 	}
	public static void ServerSide(JSONObject jArray) {
		  json = jArray;
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		
		return false;
	}

}
