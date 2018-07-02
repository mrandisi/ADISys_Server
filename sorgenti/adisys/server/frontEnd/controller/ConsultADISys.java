package adisys.server.frontEnd.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import adisys.server.utility.UIFeedbackException;

public class ConsultADISys extends ADISysAC {

    public ConsultADISys() throws ParserConfigurationException,
            SAXException, IOException {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    //***********ASSEGNAZIONE ATTRIBUTI**************//
    protected GestioneInfermieri GestioneInfermieri = new GestioneInfermieri();
    protected GestionePazienti GestionePazienti = new GestionePazienti();
    protected GestioneInterventi GestioneInterventi = new GestioneInterventi();
    protected GestionePatologie GestionePatologie = new GestionePatologie();
    protected GestionePianificazione GestionePianificazione = new GestionePianificazione();
    protected GestioneVerificaDati	GestioneVerificaDati = new GestioneVerificaDati();

    
    
    
  //************GESTIONE INFERMIERI*************//
    
    public Object creaInfermiere(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
       
        String flag = "infermiere";
        return checkEsito("creaInfermiere", parametri, flag);

    }

    public Object modificaInfermiere(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        
        String flag = "infermiere";
        return checkEsito("modificaInfermiere", parametri, flag);

    }
        
    public Object cancellaInfermiere(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        
        String flag = "infermiere";
       return checkEsito("cancellaInfermiere", parametri, flag);
             
    }

    public Object cancellaTuttiInfermieri(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        
        String flag = "infermiere";
        return checkEsito("cancellaTuttiInfermieri", parametri, flag);
             
    }
    
    public Object getTabellaInfermieri(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "infermiere";
        return checkEsito("getTabellaInfermieri", parametri, flag);

    }
    
    public Object showInfoInfermiere(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "infermiere";
        return checkEsito("showInfoInfermiere", parametri, flag);

    }
    
    
    
    
    //************GESTIONE PAZIENTI*************//
    
    public Object creaPaziente(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "paziente";
        return checkEsito("creaPaziente", parametri, flag);

    }

    public Object modificaPaziente(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "paziente";
        return checkEsito("modificaPaziente", parametri, flag);

    }
        
    public Object cancellaPaziente(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "paziente";
        return checkEsito("cancellaPaziente", parametri, flag);
             
    }

    
    public Object cancellaTuttiPazienti(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "paziente";
        return checkEsito("cancellaTuttiPazienti", parametri, flag);
             
    }
    
    public Object getTabellaPazienti(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "paziente";
        return checkEsito("getTabellaPazienti", parametri, flag);
             
    }
    
    public Object showInfoPaziente(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "paziente";
        return checkEsito("showInfoPaziente", parametri, flag);

    }
    
    
    
    
  //************GESTIONE PATOLOGIE*************//
    
    public Object creaPatologia(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "patologia";
        return checkEsito("creaPatologia", parametri, flag);

    }

    public Object modificaPatologia(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "patologia";
        return checkEsito("modificaPatologia", parametri, flag);

    }
        
    public Object cancellaPatologia(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "patologia";
        return checkEsito("cancellaPatologia", parametri, flag);
             
    }
    
    public Object getTabellaPatologie(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "patologia";
        return checkEsito("getTabellaPatologie", parametri, flag);
             
    }
    
    public Object getNomiPatologie(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "patologia";
        return checkEsito("getNomiPatologie", parametri, flag);
             
    }
    
    
    
    
  //************GESTIONE INTERVENTI*************//
    
    public Object creaIntervento(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "intervento";
        return  checkEsito("creaIntervento", parametri, flag);

    }

    public Object modificaIntervento(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "intervento";
        return checkEsito("modificaIntervento", parametri, flag);

    }
        
    public Object cancellaIntervento(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "intervento";
        return checkEsito("cancellaIntervento", parametri, flag);
             
    }

    public Object cancellaTuttiInterventi(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "intervento";
        return checkEsito("cancellaTuttiInterventi", parametri, flag);
             
    }
    
    public Object getTabellaInterventi(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException,UIFeedbackException {
        String flag = "intervento";
        return checkEsito("getTabellaInterventi", parametri, flag);
             
    }
    
    public Object showInfoIntervento(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "intervento";
        return checkEsito("showInfoIntervento", parametri, flag);
             
    }
    
    public Object getTipiIntervento(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "intervento";
        return checkEsito("getTipiIntervento", parametri, flag);
             
    }
    
    
    
    
  //************GESTIONE PIANIFICAZIONE*************//

    public Object getTabellaInfoInfermieri(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException,UIFeedbackException {
        String flag = "pianificazione";
        return checkEsito("getTabellaInfoInfermieri", parametri, flag);
             
    }
    
    public Object getTabellaVisualizzazioneInterventi(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "pianificazione";
        return checkEsito("getTabellaVisualizzazioneInterventi", parametri, flag);

    }

    public Object esportaPianificazione(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "pianificazione";
        return checkEsito("esportaPianificazione", parametri, flag);

    }
    
    
    
    
  //************GESTIONE DATI VERFICA*************//
    
    public Object caricaFile(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "verifica";
        return checkEsito("caricaFile", parametri, flag);
             
    }
        
    public Object getDatiInfermiere(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "verifica";
        return checkEsito("getDatiInfermiere", parametri, flag);
             
    }

    public Object popolaTabellaAttivita(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "verifica";
        return checkEsito("popolaTabellaAttivita", parametri, flag);
             
    }
    
    public Object popolaTabellaLog(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "verifica";
        return  checkEsito("popolaTabellaLog", parametri, flag);
             
    }

    
    public Object getListaJournaling(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "verifica";
        return checkEsito("getListaJournaling", parametri, flag);
             
    }
    
    public Object showInfoInterventoCompleto(ArrayList<ArrayList<Object>> parametri) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, Throwable,UIFeedbackException {
        String flag = "verifica";
        return checkEsito("showInfoInterventoCompleto", parametri, flag);
             
    }
    
    
    
    
  //************SWITCH ESITO*************// 
    
   private Object checkEsito(String key, ArrayList<ArrayList<Object>> parametri, String flag) throws SecurityException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException, Throwable,UIFeedbackException {

        Object esito = null;
        switch (flag) {
            case "infermiere":
                esito = GestioneInfermieri.delegate(key, parametri);
                break;
            case "paziente":
                esito = GestionePazienti.delegate(key, parametri);
                break;
            case "intervento":
                esito = GestioneInterventi.delegate(key, parametri);
                break;
            case "patologia":
            	esito = GestionePatologie.delegate(key, parametri);
                break;
            case "pianificazione":
            	esito = GestionePianificazione.delegate(key, parametri);
                break;
            case "verifica":
            	esito = GestioneVerificaDati.delegate(key, parametri);
                break;
        }
        return esito;
    }
   

    @Override
    protected InputStream getResourcePath() {
		InputStream is = this.getClass().getResourceAsStream(
				"/adisys/server/frontEnd/controller/ConsultAC.xml");
		return is;
	}
}