package org.francho.sample.eltiempo.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public class AemetContract {
		public static final String DB_NAME="aemet.db";
		public static final String AUTHORITY = "org.francho.sample.aemetprovider";

		/**
		 * No queremos que nadie instancie esta clase
		 */
		private AemetContract() {}

		public static class PredictionsTable implements BaseColumns{
			/**
			 * No queremos que nadie instancie esta clase
			 */
			private PredictionsTable() {}
			public static final String TABLE_NAME = "predictions";

			public static final String CITY="city";
			public static final String DATE="date";
			public static final String MAX_TEMP="max_temp";
			public static final String MIN_TEMP="min_temp";

			public static Uri getUri() {
				return Uri.parse("content://"+AUTHORITY+"/predictions");
			}

			public static Uri getUri(long id) {
				return Uri.parse("content://"+AUTHORITY+"/predictions/"+id);
			}

		}

	}
