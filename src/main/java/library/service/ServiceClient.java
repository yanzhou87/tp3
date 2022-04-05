package library.service;

import library.model.Article;
import library.model.Client;
import library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
