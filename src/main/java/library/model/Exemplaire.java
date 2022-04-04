package library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Exemplaire {
    @Id
    @GeneratedValue(generator = "exemplaire_seq")
    private long id;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;
    private boolean isBorrowed = false;

    public Exemplaire(Article article) {
        this.article = article;
    }
}
