package library.service;


import library.model.*;
import library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class ServiceLibrary{

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
        for(int i = 0 ; i < nbSave ; i ++){
           Exemplaire exemplaire = exemplaireRepository.save(new Exemplaire(article));
           exemplaires.add(exemplaire);
        }
       return exemplaires;
    }

    public LibraryUser saveUser(LibraryUser libraryUseruser) {
        return  libraryUserRepository.save(libraryUseruser);
    }

    public Emprunt saveEmprunt(Article article, List<Exemplaire> exemplaires, Client client, LocalDateTime date){
        boolean isAddExemplaire = false;
        Emprunt emprunt = new Emprunt();

        for(Exemplaire exemplaire : exemplaires){
            if(!exemplaire.isBorrowed() && !isAddExemplaire){
                emprunt.setExemplaire(exemplaire);
                emprunt.setClient(client);
                emprunt.setDate(date);
                exemplaire.setBorrowed(true);
                exemplaireRepository.save(exemplaire);
            }
        }
        article.setNombreExemplaires(article.getNombreExemplaires()-1);
        articleRepository.save(article);

        return empruntRepository.save(emprunt);
    }
}

