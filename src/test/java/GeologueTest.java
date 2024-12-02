import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class GeologueTest {
    String cycleVal = "2018-2021";
    String cycleInval ="2015-2016";
    String chaine = "DS0098";
    String chaineInval = "DG0098";
    String chaineInvalDeux = "ds0095";
    String chaineInvalTrois = "GK0003";
    String chaineInvalQuatre = "GKU003";
    String nom = "Dogny";
    String prenom = "Serge";
    Geologue geologueVal = new Geologue(cycleVal);
    Geologue geologueInval= new Geologue(cycleInval);

    @BeforeEach
    public void setUp(){
        Activite.compteurTotalHeuresCycle =0;
    }

    /*Validation validationCycle()*/

    @Test
    @DisplayName("Validation: cycle valide")
    public void validationCyclePos(){
        assertTrue(geologueVal.verifierCycle(cycleVal));
    }

    @Test
    @DisplayName("Validation: cycle invalide")
    public void validationCycleNeg(){
        assertFalse(geologueInval.verifierCycle(cycleInval));
    }

    /*Validation verifierDeuxLettresGeologue()*/

    @Test
    @DisplayName("Validation: deux lettres Geologue valide")
    public void validationDeuxLettresGeologue(){assertTrue(geologueVal.verifierDeuxLettresGeologue(chaine, nom, prenom));}

    @Test
    @DisplayName("Validation: deux lettres Geologue invalides")
    public void validationDeuxLettresGeologueNeg(){assertFalse(geologueVal.verifierDeuxLettresGeologue(chaineInval, nom, prenom));}

    @Test
    @DisplayName("Validation: deux lettres Geologue invalides")
    public void validationDeuxLettresGeologueNegDeux(){assertFalse(geologueVal.verifierDeuxLettresGeologue(chaineInvalDeux, nom, prenom));}

    @Test
    @DisplayName("Validation: deux lettres Geologue invalides")
    public void validationDeuxLettresGeologueNegTrois(){assertFalse(geologueVal.verifierDeuxLettresGeologue(chaineInvalTrois, nom, prenom));}

    @Test
    @DisplayName("Validation: deux lettres Geologue invalides")
    public void validationDeuxLettresGeologueNegQuatre(){assertFalse(geologueVal.verifierDeuxLettresGeologue(chaineInvalQuatre, nom, prenom));}

    /*Validation verifierNumeroDePermis()*/
    @Test
    @DisplayName("Validation: numero de permis valide")
    public void validationNumeroDePermis(){assertTrue(geologueVal.verifierNumeroDePermis(chaine, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNeg(){assertFalse(geologueVal.verifierNumeroDePermis(chaineInvalTrois, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNegDeux(){assertFalse(geologueVal.verifierNumeroDePermis(chaineInvalQuatre, nom, prenom));}

    @Test
    public void testValiderHeuresFauxParDefaut(){
        Activite.compteurTotalHeuresCycle = 0;
        assertFalse(geologueVal.verifierHeures());
        Activite.compteurTotalHeuresCycle = 0;
    }

    @Test
    public void testValiderHeuresVrai(){
        Activite.compteurTotalHeuresCycle = 55;
        assertTrue(geologueVal.verifierTotalHeuresCycle());
        Activite.compteurTotalHeuresCycle = 0;
    }
}
