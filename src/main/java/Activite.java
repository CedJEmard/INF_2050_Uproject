import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class Activite implements IErreurs{
    protected String DATE_INVALIDE ;
    protected String FORMAT_DATE ;
    protected String EXISTANCE_DATE ;
    protected String DESCRIPTION_INVALIDE ;
    protected String HEURE_INVALIDE ;
    protected String HEURES_EN_TROP ;

    protected final static String ISO8601 = "yyyy-MM-dd";

    protected String description;
    protected String categorie;
    protected long heures;
    protected String date;

    protected final Log logger = LogFactory.getLog(Activite.class);
    //protected static int finExecution = 0 ;

    //compteurs
    protected static long compteurTotalHeuresCycle;
    protected static long compteurHeuresRequisesArchitecteFormation; //17

    protected static long[] compteurHeuresActivite = {0,0,0,0,0,0,0,0,0,0,0};

    public static long getCompteurHeuresActivite(int i){return compteurHeuresActivite[i];}
    public static void setCompteurHeuresActivite(long heures,int i){compteurHeuresActivite[i] += heures;}
    public static void soustraireCompteurHeuresActivite(long heures,int i){compteurHeuresActivite[i] -= heures;}

    protected static void sommerTotalHeuresCycle(){
        for(int i = 0; i<10 ;i++){compteurTotalHeuresCycle += getCompteurHeuresActivite(i);}
    }

    protected void verifierLesHeuresDeclarees(long heures){
        if(heures > 10) {this.heures =10; envoyerErreur(HEURES_EN_TROP);}else{this.heures = heures;}
    }

    public Activite(String description, String categorie, long heures, String date){
        this.description = description;
        this.categorie = categorie;
        this.heures = heures;
        this.date = date;
    }

    public Activite(long heures){
        this.heures = heures;
    }

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String getCategorie() {return categorie;}
    public void setCategorie(String categorie) {this.categorie = categorie;}
    public long getHeures() {return heures;}
    public void setHeures(long heure) {this.heures = heure;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    protected abstract boolean validerFormatHeure();

    protected static boolean validerFormatDate(String date){
        SimpleDateFormat iso8601 = new SimpleDateFormat(ISO8601);
        try {
            iso8601.parse(date);
            return true;
        } catch (ParseException e) {return false;}
    }

    protected static boolean verifierExistanceDate(String date){
        try {
            SimpleDateFormat iso8601 = new SimpleDateFormat(ISO8601);
            iso8601.setLenient(false);
            iso8601.parse(date);
            return true;
        } catch (ParseException e) {Statistiques.incrementerDeclarationIncompletesOuInvalides();return false;}
    }

    protected static boolean verifierIntervalleDate(String date,Individu individu){
        String dateMin = individu.dateMinCycle;
        String dateMax = individu.dateMaxCycle;
        SimpleDateFormat iso8601 = new SimpleDateFormat(ISO8601);
        try {
            if (iso8601.parse(date).before(iso8601.parse(dateMin)) || iso8601.parse(date).after(iso8601.parse(dateMax))) {Statistiques.incrementerDeclarationIncompletesOuInvalides();return false;}
        } catch (ParseException e) {return false;}
        return true;
    }

    protected boolean validerDate(Individu individu){
        if(verifierExistanceDate(this.date) && validerFormatDate(this.date) && verifierIntervalleDate(this.date,individu)) {
            return true;
        }
        if(!validerFormatDate(this.date)) {envoyerErreur(FORMAT_DATE);}
        if(!verifierIntervalleDate(this.date,individu)) {envoyerErreur(DATE_INVALIDE);}
        if(!verifierExistanceDate(this.date)) {envoyerErreur(EXISTANCE_DATE);}
        return false;
    }

    protected boolean validerDescription(){
        if(this.description.length() < 21){
            Statistiques.incrementerDeclarationIncompletesOuInvalides();
            Declaration.resultats.stopperExecution(DESCRIPTION_INVALIDE);
            return false;
        }
        return true;
    }

    protected boolean validerActivite(int index){
        if (this.validerDate(Declaration.individu) && this.validerFormatHeure() && this.validerDescription()) {
            setCompteurHeuresActivite(this.heures,index);//additionne l'heure au total cycle la fin du case
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Activité{" +
                "description='" + description + '\'' +
                ", categorie='" + categorie + '\'' +
                ", heure=" + heures +
                ", date='" + date + '\'' +
                '}';
    }
}
