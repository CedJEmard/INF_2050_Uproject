import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class Statistiques {

    protected static long nbDeclarationsTraitees;
    protected static long[] nbDeclarationsCompletes = {0, 0, 0, 0, 0};
    protected static long nbDeclarationsIncompletesOuInvalides;
    protected static long[] nbDeclarationsValidesEtIncompletes = {0, 0, 0, 0, 0};
    protected static long[] nbDeclarationParSexe = {0, 0, 0};
    protected static long nbActivitesDansDeclaration;
    protected static long[] nbActiviteParCategorie = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    protected static long nbDeclarationNumPermisInvalide;
    protected static boolean dejaComptee = false;

    public static long getNbDeclarationsTraitees() {return nbDeclarationsTraitees;}

    public static long getNbDeclarationsCompletes() {
        int somme = 0;
        for(int i=0;i<nbDeclarationsCompletes.length;i++){somme += nbDeclarationsCompletes[i];}
        return somme;
    }

    public static long getNbDeclarationsCompletesArchitectes(){return nbDeclarationsCompletes[1];}
    public static long getNbDeclarationsCompletesGeologues(){return nbDeclarationsCompletes[2];}
    public static long getNbDeclarationsCompletesPsycologues(){return nbDeclarationsCompletes[3];}
    public static long getNbDeclarationsCompletesPodiatres(){return nbDeclarationsCompletes[4];}

    public static long getNbDeclarationsIncompletesOuInvalides(){return nbDeclarationsIncompletesOuInvalides;}

    public static long getNbDeclarationsValidesEtIncompletesArchitectes(){return nbDeclarationsValidesEtIncompletes[1];}
    public static long getNbDeclarationsValidesEtIncompletesGeologues(){return   nbDeclarationsValidesEtIncompletes[2];}
    public static long getNbDeclarationsValidesEtIncompletesPsycologues(){return nbDeclarationsValidesEtIncompletes[3];}
    public static long getNbDeclarationsValidesEtIncompletesPodiatres(){return   nbDeclarationsValidesEtIncompletes[4];}

    public static long getNbDeclarationParHomme(){return nbDeclarationParSexe[1];}
    public static long getNbDeclarationParFemme(){return nbDeclarationParSexe[2];}
    public static long getNbDeclarationParSexeInconnu(){return nbDeclarationParSexe[0];}

    public static long getNbActivitesDansDeclaration() {
        for(int i=0;i<nbActiviteParCategorie.length;i++){nbActivitesDansDeclaration += nbActiviteParCategorie[i];}
        return nbActivitesDansDeclaration;
    }

    public static long getNbCours(){return nbActiviteParCategorie[0];}
    public static long getNbSeminaire(){return nbActiviteParCategorie[9];}
    public static long getNbGroupeDeDiscussion(){return nbActiviteParCategorie[1];}
    public static long getNbProjetDeRecherche(){return nbActiviteParCategorie[2];}
    public static long getNbConference(){return nbActiviteParCategorie[3];}
    public static long getNbPrésentation(){return nbActiviteParCategorie[4];}
    public static long getNbRedactionProfessionnelle(){return nbActiviteParCategorie[5];}
    public static long getNbAtelier(){return nbActiviteParCategorie[6];}
    public static long getNbColloque(){return nbActiviteParCategorie[7];}
    public static long getNbLectureDirigee(){return nbActiviteParCategorie[8];}

    public static long getNbDeclarationNumPermisInvalide() {return nbDeclarationNumPermisInvalide;}

    public static void setNbDeclarationsCompletes(int i, long valeur) {Statistiques.nbDeclarationsCompletes[i] = valeur;}
    public static void setNbDeclarationsValidesEtIncompletes(int i, long valeur) {Statistiques.nbDeclarationsValidesEtIncompletes[i] = valeur;}
    public static void setNbDeclarationParSexe(int i, long valeur) {Statistiques.nbDeclarationParSexe[i] = valeur;}
    public static void setNbActiviteParCategorie(int i, long valeur) {Statistiques.nbActiviteParCategorie[i] = valeur;}

    public static void incrementerNbDeclarationsCompletes(int i) {Statistiques.nbDeclarationsCompletes[i] ++;}
    public static void incrementerNbDeclarationsValidesEtIncompletes(int i) {Statistiques.nbDeclarationsValidesEtIncompletes[i] ++;}
    public static void incrementerNbDeclarationParSexe(int i) {Statistiques.nbDeclarationParSexe[i] ++;}
    public static void incrementerNbActiviteParCategorie(int i) {Statistiques.nbActiviteParCategorie[i] ++;}



    public static void reinitialiserStatistiques(){
        nbDeclarationsTraitees = 0;
        nbDeclarationsIncompletesOuInvalides = 0;
        for(int i=0; i<nbDeclarationsCompletes.length; i++){setNbDeclarationsCompletes(i,0);;}
        for(int i=0; i<nbDeclarationsValidesEtIncompletes.length; i++){setNbDeclarationsValidesEtIncompletes(i,0);}
        for(int i=0; i<nbDeclarationParSexe.length; i++){setNbDeclarationParSexe(i,0);}
        nbActivitesDansDeclaration = 0;
        for(int i=0; i<nbActiviteParCategorie.length; i++){setNbActiviteParCategorie(i,0);}
        nbDeclarationNumPermisInvalide = 0;
    }

    public static void afficherStatistiques(String fichierStatistique){
        try{
        Object parser = new JSONParser().parse(new BufferedReader(new InputStreamReader(new FileInputStream(fichierStatistique), "UTF-8")));
        JSONObject statistiquesJSON = (JSONObject) parser;
            String statistiquesConsole = "Voici les statistiques à date :\n"
            +  afficherStatistiquesDeclaration(statistiquesJSON)
            +  afficherStatistiquesSexe(statistiquesJSON)
            +  afficherStatistiquesActivites(statistiquesJSON)
            +  afficherStatistiquesOrdre(statistiquesJSON);
            System.out.println(statistiquesConsole);
        } catch (IOException e){}catch (ParseException e){}
    }

    public static String afficherStatistiquesDeclaration(JSONObject statistiques){
        return    "Nombre total de déclarations traitées: " + statistiques.get("Nombre total de déclarations traitées") + "\n"
                + "Nombre total de déclarations complètes: " + statistiques.get("Nombre total de déclarations complètes") + "\n"
                + "Nombre total de déclarations incomplètes ou invalides: " + statistiques.get("Nombre total de déclarations incomplètes ou invalides") + "\n"
                + "Nombre total de déclarations soumises avec un numéro de permis invalide: " + statistiques.get("Nombre total de déclarations soumises avec un numéro de permis invalide") + "\n";
    }
    public static String afficherStatistiquesSexe(JSONObject statistiques){
        return    "Nombre total de déclarations faites par des hommes: " + statistiques.get("Nombre total de déclarations faites par des hommes") + "\n"
                + "Nombre total de déclarations faites par des femmes: " + statistiques.get("Nombre total de déclarations faites par des femmes") + "\n"
                + "Nombre total de déclarations faites par des gens de sexe inconnu: " + statistiques.get("Nombre total de déclarations faites par des gens de sexe inconnu") + "\n";
    }
    public static String afficherStatistiquesOrdre(JSONObject statistiques){
        return    "Nombre total de déclarations valides et complètes par architectes: " + statistiques.get("Nombre total de déclarations valides et complètes par architectes") + "\n"
                + "Nombre total de déclarations valides et complètes par géologues: " + statistiques.get("Nombre total de déclarations valides et complètes par géologues") + "\n"
                + "Nombre total de déclarations valides et complètes par psychologues: " + statistiques.get("Nombre total de déclarations valides et complètes par psychologues") + "\n"
                + "Nombre total de déclarations valides et complètes par podiatres: " + statistiques.get("Nombre total de déclarations valides et complètes par podiatres") + "\n"
                + "Nombre total de déclarations valides et incomplètes par architectes: " + statistiques.get("Nombre total de déclarations valides et incomplètes par architectes") + "\n"
                + "Nombre total de déclarations valides et incomplètes par géologue: " + statistiques.get("Nombre total de déclarations valides et incomplètes par géologues") + "\n"
                + "Nombre total de déclarations valides et incomplètes par psychologues: " + statistiques.get("Nombre total de déclarations valides et incomplètes par psychologues") + "\n"
                + "Nombre total de déclarations valides et incomplètes par podiatres: " + statistiques.get("Nombre total de déclarations valides et incomplètes par podiatres") + "\n";
    }
    public static String afficherStatistiquesActivites(JSONObject statistiques){
        return    "Nombre total d'activités dans les déclarations: " + statistiques.get("Nombre total d'activités dans les déclarations") + "\n"
                + "Nombre de Cours: " + statistiques.get("Nombre de Cours") + "\n"
                + "Nombre de Séminaires: " + statistiques.get("Nombre de Séminaires") + "\n"
                + "Nombre de Groupes de discussion: " + statistiques.get("Nombre de Groupes de discussion") + "\n"
                + "Nombre de Projets de recherche: " + statistiques.get("Nombre de Projets de recherche") + "\n"
                + "Nombre de Colloques: " + statistiques.get("Nombre de Colloques") + "\n"
                + "Nombre de Lectures dirigées: " + statistiques.get("Nombre de Lectures dirigées") + "\n"
                + "Nombre de Conférences: " + statistiques.get("Nombre de Conférences") + "\n"
                + "Nombre d' Ateliers: " + statistiques.get("Nombre d' Ateliers") + "\n"
                + "Nombre de Redactions professionnelle: " + statistiques.get("Nombre de Redactions professionnelle") + "\n"
                + "Nombre de Présentations: " + statistiques.get("Nombre de Présentations") + "\n";
    }

    public static void lireStatistiquesDeclaration(JSONObject statistiques){
        nbDeclarationNumPermisInvalide = (long) statistiques.get("Nombre total de déclarations soumises avec un numéro de permis invalide");
        nbDeclarationsTraitees = (long) statistiques.get("Nombre total de déclarations traitées");
    }
    public static void lireStatistiquesSexe(JSONObject statistiques){
        setNbDeclarationParSexe(0, (Long) statistiques.get("Nombre total de déclarations faites par des hommes"));
        setNbDeclarationParSexe(1, (Long) statistiques.get("Nombre total de déclarations faites par des femmes"));
        setNbDeclarationParSexe(2, (Long) statistiques.get("Nombre total de déclarations faites par des gens de sexe inconnu"));
    }
    public static void lireStatistiquesOrdre(JSONObject statistiques){
        setNbDeclarationsCompletes(1,(Long)statistiques.get("Nombre total de déclarations valides et complètes par architectes"));
        setNbDeclarationsCompletes(2,(Long)statistiques.get("Nombre total de déclarations valides et complètes par géologues"));
        setNbDeclarationsCompletes(3,(Long)statistiques.get("Nombre total de déclarations valides et complètes par psychologues"));
        setNbDeclarationsCompletes(4,(Long)statistiques.get("Nombre total de déclarations valides et complètes par podiatres"));
        setNbDeclarationsValidesEtIncompletes(1,(Long) statistiques.get("Nombre total de déclarations valides et incomplètes par architectes"));
        setNbDeclarationsValidesEtIncompletes(2,(Long) statistiques.get("Nombre total de déclarations valides et incomplètes par géologues"));
        setNbDeclarationsValidesEtIncompletes(3,(Long) statistiques.get("Nombre total de déclarations valides et incomplètes par psychologues"));
        setNbDeclarationsValidesEtIncompletes(4,(Long) statistiques.get("Nombre total de déclarations valides et incomplètes par podiatres"));
    }
    public static void lireStatistiquesActivites(JSONObject statistiques) {
        setNbActiviteParCategorie(0,(Long)  statistiques.get("Nombre de Cours"));
        setNbActiviteParCategorie(9,(Long)  statistiques.get("Nombre de Séminaires"));
        setNbActiviteParCategorie(1,(Long)  statistiques.get("Nombre de Groupes de discussion"));
        setNbActiviteParCategorie(2,(Long)  statistiques.get("Nombre de Projets de recherche"));
        setNbActiviteParCategorie(3,(Long)  statistiques.get("Nombre de Colloques"));
        setNbActiviteParCategorie(4,(Long)  statistiques.get("Nombre de Lectures dirigées"));
        setNbActiviteParCategorie(5,(Long)  statistiques.get("Nombre de Conférences"));
        setNbActiviteParCategorie(6,(Long)  statistiques.get("Nombre d' Ateliers"));
        setNbActiviteParCategorie(7,(Long)  statistiques.get("Nombre de Redactions professionnelle"));
        setNbActiviteParCategorie(8,(Long)  statistiques.get("Nombre de Présentations"));
    }

    public static void lireStatistiques(String fichierStatistique){
        try{
            Object parser = new JSONParser().parse(new BufferedReader(new InputStreamReader(new FileInputStream(fichierStatistique), "UTF-8")));
            JSONObject statistiquesJSON = (JSONObject) parser;
            lireStatistiquesDeclaration(statistiquesJSON);
            lireStatistiquesSexe(statistiquesJSON);
            lireStatistiquesOrdre(statistiquesJSON);
            lireStatistiquesActivites(statistiquesJSON);
        } catch (IOException e){}catch (ParseException e){}

    }

    public static void incrementerDeclarationIncompletesOuInvalides(){
        if(!dejaComptee){
            reinitialiserStatistiques();
            lireStatistiques("statistiques.json");
            nbDeclarationsTraitees++;
            nbDeclarationsIncompletesOuInvalides++;
            dejaComptee=true;
        }
    }
}
