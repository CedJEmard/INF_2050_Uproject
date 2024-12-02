import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class CoursTest {
    long heureVal = 1;
    long heureInval = -1;
    Cours coursVal = new Cours(heureVal);
    Cours coursInval = new Cours(heureInval);

    @BeforeEach
    public void setUp(){}

    @Test
    @DisplayName("Valider Format Heure +")
    public void validerFormatHeurePositif(){
        assertTrue(coursVal.validerFormatHeure());
    }

    @Test
    @DisplayName("Valider Format Heure -")
    public void validerFormatHeureNegatif(){assertFalse(coursInval.validerFormatHeure());}

    @Test
    public void testValiderActiveTrue(){
        Declaration arch1 = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",1);
        Declaration.individu = new Architecte(arch1.cycle);
        Cours cours = new Cours("Cours sur la conquête aerospatiale","cours",5,"2021-03-20");
        assertTrue(cours.validerActivite(0));
    }

    @Test
    public void testValiderActiveFalse(){
        Declaration arch1 = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",1);
        Declaration.individu = new Architecte(arch1.cycle);
        Cours cours = new Cours("Cours sur la conquête aerospatiale","cours",-5,"2021-03-20");
        assertFalse(cours.validerActivite(0));
    }
}