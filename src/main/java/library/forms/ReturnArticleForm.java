package library.forms;

import library.model.Client;
import library.model.Exemplaire;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReturnArticleForm {
    private Client client;
    private Exemplaire exemplaire;
    private LocalDateTime dateReturn;

    public ReturnArticleForm(Client client, Exemplaire exemplaire, LocalDateTime dateReturn) {
        this.client = client;
        this.exemplaire = exemplaire;
        this.dateReturn = dateReturn;
    }

    public ReturnArticleForm() {
    }

    public ReturnArticleForm(Client client) {
        this.client = client;
    }
}
