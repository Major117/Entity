package fr.laposte.entity.repository;

import fr.laposte.entity.model.Role;
import fr.laposte.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByLog(String log);

    Boolean existsByLog(String log);

    @Query("SELECT r.role FROM User r WHERE r.role = ?1")
    String findRole (String role);
}
