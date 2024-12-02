import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class ProjetDeRechercheTest {
    long heureVal = 3;
    long heureInval = -3;
    ProjetDeRecherche projetDeRechercheVal = new ProjetDeRecherche(heureVal);
    ProjetDeRecherche projetDeRechercheInval= new ProjetDeRecherche(heureInval);

    @BeforeEach
    public void setUp(){}

    @Test
    @DisplayName("Valider Format Heure +")
    public void validerFormatHeurePositif(){
        assertTrue(projetDeRechercheVal.validerFormatHeure());
    }

    @Test
    @DisplayName("Valider Format Heure -")
    public void validerFormatHeureNegatif(){assertFalse(projetDeRechercheInval.validerFormatHeure());}

    @Test
    public void testValiderActiveTrue(){
        Declaration arch1 = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",1);
        Declaration.individu = new Architecte(arch1.cycle);
        ProjetDeRecherche projetDeRecherche = new ProjetDeRecherche("Projet de recherche sur la conquête aerospatiale","projet_de_recherche",5,"2021-03-20");
        assertTrue(projetDeRecherche.validerActivite(2));
    }
}