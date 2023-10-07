package tr.com.huseyinaydin.expensemanager.repository;

import tr.com.huseyinaydin.expensemanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot.
 *
 */

public interface UserRepository extends JpaRepository<User, Long> {

    //SELECT * FROM tbl_users WHERE email = ?
    Optional<User> findByEmail(String email);
}