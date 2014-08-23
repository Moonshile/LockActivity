LockActivity
============

Lock the screen when an activity or its sub-activity is paused.

Copyright (C) 2014  Moonshile (moonshile@foxmail.com)

###Effect###

Effect of *LockActivity* is as follows. If onResume() of main page, or onPause() of 
child page or subchild page is called, then the application will return to main page 
and then be locked with default or self-defined LockerActivity.

![Main Page](https://raw.githubusercontent.com/Moonshile/LockActivity/master/shortcuts/1.png) _ ![Main Page](https://raw.githubusercontent.com/Moonshile/LockActivity/master/shortcuts/2.png)

![Main Page](https://raw.githubusercontent.com/Moonshile/LockActivity/master/shortcuts/3.png) _ ![Main Page](https://raw.githubusercontent.com/Moonshile/LockActivity/master/shortcuts/4.png)

###How To Use###

You can download the src of *LockActivity*, and import it into your workspace as a 
library project, then add it as library into you application, or just merge the files 
into your project (NOT recommended), then *LockActivity* can be used as you wish!

**USE STEP BY STEP**

1. Create a *Locker Activity*

*Locker activity* is derived from `com.moonshile.lib.ui.LockerActivity`, which will be
displayed after your application is locked. To use *Locker Activity*, you shall add an 
activity, modify its super class to `LockerActivity`, remove the generated layout file 
so as to use the default layout provided by *LockActivity* library. If you want a self-
defined layout, just follow the steps as usual. However, it is necessary to emphasize 
the following tips:

* If you create a *Locker Activity* with self-defined layout, the layout must has an
  `EditText` view with id `moonshile_lock_key`, and must also has two buttons with 
  onClick event `onOK` and `onCancel` repectively. If necessary, you need to override
  method `protected boolean rightKey(String)` to define validation process of yourself.
  
* You need to add an attribute in the manifest file to this *Locker Activity* as:

	`android:theme="@android:style/Theme.Translucent.NoTitleBar"`

2. Create a *Main Activity*

*Main Activity* is the activity that the application will return to and then be locked. 
To achieve this, you NEED to extends `LockedActivity` which has the full name 
`com.moonshile.lib.ui.LockedActivity`. Then there's two thing you MUST do:

* Initialize fileds `key` and `lockerClass` of the super class with the key to unlock your 
  application and the lockerClass you created with super class `LockerActivity` respectively. 
  For example:

	`super.key = "";`
	`super.lockerClass = MyLockerActivity.class;`

* You must start a sub-activity with `startActivityForResult`, and the sub-activity 
  must extends super class `LockedChildActivity` (will be introduced int the following).
  And then, in your `onActivityResult` method, call `super.onActivityResult(...)` first.
  For example:

	`@Override`
	`public void onActivityResult(int requestCode, int resultCode, Intent intent){`
	`	super.onActivityResult(requestCode, resultCode, intent);`
	`	switch(requestCode){`
	`	case REQUEST_CODE_CHILD:`
	`		// TODO ...`
	`		break;`
	`	// TODO ...`
	`	}`
	`	// TODO ...`
	`}`

3. Create a *Child Activity*

*Child Activity* is derived from `com.moonshile.lib.ui.LockedChildActivity`. If the application
is locked, this activity will be finished and return to its father, if its father is also 
derived from *LockedChidlActivity*, its father activity will also finished, until return to an
ancestor that is not a *Child Activity*.

There is nothing but extends the `LockedChildActivity` you need to do to achieve a child 
activity.

###Q&A###

* Why merging the files into my project is not recommended ?

  The *LockActivity* library need R class of itself, if you directly merge the files, you need 
  modify some source code manually.


