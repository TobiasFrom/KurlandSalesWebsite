package semesterprojekt.demo.Service;

import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.NewsModel;

import java.io.IOException;

public interface INewsService
{

    NewsModel saveImage(MultipartFile file)throws IOException;

    Iterable<NewsModel> fetchAllNews();

    void deleteSpecificNews(Long id);
}
