import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class RedactionProfessionnelTest {
    long heureVal = 3;
    long heureInval = -3;
    RedactionProfessionnel redactionProfessionnelVal = new RedactionProfessionnel(heureVal);
    RedactionProfessionnel redactionProfessionnelInval= new RedactionProfessionnel(heureInval);

    @BeforeEach
    public void setUp(){}

    @Test
    @DisplayName("Valider Format Heure +")
    public void validerFormatHeurePositif(){
        assertTrue(redactionProfessionnelVal.validerFormatHeure());
    }

    @Test
    @DisplayName("Valider Format Heure -")
    public void validerFormatHeureNegatif(){assertFalse(redactionProfessionnelInval.validerFormatHeure());}

    @Test
    public void testValiderActiveTrue(){
        Declaration arch1 = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",1);
        Declaration.individu = new Architecte(arch1.cycle);
        RedactionProfessionnel redactionProfessionnel = new RedactionProfessionnel("Redaction professionnelle sur la conquête aerospatiale","redaction_professionnelle",5,"2021-03-20");
        assertTrue(redactionProfessionnel.validerActivite(5));
    }
}