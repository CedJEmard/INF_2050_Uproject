import java.io.IOException;

import org.json.simple.parser.*;

public class Main {
    public static final String AIDE_COMMANDE = "Commande non reconnue: \n"
            + "- Pour soumettre une déclaration : java -jar FormationContinue.jar declaration.json resultat.json avec des fichiers en JSON. \n"
            + "- Pour afficher des statistiques : java -jar  FormationContinue.jar -S \n"
            + "- Pour réinitialiser les statistiques : java -jar FormationContinue.jar -SR";

    public static final String AIDE_STATISTIQUES = "Argument invalide. Un appel avec 1 argument vous permets :\n"
            + "- D'afficher des statistiques : java -jar FormationContinue.jar -S\n"
            + "- De les réinitialiser : java -jar FormationContinue.jar -SR";

    public static final String AIDE_FICHIER_INTROUVABLES = " Le fichier de sortie ou le fichier d'entrée est introuvable.\n"
            +"Un appel avec deux arguments vous permets de soumettre une déclaration.\n"
            +"Assurez vous d'avoir correctement entrées vos fichiers et qu'ils sont au format JSON.\n"
            +"Exemple : java -jar FormationContinue.jar declaration.json resultat.json";

    public static final String FICHIER_INVALIDE = "Le fichier d'entrée est invalide (des champs sont vides ou ne respectent pas leurs types), le cycle est incomplet.\n"
            +"La déclaration est invalide.";

    protected static final String FICHIER_STATISTIQUES = "statistiques.json";

    public static void verifierNombreArguments(String[] args){
       int size = args.length;
       if(size == 0 || size > 2){System.out.println(AIDE_COMMANDE);}
       if(size == 1){String option = args[0]; gererLesStatistiques(option);}
       if(size == 2){String fichierEntree = args[0];String fichierSortie = args[1]; parserEtEcrire(fichierEntree,fichierSortie);}
    }

    public static void gererLesStatistiques(String option){
        if(option.equals("-S")){afficherStatistiques();}
        else if(option.equals("-SR")){reinitialiserStatistiques();}
        else{System.out.println(AIDE_STATISTIQUES);}
    }

    public static void afficherStatistiques(){
        Statistiques.lireStatistiques(FICHIER_STATISTIQUES);
        Statistiques.afficherStatistiques(FICHIER_STATISTIQUES);
    }

    public static void reinitialiserStatistiques(){
        try{
            Statistiques.reinitialiserStatistiques();
            WriterJSON.ecrireStatistiques(FICHIER_STATISTIQUES);
            System.out.println("Reinitialisation des statistiques faite !");
        }catch (IOException e){}
    }

    public static void parserEtEcrire(String fichierEntree, String fichierSortie){
        try {
            Statistiques.lireStatistiques(FICHIER_STATISTIQUES);
            Statistiques.nbDeclarationsTraitees++;
            ParserJSON.parser(fichierEntree);
            WriterJSON.ecrireResultat(fichierSortie);
            WriterJSON.ecrireStatistiques(FICHIER_STATISTIQUES);
        }
        catch(IOException e){System.out.println(AIDE_FICHIER_INTROUVABLES);}
        catch(ParseException | java.text.ParseException e){Statistiques.incrementerDeclarationIncompletesOuInvalides();System.out.println(FICHIER_INVALIDE);}
    }

    public static void main(String[] args){verifierNombreArguments(args);}
}

