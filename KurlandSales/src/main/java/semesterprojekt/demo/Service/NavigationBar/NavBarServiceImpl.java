package semesterprojekt.demo.Service.NavigationBar;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semesterprojekt.demo.Model.NavigationBar;
import semesterprojekt.demo.Repo.NavigationBar.INavBarRepo;

import java.util.Optional;

@Log
@Service
public class NavBarServiceImpl implements INavBarService
{

    @Autowired
    INavBarRepo navBarRepo;

    @Override
    public NavigationBar addNavigationName(NavigationBar navigationBar)
    {
        return navBarRepo.save(navigationBar);
    }

    @Override
    public void deleteNavigationName(Long id)
    {
        navBarRepo.deleteById(id);
    }

    @Override
    public Iterable<NavigationBar> fetchAllNames()
    {
        return navBarRepo.findAll();
    }

    @Override
    public NavigationBar findNavigationName(Long id)
    {
        return navBarRepo.findAllById(id);
    }

    @Override
    public NavigationBar findNaviBarById(Long id) {
        return navBarRepo.findAllById(id);
    }

    @Override
    public void editNavBar(NavigationBar navigationBar)
    {
        Long id = navigationBar.getId();
        String name = navigationBar.getName();

        navBarRepo.UpdateNavBarById(name, id);
    }
}
