package semesterprojekt.demo.Bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import semesterprojekt.demo.Model.Servs;
import semesterprojekt.demo.Repo.IServsRepo;
import semesterprojekt.demo.Service.IServsService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServsBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    IServsRepo servsRepo;

    private List<Servs> createServs()
    {

        List<Servs> servs = new ArrayList<>();
        //tobias was here
        Servs serv = new Servs();
        Servs servs1 = new Servs();
        serv.setName("Reperation af symaskiner");
        serv.setImage("https://previews.123rf.com/images/distrikt3/distrikt31210/distrikt3121000016/16193221-a-old-lady-using-a-mobile-phone.jpg");
        serv.setShortDescription("Her kan du få fikset din symaskine hvis den ikke virker");
        serv.setLongDescription("Med mine mange års erfaring med symaskiner, har jeg udvilket et stort viden, som gør mig i stand til at fikse forskellige former for symaskiner.");
        serv.setPrice("Pris sættes efter aftale");
        servs1.setName("IT hjælp til ældre");
        servs1.setImage("https://previews.123rf.com/images/distrikt3/distrikt31210/distrikt3121000016/16193221-a-old-lady-using-a-mobile-phone.jpg");
        servs1.setShortDescription("Her kan du læse om hvordan jeg kan hjælpe den ældre generation med it hjælp");
        servs1.setLongDescription("Er du en ældre borger, som har svært ved at følge med i den moderne verden? Jeg tilbyder min hjælp til forskellige it projekter, hvad end det er hjælp til din forskudsopgørelse eller navigation gennem borger.dk");
        servs1.setPrice("Pris sættes efter aftale");
        servs.add(serv);
        servs.add(servs1);
        return servs;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        System.out.println("context refreshed");
        servsRepo.saveAll(createServs());
    }
}
