package semesterprojekt.demo.Service.AboutService;

import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.AboutModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IAboutService
{
    List<AboutModel> fetchTextAndProfileImage();

    AboutModel saveProfileImage(MultipartFile file, String heading)throws IOException;

    AboutModel findAllById(Long id);

    AboutModel updateAboutSubmit(MultipartFile imageFile, String heading, Long tmpId)throws IOException;

    void deleteAbout(Long id);

}
