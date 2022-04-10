package library.forms;

import lombok.Data;

@Data
public class ReturnArticleForm {
    private String id;
    private long clientId;
    private long articleId;

    public ReturnArticleForm(String id, long clientId, long articleId) {
        this.id = id;
        this.clientId = clientId;
        this.articleId = articleId;
    }

    public ReturnArticleForm() {
    }
}
