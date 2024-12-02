public class Geologue extends Individu{
    protected static final String HEURES_COURS_GEO = "25 heures de cours minimum requises pour Geologue";
    protected static final String HEURES_GDD_GEO = "1 heures de groupe de discussion minimum requises pour Geologue";
    protected static final String HEURES_PDR_GEO = "3 heures de projet de recherche minimum requises pour Geologue";

    protected static final String VERIF_CYCLE ="Cycle invalide. Le cycle supporté pour les géologues est \"2018-2021\". Le cycle est incomplet";
    protected static final String VERIF_NUMERO_PERMIS="Le fichier d'entrée est invalide : le numéro de permis des géologues doit être composé de 2 lettres suivies de 4 chiffres. La première lettre du numéro de permis correspond à la première lettre du nom du membre en majuscule. La deuxième lettre du numéro de permis correspond à la première lettre du prénom du membre en majuscule. Exemple : \"BJ3822\", le cycle est incomplet";

    public Geologue(String cycle) {
        super(cycle);
        this.totalHeureRequiseCycle = 55;
        this.dateMinCycle = "2018-06-01"; this.dateMaxCycle = "2021-06-01";

        this.minimumHeureCours = 22;
        this.minimumHeureProjetDeRecherche = 3;
        this.minimumHeureGroupeDeDiscussion = 1;
    }

    public Geologue(){
        super();
    }

    @Override
    protected boolean verifierCycle(String cycle) {
        if(!cycle.equals("2018-2021")){Declaration.resultats.stopperExecution(VERIF_CYCLE);return false;}
        return true;
    }

    protected static boolean verifierDeuxLettresGeologue(String chaine, String nom, String prenom){
        if(chaine.length()<2){return false;}
        if(nom.charAt(0) == chaine.charAt(0) && prenom.charAt(0)==chaine.charAt(1)){return true;}
        return false;
    }

    protected boolean verifierNumeroDePermis(String numeroDePermis, String nom, String prenom){
        if(verifierDeuxLettresGeologue(numeroDePermis, nom, prenom)
                && Declaration.verifierSiChaineSuivieDeQuatreChiffre(numeroDePermis,1)){return true;}
        Declaration.resultats.stopperExecution(VERIF_NUMERO_PERMIS);return false;
    }

    protected boolean verifierHeures(){
        int compteur = 0;
        if(Activite.getCompteurHeuresActivite(0) < 22){envoyerErreur(HEURES_COURS_GEO); compteur++;}
        if(Activite.getCompteurHeuresActivite(1) < 1){envoyerErreur(HEURES_GDD_GEO); compteur++;}
        if(Activite.getCompteurHeuresActivite(2) < 2){envoyerErreur(HEURES_PDR_GEO); compteur++;}
        if(!verifierTotalHeuresCycle() | compteur > 0){return false;}
        return true;
    }

}
