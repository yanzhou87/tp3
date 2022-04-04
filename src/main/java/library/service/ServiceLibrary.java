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

    public Article saveBook(String name) {
        return articleRepository.save(new Book(name));
    }

    public CD saveCD(String CdName) {
        return articleRepository.save(new CD(CdName));
    }

//    public Article saveDVD(String dvdName) {
//        return articleRepository.save(new DVD(dvdName));
//    }
//
//    public void saveExemplaire(Article article, int nbSave) {
//        for(int i = 0 ; i < nbSave ; i ++){
//            exemplaireRepository.save(new Exemplaire(article));
//        }
// //需要更改article里的备份数量
  //  }
}

