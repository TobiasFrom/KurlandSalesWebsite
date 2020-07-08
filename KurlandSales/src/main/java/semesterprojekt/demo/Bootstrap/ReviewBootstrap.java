package semesterprojekt.demo.Bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import semesterprojekt.demo.Model.Review;
import semesterprojekt.demo.Repo.IReviewRepo;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    IReviewRepo iReviewRepo;

    private List<Review> createReview()
    {
        List<Review> reviewList = new ArrayList<>();
        Review review = new Review("Reperation af køleskab", "Mathias Noah Blake From", "I am crazy about hats " +
                "these days. Some text about this blog entry. Fashion fashion and mauris neque quam, fermentum ut nisl vitae, convallis" +
                " maximus nisl. Sed mattis nunc id lorem euismod placerat. Vivamus porttitor" +
                " magna enim, ac accumsan tortor cursus at. Phasellus sed ultricies mi non congue ullam corper. Praesent tincidunt " +
                "sedtellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.", true);
        Review review2 = new Review("Hjælp til Computer-indstillinger", "Liam From Kurland Hønberg", "I am crazy " +
                "about hats these days. Some text about this blog entry. Fashion fashion and mauris neque quam, fermentum ut nisl vitae, " +
                "convallis maximus nisl. Sed mattis nunc id lorem euismod placerat. Vivamus porttitor" +
                " magna enim, ac accumsan tortor cursus at. Phasellus sed ultricies mi non congue ullam corper. Praesent tincidunt " +
                "sedtellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.", true);

        reviewList.add(review);
        reviewList.add(review2);

        return reviewList;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {

        System.out.println("REVIEW_BOOTSTRAP context refreshed");
        iReviewRepo.saveAll(createReview());
    }
}
