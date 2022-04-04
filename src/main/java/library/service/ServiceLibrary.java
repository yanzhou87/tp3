package library.service;


import library.model.*;
import library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


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
        return libraryUserRepository.save(libraryUseruser);
    }
}

