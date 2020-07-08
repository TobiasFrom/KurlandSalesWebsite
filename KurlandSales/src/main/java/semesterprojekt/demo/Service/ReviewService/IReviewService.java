package semesterprojekt.demo.Service.ReviewService;

import org.springframework.stereotype.Service;
import semesterprojekt.demo.Model.Review;

@Service
public interface IReviewService
{
    Iterable<Review> fetchAllReviews();
    Review findReviewById(Long id);
    Review addReview(Review r);
    void deleteReview(Long id);
    void verifyReview(Review review);
}
