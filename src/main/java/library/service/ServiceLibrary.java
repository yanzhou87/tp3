package library.service;


import library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}

