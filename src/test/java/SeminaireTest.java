import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class SeminaireTest {
    long heureVal = 3;
    long heureInval = -3;
    Seminaire seminaireVal = new Seminaire(heureVal);
    Seminaire seminaireInval= new Seminaire(heureInval);

    @BeforeEach
    public void setUp(){}

    @Test
    @DisplayName("Valider Format Heure +")
    public void validerFormatHeurePositif(){
        assertTrue(seminaireVal.validerFormatHeure());
    }

    @Test
    @DisplayName("Valider Format Heure -")
    public void validerFormatHeureNegatif(){assertFalse(seminaireInval.validerFormatHeure());}

    @Test
    public void testValiderActiveTrue(){
        Declaration arch1 = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",1);
        Declaration.individu = new Architecte(arch1.cycle);
        Seminaire seminaire = new Seminaire("Seminaire professionnelle sur la conquête aerospatiale","seminaire",5,"2021-03-20");
        assertTrue(seminaire.validerActivite(9));
    }
}