import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ParserJSON {
public static final String CLASS_CAST_EXCEPTION = "Le fichier d'entrée est invalide, veuillez verifier que vos champs ont les bons types :\n"
        +"- le numéro de permis, le nom, le prénom, le cycle, l'ordre, doivent être une chaine de caractère.\n"
        +"- les heures doivent être des entiers positifs";

static public ArrayList<Activite> ListeActivite = new ArrayList<Activite>();
    public static void traiterActivite(int i, JSONArray arrayActivite, long heures){
        JSONObject activites = (JSONObject) arrayActivite.get(i);
        String categorie = activites.get("categorie").toString(), date = activites.get("date").toString(), description = activites.get("description").toString();
        try{heures = (long) activites.get("heures");}catch (ClassCastException e){ heures = 0;}
        switch (categorie){
            case "cours": ListeActivite.add(new Cours(description,categorie,heures,date));break;
            case "atelier": ListeActivite.add(new Atelier(description,categorie,heures,date));break;
            case "séminaire": ListeActivite.add(new Seminaire(description,categorie,heures,date));break;
            case "colloque": ListeActivite.add(new Colloque(description,categorie,heures,date));break;
            case "conférence": ListeActivite.add(new Conference(description,categorie,heures,date));break;
            case "lecture dirigée": ListeActivite.add(new LectureDirigee(description,categorie,heures,date));break;
            case "présentation": ListeActivite.add(new Presentation(description,categorie,heures,date));break;
            case "groupe de discussion": ListeActivite.add(new GroupeDeDiscussion(description,categorie,heures,date));break;
            case "projet de recherche": ListeActivite.add(new ProjetDeRecherche(description,categorie,heures,date));break;
            case "rédaction professionnelle": ListeActivite.add(new RedactionProfessionnel(description,categorie,heures,date));break;
            default : ListeActivite.add(new ActiviteNonReconnue(description,categorie,heures,date));break;
        }}

    public static boolean parser(String fichierEntree) throws IOException, ParseException, java.text.ParseException{
        try{
            Object parser = new JSONParser().parse(new BufferedReader(new InputStreamReader(new FileInputStream(fichierEntree), "UTF-8")));
            JSONObject declarationJSON = (JSONObject) parser;long heures = 0;
            Declaration declaration = new Declaration(declarationJSON);
            if(!declaration.verifierDeclaration()){declaration.getResultats();return false;}
            JSONArray arrayActivite = (JSONArray) declarationJSON.get("activites");
            for (int i = 0; i < arrayActivite.toArray().length; i++) {traiterActivite(i,arrayActivite,heures);}
            soustraireHeureSupplementaireJournaliere(ListeActivite);
            declaration.getResultats();
            return true;
        }catch (ClassCastException e){envoyerErreurStatic(CLASS_CAST_EXCEPTION); System.out.println(CLASS_CAST_EXCEPTION); return false;}
    }

    public static void soustraireHeureSupplementaireJournaliere(ArrayList<Activite> ListeActivite){
        additionnerHeure(ListerActiviteParDate(ListeActivite));}

    public static void additionnerHeure(ArrayList<ArrayList<Activite>> listeDate){
        int heures=0;long diffHeures = 0;int indexSurplus = -1;String messageDepassement ="Maximum d'heure dépassé pour la journée";
        for(int i =0; i<listeDate.size(); i++) {heures = 0;indexSurplus = -1;
            for(int j = 0; j<listeDate.get(i).size();j++) {
                heures += listeDate.get(i).get(j).getHeures();
                if (heures > 10) {
                    if (indexSurplus == -1) {
                        messageDepassement += " " + listeDate.get(i).get(j).getDate() + ": \n"; indexSurplus = j; diffHeures = heures - 10;
                        mettreAJourNombreHeureDeLActivite(listeDate.get(i).get(j), diffHeures);
                        messageDepassement += " - Pour " + listeDate.get(i).get(j).getDescription() + ", " + diffHeures + "heures ont été considérée(s) \n";}
                    if (j > indexSurplus) {
                        diffHeures = listeDate.get(i).get(j).getHeures();
                        mettreAJourNombreHeureDeLActivite(listeDate.get(i).get(j), diffHeures);
                        messageDepassement += " - Pour " + listeDate.get(i).get(j).getDescription() + ", " + "0 heures ont été considérée(s) \n";}}}
            if(heures>10) {envoyerErreurStatic(messageDepassement);}messageDepassement = "Maximum d'heure dépassé pour la journée" ;}
    }
    public static void mettreAJourNombreHeureDeLActivite(Activite ActiviteActuel, long heures){
        if (0 == ActiviteActuel.getCategorie().compareTo("cours")){Activite.soustraireCompteurHeuresActivite(heures,0);}
        if (0 == ActiviteActuel.getCategorie().compareTo("séminaire")){Activite.soustraireCompteurHeuresActivite(heures,9);}
        if (0 == ActiviteActuel.getCategorie().compareTo("groupe de discussion")){Activite.soustraireCompteurHeuresActivite(heures,1);}
        if (0 == ActiviteActuel.getCategorie().compareTo("projet de recherche")){Activite.soustraireCompteurHeuresActivite(heures,2);}
        if (0 == ActiviteActuel.getCategorie().compareTo("conférence")){Activite.soustraireCompteurHeuresActivite(heures,3);}
        if (0 == ActiviteActuel.getCategorie().compareTo("présentation")){Activite.soustraireCompteurHeuresActivite(heures,4);}
        if (0 == ActiviteActuel.getCategorie().compareTo("rédaction professionnelle")){Activite.soustraireCompteurHeuresActivite(heures,5);}
        if (0 == ActiviteActuel.getCategorie().compareTo("atelier")){Activite.soustraireCompteurHeuresActivite(heures,6);}
        if (0 == ActiviteActuel.getCategorie().compareTo("colloque")){Activite.soustraireCompteurHeuresActivite(heures,7);}
        if (0 == ActiviteActuel.getCategorie().compareTo("lecture dirigée")){Activite.soustraireCompteurHeuresActivite(heures,8);}
      }

    public static ArrayList<ArrayList<Activite>> ListerActiviteParDate(ArrayList<Activite> ListeCategorie){
        ArrayList<ArrayList<Activite>> listeDate = new ArrayList<ArrayList<Activite>>();
            for (int j = 0; j < ListeCategorie.size(); j++) {
                int finalJ = j;
                if(!listeDate.contains((ArrayList<Activite>) ListeCategorie.stream().filter(s -> s.getDate().equals(ListeCategorie.get(finalJ).getDate())).collect(Collectors.toList()))) {
                    listeDate.add((ArrayList<Activite>) ListeCategorie.stream().filter(s -> s.getDate().equals(ListeCategorie.get(finalJ).getDate())).collect(Collectors.toList()));
                }
        }
        return listeDate;
    }


    public static void envoyerErreurStatic(String messageErreur){
        Declaration.resultats.ajouterUneErreur(messageErreur);
    }
}


