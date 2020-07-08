package semesterprojekt.demo.Repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import semesterprojekt.demo.Model.Contact;

import javax.transaction.Transactional;

public interface IContactRepo extends CrudRepository<Contact, Long>
{
    Contact findAllById (Long id);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("UPDATE Contact c SET " +
            "c.firstName=:fN, " +
            "c.lastName=:lN, " +
            "c.phoneNumber=:pN, " +
            "c.email=:email, " +
            "c.adresse=:adresse " +
            "WHERE c.id =:id")
    void updateContactInfoById(
            @Param("fN") String firstName,
            @Param("lN") String lastName,
            @Param("pN") int phoneNumber,
            @Param("email") String email,
            @Param("adresse") String adresse,
            @Param("id") Long id);
}
