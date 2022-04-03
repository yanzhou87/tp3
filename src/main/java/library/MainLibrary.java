package library;

import library.model.Article;
import library.repository.*;
import library.service.ServiceLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MainLibrary implements CommandLineRunner {

    @Autowired
    private ServiceLibrary serviceLibrary;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private AmendeRepository amendeRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainLibrary.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

      final Article book = serviceLibrary.saveBook("book");

    //    System.out.println(articleRepository.findArticleById(book.getId()));
//      long cdId = serviceLibrary.saveCD("cd");
//      long dvdId = serviceLibrary.saveDVD("dvd");
//      serviceLibrary.saveExemplaire(bookId, 10);
//
//      List<Object[]> books = articleRepository.findBookByName("book");
//      Object[] book = books.get(0);
//
//      long clientId = serviceLibrary.saveClient("Yan", "Zhou");
//      long empruntId = serviceLibrary.saveEmprunt(bookId, clientId);
//
//      Optional<Client> client = LibraryUserRepository.findClientByIdWithEmprunts(clientId);
    }
}
