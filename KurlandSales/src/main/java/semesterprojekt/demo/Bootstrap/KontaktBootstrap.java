package semesterprojekt.demo.Bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import semesterprojekt.demo.Model.Contact;
import semesterprojekt.demo.Repo.IContactRepo;

import java.util.ArrayList;
import java.util.List;

@Component
public class KontaktBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    IContactRepo kontaktRepo;

    private List<Contact> createContact()
    {

        List<Contact> contacts = new ArrayList<>();
        Contact kon = new Contact();
        kon.setFirstName("Kenneth");
        kon.setLastName("Kurland");
        kon.setPhoneNumber(53675310);
        kon.setEmail("k.kurland@me.com");
        kon.setAdresse("Vallerød Park 2a, 2960 Rungsted Kyst");
        contacts.add(kon);
        return contacts;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        System.out.println("KONTAKT_BOOTSTRAP context refreshed");
        kontaktRepo.saveAll(createContact());

    }
}
