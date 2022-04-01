package library;

import library.model.*;
import library.repository.*;
import library.service.ServiceLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

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

      long bookId = serviceLibrary.saveBook("book");
        System.out.println(serviceLibrary.findBookById(bookId));
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
