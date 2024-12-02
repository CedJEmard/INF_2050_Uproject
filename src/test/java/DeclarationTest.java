import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class DeclarationTest {
    Declaration arch2016 = new Declaration("A0001",2,"2016-2018","architectes","Serge","Dogny",1);
    Declaration arch1 = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",1);
    Declaration archCycleInvalide = new Declaration("A0001",2,"2025-2022","architectes","Serge","Dogny",1);
    Declaration archSexeNonValide = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",8);
    Declaration archNomVide = new Declaration("A0001",2,"2020-2022","architectes","","Dogny",1);
    Declaration archPrenomVide = new Declaration("A0001",2,"2020-2022","architectes","Serge","",1);
    Declaration geo1 = new Declaration("DS0081",2,"2018-2021","géologues","Serge","Dogny",0);
    Declaration psy1 = new Declaration("83723-34",2,"2018-2023","psychologues","Serge","Dogny",0);
    Declaration psy2 = new Declaration("83723-34",2,"2018-2023","psychologues","Serge","Dogny",2);
    Declaration psy3 = new Declaration("a3723-34", 2, "2018-2023", "psychologues", "Dogny", "Serge", 0);
    Declaration podi1 = new Declaration("45689", 2, "2018-2021", "podiatres", "Dogny", "Serge", 2);
    Declaration podi2 = new Declaration("a4568", 2, "2018-2021", "podiatres", "Dogny","Serge",0);
    Declaration podiNumeroDePermis4chiffres = new Declaration("4568", 2, "2018-2021", "podiatres", "Dogny","Serge",0);
    Declaration invalide = new Declaration("F0LS3",2,"2020-2022","inconnu","Serge","Dogny",3);
    Declaration invalide2 = new Declaration("F0123",2,"2020-2022","inconnu","Serge","Dogny",0);
    Declaration invalide3 = new Declaration("F01253",2,"2020-2022","inconnu","Serge","Dogny",8);

   
    JSONObject fakeDeclarationArchitecteInvalide = new JSONObject();
    JSONObject fakeDeclarationAutresOrdresValide = new JSONObject();

    @Test
    public void testdiscriminerIndividuArchitecte() {
        int expected = 1;
        int actual = arch1.discriminerIndividu(arch1.cycle);
        assertEquals(expected, actual);
    }
    @Test
    public void testdiscriminerIndividuArchitecte2016(){
        int actual = arch2016.discriminerIndividu(arch2016.cycle);
        assertEquals(1,actual);
    }

    @Test
    public void testdiscriminerIndividuArchitecteInvalide() {
        int expected = 2;
        int actual = arch1.discriminerIndividu(arch1.cycle);
        assertNotEquals(expected, actual);
    }

    @Test
    public void testdiscriminerIndividuGeologue() {
        int expected = 2;
        int actual = geo1.discriminerIndividu(geo1.cycle);
        assertEquals(expected, actual);
    }

    @Test
    public void testdiscriminerIndividuPsycologue() {
        int expected = 3;
        int actual = psy1.discriminerIndividu(psy1.cycle);
        assertEquals(expected, actual);
    }

    @Test
    public void testdiscriminerIndividuPodiatre() {
        int expected = 4;
        int actual = podi1.discriminerIndividu(podi1.cycle);
        assertEquals(expected, actual);
    }

    @Test
    public void testdiscriminerIndividuInconnu() {
        int expected = 0;
        int actual = invalide.discriminerIndividu(invalide.cycle);
        assertEquals(expected, actual);
    }

    @Test
    public void testdiscriminerIndividuInconnuInvalide1() {
        int expected = 1;
        int actual = invalide.discriminerIndividu(invalide.cycle);
        assertNotEquals(expected, actual);
    }

    @Test
    public void testdiscriminerIndividuInconnuInvalide2() {
        int expected = 2;
        int actual = invalide.discriminerIndividu(invalide.cycle);
        assertNotEquals(expected, actual);
    }

    @Test
    public void testdiscriminerIndividuInconnuInvalide3() {
        int expected = 3;
        int actual = invalide.discriminerIndividu(invalide.cycle);
        assertNotEquals(expected, actual);
    }
    @Test
    public void toStringDeclarationTest(){
        String expected = "Déclaration{" +
                "Nom='" + arch1.nom + '\'' +
                "Prénom='" + arch1.prenom + '\'' +
                "Sexe='" + arch1.genrer() + '\'' +
                "numéro de permis='" + arch1.numeroDePermis + '\'' +
                ", heures transferées du cycle précédent='" + arch1.heuresTransfereesDuCyclePrecedent + '\'' +
                ", cycle='" + arch1.cycle + '\'' ;
        String actual = arch1.toString();
        assertEquals(expected, actual);
    }

    /*Testes verifierSiChaineCommenceParCinqChiffre()*/

    @Test
    @DisplayName("Validation : si chaine commence par 5 chiffres Podiatres valide")
    public void validationSiChaineCommenceParCinqChiffres(){assertTrue(Declaration.verifierSiChaineCommenceParCinqChiffre(podi1.numeroDePermis));}

    @Test
    @DisplayName("Validation : si chaine commence par 5 chiffres Podiatres invalide")
    public void validationSiChaineCommenceParCinqChiffresNeg(){assertFalse(Declaration.verifierSiChaineCommenceParCinqChiffre(podi2.numeroDePermis));}

    @Test
    @DisplayName("Validation : si chaine commence par 5 chiffres Psychologues valide")
    public void validationSiChaineCommenceParCinqChiffresNegDeux(){assertFalse(Declaration.verifierSiChaineCommenceParCinqChiffre(psy1.numeroDePermis));}

    @Test
    @DisplayName("Validation : si chaine commence par 5 chiffres Psychologues invalide")
    public void validationSiChaineCommenceParCinqChiffresNegTrois(){assertFalse(Declaration.verifierSiChaineCommenceParCinqChiffre(psy3.numeroDePermis));}

    @Test
    @DisplayName("Validation : si chaine commence par 4 chiffres Podiatre invalide")
    public void validationSiChaineCommenceParCinqChiffresNegQuatre(){assertFalse(Declaration.verifierSiChaineCommenceParCinqChiffre(podiNumeroDePermis4chiffres.numeroDePermis));}

    @Test
    public void verifierSiChaineSuivieDeQuatreChiffreTestValide(){
        assertTrue(Declaration.verifierSiChaineSuivieDeQuatreChiffre(arch1.numeroDePermis,0));
    }

    @Test
    public void verifierSiChaineSuivieDeQuatreChiffreTestInvalide(){
        assertFalse(Declaration.verifierSiChaineSuivieDeQuatreChiffre(psy1.numeroDePermis,0));
    }


    @Test
    public void verifierSiChaineSuivieDeQuatreChiffreTestValideInvalide(){
        assertTrue(Declaration.verifierSiChaineSuivieDeQuatreChiffre(invalide2.numeroDePermis,0));
    }

    @Test
    public void verifierSiChaineSuivieDeQuatreChiffreTestTroplong(){
        assertFalse(Declaration.verifierSiChaineSuivieDeQuatreChiffre(invalide3.numeroDePermis,0));
    }

    @Test
    public void testVerifierSexeMasculinEquals(){
        String expected = "masculin";
        String actual = arch1.genrer();
        assertEquals(expected, actual);
    }
    @Test
    public void testVerifierSexeFemininEqual(){
        String expected = "féminin";
        String actual = psy2.genrer();
        assertEquals(expected, actual);
    }
    @Test
    public void testVerifierSexeInconnuEquals() {
        String expected = "inconnu";
        String actual = psy1.genrer();
        assertEquals(expected, actual);
    }
    @Test
    public void testVerifierSexeMasculinNotEquals(){
        String expected = "masculin";
        String actual = geo1.genrer();
        assertNotEquals(expected, actual);
    }
    @Test
    public void testVerifierSexeFemininNotEqual(){
        String expected = "féminin";
        String actual = psy1.genrer();
        assertNotEquals(expected, actual);
    }
    @Test
    public void testVerifierSexeInconnuNotEquals() {
        String expected = "inconnu";
        String actual = arch1.genrer();
        assertNotEquals(expected, actual);
    }
    @Test
    public void testVerifierSexeDefault() {
        String expected = null;
        String actual = invalide.genrer();
        assertEquals(expected, actual);
    }

    @Test
    public void verifierChampNomOuPrenomCasNomVide(){
        assertFalse(archNomVide.verifierChampNomOuPrenom());
    }

    @Test
    public void verifierChampNomOuPrenomCasPrenomVide(){
        assertFalse(archPrenomVide.verifierChampNomOuPrenom());
    }

    @Test
    public void verifierChampNomOuPrenomCasValide(){
        assertTrue(arch1.verifierChampNomOuPrenom());
    }

    @Test
    public void testVerifierCycleFemme(){assertTrue(psy2.verifierSexe());}
    @Test
    public void testVerifierCycleHomme(){assertTrue(arch1.verifierSexe());}
    @Test
    public void testVerifierCycleNonReconnu(){assertTrue(geo1.verifierSexe());}
    @Test
    public void testVerifierCycleInconnu(){assertFalse(invalide3.verifierSexe());}

    @Test
    public void testVerifierDeclarationInvalideCasOrdreNonReconnu(){assertFalse(invalide2.verifierDeclaration());}
    @Test
    public void testVerifierDeclarationInvalideCasNomVide(){assertFalse(archNomVide.verifierDeclaration());}
    @Test
    public void testVerifierDeclarationInvalideCasPrenomVide(){assertFalse(archPrenomVide.verifierDeclaration());}
    @Test
    public void testVerifierDeclarationInvalideCasNumPermisInvalide(){assertFalse(podiNumeroDePermis4chiffres.verifierDeclaration());}
    @Test
    public void testVerifierDeclarationInvalideCasCycleInvalide(){assertFalse(archCycleInvalide.verifierDeclaration());}
    @Test
    public void testVerifierDeclarationInvalideCasSexeInvalide(){assertFalse(archSexeNonValide.verifierDeclaration());}
    @Test
    public void testVerifierDeclarationValide(){assertTrue(arch1.verifierDeclaration());}

    @Test
    public void testVerifierChampJSONManquantArchitecte(){assertFalse(arch1.verifierChampJSONHeuresTransferees(fakeDeclarationArchitecteInvalide));}
    
    @Test
    public void testVerifierChampJSONManquantPodiatre(){assertTrue(podi1.verifierChampJSONHeuresTransferees(fakeDeclarationAutresOrdresValide));}

    @Test
    public void testGetResultatFalseParDefaut(){assertFalse(arch1.getResultats());}

    public Declaration creerFakeDeclaration(){
        JSONObject fakeDeclarationJSON = new JSONObject();
        fakeDeclarationJSON.put("cycle","2020-2022");
        fakeDeclarationJSON.put("numero_de_permis","A0001");
        fakeDeclarationJSON.put("ordre","architectes");
        fakeDeclarationJSON.put("sexe",2L);
        fakeDeclarationJSON.put("nom","Dogny");
        fakeDeclarationJSON.put("prenom","Serge");
        fakeDeclarationJSON.put("heures_transferees_du_cycle_precedent",2L);
        Declaration declarationFake = new Declaration(fakeDeclarationJSON);
        return declarationFake;
    }


    @Test
    public void testConstructionDeclaration(){
        Declaration declarationFake = creerFakeDeclaration();
        assertAll(
                () -> assertEquals("2020-2022",declarationFake.cycle),
                () -> assertEquals("A0001",declarationFake.numeroDePermis),
                () -> assertEquals("architectes",declarationFake.ordre),
                () -> assertEquals(2,declarationFake.sexeIso),
                () -> assertEquals("Dogny",declarationFake.nom),
                () -> assertEquals("Serge",declarationFake.prenom),
                () -> assertEquals(2,declarationFake.heuresTransfereesDuCyclePrecedent)
        );

    }
}