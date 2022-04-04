package library.service;


import library.model.*;
import library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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

    public CD saveCD(String CdName) {
        return articleRepository.save(new CD(CdName));
    }

    public Article saveDVD(String dvdName) {
        return articleRepository.save(new DVD(dvdName));
    }

//    public List<Exemplaire> saveExemplaire(Article article, int nbSave) {
//        List<Exemplaire> exemplaires = new ArrayList<>();
//        for(int i = 0 ; i < nbSave ; i ++){
//           Exemplaire exemplaire = exemplaireRepository.save(new Exemplaire(article));
//            exemplaires.add(exemplaire);
//        }
//       return exemplaires;
//    }
}

