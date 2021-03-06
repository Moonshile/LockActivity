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

import com.moonshile.lib.ui.LockedActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends LockedActivity<MyLockerActivity> {
	public static final int REQUEST_CODE_CHILD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.key = "";
        super.lockerClass = MyLockerActivity.class;
        setContentView(R.layout.activity_main);
        
		((TextView)findViewById(R.id.main_text)).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/SNAP.TTF"));
    }
    
    public void onClick(View view){
    	Intent child = new Intent(this, ChildActivity.class);
    	this.startActivityForResult(child, REQUEST_CODE_CHILD);
    }
    
    @Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent){
    	super.onActivityResult(requestCode, resultCode, intent);
    	switch(requestCode){
    	case REQUEST_CODE_CHILD:
    		break;
    	}
    }
}
