import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class WriterJSON {

    public static void ecrireResultat(String fichierSortie) throws  IOException {
        JSONObject resultat = new JSONObject();
        resultat.put("complet", Declaration.resultats.estComplet());
        resultat.put("erreurs", Declaration.resultats.getMesssagesErreurs());
        if(Declaration.resultats.estComplet()){resultat.put("Statut", "La déclaration à été complété avec succés.");}
        PrintWriter pw = new PrintWriter(fichierSortie,"UTF-8");
        pw.write(resultat.toJSONString());
        pw.flush();pw.close();
    }

   public static void ecrireStatistiques(String fichierStatistiques) throws FileNotFoundException, UnsupportedEncodingException {
        JSONObject statistiques = new JSONObject();
        PrintWriter pw = new PrintWriter(fichierStatistiques,"UTF-8");
        ecrireStatistiquesDeclarations(statistiques);
        ecrireStatistiquesSexe(statistiques);
        ecrireStatistiquesActivites(statistiques);
        ecrireStatistiquesParOrdre(statistiques);
        pw.write(statistiques.toJSONString());
        pw.flush();pw.close();
    }

    public static void ecrireStatistiquesDeclarations(JSONObject statistiques){
        statistiques.put("Nombre total de déclarations traitées",Statistiques.getNbDeclarationsTraitees());
        statistiques.put("Nombre total de déclarations complètes",Statistiques.getNbDeclarationsCompletes());
        statistiques.put("Nombre total de déclarations incomplètes ou invalides",Statistiques.getNbDeclarationsIncompletesOuInvalides());
        statistiques.put("Nombre total de déclarations soumises avec un numéro de permis invalide",Statistiques.getNbDeclarationNumPermisInvalide());
    }
    public static void ecrireStatistiquesSexe(JSONObject statistiques){
        statistiques.put("Nombre total de déclarations faites par des hommes",Statistiques.getNbDeclarationParHomme());
        statistiques.put("Nombre total de déclarations faites par des femmes",Statistiques.getNbDeclarationParFemme());
        statistiques.put("Nombre total de déclarations faites par des gens de sexe inconnu",Statistiques.getNbDeclarationParSexeInconnu());
    }
    public static void ecrireStatistiquesActivites(JSONObject statistiques){
        statistiques.put("Nombre total d'activités dans les déclarations",Statistiques.getNbActivitesDansDeclaration());
        statistiques.put("Nombre de Cours",Statistiques.getNbCours());
        statistiques.put("Nombre de Séminaires",Statistiques.getNbSeminaire());
        statistiques.put("Nombre de Groupes de discussion",Statistiques.getNbGroupeDeDiscussion());
        statistiques.put("Nombre de Projets de recherche",Statistiques.getNbProjetDeRecherche());
        statistiques.put("Nombre de Conférences", Statistiques.getNbConference());
        statistiques.put("Nombre de Colloques",Statistiques.getNbColloque());
        statistiques.put("Nombre de Lectures dirigées",Statistiques.getNbLectureDirigee());
        statistiques.put("Nombre d' Ateliers",Statistiques.getNbAtelier());
        statistiques.put("Nombre de Redactions professionnelle",Statistiques.getNbRedactionProfessionnelle());
        statistiques.put("Nombre de Présentations",Statistiques.getNbPrésentation());
    }
    public static void ecrireStatistiquesParOrdre(JSONObject statistiques){
        statistiques.put("Nombre total de déclarations valides et complètes par architectes",Statistiques.getNbDeclarationsCompletesArchitectes());
        statistiques.put("Nombre total de déclarations valides et complètes par géologues",Statistiques.getNbDeclarationsCompletesGeologues());
        statistiques.put("Nombre total de déclarations valides et complètes par psychologues",Statistiques.getNbDeclarationsCompletesPsycologues());
        statistiques.put("Nombre total de déclarations valides et complètes par podiatres",Statistiques.getNbDeclarationsCompletesPodiatres());
        statistiques.put("Nombre total de déclarations valides et incomplètes par architectes", Statistiques.getNbDeclarationsValidesEtIncompletesArchitectes());
        statistiques.put("Nombre total de déclarations valides et incomplètes par géologues",   Statistiques.getNbDeclarationsValidesEtIncompletesGeologues());
        statistiques.put("Nombre total de déclarations valides et incomplètes par psychologues",Statistiques.getNbDeclarationsValidesEtIncompletesPsycologues());
        statistiques.put("Nombre total de déclarations valides et incomplètes par podiatres",   Statistiques.getNbDeclarationsValidesEtIncompletesPodiatres());
    }
    
}
