package org.francho.sample.eltiempo.widget;

import org.francho.sample.eltiempo.R;
import org.francho.sample.eltiempo.contentprovider.AemetContract.PredictionsTable;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.format.DateUtils;
import android.widget.TextView;

public class ElTiempoAdapter extends SimpleCursorAdapter  {
	private static final String[] FROM = new String[] { PredictionsTable.DATE, PredictionsTable.MAX_TEMP, PredictionsTable.MIN_TEMP };
	private static final int[] TO = new int[] { R.id.day, R.id.max_temp, R.id.min_temp };
	private Context context;

	public ElTiempoAdapter(Context context) {
		super(context, R.layout.listitem_dayinfo , null, FROM, TO, FLAG_REGISTER_CONTENT_OBSERVER);

		this.context = context;
		initCursor(context);
	}

	private void initCursor(Context context) {

		Uri uri = PredictionsTable.getUri();
		String[] projection = new String[]{PredictionsTable._ID, PredictionsTable.DATE, PredictionsTable.MAX_TEMP, PredictionsTable.MIN_TEMP};
		String selection = null;
		String[] selectionArgs = null;
		String sortOrder = PredictionsTable.DATE + " ASC";

		final Cursor cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
		this.changeCursor(cursor);

	}

	@Override
	public void setViewText(TextView v, String text) {
		if(isDateView(v)) {
			text = getFormattedDate(text);
		}
		
		super.setViewText(v, text);
	}

	private boolean isDateView(TextView v) {
		return v.getId() == R.id.day;
	}

	private String getFormattedDate(String text) {
		Long millis = Long.parseLong(text);
		return (String)DateUtils.formatDateTime(context, millis, DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_DATE);
	}
}