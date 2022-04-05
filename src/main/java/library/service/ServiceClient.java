package library.service;

import library.model.*;
import library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    public void returnEmprunt(Client client, long bookId, LocalDateTime dateReturn) {
        for (Emprunt emprunt : client.getEmprunts()) {
            if (emprunt.getExemplaire().getArticle().getId() == bookId) {
                emprunt.setReturn(true);
                emprunt.getExemplaire().getArticle().setNombreExemplaires(emprunt.getExemplaire().getArticle().getNombreExemplaires() + 1);
                emprunt.getExemplaire().setBorrowed(false);
                emprunt.setDateReturn(dateReturn);
                articleRepository.save(emprunt.getExemplaire().getArticle());
                exemplaireRepository.save(emprunt.getExemplaire());
                empruntRepository.save(emprunt);

                java.time.Duration duration = java.time.Duration.between(emprunt.getDateReturn(), emprunt.getDateEmprunt());
                if (duration.toDays() > 21) {
                    Amende amende = new Amende(client, duration.toDays());
                    amendeRepository.save(amende);
                    client.addAmende(amende);
                }
            }
        }
    }
}
