import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class PsychologueTest {
    String cycleVal = "2018-2023";
    String cycleInval ="2015-2016";
    String chaine ="45678-00";
    String chaineInval ="5678-00";
    String chaineInvalDeux ="456000-00";
    String chaineInvalTrois ="56789";
    String chaineInvalQuatre = "56789-0";
    String chaineInvalCinq = "56789-";
    String nom = "Dogny";
    String prenom = "Serge";
    Psychologue psychologueVal = new Psychologue(cycleVal);
    Psychologue psychologueInval= new Psychologue(cycleInval);

    @BeforeEach
    public void setUp(){
        Activite.compteurTotalHeuresCycle =0;
    }

    /*Validation validationCycle()*/

    @Test
    @DisplayName("Validation: cycle valide")
    public void validationCyclePos(){assertTrue(psychologueVal.verifierCycle(cycleVal));}

    @Test
    @DisplayName("Validation: cycle invalide")
    public void validationCycleNeg(){assertFalse(psychologueInval.verifierCycle(cycleInval));}

    /*Validation verifierNumeroDePermis()*/

    @Test
    @DisplayName("Validation: numero de permis valide")
    public void validationNumeroDePermis(){assertTrue(psychologueVal.verifierNumeroDePermis(chaine, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNeg(){assertFalse(psychologueVal.verifierNumeroDePermis(chaineInval, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNegDeux(){assertFalse(psychologueVal.verifierNumeroDePermis(chaineInvalDeux, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNegTrois(){assertFalse(psychologueVal.verifierNumeroDePermis(chaineInvalTrois, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNegQuatre(){assertFalse(psychologueVal.verifierNumeroDePermis(chaineInvalQuatre, nom, prenom));}

    @Test
    @DisplayName("Validation: numero de permis invalide")
    public void validationNumeroDePermisNegCinq(){assertFalse(psychologueVal.verifierNumeroDePermis(chaineInvalCinq, nom, prenom));}

    @Test
    public void testValiderHeuresFauxParDefaut(){
        Activite.compteurTotalHeuresCycle = 0;
        assertFalse(psychologueVal.verifierHeures());
        Activite.compteurTotalHeuresCycle = 0;
    }

    @Test
    public void testValiderHeuresVrai(){
        Activite.compteurTotalHeuresCycle = psychologueVal.totalHeureRequiseCycle;
        assertTrue(psychologueVal.verifierTotalHeuresCycle());
        Activite.compteurTotalHeuresCycle = 0;
    }
}
