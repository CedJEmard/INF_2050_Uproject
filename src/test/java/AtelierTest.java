import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class AtelierTest {
    long heureVal = 3;
    long heureInval = -3;
    Atelier atelierVal = new Atelier(heureVal);
    Atelier atelierInval= new Atelier(heureInval);

    @BeforeEach
    public void setUp(){}

    @Test
    @DisplayName("Valider Format Heure +")
    public void validerFormatHeurePositif(){
        assertTrue(atelierVal.validerFormatHeure());
    }

    @Test
    @DisplayName("Valider Format Heure -")
    public void validerFormatHeureNegatif(){assertFalse(atelierInval.validerFormatHeure());}

    @Test
    public void testValiderActiveTrue(){
        Declaration arch1 = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",1);
        Declaration.individu = new Architecte(arch1.cycle);
        Atelier atelier = new Atelier("Atelier sur la conquête aerospatiale","atelier",5,"2021-03-20");
        assertTrue(atelier.validerActivite(6));
    }
}