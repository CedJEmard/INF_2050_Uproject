import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {
    String argsSuperieursADeux[] = {"arg1", "arg2", "arg3"};

    @Test
    public void testMainArgumentsTropNombreux(){
        Main.verifierNombreArguments(argsSuperieursADeux);
        assertEquals(3,argsSuperieursADeux.length);
    }
}