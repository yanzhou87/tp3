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
    @JoinColumn(name = "article_id")
    private Article article;
    private boolean isBorrowed = false;
}
