import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class ActiviteTest {
    String descriptionVal="L'Activité Colloque avec plus de 21 caractères";
    String descriptionInval="";
    String dateVal="2021-03-20";
    String dateHorsIntervale ="2018-04-19";
    String dateInval="200102-30";
    String categorie="colloque";
    long heures=5;
    Individu individu = new Architecte("2020-2022");

    int index=0;
    Activite activiteVal = new Activite(descriptionVal, categorie, heures, dateVal) {
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };

    Activite activiteInval = new Activite(descriptionInval, categorie, heures, dateInval) {
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };

    Activite activiteHeuresEnTrop = new Activite(dateInval,categorie,24,dateVal) {
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };
    @BeforeEach
    public void setUp(){
        activiteHeuresEnTrop.setHeures(24);
    }

    @Test
    @DisplayName("Valider format de la date valide")
    public void validerFormatDatePos(){
        assertTrue(Activite.validerFormatDate(dateVal));
    }

    @Test
    @DisplayName("Valider format de la date invalide")
    public void validerFormatDateNeg(){
        assertFalse(Activite.validerFormatDate(dateInval));
    }

    @Test
    @DisplayName("La date existe")
    public void verifierExistenceDatePos(){
        assertTrue(Activite.verifierExistanceDate(dateVal));
    }

    @Test
    @DisplayName("La date n'existe pas")
    public void verifierExistenceDateNeg(){
        assertFalse(Activite.verifierExistanceDate(dateInval));
    }

    @Test
    @DisplayName("Verification de l'intervalle valide de la date")
    public void verifierIntervalleDatePos(){assertTrue(Activite.verifierIntervalleDate(activiteVal.getDate(),individu));}

    @Test
    @DisplayName("Verification de l'intervalle invalide de la date")
    public void verifierIntervalleDateNeg(){assertFalse(Activite.verifierIntervalleDate(dateHorsIntervale,individu));}

    @Test
    @DisplayName("Validation: La description valide")
    public void validerDescriptionPos(){assertTrue(activiteVal.validerDescription());}

    @Test
    @DisplayName("Validation: La description invalide")
    public void validerDescriptionNeg(){assertFalse(activiteInval.validerDescription());}

    @Test
    @DisplayName("Validation: La date valide")
    public void validerDatePos(){assertTrue(activiteVal.validerDate(individu));}

    @Test
    @DisplayName("Validation: La date invalide")
    public void validerDateNeg(){assertFalse(activiteInval.validerDate(individu));}

    @Test
    public void testerLesHeuresDeclarareeFalse(){
        activiteHeuresEnTrop.verifierLesHeuresDeclarees(activiteHeuresEnTrop.heures);
        assertEquals(10,activiteHeuresEnTrop.getHeures());
    }

    @Test
    public void testerLesHeuresDeclarareeTrue(){
        activiteVal.verifierLesHeuresDeclarees(activiteVal.heures);
        assertEquals(activiteVal.getHeures(),activiteVal.getHeures());
    }

    @Test
    public void testToString(){
        String expected = "Activité{" +
                "description='" + activiteVal.getDescription() + '\'' +
                ", categorie='" + activiteVal.getCategorie() + '\'' +
                ", heure=" + activiteVal.getHeures() +
                ", date='" + activiteVal.getDate() + '\'' +
                '}';
        assertEquals(expected,activiteVal.toString());
    }

}