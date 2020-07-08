package semesterprojekt.demo.Service.AboutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import semesterprojekt.demo.Model.AboutModel;
import semesterprojekt.demo.Repo.IAboutRepo;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class AboutServiceImpl implements IAboutService
{
    @Autowired
    private IAboutRepo iAboutRepo;

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public AboutModel saveProfileImage(MultipartFile file, String heading) throws IOException {

        if(!file.isEmpty())
        {
            AboutModel aboutModel = new AboutModel();
            byte [] byteArr = file.getBytes();
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedImage = "data:image/png;base64," + encoder.encodeToString(byteArr);
            aboutModel.setFileName(file.getOriginalFilename());
            aboutModel.setProfileImage(encodedImage);
            aboutModel.setHeading(heading);
            iAboutRepo.save(aboutModel);
        }
        return null;
    }

    @Override
    public AboutModel findAllById(Long id)
    {
        return iAboutRepo.findAllById(id);
    }

    @Override
    public List<AboutModel> fetchTextAndProfileImage()
    {
        return (List<AboutModel>) iAboutRepo.findAll();
    }

    @Override
    public AboutModel updateAboutSubmit(MultipartFile imageFile, String heading, Long tmpId)throws IOException
    {
        //Convert picture to Base64 String
        byte [] byteArr = imageFile.getBytes();
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedImage = "data:image/png;base64," + encoder.encodeToString(byteArr);
        if(!imageFile.isEmpty()) {
            iAboutRepo.updateAboutInfoById(heading, imageFile.getOriginalFilename(), encodedImage, tmpId);
        }else {
            iAboutRepo.updateAboutInfoByIdWithOutImage(heading,tmpId);
        }


        return null;
    }

    @Override
    public void deleteAbout(Long id) {
        iAboutRepo.deleteById(id);
    }
}
