package semesterprojekt.demo.Service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.NewsModel;
import semesterprojekt.demo.Repo.INewsRepo;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;

@Log
@Service
public class NewsServiceImpl implements INewsService
{
    @Autowired
    INewsRepo iNewsRepo;

    @Override
    @Transactional
    public NewsModel saveImage(MultipartFile imageFile)throws IOException
    {
        //Converting imageFile into String
        if(!imageFile.isEmpty())
        {
            NewsModel newsModel = new NewsModel();
            byte [] byteArr = imageFile.getBytes();
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedImage = "data:image/png;base64," + encoder.encodeToString(byteArr);

            System.out.println(encodedImage);

            newsModel.setFileName(imageFile.getOriginalFilename());
            newsModel.setImage(encodedImage);
            iNewsRepo.save(newsModel);
        }
        return null;
    }

    @Override
    public Iterable<NewsModel> fetchAllNews()
    {
        return iNewsRepo.findAll();
    }

    @Override
    public void deleteSpecificNews(Long id)
    {
        iNewsRepo.deleteById(id);
    }
}
