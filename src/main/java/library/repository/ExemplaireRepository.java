package library.repository;

import library.model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {
    @Query("select e from Exemplaire e where e.article.id = :articleId")
    List<Exemplaire> findByArticleId(@Param("articleId") long articleId);
}
