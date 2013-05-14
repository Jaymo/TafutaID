package com.tafuta.android.app.util;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class CustomOverlayItem extends OverlayItem {

	
	public CustomOverlayItem(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
		
	}

	
}

