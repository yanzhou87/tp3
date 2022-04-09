package library.forms;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReturnArticleForm {
    private String id;
    private long clientId;
    private long exemplaireId;
    private LocalDateTime dateReturn;

    public ReturnArticleForm(String id, long clientId, long exemplaireId,LocalDateTime dateReturn) {
        this.id = id;
        this.clientId = clientId;
        this.exemplaireId = exemplaireId;
        this.dateReturn = dateReturn;
    }

    public ReturnArticleForm() {
    }



}
