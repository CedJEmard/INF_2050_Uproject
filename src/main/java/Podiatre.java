public class Podiatre extends Individu{
    protected static final String HEURES_COURS_POD = "25 heures de cours minimum requises pour Podiatre";
    protected static final String HEURES_GDD_POD = "1 heures de groupe de discussion minimum requises pour Podiatre";
    protected static final String HEURES_PDR_POD = "3 heures de projet de recherche minimum requises pour Podiatre";

    protected static final String VERIF_CYCLE ="Cycle invalide. Le cycle supporté pour les podiatres est \"2018-2021\". Le cycle est incomplet\"";
    protected static final String VERIF_NUMERO_PERMIS="Le fichier d'entrée est invalide : le numéro de permis des podiatres doit être composé de 5 chiffres. Exemple : \"83453\", le cycle est incomplet";

    public Podiatre(String cycle) {
        super(cycle);
        this.totalHeureRequiseCycle = 60;
        this.dateMinCycle = "2018-06-01"; this.dateMaxCycle = "2021-06-01";

        this.minimumHeureCours = 22;
        this.minimumHeureProjetDeRecherche = 3;
        this.minimumHeureGroupeDeDiscussion = 1;
    }

    public Podiatre(){
        super();
    }

    @Override
    protected boolean verifierCycle(String cycle) {
        if(!cycle.equals("2018-2021")){Declaration.resultats.stopperExecution(VERIF_CYCLE);return false;}
        return true;
    }

    protected boolean verifierNumeroDePermis(String numeroDePermis, String nom, String prenom){
        if(numeroDePermis.length() != 5){Declaration.resultats.stopperExecution(VERIF_NUMERO_PERMIS);return false;}
        if(Declaration.verifierSiChaineCommenceParCinqChiffre(numeroDePermis)){return true;}
        Declaration.resultats.stopperExecution(VERIF_NUMERO_PERMIS);return false;
    }

    protected boolean verifierHeures(){
        int compteur = 0;
        if(Activite.getCompteurHeuresActivite(0) < 22){envoyerErreur(HEURES_COURS_POD); compteur++;}
        if(Activite.getCompteurHeuresActivite(1) < 1){envoyerErreur(HEURES_GDD_POD); compteur++;}
        if(Activite.getCompteurHeuresActivite(2) < 2){envoyerErreur(HEURES_PDR_POD); compteur++;}
        if(!verifierTotalHeuresCycle() | compteur > 0){return false;}
        return true;
    }

}
