package org.francho.sample.eltiempo.contentprovider;

import org.francho.sample.eltiempo.contentprovider.AemetContract.PredictionsTable;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class AemetContentProvider extends ContentProvider {

	private AemetDbHelper mDbHelper;

	private static final UriMatcher sUriMatcher;

	private static final int TYPE_PREDICTIONS_COLLECTION = 1;
	private static final int TYPE_PREDICTIONS_ITEM = 2;

	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

		sUriMatcher.addURI(AemetContract.AUTHORITY, "predictions", TYPE_PREDICTIONS_COLLECTION);
		sUriMatcher.addURI(AemetContract.AUTHORITY, "predictions/#", TYPE_PREDICTIONS_ITEM);
	}


	@Override
	public boolean onCreate() {
		mDbHelper = new AemetDbHelper(getContext());
		return true;
	}

	@Override
	public String getType(Uri uri) {
		switch(sUriMatcher.match(uri)) {
		case TYPE_PREDICTIONS_COLLECTION:
			return "android.cursor.dir/vnd.org.francho.sample.eltiempo.predictions";
		case TYPE_PREDICTIONS_ITEM:
			return "android.cursor.item/vnd.org.francho.sample.eltiempo.predictions";
		default:
			return null;
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriType = sUriMatcher.match(uri);
		if( uriType != TYPE_PREDICTIONS_COLLECTION) {
			return -1;
		}

		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		return db.delete(PredictionsTable.TABLE_NAME, selection, selectionArgs);
	}



	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sUriMatcher.match(uri);
		if( uriType != TYPE_PREDICTIONS_COLLECTION) {
			return null;
		}

		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		long id = db.insert(PredictionsTable.TABLE_NAME, null, values);

		Uri newUri = PredictionsTable.getUri(id);
		
		getContext().getContentResolver().notifyChange(uri, null);

		return newUri;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		final SQLiteDatabase db = mDbHelper.getReadableDatabase();

		switch(sUriMatcher.match(uri)) {
		case TYPE_PREDICTIONS_ITEM:
			String id = uri.getLastPathSegment();
			if(selection==null) { selection = ""; } 
			selection += (!TextUtils.isEmpty(selection)) ? " AND" : "";
			selection += PredictionsTable._ID + "=" + id;
		case TYPE_PREDICTIONS_COLLECTION:
			String table = PredictionsTable.TABLE_NAME;
			String groupBy = null;
			String having = null;
			Cursor cursor = db.query(table, projection, selection, selectionArgs, groupBy, having, sortOrder);
			return cursor;
		default:
			throw new IllegalArgumentException("Unknown uri "+uri);
		}

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		int uriType = sUriMatcher.match(uri);
		if( uriType != TYPE_PREDICTIONS_COLLECTION) {
			return -1;
		}

		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		int affectedRows = db.update(PredictionsTable.TABLE_NAME, values, selection, selectionArgs);
		
		getContext().getContentResolver().notifyChange(uri, null);
		
		return affectedRows;
	}

}