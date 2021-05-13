package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testcase.Users.Log4j;
import testcase.Users.User;
import testcase.pages.*;

import static org.testng.AssertJUnit.*;

public class Gittigidiyortest {

    public static void main(String[] args) throws InterruptedException {


   // STEP 1 - www.gittigidiyor.com sitesi açılır.
    // STEP 2 - Ana sayfanın açıldığı kontrol edilir. Siteye login olunur
    //   STEP 3   - Login işlemi kontrol edilir.
     //   STEP 4  - Arama kutucuğuna bilgisayar kelimesi girilir.
   //  STEP 5 - Arama sonuçları sayfasından 2.sayfa açılır.
   //  STEP6 - 2.sayfanın açıldığı kontrol edilir.
  //   STEP 7 - Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.
   //    STEP 8       - Seçilen ürün sepete eklenir.
   //      STEP 9    - Ürün sayfasındaki fiyat ile sepette yer alan ürün fiyatının doğruluğu karşılaştırılır.
    //    STEP 10  - Adet arttırılarak ürün adedinin 2 olduğu doğrulanır.
    // STEP  11  - Ürün sepetten silinerek sepetin boş olduğu kontrol edilir.






        System.setProperty("webdriver.chrome.driver","././drivers/chromedriver");
       // WebDriver driver=new ChromeDriver();
       // driver.get("https://www.gittigidiyor.com/");
       // driver.manage().window().fullscreen();

     String title = "GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi";
        String email= "gostagultu@biyac.com";
        String password= "Tryout1234";
        String baseUrl ="https://www.gittigidiyor.com/";
       WebDriver webDriver = new ChromeDriver();
     Log4j.info("anasayfaya git");
       webDriver.get(baseUrl);
       HomePage homePage = new HomePage(webDriver);
     Log4j.info("giris sayfasina git");
     LoginPage loginPage = homePage.getLoginPage();
     Log4j.info("email: " + email + ", password: " + password);
     User user = new User(email, password);
     loginPage.login(user);
     Thread.sleep(1000);
     String accountButtonText = homePage.getAccountText();
     assertTrue(accountButtonText.contains("Hesabım"));
     Thread.sleep(1000);
     Log4j.info("arama yerine bilgisayar yazildi");
     SearchResultPage searchResultPage = homePage.search("bilgisayar");
     Thread.sleep(1000);
     searchResultPage.scrollToPage("7500");
     Log4j.info("arama sonuc ekrani geldi");
     Log4j.info("2. sayfaya gidildi");
     Thread.sleep(1000);
     searchResultPage.choosePage("2");
     assertTrue(webDriver.getCurrentUrl().contains("2"));
     Log4j.info("2. sayfa kontrolu");
     Thread.sleep(1000);
     Log4j.info("urun detayina gidis");
     ProductDetailsPage productDetailsPage = searchResultPage.goToProductDetails();
     Thread.sleep(1000);
     Log4j.info("ileride karsilastirmak icin urunun fiyati alindi");
     String productPrice = productDetailsPage.productPrice();
     productDetailsPage.scrollToPage("1000");
     Log4j.info("sepete ekle");
     productDetailsPage.addToBasket();
     Thread.sleep(1000);
     Log4j.info("sepete git");
     BasketPage basketPage = productDetailsPage.goToBasket();
     Log4j.info("match et biraz onceki urun fiyatiyla, sepetteki ayni mi");
     assertEquals(productPrice, basketPage.priceInTheBasket());
     Thread.sleep(1000);
     //urun sayisi 1 tane kaldiysa stockta hata
     Log4j.info("urun sayisini 2 ye cikar");
     basketPage.setNumberOfProducts();
     Thread.sleep(1000);
     assertTrue(basketPage.getTotalProduct().contains("2 Adet"));
     Log4j.info("urunleri sil");
     basketPage.deleteProduct();
     Thread.sleep(1000);
     String basketMessage = "Sepetinizde ürün bulunmamaktadır.";
     Log4j.info("urun silindi mi kontrol et");
     assertEquals(basketMessage, basketPage.isEmpty());





     //berkozdoruk






    }

}
