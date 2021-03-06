package library.service;

import library.model.*;
import library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceClient {

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

    public Optional<Article> findArticleById(long id) {
        return articleRepository.findArticleById(id);
    }

    public List<Object[]> findBookBySeach(String seach) {
        return articleRepository.findBookBySeach(seach);
    }

    public Optional<Client> findClientById(long id) {
        return libraryUserRepository.findClientById(id);
    }

    public List<Object[]> findEmpruntByClientId(long id) {
        return empruntRepository.findEmpruntByClientId(id);
    }

    public void returnEmprunt(Client client, long bookId, LocalDate dateReturn) {

        for (Emprunt emprunt : client.getEmprunts()) {
            if (emprunt.getExemplaire().getArticle().getId() == bookId) {
                emprunt.setReturn(true);
                emprunt.getExemplaire().getArticle().setNombreExemplaires(emprunt.getExemplaire().getArticle().getNombreExemplaires() + 1);
                emprunt.getExemplaire().setBorrowed(false);
                emprunt.setDateReturn(dateReturn);

                articleRepository.save(emprunt.getExemplaire().getArticle());
                exemplaireRepository.save(emprunt.getExemplaire());
                empruntRepository.save(emprunt);

                long duration = ChronoUnit.DAYS.between(emprunt.getDateEmprunt(),emprunt.getDateReturn());

                if (duration > emprunt.getExemplaire().getArticle().dayEmprunt()) {
                    Amende amende = new Amende(client, duration);
                    amendeRepository.save(amende);
                    client.addAmende(amende);
                    Client client1 = libraryUserRepository.getClientWithAmendes(client.getId()).get();
                    client1.setEmprunts(libraryUserRepository.findClientById(client.getId()).get().getEmprunts());
                    libraryUserRepository.save(client1);
                }
             }
        }
    }

    public List<LibraryUser> findAllClients() {
        return libraryUserRepository.findAll();
    }

    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    public List<Emprunt> findAllEmprunts() {
        return empruntRepository.findAll();
    }

    public Exemplaire findExemplaireByid(long exemplaireId) {
        return exemplaireRepository.findById(exemplaireId).get();
    }

    public List<Exemplaire> findALLExemplairesByArticleId(long articleId) {
        return exemplaireRepository.findByArticleId(articleId);
    }
}
