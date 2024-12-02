import java.util.ArrayList;

public class Resultats {
    protected boolean complet;
    protected int nombreErreurs;
    protected ArrayList<String> messagesErreurs = new ArrayList<String>();
    protected ArrayList<String> messageFinExecution = new ArrayList<String>();
    protected boolean executionFinie = false;

    public boolean estComplet() {return complet;}

    public void setComplet(boolean complet) {
        this.complet = complet;
    }

    public int getNombreErreurs() {
        return nombreErreurs;
    }

    public void setNombreErreurs(int nombreErreurs) {
        this.nombreErreurs = nombreErreurs;
    }

    public ArrayList<String> getMesssagesErreurs() {
        return messagesErreurs;
    }

    public void setMessage(String message) {
        this.messagesErreurs.add(message);
    }
    public void setMessageFinExecution(String message) {
        this.messageFinExecution.add(message);
    }

    public void supprimerMessages() {this.messagesErreurs.clear();}

    public void ajouterUneErreur(String message){
        setMessage(message);
        setNombreErreurs(this.nombreErreurs + 1);
    }

    public void stopperExecution(String message){
        if(!executionFinie){
            executionFinie = true;
            setMessageFinExecution(message);
        }
    }

    public boolean mettreFinALExecution(){
        if(executionFinie) {
            supprimerMessages();
            ajouterUneErreur(messageFinExecution.get(0));
            System.out.println(messageFinExecution.get(0));
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Resultats{" +
                "complet=" + complet +
                ", nombreErreurs=" + nombreErreurs +
                ", erreurs=" + messagesErreurs +
                '}';
    }
}
