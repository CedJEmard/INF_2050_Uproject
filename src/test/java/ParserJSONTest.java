import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParserJSONTest {
    Activite cours = new Activite("Cours sur la conquête aerospatiale", "cours", 5, "2021-03-20") {
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };
    Activite atelier = new Activite("Atelier sur la conquête aerospatiale","atelier",5,"2021-05-20"){
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };
    Activite colloque = new Activite("Colloque sur la conquête aerospatiale","colloque",5,"2021-03-20"){
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };
    Activite conference = new Activite("Conference sur la conquête aerospatiale","conférence",5,"2021-05-20"){
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };
    Activite seminaire = new Activite("Seminaire sur la conquête aerospatiale","séminaire",5,"2021-05-20"){
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };
    Activite groupeDeDiscussion  = new Activite("Groupe de discussion sur la conquête aerospatiale","groupe de discussion",5,"2021-05-20"){
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };
    Activite projetDeRecherche  = new Activite("Projet de recherche sur la conquête aerospatiale","projet de recherche",5,"2021-05-20"){
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };
    Activite lectureDirigee  = new Activite("Lecture dirigée sur la conquête aerospatiale","lecture dirigée",5,"2021-05-20"){
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };
    Activite presentation  = new Activite("Presentation sur la conquête aerospatiale","présentation",5,"2021-05-20"){
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };
    Activite redactionProfessionnel  = new Activite("Redaction pro sur la conquête aerospatiale","rédaction professionnelle",5,"2021-05-20"){
        @Override
        protected boolean validerFormatHeure() {
            return false;
        }

        @Override
        public void envoyerErreur(String messageErreur) {

        }
    };
    ArrayList<Activite> listeActivite = new ArrayList<>();

    @Test
    public void testMiseAJourDesHeuresEnTropCours(){
        Activite.compteurHeuresActivite[0] = 0;
        Activite.setCompteurHeuresActivite(5,0);
        ParserJSON.mettreAJourNombreHeureDeLActivite(cours,5);
        assertEquals(0,Activite.getCompteurHeuresActivite(0));
        Activite.compteurHeuresActivite[0] = 0;
    }
    @Test
    public void testMiseAJourDesHeuresEnTropConference(){
        Activite.compteurHeuresActivite[3] = 0;
        Activite.setCompteurHeuresActivite(5,3);
        ParserJSON.mettreAJourNombreHeureDeLActivite(conference,5);
        assertEquals(0,Activite.getCompteurHeuresActivite(3));
        Activite.compteurHeuresActivite[3] = 0;
    }
    @Test
    public void testMiseAJourDesHeuresEnTropColloque(){
        Activite.compteurHeuresActivite[7] = 0;
        Activite.setCompteurHeuresActivite(5,7);
        ParserJSON.mettreAJourNombreHeureDeLActivite(colloque,5);
        assertEquals(0,Activite.getCompteurHeuresActivite(7));
        Activite.compteurHeuresActivite[7] = 0;
    }

    @Test
    public void testMiseAJourDesHeuresEnTropAtelier(){
        Activite.compteurHeuresActivite[6] = 0;
        Activite.setCompteurHeuresActivite(10,6);
        ParserJSON.mettreAJourNombreHeureDeLActivite(atelier,5);
        assertEquals(5,Activite.getCompteurHeuresActivite(6));
        Activite.compteurHeuresActivite[6] = 0;
    }

    @Test
    public void testMiseAJourDesHeuresEnTropSeminaire(){
        Activite.compteurHeuresActivite[9] = 0;
        Activite.setCompteurHeuresActivite(10,9);
        ParserJSON.mettreAJourNombreHeureDeLActivite(seminaire,5);
        assertEquals(5,Activite.getCompteurHeuresActivite(9));
        Activite.compteurHeuresActivite[9] = 0;
    }

    @Test
    public void testMiseAJourDesHeuresEnTropGDD(){
        Activite.compteurHeuresActivite[1] = 0;
        Activite.setCompteurHeuresActivite(10,1);
        ParserJSON.mettreAJourNombreHeureDeLActivite(groupeDeDiscussion,5);
        assertEquals(5,Activite.getCompteurHeuresActivite(1));
        Activite.compteurHeuresActivite[1] = 0;
    }

    @Test
    public void testMiseAJourDesHeuresEnTropPDR(){
        Activite.compteurHeuresActivite[2] = 0;
        Activite.setCompteurHeuresActivite(10,2);
        ParserJSON.mettreAJourNombreHeureDeLActivite(projetDeRecherche,5);
        assertEquals(5,Activite.getCompteurHeuresActivite(2));
        Activite.compteurHeuresActivite[2] = 0;
    }
    @Test
    public void testMiseAJourDesHeuresEnTropLectureDirigee(){
        Activite.compteurHeuresActivite[8] = 0;
        Activite.setCompteurHeuresActivite(10,8);
        ParserJSON.mettreAJourNombreHeureDeLActivite(lectureDirigee,5);
        assertEquals(5,Activite.getCompteurHeuresActivite(8));
        Activite.compteurHeuresActivite[8] = 0;
    }

    @Test
    public void testMiseAJourDesHeuresEnTropRedacPro(){
        Activite.compteurHeuresActivite[5] = 0;
        Activite.setCompteurHeuresActivite(1,5);
        ParserJSON.mettreAJourNombreHeureDeLActivite(redactionProfessionnel,1);
        assertEquals(0,Activite.getCompteurHeuresActivite(5));
        Activite.compteurHeuresActivite[5] = 0;
    }
    @Test
    public void testMiseAJourDesHeuresEnTropPresentation(){
        Activite.compteurHeuresActivite[4] = 0;
        Activite.setCompteurHeuresActivite(1,4);
        ParserJSON.mettreAJourNombreHeureDeLActivite(presentation,1);
        assertEquals(0,Activite.getCompteurHeuresActivite(4));
        Activite.compteurHeuresActivite[4] = 0;
    }

    @Test
    public void testListerActiviteParDate(){
        listeActivite.add(cours); listeActivite.add(atelier);
        ArrayList<ArrayList<Activite>> listeDate = ParserJSON.ListerActiviteParDate(listeActivite);
        assertFalse(listeDate.isEmpty());
        listeDate.clear();listeActivite.clear();
    }

    @Test
    public void testDixHeuresJournalieresRespectee(){
        listeActivite.add(cours); listeActivite.add(atelier);
        ArrayList<ArrayList<Activite>> listeDate = ParserJSON.ListerActiviteParDate(listeActivite);
        ParserJSON.additionnerHeure(listeDate);
        Declaration.resultats = new Resultats();
        assertTrue(Declaration.resultats.getMesssagesErreurs().isEmpty()); //10 heures journalières non dépassé
        Declaration.resultats.getMesssagesErreurs().clear();
        listeDate.clear();listeActivite.clear();
    }

    @Test
    public void testDixHeuresJournalieresNonRespectee(){
        listeActivite.add(cours);listeActivite.add(cours);listeActivite.add(cours);listeActivite.add(cours);
        ArrayList<ArrayList<Activite>> listeDate = ParserJSON.ListerActiviteParDate(listeActivite);
        ParserJSON.additionnerHeure(listeDate);
        assertEquals(5,listeDate.get(0).get(2).getHeures());
        listeDate.clear();listeActivite.clear();Activite.compteurHeuresActivite[0] = 0;
    }

}