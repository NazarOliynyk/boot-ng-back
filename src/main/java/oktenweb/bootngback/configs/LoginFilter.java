package oktenweb.bootngback.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import oktenweb.bootngback.dao.UserDAO;
import oktenweb.bootngback.models.AccountCredentials;
import oktenweb.bootngback.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }


//    During the authentication attempt,
// which is dealt by the attemptAuthentication method,
// we retrieve the username and password from the request.
// After they are retrieved, we use the AuthenticationManager to verify that these details match with an existing user.
// If it does, we enter the successfulAuthentication method.
// In this method we fetch the name from the authenticated user,
// and pass it on to TokenAuthenticationService, which will then add a JWT to the response.

    private AccountCredentials creds;

//    @Autowired
//    UserDAO userDAO;
//    @Bean
//    List<AccountCredentials> findAll(){
//        return new UserServiceImpl().findAll();
//    };
//    @Bean
//    AccountCredentials loadUserByUsername(String username){
//        return (AccountCredentials) new UserServiceImpl().loadUserByUsername(username);
//    };

//    UserServiceImpl userService = new UserServiceImpl();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        //this method react  on /login url  and retrive body from request
        //then map it to model AccountCredential
        creds = new ObjectMapper()
                .readValue(httpServletRequest.getInputStream(), AccountCredentials.class);

        System.out.println(creds);
        // find our object in database, parse it and ad it as auth object


        // then  get default method getAuthenticationManager()
        // and set Authentication object based on data from creds object

        // if auth process if success we jump to line 65 successfulAuthentication()
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUsername(),
                        creds.getPassword(),
                        Collections.emptyList()
                )
        );


    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        // if in prev method we was authenticate
        // we create token

        String jwtoken = Jwts.builder()
                .setSubject(auth.getName()) // this auth goes from the previous method
                                            // returned from that method
                .signWith(SignatureAlgorithm.HS512, "yes".getBytes())
                .setExpiration(new Date(System.currentTimeMillis() + 200000))
                .compact();
        //and add it to header
        ObjectMapper mapper = new ObjectMapper();
        res.addHeader("Authorization", "Bearer " + jwtoken);
        res.addHeader("UserLogged", "User.password " + creds.getUsername());
        res.addHeader("UserClass", "User.class " + creds.getClass().toString());
        res.addHeader("UserLoggedBody", "User.body " + mapper.writeValueAsString(creds));
        res.addHeader("UserLoggedTotal", "User " + creds);
       // AccountCredentials accountCredentials = (AccountCredentials)auth;
        res.addHeader("UserAuthenticated", "User " + auth.getName());

    }
}


