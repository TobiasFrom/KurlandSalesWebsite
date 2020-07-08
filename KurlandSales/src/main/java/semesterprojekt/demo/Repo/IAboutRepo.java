package semesterprojekt.demo.Repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import semesterprojekt.demo.Model.AboutModel;

import javax.transaction.Transactional;


public interface IAboutRepo extends CrudRepository<AboutModel, Long>
{
    AboutModel findAllById (Long id);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("UPDATE AboutModel a SET " +
            "a.heading=:hD, " +
            "a.fileName=:fN, " +
            "a.profileImage=:pI " +
            "WHERE a.id =:id")
    void updateAboutInfoById(
            @Param("hD") String heading,
            @Param("fN") String fileName,
            @Param("pI") String profileImage,
            @Param("id") Long id);


    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("UPDATE AboutModel a SET " +
            "a.heading=:hD " +
            "WHERE a.id =:id")
    void updateAboutInfoByIdWithOutImage(
            @Param("hD") String heading,
            @Param("id") Long id);

}
