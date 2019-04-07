package oktenweb.bootngback.services;

import oktenweb.bootngback.dao.ContactDAO;
import oktenweb.bootngback.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService  {

    @Autowired
    private ContactDAO contactDAO;

    public void save(Contact contact){
        if(contact!=null){
            contactDAO.save(contact);
        }
    }
    public List<Contact> findAll(){
        return contactDAO.findAll();
    }
//    public List<Contact> findAllByName(String name) {
//        if (!name.isEmpty()) {
//            return contactDAO.findAllByName(name); // our customized method from ContactDAO
//        }
//        return null;
//    }
    public Contact getOne(int id){

        return contactDAO.getOne(id);
    }
}
