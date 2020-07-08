package semesterprojekt.demo.Service;

import semesterprojekt.demo.Model.Contact;


public interface IContactService {

    Iterable<Contact> findAll();
    Contact findContactById(Long id);
    Contact addContact(Contact k);
    Contact editContact(Contact k);
    void updateContact(Contact contact);
    void deleteContact(Long id);

}
