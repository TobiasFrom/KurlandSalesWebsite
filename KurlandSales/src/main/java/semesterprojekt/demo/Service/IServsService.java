package semesterprojekt.demo.Service;

import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.Servs;

import java.io.IOException;

public interface IServsService {

    Iterable<Servs> findAll();

    Servs findServsById(Long id);

    Servs createServs(Servs s);

    Servs saveNewSercs(String name,
                       String shortDescription,
                       String longDescription,
                       String price,
                       MultipartFile file)throws IOException;

    void deleteServs(Long id);

    Iterable<Servs> searchServices(String searchAll);

    void editServs(String name,
                   String shortDescription,
                   String longDescription,
                   String price,
                   MultipartFile imageFile,
                   Long id)throws IOException;


}