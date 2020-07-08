package semesterprojekt.demo.Repo;

import org.springframework.data.repository.CrudRepository;
import semesterprojekt.demo.Model.NewsModel;


public interface INewsRepo extends CrudRepository<NewsModel, Long>
{

}
