public class Individu implements IErreurs {
    protected String cycle;
    protected String dateMinCycle;
    protected String dateMaxCycle;
    protected long totalHeureRequiseCycle;

    protected long minimumHeureCours; //0
    protected long minimumHeureGroupeDeDiscussion; //1
    protected long minimumHeureProjetDeRecherche; //2
    protected long minimumHeureFormationContinue; //60
    
    protected long maximumHeureConference; //3
    protected long maximumHeureGroupeDeDiscussion;
    protected long maximumHeurePresentation; //4
    protected long maximumHeureProjetDeRecherche;
    protected long maximumHeureRedactionProfessionnelle; //5

    public Individu(String cycle) {this.cycle = cycle;}

    public Individu() {

    }

    protected boolean verifierCycle(String cycle){
        return false;
    }

    protected boolean verifierNumeroDePermis(String numeroDePermis, String nom, String prenom){
        return false;
    }

    protected boolean verifierHeures(){
        return false;
    };

    protected boolean verifierTotalHeuresCycle() {
        Activite.sommerTotalHeuresCycle();
        if(Activite.compteurTotalHeuresCycle < this.totalHeureRequiseCycle){
            Declaration.heuresManquantesCycle = this.totalHeureRequiseCycle - Activite.compteurTotalHeuresCycle;
            envoyerErreur(Declaration.HEURE_FORMATION + Declaration.heuresManquantesCycle);
            return false;
        }
        return true;
    }

    @Override
    public void envoyerErreur(String messageErreur) {Declaration.resultats.ajouterUneErreur(messageErreur);}
}
