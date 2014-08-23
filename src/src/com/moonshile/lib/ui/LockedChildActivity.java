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

/**
 * If onPause() is called, this activity will finish and
 * then return to the root LockedActivity which start it
 * or start its LockedChildActivity ancestors.
 * This kind of activity must be started by activities
 * extend from LockedActivity or LockedChildActivity.
 */
public abstract class LockedChildActivity extends Activity {
	protected boolean lockable = true;
	
	@Override
	public void onPause(){
		super.onPause();

		if(lockable){
			Intent res = getIntent();
			this.setResult(LockerActivity.RESULT_LOCK, res);
			this.finish();
		}
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent){
		lockable = true;
		if(resultCode == LockerActivity.RESULT_LOCK){
			this.onPause();
		}
	}
	
	@Override
	public void startActivityForResult(Intent intent, int requestCode){
		lockable = false;
		super.startActivityForResult(intent, requestCode);
	}
	
}
