public class ActiviteNonReconnue extends Activite implements IErreurs{
    protected final String NON_PRIS_EN_CHARGE = "Activite non prise en charge : " + this.description ;
    protected int index = 10;
    public ActiviteNonReconnue(String description, String categorie, long heures, String date){
        super(description, categorie, heures, date);
        Statistiques.incrementerDeclarationIncompletesOuInvalides();
        super.validerActivite(index);
    }

    public ActiviteNonReconnue(long heures){
        super(heures);
    }

    @Override
    protected boolean validerFormatHeure() {
        this.heures = 0;
        envoyerErreur(NON_PRIS_EN_CHARGE);
        return false;
    }

    @Override
    public void envoyerErreur(String messageErreur) {
        Declaration.resultats.ajouterUneErreur(messageErreur);
    }
}
