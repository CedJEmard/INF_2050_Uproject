import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class WriterJSONTest {
    JSONObject fakeStatistiques = new JSONObject();

    @BeforeEach
    public void setUp(){}

    @Test
    public void testEcrireLesStatistiquesParOrdre(){
        WriterJSON.ecrireStatistiquesParOrdre(fakeStatistiques);
        assertTrue(fakeStatistiques.containsKey("Nombre total de déclarations valides et complètes par architectes"));
        fakeStatistiques.clear();
    }

    @Test
    public void testEcrireLesStatistiquesDeclaration(){
        WriterJSON.ecrireStatistiquesDeclarations(fakeStatistiques);
        assertTrue(fakeStatistiques.containsKey("Nombre total de déclarations traitées"));
        fakeStatistiques.clear();
    }

    @Test
    public void testEcrireLesStatistiquesSexe(){
        WriterJSON.ecrireStatistiquesSexe(fakeStatistiques);
        assertTrue(fakeStatistiques.containsKey("Nombre total de déclarations faites par des hommes"));
        fakeStatistiques.clear();
    }

    @Test
    public void testEcrireLesStatistiquesActivite(){
        WriterJSON.ecrireStatistiquesActivites(fakeStatistiques);
        assertTrue(fakeStatistiques.containsKey("Nombre de Cours"));
    }

}