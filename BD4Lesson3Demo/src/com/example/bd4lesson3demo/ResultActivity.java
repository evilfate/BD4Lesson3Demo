package com.example.bd4lesson3demo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private static final String TAG = ResultActivity.class.getSimpleName();

	private String receivedString;
	private int receivedInt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		Bundle bundle = getIntent().getExtras();

		if (bundle != null) {
			receivedString = bundle.getString("STRING");
			receivedInt = bundle.getInt("INT");
		}

		String simpleString = getIntent().getStringExtra("SIMPLE");
		Log.d(TAG, "simpleString : " + simpleString);

		TextView tvReceivedString = (TextView) findViewById(R.id.tvReceivedString);
		if ("".equals(receivedString)) {
			tvReceivedString.setText(getString(R.string.received, "EMPTY"));
			Log.d(TAG, "receivedString : EMPTY");
		} else {
			tvReceivedString.setText(getString(R.string.received,
					receivedString));
			Log.d(TAG, "receivedString : " + receivedString);
		}

		Log.d(TAG, "receivedInt : " + receivedInt);
	}

	public void returnResult(View v) {
		Intent intent = getIntent();
		Bundle bundle = new Bundle();
		if ("".equals(receivedString)) {
			bundle.putString("RESULT", "this is return data");
		} else {
			bundle.putString("RESULT", receivedString);
		}
		intent.putExtras(bundle);

		this.setResult(MainActivity.REQUEST_CODE, intent);
		this.finish();
	}
}
