import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class ColloqueTest {
    long heureVal = 10;
    long heureInval = -10;
    Colloque colloqueVal = new Colloque(heureVal);
    Colloque colloqueInval= new Colloque(heureInval);

    @BeforeEach
    public void setUp(){}

    @Test
    @DisplayName("Valider Format Heure +")
    public void validerFormatHeurePositif(){
        assertTrue(colloqueVal.validerFormatHeure());
    }

    @Test
    @DisplayName("Valider Format Heure -")
    public void validerFormatHeureNegatif(){assertFalse(colloqueInval.validerFormatHeure());}

    @Test
    public void testValiderActiveTrue(){
        Declaration arch1 = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",1);
        Declaration.individu = new Architecte(arch1.cycle);
        Colloque colloque = new Colloque("Colloque sur la conquête aerospatiale","colloque",5,"2021-03-20");
        assertTrue(colloque.validerActivite(7));
    }
}