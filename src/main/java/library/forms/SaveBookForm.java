package library.forms;

import library.model.Book;
import lombok.Data;

@Data
public class SaveBookForm {
    private String id;
    private String title;
    private String author;
    private String articleType;

    public SaveBookForm(String id, String title, String author, String articleType) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.articleType = articleType;
    }

    public SaveBookForm(){}
    public SaveBookForm(Book book){
        this(Long.toString(book.getId()),book.getTitle(),book.getAuthor(),book.getArticleType());
    }

    public Book toBook(){
        return new Book(title,author,articleType);
    }
}
