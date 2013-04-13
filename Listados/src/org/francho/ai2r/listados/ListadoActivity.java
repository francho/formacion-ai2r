package org.francho.ai2r.listados;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListadoActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Context context = this;
		int textViewResourceId = android.R.layout.simple_list_item_1;
		String[] datos = getResources().getStringArray(R.array.datos);
		
		ListAdapter adapter = new ArrayAdapter<String>(context, textViewResourceId, datos);
		setListAdapter(adapter );
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		String dato = (String) getListAdapter().getItem(position);
		
		Intent intent = new Intent("org.francho.a12r.listados.DETALLE");
		intent.putExtra("dato", dato);
		
		startActivity(intent);
	}

	
	
}
