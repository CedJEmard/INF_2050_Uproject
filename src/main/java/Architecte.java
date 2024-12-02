public class Architecte extends Individu{
    protected static final String VERIF_CYCLE = "Cycle invalide. Les cycles supportés pour les architectes sont \"2020-2022\", \"2018-2020\" ou \"2016-2018\". Le cycle est incomplet";
    protected static final String VERIF_NUMERO_PERMIS= "Le fichier d'entrée est invalide : le numéro de permis des architectes doit être composé d'une lettre suivie de 4 chiffres. La lettre peut être un A ou un T en majuscule. Exemple : T3443, le cycle est incomplet";

    public Architecte(String cycle) {
        super(cycle);
        if(cycle.equals("2018-2020")){
            this.totalHeureRequiseCycle =42; this.dateMinCycle = "2018-04-01"; this.dateMaxCycle = "2020-04-01"; }
        if(cycle.equals("2016-2018")){
            this.totalHeureRequiseCycle =42; this.dateMinCycle = "2016-04-01"; this.dateMaxCycle = "2018-07-01"; }
        if(cycle.equals("2020-2022")){
            this.totalHeureRequiseCycle =40; this.dateMinCycle = "2020-04-01"; this.dateMaxCycle = "2022-04-01"; }

        maximumHeurePresentation = 23;
        maximumHeureProjetDeRecherche = 23;
        maximumHeureRedactionProfessionnelle = 17;
        maximumHeureGroupeDeDiscussion = 17;
    }

    public Architecte(){
        super();
    }

    @Override
    protected boolean verifierCycle(String cycle) {
        if(cycle.equals("2018-2020") | cycle.equals("2016-2018") | cycle.equals("2020-2022")){return true;}
        Declaration.resultats.stopperExecution(VERIF_CYCLE);return false;
    }
    protected static boolean verifierPremiereLettreArchitecte(String chaine){
        if(chaine.length()==0){return false;}
        char[] lettresPermises = {'A', 'T'};
        for (char lettre : lettresPermises){
            if(chaine.charAt(0)==lettre) {return true;}
        }
        return false;
    }
    @Override
    protected boolean verifierNumeroDePermis(String numeroDePermis, String nom, String prenom) {
        if(verifierPremiereLettreArchitecte(numeroDePermis) && Declaration.verifierSiChaineSuivieDeQuatreChiffre(numeroDePermis,0)){return true;}
        Declaration.resultats.stopperExecution(VERIF_NUMERO_PERMIS);return false;
    }

    protected boolean verifierHeuresRequisesFormation(){
        if (Activite.compteurHeuresRequisesArchitecteFormation < 17) {
            Declaration.heuresManquantesFormation = 17 - Activite.compteurHeuresRequisesArchitecteFormation;
            envoyerErreur( Declaration.heuresManquantesFormation + Declaration.HEURE_CUMUL);
            return false;
        }return true;
    }

    protected boolean verifierHeures(){
        if(Activite.getCompteurHeuresActivite(1) > 17){Activite.compteurHeuresActivite[1] = 17;}
        if(Activite.getCompteurHeuresActivite(2) > 23){Activite.compteurHeuresActivite[2] = 23;}
        if(Activite.getCompteurHeuresActivite(4) > 23){Activite.compteurHeuresActivite[4] = 23;}
        if(Activite.getCompteurHeuresActivite(5) > 17){Activite.compteurHeuresActivite[5] = 17;}
        if(!verifierTotalHeuresCycle() | !verifierHeuresRequisesFormation()){return false;}
        return true;
    }


}
