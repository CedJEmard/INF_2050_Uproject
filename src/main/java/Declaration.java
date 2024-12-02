import org.json.simple.JSONObject;

public class Declaration implements IErreurs{
    protected static long heuresManquantesCycle;
    protected static long heuresManquantesFormation;
    protected static final String HEURE_FORMATION = "Heures manquantes pour compléter le cycle : ";
    protected static final String HEURE_CUMUL = " heures manquantes pour compléter la formation de 17h (cours,atelier,seminaire,colloque,conférence et lecture dirigée).";

    protected static final String VERIF_HEURES_TRANSFEREE = "Le nombre d'heures transférées est supérieur à 7 : 7h maximum pris en charge dans les calculs";
    protected static final String VERIF_HEURES_TRANSFEREE_NEGATIVE = "Le nombre d'heures transférées est inférieur à 0 : les heures doivent être des entiers positifs";

    protected static final String VERIF_ORDRE = "Ordre non reconnu: ";

    protected static final String VERIF_JSON = "Il n'existe aucun transfert d'heures vers un autre cycle. Le champ heures_transferees_du_cycle_precedent ne devrait pas exister.";
    protected static final String VERIF_JSON_ARCHI = "Le champ heures_transferees_du_cycle_precedent est manquant.";
    protected static final String VERIF_CHAMP_ORDRE = "Le champ ordre est manquant.";

    protected static final String VERIF_SEXE = "Le fichier d'entrée est invalide : le sexe doit appartenir à {0, 1, 2}, le cycle est incomplet. La déclaration est invalide.";
    protected static final String VERIF_IDENTITE = "Le champ nom et/ou prenom doivent être mentionnés, ne pas le laisser vide, la déclaration est invalide";

    protected String numeroDePermis;
    protected long heuresTransfereesDuCyclePrecedent;
    protected String cycle;
    protected String ordre;
    protected String prenom;
    protected String nom;
    protected long sexeIso;
    protected String sexe;

    protected static Resultats resultats = new Resultats();
    protected static Individu individu;

    public Declaration(JSONObject declarationJSON){
        this.cycle = (String)declarationJSON.get("cycle");
        try{
            this.ordre = (String)declarationJSON.get("ordre");
            discriminerIndividu(this.cycle);
            verifierChampJSONHeuresTransferees(declarationJSON);
        } catch(java.lang.NullPointerException e){System.out.println(VERIF_CHAMP_ORDRE);}
        this.numeroDePermis = (String)declarationJSON.get("numero_de_permis");
        this.prenom = (String)declarationJSON.get("prenom");
        this.nom = (String)declarationJSON.get("nom");
        this.sexeIso = (long)declarationJSON.get("sexe");
    }

    public Declaration(String numeroDePermis,long heuresTransfereesDuCyclePrecedent, String cycle, String ordre, String nom, String prenom, long sexeIso ) {
        this.numeroDePermis = numeroDePermis;
        this.heuresTransfereesDuCyclePrecedent = heuresTransfereesDuCyclePrecedent;
        this.cycle = cycle;
        this.ordre = ordre;
        this.prenom = prenom;
        this.nom = nom;
        this.sexeIso = sexeIso;
    }

    public boolean verifierChampNomOuPrenom(){
        if(this.nom.equals("")){return false;}
        if(this.prenom.equals("")){return false;}
        return true;
    }

    @Override
    public void envoyerErreur(String messageErreur) {
        resultats.ajouterUneErreur(messageErreur);
        resultats.setComplet(resultats.getNombreErreurs() == 0);
    }

    protected int discriminerIndividu(String cycle){
        // Discrimination d'individu
        if(this.ordre.equals("architectes")){this.individu = new Architecte(cycle); return 1;}
        if(this.ordre.equals("géologues")){this.individu  = new Geologue(cycle); return 2;}
        if(this.ordre.equals("psychologues")){this.individu = new Psychologue(cycle); return 3;}
        if(this.ordre.equals("podiatres")){this.individu = new Podiatre(cycle); return 4;}
        this.individu = new Individu(cycle); return 0;
    }

