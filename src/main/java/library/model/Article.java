package library.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ARTICLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Article {
    @Id
    @GeneratedValue(generator = "article_seq")
    protected long id;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    protected List<Exemplaire> exemplaires;

    protected String title;
    protected String author;
    protected String yearPublication;
    protected String articleType;
    protected int nombreExemplaires = 1;

    public Article(String title) {
        this.title = title;
    }

    public abstract int dayEmprunt();

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublication='" + yearPublication + '\'' +
                ", articleType='" + articleType + '\'' +
                ", nombreExemplaires=" + nombreExemplaires +
                '}';
    }
}
