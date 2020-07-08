package semesterprojekt.demo.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Review
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    private String name;

    @Type(type="text")
    private String description;

    private Boolean verified = false;

    public Review(String topic, String name, String description)
    {
        this.topic = topic;
        this.name = name;
        this.description = description;
    }

    public Review(String topic, String name, String description, Boolean verified)
    {
        this.topic = topic;
        this.name = name;
        this.description = description;
        this.verified = verified;
    }

    public Review()
    {

    }
}
