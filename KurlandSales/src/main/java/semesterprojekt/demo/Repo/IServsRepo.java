package semesterprojekt.demo.Repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import semesterprojekt.demo.Model.Servs;

import javax.transaction.Transactional;

public interface IServsRepo extends CrudRepository<Servs, Long> {

    Servs findAllById(Long id);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("UPDATE Servs s SET " +
            "s.name=:name, " +
            "s.price=:price, " +
            "s.shortDescription=:sD, " +
            "s.longDescription=:lD, " +
            "s.servsFileName=:sF, " +
            "s.image=:img " +
            "WHERE s.id =:id")
    void updateServsInfoById(
            @Param("name") String name,
            @Param("price") String price,
            @Param("sD") String shortDescription,
            @Param("lD") String longDescription,
            @Param("sF") String productFileName,
            @Param("img") String productImage,
            @Param("id") Long id);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("UPDATE Servs s SET " + "" +
            "s.name=:name, " +
            "s.price=:price, " +
            "s.shortDescription=:sD, " +
            "s.longDescription=:lD " +
            "WHERE s.id =:id")
    void updateServsInfoByIdWithoutImage(
            @Param("name") String name,
            @Param("price") String price,
            @Param("sD") String shortDescription,
            @Param("lD") String longDescription,
            @Param("id") Long id);


    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("SELECT s FROM Servs s " +
            "WHERE lower(s.name) LIKE %:search% OR lower(s.shortDescription) LIKE %:search% OR lower(s.longDescription) LIKE %:search%")
    Iterable<Servs> searchAll(
            @Param("search") String search);



}
