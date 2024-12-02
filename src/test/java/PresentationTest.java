import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class PresentationTest {
    long heureVal = 3;
    long heureInval = -3;
    Presentation presentationVal = new Presentation(heureVal);
    Presentation presentationInval= new Presentation(heureInval);

    @BeforeEach
    public void setUp(){}

    @Test
    @DisplayName("Valider Format Heure +")
    public void validerFormatHeurePositif(){
        assertTrue(presentationVal.validerFormatHeure());
    }

    @Test
    @DisplayName("Valider Format Heure -")
    public void validerFormatHeureNegatif(){assertFalse(presentationInval.validerFormatHeure());}

    @Test
    public void testValiderActiveTrue(){
        Declaration arch1 = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",1);
        Declaration.individu = new Architecte(arch1.cycle);
        Presentation presentation = new Presentation("Presentation dirigee sur la conquête aerospatiale","presentation",5,"2021-03-20");
        assertTrue(presentation.validerActivite(4));
    }
}