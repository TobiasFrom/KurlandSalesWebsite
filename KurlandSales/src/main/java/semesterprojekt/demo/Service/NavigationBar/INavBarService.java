package semesterprojekt.demo.Service.NavigationBar;

import org.springframework.stereotype.Service;
import semesterprojekt.demo.Model.NavigationBar;

import java.util.Optional;

@Service
public interface INavBarService
{
    NavigationBar addNavigationName(NavigationBar navigationBar);
    NavigationBar findNaviBarById(Long id);
    NavigationBar findNavigationName(Long id);
    Iterable<NavigationBar> fetchAllNames();
    void deleteNavigationName(Long id);
    void editNavBar(NavigationBar navigationBar);
}
