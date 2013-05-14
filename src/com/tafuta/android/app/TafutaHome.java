package com.tafuta.android.app;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class TafutaHome  extends TabActivity{
	private Bundle bundle;
    private Bundle extras;
	private TabHost tabHost;
    private TextView activityTitle;
    private static final int VIEW_SEARCH = 0;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        new Handler();
        bundle = new Bundle();
        extras = this.getIntent().getExtras();
        tabHost = getTabHost();
        PopulateTab();
        tabHost.setCurrentTab(0);
        setTabColor(tabHost);
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {

            public void onTabChanged(String arg0) {
                setTabColor(tabHost);
            }

        });

        
        
	}
	
	
		
//Vile i will handle tab selection
	public static void setTabColor(TabHost tabhost) {
        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            // unselected
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#ff355689"));

            TextView tv = (TextView)tabhost.getTabWidget().getChildAt(i)
                    .findViewById(android.R.id.title); 
            tv.setTextColor(Color.parseColor("#ffffff"));

        }
        
        // selected
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab())
        
                .setBackgroundColor(Color.parseColor("#ff355689"));
        TextView tv = (TextView)tabhost.getCurrentTabView().findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#ffffff"));
    }
	public void PopulateTab() {
		if (activityTitle != null)
            activityTitle.setText(getTitle());
		
        tabHost.addTab(tabHost
                .newTabSpec("Search ID")
                .setIndicator(getString(R.string.description_search),
                        getResources().getDrawable(R.drawable.search_unfocused))
                .setContent(new Intent(TafutaHome .this, TafutaSearchId.class)));

   
        tabHost.addTab(tabHost
                .newTabSpec("Submit ID")
                .setIndicator(getString(R.string.description_submit),
                        getResources().getDrawable(R.drawable.submit_id_unselected))
                .setContent(new Intent(TafutaHome .this, TafutaSubmitId.class)));
        
        tabHost.addTab(tabHost
                .newTabSpec("Settings")
                .setIndicator(getString(R.string.description_settings),
                        getResources().getDrawable(R.drawable.ic_menu_settings))
                .setContent(new Intent(TafutaHome .this, TafutaSettings.class)));
     
    
        tabHost.addTab(tabHost
                .newTabSpec("About")
                .setIndicator(getString(R.string.description_about),
                        getResources().getDrawable(R.drawable.about_unselected))
                .setContent(new Intent(TafutaHome .this, TafutaAbout.class)));
       
    }
		
	}

