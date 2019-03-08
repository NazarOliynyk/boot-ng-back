package oktenweb.bootngback.services;

import oktenweb.bootngback.models.AccountCredentials;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save(AccountCredentials accountCredentials);

    List<AccountCredentials> findAll();

    AccountCredentials findOneById(Integer id);

}
