package mobile.view;

import java.util.ArrayList;

import file.log.ServizioTimer;

import mobile.ADISYS.R;
import mobile.control.Controller;
import mobile.model.Intervento;
import mobile.model.ListaInterventi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SchedaDettaglio extends Activity implements OnClickListener,
		OnItemClickListener, OnDrawerOpenListener, OnDrawerCloseListener {

	private Intervento inter;
	private String oraInizio, oraFine, data;
	private String formatoData = "yyyy-MM-dd";
	private String formatoOra = "HH:mm:ss";
	public int posizione, selezione;
	public Context cnt = this;
	private Intent mServiceIntent;
	public TextView Nome, Cognome, EtaPaziente, Indirizzo, DataOra;
	private ArrayAdapter<String> adapter, adaptCellulari;
	ListView listv;
	private ListView ListaCellulare;
	private static ArrayList<String> cellulari;
	private static ArrayList<String> interventi;
	// private static ArrayList<Intervento> lista;
	private SlidingDrawer slidingDrawer;
	private Button BtnAttiva, BtnIndietro, BtnSalva;
	private boolean flag;
	private EditText Note;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dettagliolayout);
		selezione = getIntent().getIntExtra("selezione", 0);
		inter = ListaInterventi.getListaInterventi().get(selezione);
		interventi = new ArrayList<String>();
		cellulari = new ArrayList<String>();
		// SET LISTA INTERVENTI
		/*
		 * int num=inter.getTipoIntervento().size(); for(int i = 0; i<num;i++){
		 * interventi.add(inter.getTipoIntervento().get(i).getNome()); }
		 */
		// SET LISTA PATOLOGIE
		int num = inter.getTipoIntervento().size();
		for (int i = 0; i < num; i++) {
			interventi.add(inter.getTipoIntervento().get(i).getPatologia());
		}

		findViewsById();
		flag = false;
		BtnAttiva.setOnClickListener(this);
		BtnIndietro.setOnClickListener(this);
		BtnSalva.setOnClickListener(this);

		assegna();
		listv.setOnItemClickListener(this);
		slidingDrawer.setOnDrawerOpenListener(this);
		slidingDrawer.setOnDrawerCloseListener(this);
	}

	private void assegna() {
		Nome.setText(inter.getNome());
		Cognome.setText(inter.getCognome());
		Indirizzo.setText(inter.getCitta() + "   " + inter.getnCivico());
		DataOra.setText(inter.getData() + "   " + inter.getOraInizio());
		Note.setText(inter.getNote());
		cellulari.addAll(inter.getCellulari());
		adaptCellulari = new ArrayAdapter<String>(this, R.layout.listitem,
				cellulari);
		adapter = new ArrayAdapter<String>(this, R.layout.listitem, interventi);
		ListaCellulare.setAdapter(adaptCellulari);
		listv.setAdapter(adapter);
		slidingDrawer = (SlidingDrawer) this.findViewById(R.id.slide);
		mServiceIntent = new Intent(cnt, ServizioTimer.class);
		mServiceIntent.putExtra("paziente", selezione);
	}

	private void findViewsById() {
		Nome = (TextView) findViewById(R.id.NomePaziente);
		Note = (EditText) findViewById(R.id.editnote);
		Cognome = (TextView) findViewById(R.id.CognomePaziente);
		Indirizzo = (TextView) findViewById(R.id.CittaCivico);
		DataOra = (TextView) findViewById(R.id.DataOra);
		ListaCellulare = (ListView) findViewById(R.id.listaCellulari);
		BtnAttiva = (Button) findViewById(R.id.btnAttivazione);
		BtnIndietro = (Button) findViewById(R.id.Indietro);
		BtnSalva = (Button) findViewById(R.id.btnSaveExit);
		listv = (ListView) findViewById(R.id.listaInterventi);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.btnAttivazione) {
			flag = true;
			BtnAttiva.setVisibility(View.GONE);
			BtnIndietro.setVisibility(View.GONE);
			BtnSalva.setVisibility(View.VISIBLE);
			oraInizio = mobile.control.Controller.Adesso(formatoOra);
			inter.setOraInizio(oraInizio);
			listv.setVisibility(View.VISIBLE);
			startService(mServiceIntent);
		} else if (v.getId() == R.id.Indietro) {
			Intent Indietro = new Intent(cnt, ADISYSMobileActivity.class);
			startActivity(Indietro);
			finish();

		} else if (v.getId() == R.id.btnSaveExit) {
			inter = ListaInterventi.getListaInterventi().get(selezione);
			oraFine = mobile.control.Controller.Adesso(formatoOra);
			data = mobile.control.Controller.Adesso(formatoData);
			inter.setData(data);
			inter.setOraFine(oraFine);
			inter.setNote(Note.getText().toString());
			stopService(mServiceIntent);
			try {
				Controller.scriviAttivita(ListaInterventi.getListaInterventi()
						.get(selezione));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Intent Indietro = new Intent(cnt, ADISYSMobileActivity.class);
			ListaInterventi.removeIntervento(selezione);
			startActivity(Indietro);
			finish();
		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		int interSelezionato = position;
		Intent i = new Intent(SchedaDettaglio.this, DettaglioIntervento.class);
		i.putExtra("posizione", interSelezionato);
		i.putExtra("Paziente", selezione);
		startActivity(i);
	}

	public void onDrawerOpened() {
		listv.setVisibility(ListView.GONE);
		Note.setVisibility(EditText.INVISIBLE);
		Indirizzo.setVisibility(TextView.INVISIBLE);
		DataOra.setVisibility(TextView.INVISIBLE);
		ListaCellulare.setVisibility(ListView.VISIBLE);
	}

	public void onDrawerClosed() {
		Note.setVisibility(EditText.VISIBLE);
		Cognome.setVisibility(TextView.VISIBLE);
		Indirizzo.setVisibility(TextView.VISIBLE);
		DataOra.setVisibility(TextView.VISIBLE);
		ListaCellulare.setVisibility(ListView.GONE);
		if (flag == true) {
			listv.setVisibility(ListView.VISIBLE);

		}
	}

}
