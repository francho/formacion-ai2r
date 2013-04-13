package org.francho.ai2r.ejemplosintent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final View button = findViewById(R.id.button1);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		
		startActivityForResult(intent, 100);
		
		// Mas ejemplos en-> http://francho.org/2011/04/11/android-ejemplos-de-llamadas-a-intents/
	}
	

}
