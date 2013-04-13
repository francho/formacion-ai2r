package org.francho.sample.eltiempo.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;

import org.francho.sample.eltiempo.aemet.AemetDayInfo;
import org.francho.sample.eltiempo.aemet.AemetRss;
import org.francho.sample.eltiempo.contentprovider.AemetContract.PredictionsTable;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.net.Uri;
import android.support.v4.util.TimeUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;

public class AemetPredictionService extends IntentService {
	private static final String TAG = "AemetPredictionService";

	private ContentResolver mContentResolver;

	public AemetPredictionService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "onHandleIntent");

		downloadPredictionAndSave();
		deleteOldPredictions();
	}

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		super.onCreate();

		mContentResolver = getContentResolver();
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}

	private void downloadPredictionAndSave() {
		final AemetRss aemet = new AemetRss();

		final ArrayList<AemetDayInfo> prediction = aemet.getLogronoPrediction();

		for (AemetDayInfo dayinfo : prediction) {
			saveDayInfo(dayinfo);
		}
	}

	private void saveDayInfo(AemetDayInfo dayinfo) {
		final ContentValues values = new ContentValues();
		final long timestamp = dayinfo.getDate().getTime();
		values.put(PredictionsTable.CITY, "Logro–o");
		values.put(PredictionsTable.DATE, timestamp);
		values.put(PredictionsTable.MAX_TEMP, dayinfo.getMaxTemp());
		values.put(PredictionsTable.MIN_TEMP, dayinfo.getMinTemp());

		final Uri uri = PredictionsTable.getUri();

		final String where = PredictionsTable.DATE + "=?";
		final String[] whereArgs = new String[] { "" + timestamp };
		final int affectedRows = mContentResolver.update(uri, values, where, whereArgs);

		if (affectedRows<1) {
			mContentResolver.insert(uri, values);
		}

	}

	private void deleteOldPredictions() {
		final Uri uri = PredictionsTable.getUri();
		
		Calendar today = Calendar.getInstance();
	    today.set(Calendar.HOUR_OF_DAY, 0);
	    today.set(Calendar.MINUTE, 0);
	    today.set(Calendar.SECOND, 0);
	    today.set(Calendar.MILLISECOND,0);

		final String where = PredictionsTable.DATE + "<?";
		final String[] whereArgs = new String[] { "" + today.getTimeInMillis() };
		
		mContentResolver.delete(uri, where, whereArgs);
	}
}
