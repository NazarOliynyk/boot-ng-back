package oktenweb.bootngback.dao;

import oktenweb.bootngback.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactDAO extends JpaRepository<Contact, Integer>  {
    //List<Contact> findAllByName(String name);
}
