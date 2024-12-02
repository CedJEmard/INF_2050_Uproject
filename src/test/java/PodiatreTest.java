import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class PodiatreTest {

    String cycleVal = "2018-2021";
    String cycleInval ="2015-2016";
    String chaine = "12345";
    String chaineInval = "1234";
    String chaineInvalDeux = "123456";
    String chaineInvalTrois = "a1234";
    String chaineInvalQuatre = "A12345";
    String nom = "Dogny";
    String prenom = "Serge";
    Podiatre podiatreVal = new Podiatre(cycleVal);
    Podiatre podiatreInval = new Podiatre(cycleInval);

    @BeforeEach
    public void setUp(){
        Activite.compteurTotalHeuresCycle = 0;
    }

    /*Validation validationCycle()*/

    @Test
    @DisplayName("Validation: cycle valide")
    public void validationCyclePos(){
        assertTrue(podiatreVal.verifierCycle(cycleVal));
    }

    @Test
    @DisplayName("Validation: cycle invalide")
    public void validationCycleNeg(){
        assertFalse(podiatreInval.verifierCycle(cycleInval));
    }

    /*Validation verifierNumeroDePermis()*/

    @Test
    @DisplayName("Validation: numero de permis valide")
    public void validationNumeroDePermis(){assertTrue(podiatreVal.verifierNumeroDePermis(chaine, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNeg(){assertFalse(podiatreVal.verifierNumeroDePermis(chaineInval, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNegDeux(){assertFalse(podiatreVal.verifierNumeroDePermis(chaineInvalDeux, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNegTrois(){assertFalse(podiatreVal.verifierNumeroDePermis(chaineInvalTrois, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNegQuatre(){assertFalse(podiatreVal.verifierNumeroDePermis(chaineInvalQuatre, nom, prenom));}

    @Test
    public void testValiderHeuresFauxParDefaut(){
        Activite.compteurTotalHeuresCycle = 0;
        assertFalse(podiatreVal.verifierHeures());
        Activite.compteurTotalHeuresCycle = 0;
    }

    @Test
    public void testValiderHeuresVrai(){
        Activite.compteurTotalHeuresCycle = podiatreVal.totalHeureRequiseCycle;
        assertTrue(podiatreVal.verifierTotalHeuresCycle());
        Activite.compteurTotalHeuresCycle = 0;
    }
}