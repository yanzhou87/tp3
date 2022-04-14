package library.service;


import library.model.*;
import library.repository.*;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class ServiceLibrary {

    private ArticleRepository articleRepository;

    private LibraryUserRepository libraryUserRepository;

    private ExemplaireRepository exemplaireRepository;

    private EmpruntRepository empruntRepository;

    private AmendeRepository amendeRepository;

    public ServiceLibrary(ArticleRepository articleRepository, LibraryUserRepository libraryUserRepository, ExemplaireRepository exemplaireRepository, EmpruntRepository empruntRepository, AmendeRepository amendeRepository) {
        this.articleRepository = articleRepository;
        this.libraryUserRepository = libraryUserRepository;
        this.exemplaireRepository = exemplaireRepository;
        this.empruntRepository = empruntRepository;
        this.amendeRepository = amendeRepository;
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public List<Exemplaire> saveExemplaire(Article article, int nbSave) {
        List<Exemplaire> exemplaires = new ArrayList<>();
        for (int i = 0; i < nbSave; i++) {
            Exemplaire exemplaire = exemplaireRepository.save(new Exemplaire(article));
            exemplaires.add(exemplaire);
        }
        return exemplaires;
    }

    public LibraryUser saveUser(LibraryUser libraryUseruser) {
        return libraryUserRepository.save(libraryUseruser);
    }

    public Emprunt saveEmprunt(Article article, List<Exemplaire> exemplaires, Client client, LocalDate date) {
        boolean isAddExemplaire = false;
        Emprunt emprunt = new Emprunt();

        if (article.getNombreExemplaires() != 0) {
            for (Exemplaire exemplaire : exemplaires) {
                if (!exemplaire.isBorrowed() && !isAddExemplaire) {
                    emprunt.setExemplaire(exemplaire);
                    emprunt.setClient(client);
                    emprunt.setDateEmprunt(date);
                    exemplaire.setBorrowed(true);
                    exemplaireRepository.save(exemplaire);
                    isAddExemplaire = true;
                }
            }
        }

        article.setNombreExemplaires(article.getNombreExemplaires() - 1);
        articleRepository.save(article);
        return empruntRepository.save(emprunt);
    }

    @Transactional
    public void addEmpruntToClient(long empruntId, long clientId) {
        var empruntOpt = empruntRepository.findEmpruntById(empruntId);
        var clientOpt = libraryUserRepository.findClientById(clientId);

        Emprunt emprunt = empruntOpt.get();
        Client client = clientOpt.get();

        client.addEmprunt(emprunt);
        libraryUserRepository.save(client);

    }

    public EmpruntRepository getEmpruntRepository() {
        return empruntRepository;
    }

}

