package org.francho.ai2r.listados;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalleActivity extends Activity {

	private TextView texto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.detalle_layout);
		texto = (TextView) findViewById(R.id.textView1);
	}

	@Override
	protected void onStart() {
		super.onStart();
	
		String dato = getIntent().getStringExtra("dato");
		if(dato == null) {
			dato = "";
		}
		
		texto.setText(dato);
	}

	
	
}
