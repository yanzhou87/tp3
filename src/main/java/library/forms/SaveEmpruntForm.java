package library.forms;

import library.model.Emprunt;
import lombok.Data;

@Data
public class SaveEmpruntForm {
    private String id;
    private long clientId;
    private long articleId;

    public SaveEmpruntForm(String id, long clientId, long articleId) {
        this.id = id;
        this.clientId = clientId;
        this.articleId = articleId;
    }

    public SaveEmpruntForm() {
    }

    public SaveEmpruntForm(Emprunt emprunt) {
        this(Long.toString(emprunt.getId()),emprunt.getClient().getId(),emprunt.getExemplaire().getArticle().getId());
    }


}
