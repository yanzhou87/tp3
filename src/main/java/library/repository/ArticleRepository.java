package library.repository;

import library.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long>{

    @Query("select a.id, a.title from Article a where a.id = :bookId")
     Optional<Object> findArticleById(@Param("bookId")long bookId);
}
