package semesterprojekt.demo.Bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import semesterprojekt.demo.Model.AboutModel;
import semesterprojekt.demo.Repo.IAboutRepo;

import java.util.ArrayList;
import java.util.List;

@Component
public class AboutBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    IAboutRepo aboutRepo;

    private List<AboutModel> createAbout()
    {
        List<AboutModel> ab = new ArrayList<>();
        AboutModel aboutModel = new AboutModel();
        aboutModel.setHeading("Jeg hedder Kenneth Kurland. Jeg er en mand på 61, som elsker " +
                "at holde mig igang. Jeg har siden en meget ung alder, haft en passion for at reparere alt fra maskiner " +
                "til hverdags problemer. Derudover har jeg en stor viden inden for international handel.");
        aboutModel.setProfileImage("https://1stpoker.dk/wp-content/uploads/2016/12/15451392_750319965115789_1067922773_n.jpg");
        ab.add(aboutModel);

        return ab;
    }



    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        aboutRepo.saveAll(createAbout());

    }
}
