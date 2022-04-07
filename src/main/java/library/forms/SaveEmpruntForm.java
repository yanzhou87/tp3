package library.forms;

import library.model.Client;
import library.model.Emprunt;
import library.model.Exemplaire;
import lombok.Data;

@Data
public class SaveEmpruntForm {
    private String id;
    private Client client;
    private Exemplaire exemplaire;

    public SaveEmpruntForm(String id, Client client, Exemplaire exemplaire) {
        this.id = id;
        this.client = client;
        this.exemplaire = exemplaire;
    }

    public SaveEmpruntForm() {
    }

    public SaveEmpruntForm(Emprunt emprunt){
        this(Long.toString(emprunt.getId()),emprunt.getClient(),emprunt.getExemplaire());
    }

    public Emprunt toEmprunt(){
        return new Emprunt(client,exemplaire);
    }
}
