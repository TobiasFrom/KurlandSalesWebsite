package semesterprojekt.demo.Bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import semesterprojekt.demo.Model.NewsModel;
import semesterprojekt.demo.Repo.INewsRepo;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsBootstrap implements ApplicationListener<ContextRefreshedEvent>
{

    @Autowired
    INewsRepo newsRepo;

    private List<NewsModel> createNews()
    {
        List<NewsModel> nm = new ArrayList<>();
        NewsModel newsModel = new NewsModel();
        NewsModel newsModel1 = new NewsModel();
        newsModel.setImage("https://i.ibb.co/4dscPh8/Kurland-nyhed.png");
        newsModel1.setImage("https://i.ibb.co/7Wv57C9/kurland-tilbud.png");
        nm.add(newsModel);
        nm.add(newsModel1);

        return nm;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {

        newsRepo.saveAll(createNews());
    }
}
