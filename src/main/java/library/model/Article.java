package library.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ARTICLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Article_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Article{
    @Id
    @GeneratedValue(generator = "article_seq")
    protected long id;

    protected String title;
    protected String author;
    protected String yearPublication;
    protected String articleType;
    protected int nombreExemplaires = 0;


}
