package semesterprojekt.demo.Bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import semesterprojekt.demo.Model.ProductCategories;
import semesterprojekt.demo.Model.ProductModel;
import semesterprojekt.demo.Repo.ProductRepo.ICategoriesRepo;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCategoriesBootstrap implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    ICategoriesRepo iCategoriesRepo;

    private List<ProductCategories> createProductCategories()
    {

        //Product Categories
        List<ProductCategories> productCategoriesList = new ArrayList<>();
        ProductCategories fjernsyn = new ProductCategories("Fjernsyn","https://www.lg.com/sa_en/images/tvs/md05860015/gallery/86SJ957V-Desktop_01-22717.jpg");
        ProductCategories køleskabe = new ProductCategories("Køleskabe", "https://imagehvidevarer.foetex.dk/retrievefile.axd?file=1374103&mh=350&mw=350&ps=FullSize&q=95&hash=0E926AAAB9C9AFE594276A391187E7003B6F738A");
        ProductCategories vin = new ProductCategories("Vin", "https://i0.wp.com/gastromand.dk/wordpress/wp-content/uploads/2017/09/Vin.png?resize=700%2C325&ssl=1");
        ProductCategories elapparater = new ProductCategories("Elapparater",  "https://shop11399.hstatic.dk/upload_dir/shop/category/el-apparater.png");

        //Products
        ProductModel fjersyn1 = new ProductModel("Phillips", 7500, "Good quality", "Really good quality actually", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", fjernsyn);
        ProductModel fjersyn2 = new ProductModel("Samsung", 6500.50, "Best quality", "The best quality in the world","https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", fjernsyn);
        ProductModel fjersyn3 = new ProductModel("LG", 5400, "Good quality", "Really good quality actually","https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", fjernsyn);
        ProductModel køleskabe1 = new ProductModel("Maffeno", 3000, "Low price", "perfect to store food", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", køleskabe);
        ProductModel køleskabe2 = new ProductModel("Fexez", 3800, "yes", "Pretty good actually", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com",  køleskabe);
        ProductModel køleskabe3 = new ProductModel("SENZ", 4000.5, "Amazing good", "amazingly perfect", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", køleskabe);
        ProductModel vin1 = new ProductModel("Amarone", 230, "nice", "really nice", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", vin);
        ProductModel vin2 = new ProductModel("bourgogne", 300, "Tasteful", "quality wine", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", vin);
        ProductModel vin3 = new ProductModel("Hardy's", 240, "Lækkert", "Virkelig lækker vin", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", vin);
        ProductModel vin4 = new ProductModel("Yellow Tail", 300, "Pragtfuld", "Lækker vin!", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", vin);
        ProductModel vin5 = new ProductModel("Jacobs creek", 430, "yep", "SMukt!", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", vin);
        ProductModel vin6 = new ProductModel("Beringer", 290, "God", "Udmærkert vin", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", vin);
        ProductModel elapparater1 = new ProductModel("Kaffe maskine", 501.50, "Brugbar", "Brugbar til kaffe", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", elapparater);
        ProductModel elapparater2 = new ProductModel("Baby alarmer", 730, "Effektiv", "Virkelig effektiv apparat", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", elapparater);
        ProductModel elapparater3 = new ProductModel("Støvsuger", 300, "Lige til brug", "Virker 100 % som den skal", "https://scontent-dfw5-1.cdninstagram.com/vp/c2cfb198dc0445f3d8d5b1bbecd7761c/5D54C1FB/t51.2885-19/s150x150/16463955_392110677817121_6298838546270650368_a.jpg?_nc_ht=scontent-dfw5-1.cdninstagram.com", elapparater);

        //fjernsyn
        fjernsyn.getProductModels().add(fjersyn1);
        fjernsyn.getProductModels().add(fjersyn2);
        fjernsyn.getProductModels().add(fjersyn3);


        //Købeskab
        køleskabe.getProductModels().add(køleskabe1);
        køleskabe.getProductModels().add(køleskabe2);
        køleskabe.getProductModels().add(køleskabe3);

        //vin
        vin.getProductModels().add(vin1);
        vin.getProductModels().add(vin2);
        vin.getProductModels().add(vin3);
        vin.getProductModels().add(vin4);
        vin.getProductModels().add(vin5);
        vin.getProductModels().add(vin6);

        //elapparater
        elapparater.getProductModels().add(elapparater1);
        elapparater.getProductModels().add(elapparater2);
        elapparater.getProductModels().add(elapparater3);

        //Add the categories to list
        productCategoriesList.add(fjernsyn);
        productCategoriesList.add(køleskabe);
        productCategoriesList.add(vin);
        productCategoriesList.add(elapparater);

        return productCategoriesList;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        System.out.println("PRODUCT_CATEGORIES context Refreshed");
        iCategoriesRepo.saveAll(createProductCategories());
    }
}
