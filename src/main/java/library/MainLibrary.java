package library;

import library.model.*;
import library.service.ServiceClient;
import library.service.ServiceLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class MainLibrary implements CommandLineRunner {

    @Autowired
    private ServiceLibrary serviceLibrary;

    @Autowired
    private ServiceClient serviceClient;

    public static void main(String[] args) {
        SpringApplication.run(MainLibrary.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

      final Article book = serviceLibrary.saveArticle(new Book("book","yan","2010-02-09","Roman","yanZhou",300));
      final Article cd = serviceLibrary.saveArticle(new CD("cd"));
      final Article dvd = serviceLibrary.saveArticle(new DVD("dvd"));

      System.out.println(serviceClient.findArticleById(book.getId()));

      List<Exemplaire> exemplaires = serviceLibrary.saveExemplaire(book, 10);
      book.setNombreExemplaires(exemplaires.size());
      serviceLibrary.saveArticle(book);
      book.setExemplaires(exemplaires);
      serviceLibrary.saveArticle(book);

      System.out.println(serviceClient.findArticleById(book.getId()));

      List<Object[]> books = serviceClient.findBookBySeach("2010");
      Object[] book1 = books.get(0);
      System.out.println(book1[0]);

      LibraryUser client = serviceLibrary.saveUser(new Client("Yan", "Zhou", 99));
      Emprunt emprunt = serviceLibrary.saveEmprunt(book,exemplaires, (Client) client, LocalDateTime.now());

        List<Object[]> emprunts = serviceClient.findEmpruntByClientId(client.getId());
        Object[] emprunt1 = emprunts.get(0);
        System.out.println(emprunt1[0]);

        serviceLibrary.addEmpruntToClient(emprunt.getId(), client.getId());
       //serviceLibrary.addEmpruntToClient(emprunt, (Client)client); 哪个好

       System.out.println(serviceClient.findClientById(client.getId()));
       //返回书
        serviceClient.returnEmprunt((Client) client,book.getId(),LocalDateTime.now());
        //创建罚款
    }
}
