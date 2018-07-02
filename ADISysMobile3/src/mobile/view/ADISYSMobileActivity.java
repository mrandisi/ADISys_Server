package mobile.view;

import java.util.ArrayList;

import mobile.ADISYS.R;
import mobile.control.*;
import mobile.model.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class ADISYSMobileActivity extends Activity {
	/** Called when the activity is first created. */
	private ListView lv;
	private static ArrayList<Intervento> list;
	private static ArrayList<String> interventi;
	private ArrayAdapter<String> adapter;
	public Context cnt = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try {
			interventi = new ArrayList<String>();
			Controller.leggiFile(cnt);
			restituisciAttivita();
			if (interventi.isEmpty()) {
				Toast.makeText(getApplicationContext(),
						"Non ci sono interventi!\n Il programma sarà chiuso!",
						Toast.LENGTH_LONG).show();
				Controller.chiudiAttivita();
				finish();
			}

			lv = (ListView) findViewById(R.id.id_lv);
			adapter = new ArrayAdapter<String>(this, R.layout.listitem,
					interventi);
			lv.setAdapter(adapter);
			lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					int selezione = position;
					Intent i = new Intent(cnt, SchedaDettaglio.class);
					i.putExtra("selezione", selezione);
					startActivity(i);
					finish();
				}
			});
		} catch (Exception e) {
			finish();
		}

	}

	private static void restituisciAttivita() {
		interventi = new ArrayList<String>();
		interventi.clear();
		list = ListaInterventi.getListaInterventi();
		for (Intervento i : list) {
			String s = i.getnCivico() + "      " + i.getOraInizio();
			interventi.add(s);
		}
	}
}