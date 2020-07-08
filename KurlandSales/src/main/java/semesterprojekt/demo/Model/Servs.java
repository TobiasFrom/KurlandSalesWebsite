package semesterprojekt.demo.Model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Servs
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String servsFileName;
    @Type(type = "text")
    private String image;

    private String shortDescription;
    private String longDescription;
    private String price;

    public Servs(String name, String image, String shortDescription, String longDescription, String price)
    {
        this.name = name;
        this.image = image;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
    }
}
