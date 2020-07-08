package semesterprojekt.demo.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NavigationBar
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String urlPath;

    public NavigationBar(String name, String urlPath)
    {
        this.name = name;
        this.urlPath = urlPath;
    }
}
