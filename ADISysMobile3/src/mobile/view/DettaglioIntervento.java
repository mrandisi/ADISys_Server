package mobile.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import mobile.ADISYS.R;
import mobile.model.Intervento;
import mobile.model.ListaInterventi;
import mobile.model.TipiIntervento;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DettaglioIntervento extends Activity implements OnClickListener {

	public TextView NomePatologia;
	public EditText NomeInter, noteTxt, Rilevazione;
	private Button BtnSalva;
	private int indicePaziente, indiceIntervento;
	public Context cnt = this;
	private String formatoOra = "HH:mm:ss";
	private Intervento Paziente;
	private TipiIntervento interPaziente;
	private String oraInizio;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layoutintervento);
		findViewsById();
		assegna();

	}

	private void assegna() {
		indicePaziente = (getIntent().getIntExtra("Paziente", 0));
		indiceIntervento = (getIntent().getIntExtra("posizione", 0));
		Paziente = ListaInterventi.getListaInterventi().get(indicePaziente);
		interPaziente = Paziente.getTipoIntervento().get(indiceIntervento);
		NomePatologia.setText(interPaziente.getPatologia());
		NomeInter.setText(interPaziente.getNome());
		noteTxt.setText(interPaziente.getNote());
		Rilevazione.setText(interPaziente.getMisura());
		BtnSalva.setOnClickListener(this);
		oraInizio = mobile.control.Controller.Adesso(formatoOra);
	}

	private void findViewsById() {
		NomePatologia = (TextView) findViewById(R.id.NomePatologia);
		NomeInter = (EditText) findViewById(R.id.editText1);
		noteTxt = (EditText) findViewById(R.id.note);
		Rilevazione = (EditText) findViewById(R.id.valoreRilevato);
		BtnSalva = (Button) findViewById(R.id.salva);

	}

	public void onClick(View v) {

		if (v.getId() == R.id.salva) {
			if ((Rilevazione.getText().toString()) != (interPaziente
					.getMisura())) {
				interPaziente.setMisura(Rilevazione.getText().toString());
				interPaziente.setNote(noteTxt.getText().toString());
				String oraFine = mobile.control.Controller.Adesso(formatoOra);
				String tempo = diffOreMinuti(oraInizio, oraFine);
				interPaziente.setTempo(tempo);
				ListaInterventi.setTipoIntervento(interPaziente,
						indicePaziente, indiceIntervento);

			}
			finish();
		}
	}

	private String diffOreMinuti(String inizio, String fine) {

		final String pattern = "HH:mm:ss";
		SimpleDateFormat formattatore = new SimpleDateFormat(pattern);

		try {
			// Oggetto inizio
			GregorianCalendar gcInizio = new GregorianCalendar();
			gcInizio.setTime(formattatore.parse(inizio));

			// Oggetto fine
			GregorianCalendar gcFine = new GregorianCalendar();
			gcFine.setTime(formattatore.parse(fine));

			// Oggetto differenza
			GregorianCalendar gcDiff = new GregorianCalendar();
			gcDiff.setTimeInMillis(gcFine.getTimeInMillis()
					- gcInizio.getTimeInMillis());

			// Output
			return formattatore.format(gcDiff.getTime());

		} catch (ParseException pe) {
			return "00:00:00";
		}

	}

}
