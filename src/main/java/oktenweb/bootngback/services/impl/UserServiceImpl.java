package oktenweb.bootngback.services.impl;

import oktenweb.bootngback.dao.UserDAO;
import oktenweb.bootngback.models.AccountCredentials;
import oktenweb.bootngback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void save(AccountCredentials user) {
        if(user!=null) userDAO.save(user);
    }

    @Override
    public List<AccountCredentials> findAll() {
        return null;
    }

    @Override
    public AccountCredentials findOneById(Integer id) {
        return null;
    }

    // beacause  UserService extends UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username);
    }
}

