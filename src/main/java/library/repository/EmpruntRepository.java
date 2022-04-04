package library.repository;

import library.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmpruntRepository extends JpaRepository<Emprunt,Long> {
    @Query("select e from Emprunt e where e.id = :empruntId")
    Optional<Emprunt> findEmpruntById(@Param("empruntId") long empruntId);
}
