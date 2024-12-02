public class Psychologue extends Individu {
    protected static final String HEURES_COURS_PSY = "22 heures de cours minimum requises pour Psychologue";

    protected static final String VERIF_CYCLE ="Cycle invalide. Le cycle supporté pour les psychologues est \"2018-2023\". Le cycle est incomplet";
    protected static final String VERIF_NUMERO_PERMIS="Le fichier d'entrée est invalide : le numéro de permis des psychologues doit être composé de 5 chiffres, d'un trait d'union et de 2 autres chiffres. Exemple : \"83723-34\", le cycle est incomplet";

    public Psychologue(String cycle) {
        super(cycle);
        this.totalHeureRequiseCycle = 90;
        this.dateMinCycle = "2018-01-01"; this.dateMaxCycle = "2023-01-01";

        this.minimumHeureCours = 25;
        this.maximumHeureConference= 15;
    }

    public Psychologue(){
        super();
    }

    @Override
    protected boolean verifierCycle(String cycle) {
        if(!cycle.equals("2018-2023")){Declaration.resultats.stopperExecution(VERIF_CYCLE);return false;}
        return true;
    }

    protected boolean verifierNumeroDePermis(String numeroDePermis, String nom, String prenom){
        if(numeroDePermis.length() != 8){Declaration.resultats.stopperExecution(VERIF_NUMERO_PERMIS);return false;}
        if(numeroDePermis.charAt(5) == '-'
                && Character.isDigit(numeroDePermis.charAt(6))
                && Character.isDigit(numeroDePermis.charAt(7))
                && Declaration.verifierSiChaineCommenceParCinqChiffre(numeroDePermis.substring(0,5))){
            return true;}
        Declaration.resultats.stopperExecution(VERIF_NUMERO_PERMIS);return false;
    }

    protected boolean verifierHeures(){
        int compteur = 0;
        if(Activite.getCompteurHeuresActivite(0) < 25){envoyerErreur(HEURES_COURS_PSY); compteur++;}
        if(Activite.getCompteurHeuresActivite(3) > 15){Activite.compteurHeuresActivite[3] = 15;}
        if(!verifierTotalHeuresCycle() | compteur >0){return false;}
        return true;
    }

}
