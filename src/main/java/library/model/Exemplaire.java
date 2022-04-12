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

    private long empruntId;

    public Exemplaire(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Exemplaire{" +
                "id=" + id +
                ", article=" + article +
                ", isBorrowed=" + isBorrowed +
                ", empruntId=" + empruntId +
                '}';
    }
}
