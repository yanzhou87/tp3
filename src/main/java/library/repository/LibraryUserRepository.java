package library.repository;

import library.model.Client;
import library.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LibraryUserRepository extends JpaRepository<LibraryUser,Long> {

    @Query("select c from Client c left join fetch c.emprunts where c.id = :seachId")
    Optional<Client> findClientById(@Param("seachId") long id);
}
