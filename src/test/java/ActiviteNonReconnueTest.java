import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class ActiviteNonReconnueTest {
    long heurePasPris = 10;
    ActiviteNonReconnue activiteNonReconnue = new ActiviteNonReconnue(heurePasPris);

    @BeforeEach
    public void setUp(){}

    @Test
    @DisplayName("Valider Format Heure non-pris en charge")
    public void validerFormatHeureNegatif(){
        assertFalse(activiteNonReconnue.validerFormatHeure());
    }

    @Test
    public void testValiderActiveTrue(){
        Declaration arch1 = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",1);
        Declaration.individu = new Architecte(arch1.cycle);
        ActiviteNonReconnue voyage = new ActiviteNonReconnue("Voyage dans l'espace avec la NASA","voyage",120,"2021-03-20");
        assertFalse(voyage.validerActivite(10));
    }
}