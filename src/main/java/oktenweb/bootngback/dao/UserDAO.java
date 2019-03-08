package oktenweb.bootngback.dao;


import oktenweb.bootngback.models.AccountCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository <AccountCredentials, Integer> {

    AccountCredentials findByUsername(String username);
}
