import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cours extends Activite implements IErreurs{
    protected int index = 0;

    public Cours(String description, String categorie, long heures, String date){
        super(description, categorie, heures, date);
        Statistiques.incrementerNbActiviteParCategorie(index);
        DATE_INVALIDE = "Date hors de l'intervalle pour "+ this.description + " - Activité ignorée";
        FORMAT_DATE = "La date de "+ this.description +" doit être au format YYYY-mm-dd - Activité ignorée";
        EXISTANCE_DATE = "La date de "+ this.description +" est n'existe pas ";
        DESCRIPTION_INVALIDE = "Fichier invalide, cycle incomplet : la description de " + this.description + " doit avoir un minimum de 21 caractères";
        HEURE_INVALIDE = "Fichier invalide, heures pour l'Activité: "+ this.description +" doivent être des entiers positif";
        HEURES_EN_TROP = 10 + " heures prises en compte pour l'Activité: "+ this.description + " (10 heures max sur une journée)";
        verifierLesHeuresDeclarees(heures);
        super.validerActivite(index);
    }

    public Cours(long heures){
        super(heures);
    }

    @Override
    protected boolean validerFormatHeure() {
        if (this.heures < 1) {
            Statistiques.incrementerDeclarationIncompletesOuInvalides();
            Declaration.resultats.stopperExecution(HEURE_INVALIDE);
            return false;
        }
        compteurHeuresRequisesArchitecteFormation += this.heures; //pour avoir les 17h requises pour l'architecte
        return true;
    }


    @Override
    public void envoyerErreur(String messageErreur) {Declaration.resultats.ajouterUneErreur(messageErreur);}

}
