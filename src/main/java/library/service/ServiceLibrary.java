package library.service;


import library.model.*;
import library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class ServiceLibrary {

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

    public Emprunt saveEmprunt(Article article, List<Exemplaire> exemplaires, Client client, LocalDateTime date) {
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

    public void addEmpruntToClient(long empruntId, long clientId) {
        var empruntOpt = empruntRepository.findEmpruntById(empruntId);
        var clientOpt = libraryUserRepository.findClientById(clientId);

        Emprunt emprunt = empruntOpt.get();
        Client client = clientOpt.get();

        client.addEmprunt(emprunt);
        libraryUserRepository.save(client);

    }
//    public void addEmpruntToClient(Emprunt emprunt, Client client) {
//
//        client.addEmprunt(emprunt);
//        libraryUserRepository.save(client);
//
//    }
}

