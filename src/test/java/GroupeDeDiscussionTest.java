import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class GroupeDeDiscussionTest {
    long heureVal = 3;
    long heureInval = -3;
    GroupeDeDiscussion groupeDeDiscussionVal = new GroupeDeDiscussion(heureVal);
    GroupeDeDiscussion groupeDeDiscussionInval= new GroupeDeDiscussion(heureInval);

    @BeforeEach
   public void setUp(){}

    @Test
    @DisplayName("Valider Format Heure +")
    public void validerFormatHeurePositif(){
        assertTrue(groupeDeDiscussionVal.validerFormatHeure());
    }

    @Test
    @DisplayName("Valider Format Heure -")
    public void validerFormatHeureNegatif(){assertFalse(groupeDeDiscussionInval.validerFormatHeure());}

    @Test
    public void testValiderActiveTrue(){
        Declaration arch1 = new Declaration("A0001",2,"2020-2022","architectes","Serge","Dogny",1);
        Declaration.individu = new Architecte(arch1.cycle);
        GroupeDeDiscussion groupeDeDiscussion = new GroupeDeDiscussion("Groupe de discussion sur la conquête aerospatiale","groupe_de_discussion",5,"2021-03-20");
        assertTrue(groupeDeDiscussion.validerActivite(1));
    }
}