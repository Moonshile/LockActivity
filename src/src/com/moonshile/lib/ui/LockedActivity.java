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
package com.moonshile.lib.ui;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

/**
 * This activity will be locked if onResume() called
 */
public abstract class LockedActivity<T> extends Activity {
	
	protected boolean lockable = false;
	protected Class<T> lockerClass = null;
	protected String key = null;
	
	private static final String MOONSHILE_LOG_E = "moonshile.error";
	
	public static final int MOONSHILE_REQUEST_CODE_LOCK = 0x10000000;
	
	@Override
	protected void onResume(){
		super.onResume();
		
		if(lockerClass == null){
			Log.e(MOONSHILE_LOG_E, "NEED to set field com.moonshile.lib.ui.LockerActivity.lockerClass\n" + 
					"in onCreate() method of subclass");
			return;
		}
		if(key == null){
			Log.e(MOONSHILE_LOG_E, "NEED to set field com.moonshile.lib.ui.LockerActivity.key\n" + 
					"in onCreate() method of subclass");
		}
		
		if(lockable){
			Intent lock = new Intent(this, lockerClass);
			lock.putExtra(LockerActivity.MOONSHILE_INTENT_KEY, key);
			this.startActivityForResult(lock, MOONSHILE_REQUEST_CODE_LOCK);
		}
		
		lockable = true;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent){
		switch(requestCode){
		case MOONSHILE_REQUEST_CODE_LOCK:
			switch(resultCode){
			case LockerActivity.RESULT_OK:
				break;
			case LockerActivity.RESULT_CANCELED:
				this.finish();
				break;
			}
			break;
		}
		lockable = resultCode == LockerActivity.RESULT_LOCK ? true : false;
	}
	
	
}
