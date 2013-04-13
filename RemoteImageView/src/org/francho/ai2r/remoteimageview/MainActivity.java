package org.francho.ai2r.remoteimageview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		RemoteImageView image = (RemoteImageView) findViewById(R.id.imagen);
		image.setRemoteImage("http://franchojoven.files.wordpress.com/2012/12/sidra.jpg?w=450&h=338");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