    protected boolean verifierChampJSONHeuresTransferees(JSONObject declarationJSON){
        try { this.heuresTransfereesDuCyclePrecedent = ((long) declarationJSON.get("heures_transferees_du_cycle_precedent"));
            if(discriminerIndividu(this.cycle) == 2){ Declaration.resultats.stopperExecution(VERIF_JSON); return false; }
            if(discriminerIndividu(this.cycle) == 3){ Declaration.resultats.stopperExecution(VERIF_JSON); return false; }
            if(discriminerIndividu(this.cycle) == 4){ Declaration.resultats.stopperExecution(VERIF_JSON); return false; }
            return true;
        } catch (java.lang.NullPointerException e) {
            if(discriminerIndividu(this.cycle) == 1){ Declaration.resultats.stopperExecution(VERIF_JSON_ARCHI); return false; }
            return true;
        }
    }

    public boolean getResultats(){
        if(Declaration.resultats.mettreFinALExecution()){return false;}
        if(!individu.verifierHeures()){
            Statistiques.incrementerNbDeclarationsValidesEtIncompletes(discriminerIndividu(this.cycle));
            return false;
        }
        resultats.setComplet(true);
        Statistiques.incrementerNbDeclarationsCompletes(discriminerIndividu(this.cycle));
        return true;
    }

    public static boolean verifierSiChaineSuivieDeQuatreChiffre(String chaine, int indice){
        if(chaine.length() != indice+5) {return false;}
        if(Character.isDigit(chaine.charAt(indice+1))
                && Character.isDigit(chaine.charAt(indice+2))
                && Character.isDigit(chaine.charAt(indice+3))
                && Character.isDigit(chaine.charAt(indice+4))
        ){return true;}
        return false;
    }

    public static boolean verifierSiChaineCommenceParCinqChiffre(String chaineCinq){
        if(chaineCinq.length() < 5) {return false;}
        return chaineCinq.matches("^[0-9]{5}");
    }


    public boolean verifierDeclaration() {
        if(discriminerIndividu(this.cycle) == 0) {Statistiques.incrementerDeclarationIncompletesOuInvalides(); envoyerErreur(VERIF_ORDRE + ordre); return false; }
        if(!individu.verifierNumeroDePermis(this.numeroDePermis,this.nom,this.prenom)){Statistiques.incrementerDeclarationIncompletesOuInvalides();Statistiques.nbDeclarationNumPermisInvalide++;Declaration.resultats.stopperExecution("Numero permis invalide " + numeroDePermis); return false; }
        if(!individu.verifierCycle(this.cycle)) {return false;}
        if(!verifierChampNomOuPrenom()){Statistiques.incrementerDeclarationIncompletesOuInvalides(); envoyerErreur(VERIF_IDENTITE); return false;}
        if(this.heuresTransfereesDuCyclePrecedent > 7) {this.heuresTransfereesDuCyclePrecedent = 7;envoyerErreur(VERIF_HEURES_TRANSFEREE); }
        if(this.heuresTransfereesDuCyclePrecedent < 0) {Statistiques.incrementerDeclarationIncompletesOuInvalides();this.heuresTransfereesDuCyclePrecedent = 0; envoyerErreur(VERIF_HEURES_TRANSFEREE_NEGATIVE); return false; }
        Activite.compteurHeuresRequisesArchitecteFormation += this.heuresTransfereesDuCyclePrecedent;
        if(!this.verifierSexe()){Statistiques.incrementerDeclarationIncompletesOuInvalides();return false;}
        return true;
    }

    protected boolean verifierSexe(){
        if(this.sexeIso==0){Statistiques.incrementerNbDeclarationParSexe(0); return true;}
        if(this.sexeIso==1){Statistiques.incrementerNbDeclarationParSexe(1); return true;}
        if(this.sexeIso==2){Statistiques.incrementerNbDeclarationParSexe(2); return true;}
        envoyerErreur(VERIF_SEXE);
        return false;
    }

    protected String genrer(){
        long isoEnString=sexeIso;
        switch ((int) isoEnString) {
            case 0: sexe="inconnu"; break;
            case 1: sexe="masculin"; break;
            case 2: sexe="féminin"; break;
            default: sexe=null; break;
        }return sexe;}

    @Override
    public String toString() {
        return "Déclaration{" +
                "Nom='" + nom + '\'' +
                "Prénom='" + prenom + '\'' +
                "Sexe='" + sexe + '\'' +
                "numéro de permis='" + numeroDePermis + '\'' +
                ", heures transferées du cycle précédent='" + heuresTransfereesDuCyclePrecedent + '\'' +
                ", cycle='" + cycle + '\'' ;
    }

}

