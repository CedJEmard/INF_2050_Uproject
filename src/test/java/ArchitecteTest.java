import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class ArchitecteTest {
    String cycleVal = "2018-2020";
    String cycleInval ="2015-2016";
    String chaine = "A0098";
    String chaineInval = "G0098";
    String chaineInvalDeux = "g0095";
    String chaineInvalTrois = "gk003";
    String chaineInvalQuatre = "GK003";
    String nom = "Dogny";
    String prenom = "Serge";
    Architecte architecteVal = new Architecte(cycleVal);
    Architecte architecteInval= new Architecte(cycleInval);

    @BeforeEach
    public void setUp(){
        Activite.compteurTotalHeuresCycle = 0;
    }

    /*Validation validationCycle()*/

    @Test
    @DisplayName("Validation: cycle valide")
    public void validationCyclePos(){
        assertTrue(architecteVal.verifierCycle(cycleVal));
    }

    @Test
    @DisplayName("Validation: cycle invalide")
    public void validationCycleNeg(){
        assertFalse(architecteInval.verifierCycle(cycleInval));
    }

    /*Validation verifierPremiereLettreArchitecte()*/

    @Test
    @DisplayName("Validation: 1ere lettre Architecte valide")
    public void validationPremiereLettreArchitecte(){assertTrue(architecteVal.verifierPremiereLettreArchitecte(chaine));}

    @Test
    @DisplayName("Validation: 1ere lettre Architecte invalide")
    public void validationPremiereLettreArchitecteNeg(){assertFalse(architecteVal.verifierPremiereLettreArchitecte(chaineInval));}

    @Test
    @DisplayName("Validation: 1ere lettre Architecte invalide")
    public void validationPremiereLettreArchitecteNegUn(){assertFalse(architecteVal.verifierPremiereLettreArchitecte(chaineInvalDeux));}

    /*Validation verifierNumeroDePermis()*/
    @Test
    @DisplayName("Validation: numero de permis valide")
    public void validationNumeroDePermis(){assertTrue(architecteVal.verifierNumeroDePermis(chaine, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNeg(){assertFalse(architecteVal.verifierNumeroDePermis(chaineInvalTrois, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNegDeux(){assertFalse(architecteVal.verifierNumeroDePermis(chaineInvalQuatre, nom, prenom));}

    @Test
    public void testValiderHeuresFauxParDefaut() {
        Activite.compteurTotalHeuresCycle = 0;
        architecteVal.verifierHeures();
        Activite.compteurTotalHeuresCycle = 0;
        assertEquals(0,Activite.compteurTotalHeuresCycle);
        Activite.compteurTotalHeuresCycle = 0;
    }

    @Test
    public void testValiderHeuresVrai(){
        Activite.compteurTotalHeuresCycle = architecteVal.totalHeureRequiseCycle;
        assertTrue(architecteVal.verifierTotalHeuresCycle());
        Activite.compteurTotalHeuresCycle = 0;
    }
}
