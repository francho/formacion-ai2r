package org.francho.sample.eltiempo.app;

import android.content.Intent;
import android.net.Uri;

public class AppIntent extends Intent {

	public static Intent getAemetService() {
		Intent intent = new Intent("org.francho.sample.eltiempo.AEMET_SERVICE");
		return intent;
	}
	
	public static Intent getDetailActivity(Uri data) {
		Intent intent = new Intent("org.francho.sample.eltiempo.ACTION_DETAIL");
		intent.setData(data);
		return intent;
	}
}
