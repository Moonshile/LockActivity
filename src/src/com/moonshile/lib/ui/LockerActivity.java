/********************************************************
 * 
 * {software name} version 1.0
 * Copyright (C) {year}  Moonshile (moonshile@foxmail.com)
 * This software comes with ABSOLUTELY NO WARRANTY; this is free 
 * software, and you are welcome to redistribute it under certain 
 * conditions, for details see the LICENSE file attached to 
 * source.
 * 
 *********************************************************/
package com.moonshile.lib.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;


/**
 * While an activity is locked, this activity will be displayed.
 */
public abstract class LockerActivity extends Activity {
	
	protected int wrong = 2;
	protected String key = null;
	
	public static final String MOONSHILE_INTENT_KEY = "MOONSHILE_INTENT_KEY";
	
	public static final int RESULT_LOCK = -100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO setTheme will not make screen translucent but black.
		//      Now you can add android:theme="@android:style/Theme.Translucent.NoTitleBar"
		//      to the subclass in manifest file to fix this bug
		// theme must be set before super.onCreate()
		this.setTheme(android.R.style.Theme_Translucent_NoTitleBar);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.moonshile_lock_default);
		
		Intent intent = getIntent();
		key = intent.getStringExtra(MOONSHILE_INTENT_KEY);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId() == android.R.id.home){
			onCancel(null);
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onOK(View view){
		String keyStr = ((EditText)findViewById(R.id.moonshile_lock_key)).getText().toString();
		if(rightKey(keyStr)){
			Intent res = getIntent();
			this.setResult(RESULT_OK, res);
			this.finish();
		}else if(wrong > 0){
			Toast.makeText(this, getResources().getString(R.string.moonshile_lock_wrong_key) + wrong, Toast.LENGTH_SHORT).show();
			wrong--;
			((EditText)findViewById(R.id.moonshile_lock_key)).setText("");
		}else{
			onCancel(null);
		}
	}
	
	protected boolean rightKey(String keyActual){
		return key.equals(keyActual);
	}
	
	public void onCancel(View view){
		Intent res = getIntent();
		this.setResult(RESULT_CANCELED, res);
		this.finish();
	}
}
