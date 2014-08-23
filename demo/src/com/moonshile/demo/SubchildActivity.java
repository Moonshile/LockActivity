/********************************************************
 * 
 * LockActivity version 1.0
 * Copyright (C) 2014  Moonshile (moonshile@foxmail.com)
 * This software comes with ABSOLUTELY NO WARRANTY; this is free 
 * software, and you are welcome to redistribute it under certain 
 * conditions, for details see the LICENSE file attached to 
 * source.
 * 
 *********************************************************/
package com.moonshile.demo;

import com.moonshile.lib.ui.LockedChildActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class SubchildActivity extends LockedChildActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subchild);
		
		((TextView)findViewById(R.id.subchild_text)).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/SNAP.TTF"));
	}
}
