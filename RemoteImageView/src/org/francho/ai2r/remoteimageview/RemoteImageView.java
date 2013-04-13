package org.francho.ai2r.remoteimageview;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RemoteImageView extends ImageView {

	private DownloadImageAsyncTask downloadTask;

	public RemoteImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public RemoteImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RemoteImageView(Context context) {
		super(context);
	}

	public void setRemoteImage(String url) {
		if(downloadTask != null) {
			downloadTask.cancel(true);
		}
		
		downloadTask = new DownloadImageAsyncTask();
		downloadTask.execute(url);
	}

	class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			if (url == null) {
				return null;
			}

			URL myFileUrl;

			Bitmap imagen = null;

			try {
				myFileUrl = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) myFileUrl
						.openConnection();
				conn.setDoInput(true);
				conn.connect();
				InputStream is = conn.getInputStream();
				//if(this.isCancelled())
				//this.publishProgress(values);
				
				imagen = BitmapFactory.decodeStream(is);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return imagen;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			
			setImageBitmap(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			setImageResource(R.drawable.ic_launcher); 
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
		
		

	}

}
