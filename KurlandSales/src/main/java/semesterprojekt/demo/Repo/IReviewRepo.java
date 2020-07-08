package semesterprojekt.demo.Repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import semesterprojekt.demo.Model.Review;

import javax.transaction.Transactional;

public interface IReviewRepo extends CrudRepository<Review, Long>
{
    Review findAllById(Long id);

    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("UPDATE Review r SET " +
            "r.topic=:topic, " +
            "r.name=:name, " +
            "r.description=:description, " +
            "r.verified= true " +
            "WHERE r.id =:id")
    void verifyReview(
            @Param("topic") String topic,
            @Param("name") String name,
            @Param("description") String description,
            @Param("id") Long id);
}
