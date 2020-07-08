package semesterprojekt.demo.Service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semesterprojekt.demo.Model.Review;
import semesterprojekt.demo.Repo.IReviewRepo;

@Service
public class ReviewServiceImpl implements IReviewService
{
    @Autowired
    IReviewRepo iReviewRepo;

    @Override
    public Iterable<Review> fetchAllReviews()
    {
        return iReviewRepo.findAll();
    }

    @Override
    public Review findReviewById(Long id)
    {
        return iReviewRepo.findAllById(id);
    }

    @Override
    public Review addReview(Review r)
    {
        return iReviewRepo.save(r);
    }

    @Override
    public void deleteReview(Long id)
    {
        iReviewRepo.deleteById(id);
    }

    @Override
    public void verifyReview(Review review)
    {
        Long id = review.getId();
        String topic = review.getTopic();
        String name = review.getName();
        String description = review.getDescription();

        iReviewRepo.verifyReview(topic, name, description, id);
    }
}
