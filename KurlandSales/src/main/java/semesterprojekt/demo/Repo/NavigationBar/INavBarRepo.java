package semesterprojekt.demo.Repo.NavigationBar;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import semesterprojekt.demo.Model.NavigationBar;
import semesterprojekt.demo.Model.ProductCategories;

import javax.transaction.Transactional;
import java.util.Optional;

public interface INavBarRepo extends CrudRepository<NavigationBar, Long>
{

    NavigationBar findAllById (Long id);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("UPDATE NavigationBar n SET " +
            "n.name=:name " +
            "WHERE n.id =:id")
    void UpdateNavBarById(
            @Param("name") String name,
            @Param("id") Long id);

}
