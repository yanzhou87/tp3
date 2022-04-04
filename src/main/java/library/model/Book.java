package library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("book")
public class Book extends Article {

    private String editor;
    private int numbrePages;

    public Book(String name) {
        this.title = name;
    }

    public Book(String title, String author, String yearPublication, String articleType, String editor, int numbrePages) {
        this.title = title;
        this.author = author;
        this.yearPublication = yearPublication;
        this.articleType = articleType;
        this.editor = editor;
        this.numbrePages = numbrePages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearPublication='" + yearPublication + '\'' +
                ", articleType='" + articleType + '\'' +
                ", nombreExemplaires=" + nombreExemplaires +
                ", editor='" + editor + '\'' +
                ", numbrePages=" + numbrePages +
                '}';
    }
}
