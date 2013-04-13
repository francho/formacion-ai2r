package org.francho.sample.eltiempo;

import java.util.Date;

import org.francho.sample.eltiempo.aemet.AemetDayInfo;
import org.francho.sample.eltiempo.contentprovider.AemetContract.PredictionsTable;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class DetailActivity extends Activity {
	AemetDayInfo mDayInfo = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_detail);
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		mDayInfo = loadDayInfo();
	}

	private AemetDayInfo loadDayInfo() {
		Uri uri = getIntent().getData();
		String selection = null;
		String[] projection = new String[]{
				PredictionsTable.DATE, // 0
				PredictionsTable.MAX_TEMP, // 1
				PredictionsTable.MIN_TEMP // 2
		};
		String[] selectionArgs = null;
		String sortOrder =null;
		
		mDayInfo= new AemetDayInfo();
		
		Cursor cursor=getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
		
		while(cursor.moveToNext()) {
			Date date = new Date(cursor.getLong(0));
			mDayInfo.setDate(date);
			mDayInfo.setMaxTemp(cursor.getInt(1));
			mDayInfo.setMinTemp(cursor.getInt(2));
		} 
		cursor.close();
		
		Toast.makeText(this,"Day info loaded "+mDayInfo, Toast.LENGTH_LONG).show();
	
		return null;
	}
	
	
}
