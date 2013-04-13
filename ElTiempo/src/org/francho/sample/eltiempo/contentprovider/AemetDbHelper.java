package org.francho.sample.eltiempo.contentprovider;

import org.francho.sample.eltiempo.contentprovider.AemetContract.PredictionsTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AemetDbHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 2;

	public AemetDbHelper(Context context) {
		super(context, AemetContract.DB_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createPredictionsTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {
		case 1:
			createPredictionsTable(db);
		}
	}

	private void createPredictionsTable(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + PredictionsTable.TABLE_NAME);
		db.execSQL("CREATE TABLE " + PredictionsTable.TABLE_NAME + "("
				+ PredictionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ PredictionsTable.CITY + " STRING," + PredictionsTable.DATE
				+ " LONG UNIQUE," + PredictionsTable.MAX_TEMP + " INT,"
				+ PredictionsTable.MIN_TEMP + " INT" + ")");
	}

}
