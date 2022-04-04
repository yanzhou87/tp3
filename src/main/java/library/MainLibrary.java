package library;

import library.model.*;
import library.repository.*;
import library.service.ServiceLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


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

      final Article book = serviceLibrary.saveArticle(new Book("book"));

       System.out.println(articleRepository.findArticleById(book.getId()));

      final Article cd = serviceLibrary.saveArticle(new CD("cd"));
      final Article dvd = serviceLibrary.saveArticle(new DVD("dvd"));

      List<Exemplaire> exemplaires = serviceLibrary.saveExemplaire(book, 10);
      book.setNombreExemplaires(exemplaires.size());
      serviceLibrary.saveArticle(book);
      book.setExemplaires(exemplaires);
      serviceLibrary.saveArticle(book);
//      List<Object[]> books = articleRepository.findBookByName("book");
//      Object[] book = books.get(0);
//
//      long clientId = serviceLibrary.saveClient("Yan", "Zhou");
//      long empruntId = serviceLibrary.saveEmprunt(bookId, clientId);
//
//      Optional<Client> client = LibraryUserRepository.findClientByIdWithEmprunts(clientId);
    }
}
