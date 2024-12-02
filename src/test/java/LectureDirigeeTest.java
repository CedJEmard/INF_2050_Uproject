import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class LectureDirigeeTest {
    long heureVal = 3;
    long heureInval = -3;
    LectureDirigee lectureDirigeeVal = new LectureDirigee(heureVal);
    LectureDirigee lectureDirigeeInval= new LectureDirigee(heureInval);

    @BeforeEach
    public void setUp(){}

    @Test
    @DisplayName("Valider Format Heure +")
    public void validerFormatHeurePositif(){
        assertTrue(lectureDirigeeVal.validerFormatHeure());
    }

    @Test
    @DisplayName("Valider Format Heure -")
    public void validerFormatHeureNegatif(){assertFalse(lectureDirigeeInval.validerFormatHeure());}

    @Test
    public void testValiderActiveTrue(){
        Declaration arch1 = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",1);
        Declaration.individu = new Architecte(arch1.cycle);
        LectureDirigee lectureDirigee = new LectureDirigee("Lecture dirigee sur la conquête aerospatiale","lecture_dirigee",5,"2021-03-20");
        assertTrue(lectureDirigee.validerActivite(8));
    }
}