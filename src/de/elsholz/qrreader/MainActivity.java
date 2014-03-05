package de.elsholz.qrreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button buttonStartScan = (Button) findViewById(R.id.button1);
		buttonStartScan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				startActivityForResult(intent, 0);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			String contents = "";
			String format = "";
			if (resultCode == RESULT_OK) {
				contents = intent.getStringExtra("SCAN_RESULT");
				format = "Format: "
						+ intent.getStringExtra("SCAN_RESULT_FORMAT") + "\n";
				// Handle successful scan
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
				contents = "- Scan abgebrochen -";
			}
			TextView textView = (TextView) findViewById(R.id.textView1);
			textView.setText(format + contents);
		}
	}
}
