package semesterprojekt.demo.Service;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.Servs;
import semesterprojekt.demo.Repo.IServsRepo;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;

@Log
@Service
public class ServsServiceImpl implements IServsService
{

    @Autowired
    IServsRepo servsRepo;

    @Override
    public Iterable<Servs> findAll()
    {
        return servsRepo.findAll();
    }

    @Override
    public Servs findServsById(Long id)
    {
        return servsRepo.findAllById(id);
    }

    @Override
    public Servs createServs(Servs s)
    {
        return servsRepo.save(s);
    }

    @Override
    public void editServs(String name,
                          String shortDescription,
                          String longDescription,
                          String price,
                          MultipartFile imageFile,
                          Long id)throws IOException {
        if (imageFile.isEmpty()) {
            servsRepo.updateServsInfoByIdWithoutImage(name, price, shortDescription, longDescription, id);
        } else {

            byte[] byteArr = imageFile.getBytes();
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedImage = "data:image/png;base64," + encoder.encodeToString(byteArr);


            servsRepo.updateServsInfoById(
                    name,
                    price,
                    shortDescription,
                    longDescription,
                    imageFile.getOriginalFilename(),
                    encodedImage,
                    id);

        }
    }

    @Override
    public void deleteServs(Long id)
    {
        servsRepo.deleteById(id);
    }

    @Override
    @Transactional
    public Servs saveNewSercs(String name,
                              String shortDescription,
                              String longDescription,
                              String price,
                              MultipartFile file) throws  IOException
    {
        byte [] byteArr = file.getBytes();
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedImage = "data:image/png;base64," + encoder.encodeToString(byteArr);

        if (!file.isEmpty())
        {
            Servs s = new Servs();
            s.setName(name);
            s.setServsFileName(file.getOriginalFilename());
            s.setShortDescription(shortDescription);
            s.setLongDescription(longDescription);
            s.setPrice(price);
            s.setImage(encodedImage);
            servsRepo.save(s);
        }
        return null;
    }

    private void saveImages (Servs s, MultipartFile multipartFile) throws IOException
    {
        byte [] byteArr = multipartFile.getBytes();
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedImage = "data:image/png;base64," + encoder.encodeToString(byteArr);

        s.setServsFileName(multipartFile.getOriginalFilename());
        s.setImage(encodedImage);

    }

    public Iterable<Servs> searchServices(String searchAll)
    {
        try
        {
            if(!searchAll.equals(""))
            {
                return servsRepo.searchAll(searchAll.toLowerCase());
            }
        } catch (Exception a)
        {
            log.info(String.valueOf(a));
        }

        return null;
    }

}
