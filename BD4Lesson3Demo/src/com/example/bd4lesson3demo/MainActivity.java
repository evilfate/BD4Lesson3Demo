package com.example.bd4lesson3demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String TAG = MainActivity.class.getSimpleName();

	public static final int REQUEST_CODE = 0;

	EditText etToSave;
	TextView tvResult;
	Button btnGetResult;
	Button btnOpenUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Activity lifecycle demo : onCreate");
		setContentView(R.layout.activity_main);

		etToSave = (EditText) findViewById(R.id.etToSave);
		tvResult = (TextView) findViewById(R.id.tvResult);
		btnGetResult = (Button) findViewById(R.id.btnGetResult);
		btnOpenUrl = (Button) findViewById(R.id.btnOpenUrl);

		if (null != savedInstanceState) {
			Log.d(TAG, "savedInstanceState NOT null");
		} else {
			Log.d(TAG, "savedInstanceState IS null");
		}

		tvResult.setText(getString(R.string.result, "lalala"));

		btnGetResult.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//Start new Activity and pass data using Bundle
				Intent intent = new Intent(MainActivity.this,
						ResultActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("STRING", etToSave.getText().toString());
				bundle.putInt("INT", 123454321);
				intent.putExtras(bundle);
				
				// Add parameter in simple way
				intent.putExtra("SIMPLE", "Simple value");

				startActivityForResult(intent, REQUEST_CODE);
			}
		});
		
		btnOpenUrl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = "http://www.google.com";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(TAG, "Activity lifecycle demo : onSaveInstanceState");
		String editTextString = etToSave.getText().toString();
		// Save data you want to save
		outState.putString("STRING", "TEXT");
		outState.putInt("INT", 123);
		outState.putString("EDITTEXT", editTextString);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(TAG, "Activity lifecycle demo : onRestoreInstanceState");
		// Load saved data
		String savedString = savedInstanceState.getString("STRING");
		int savedInt = savedInstanceState.getInt("INT");
		String savedEditTextString = savedInstanceState.getString("EDITTEXT");
		Log.d(TAG, "savedString : " + savedString);
		Log.d(TAG, "savedInt : " + savedInt);
		Log.d(TAG, "savedEditTextString : " + savedEditTextString);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "Activity lifecycle demo : onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "Activity lifecycle demo : onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "Activity lifecycle demo : onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "Activity lifecycle demo : onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "Activity lifecycle demo : onDestroy");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "Activity lifecycle demo : onRestart");

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG, "onActivityResult : requestCode : " + requestCode);
		switch (requestCode) {
		case REQUEST_CODE:
			Bundle bundle = data.getExtras();
			String result = bundle.getString("RESULT");
			tvResult.setText(getString(R.string.result, result));
			break;

		default:
			break;
		}

	}

	public void startDialog(View v) {
		Intent intent = new Intent(MainActivity.this, DialogActivity.class);
		startActivity(intent);
	}
}
