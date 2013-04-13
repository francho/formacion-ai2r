package org.francho.sample.eltiempo;

import org.francho.sample.eltiempo.app.AppIntent;
import org.francho.sample.eltiempo.contentprovider.AemetContract.PredictionsTable;
import org.francho.sample.eltiempo.widget.ElTiempoAdapter;

import android.app.ListActivity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class WeatherActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		startService(AppIntent.getAemetService());
		
		initAdapter();
	}
	
	protected void initAdapter() {
		ElTiempoAdapter adapter = new ElTiempoAdapter(this);
		setListAdapter(adapter);
	}

	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Uri data = PredictionsTable.getUri(id);
		startActivity(AppIntent.getDetailActivity(data));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_weather, menu);
		return true;
	}

}
