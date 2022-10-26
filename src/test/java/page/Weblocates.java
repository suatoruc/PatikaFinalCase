package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.nio.charset.StandardCharsets;
import java.util.*;


public class Weblocates {
    private WebDriver driver= Driver.getDriver();
    static String secilenUrun;
    static String seller1;
    static String seller2;
    static String seller3;
    static String productTitle1;
    static String productTitle2;
    static String productTitle3;
    static String originalWindowHandle;
    List<WebElement>urunler;


    By header_myAccount_button= By.id("myAccount");
    By login_UnderMyAccount_button= By.id("login");
    By loginPage_epostaOrTelefonNumber_inputBox=By.xpath("//input[@name='username']");
    By loginPage_error_text=By.xpath("//div[contains(text(),'Beklenmeyen bir hata oluştu.')]");

    By loginPage_sifre_inputBox=By.xpath("//input[@id='txtPassword']");
    By loginPage_girisyap2_button=By.cssSelector("[id='btnEmailSelect']");
    By loginPage_facebook_button=By.cssSelector("[id='btnFacebook']");
    By facebookPage_email_inputBox=By.id("email");
    By facebookPage_password_inputBox=By.id("pass");
    By facebookPage_girisYap_button=By.id("loginbutton");
    By header_searchBox=By.cssSelector("[role='combobox']>[type='text']");
    By headerAccount_button=By.cssSelector("[title='Hesabım']");
    By productList=By.xpath("//li[starts-with(@class,'productListContent-z')]");

    By productPage_header_title_text=By.xpath("//header[@class=\"title-wrapper\"]//h1");
    By productPage_header_sellerName_text=By.xpath("//span[@class=\"seller\"]//a");


    By productPage_otherSeller_addToCard_button=By.xpath("(//div[@class='marketplace-list']//tr//span[text()='Sepete Ekle'])");
    By productPage_addToCart_button=By.id("addToCart");
    By productPage_afterAddCart_closePopUp_button=By.cssSelector("a[class='checkoutui-Modal-2iZXl']");
    By header_cartPage_button=By.id("shoppingCart");
    By header_logout_button=By.xpath("//a[contains(text(),'Çıkış Yap')]");
    By cartPage_delete_product_button=By.cssSelector("[class='product_delete_1zR-0']");










    @Step("Hepsiburada Sayfası Acılır")
    public void hepsiburada_sayfasini_ac(){
        driver.get(ConfigReader.getProperty("baseUrl"));

    }

   @Step("Kullanici Olarak Login Olunur")
    public void kullanici_girisi_yap(){
        ReusableMethods.hover(driver.findElement(header_myAccount_button));
        driver.findElement(login_UnderMyAccount_button).click();
         driver.findElement(loginPage_epostaOrTelefonNumber_inputBox).
               sendKeys(ConfigReader.getProperty("username")+ Keys.ENTER);
         ReusableMethods.waitFor(3);
         List<WebElement>errorMesaj=driver.findElements(loginPage_error_text);
        if(errorMesaj.size()==0){
           driver.findElement(loginPage_sifre_inputBox).
                   sendKeys(ConfigReader.getProperty("password"));
           driver.findElement(loginPage_girisyap2_button).click();
         }else{
          facebookla_login_ol();
       }


    }
    @Step("Kullanıcı Olarak Giriş Yapıldığı Dogrulanır")
    public void kullanici_girisin_onayla(){
       ReusableMethods.waitFor(8);
        // ReusableMethods.waitForVisibility(driver.findElement(header_myAccount_button),10);
        Assert.assertTrue(driver.findElement(headerAccount_button).isDisplayed());

    }

    @Step("{0} Urun Araması Yapılır")
    public void urun_aramasi_yap(String urunAdi){
        driver.findElement(header_searchBox).
                sendKeys(urunAdi+Keys.ENTER);

    }
    @Step("Arama Cubugunda {0} Araması Yapıldıgı Dogrulanır")
    public void arama_islemini_dogrulama(String urunAdi){
        ReusableMethods.waitFor(5);
        String expected=urunAdi;
        String actual=driver.findElement(header_searchBox).getAttribute("value");
        Assert.assertEquals(expected,actual);
        originalWindowHandle=driver.getWindowHandle();


    }

    @Step("Urun Sayfasından Rastgele Bir Urun Secilir")
    public void sayfadan_rastgeleUrun_sec(){
        List<WebElement> urunListesi=driver.findElements(productList);
        int secilenUrunSirasi=random_urun_sec(urunListesi.size());
        String dinamikPath="(//li[starts-with(@class,'productListContent-z')])["+secilenUrunSirasi+"]";
        WebElement urun=driver.findElement(By.xpath(dinamikPath));
        ReusableMethods.scrollIntoView(urun);
        secilenUrun=driver.findElement(By.xpath(dinamikPath+"//h3")).getText();
        secilenUrun=secilenUrun.substring(0,60)+"Fiyatı";
        urun.click();
          }

    private int random_urun_sec(int size) {
        Random random=new Random();
        int secilenUrunSirasi=random.nextInt(size);
        if(secilenUrunSirasi==0){random_urun_sec(size);}
return secilenUrunSirasi;
    }

    @Step("Secilen Urunun Detay Sayfasi Acilir")
    public void urun_sayfasina_gecis_yap(){
        ReusableMethods.switchToWindow(secilenUrun);
         }
    @Step("Urunu Satan Diger Satıcılar Kontrol Edilir")
    public void diger_satici_sayisini_kontrolEt_urunSec(){

        driver.findElement(productPage_addToCart_button).sendKeys(Keys.PAGE_DOWN);
        List<WebElement>digerSaticilar=driver.findElements(productPage_otherSeller_addToCard_button);
        if(digerSaticilar.size()<2){
            System.out.println("iki alternatif satıcı yoktur.");
                 arama_sayfasina_don("laptop");
                 sayfadan_rastgeleUrun_sec();
                 ReusableMethods.switchToWindow(secilenUrun);
                 diger_satici_sayisini_kontrolEt_urunSec();
        }else {
            productTitle1 = driver.findElement(productPage_header_title_text).getText();
            seller1 = driver.findElement(productPage_header_sellerName_text).getText().toLowerCase();
            seller1 = string_manipule_et(seller1);
            driver.findElement(productPage_addToCart_button).click();
            ReusableMethods.waitForClickablility(productPage_afterAddCart_closePopUp_button, 10);
            driver.findElement(productPage_afterAddCart_closePopUp_button).click();

            diger_satici_secimi_yap(1);
            diger_satici_secimi_yap(2);


        }
    }
   @Step("Secilen Urunu Satan {0}.Satıcının Urunu Sepete Eklenir")
    public void diger_satici_secimi_yap(int saticiSirasi){
        String path="(//div[@class='marketplace-list']//tr//button[@class='add-to-basket button small'])["+saticiSirasi+"]";
        String seller_name_path="(//div[@class='marketplace-list']//div[@class='merchant-info']//a[@rel='nofollow'])["+saticiSirasi+"]";
        if(saticiSirasi==1){
      seller2=driver.findElement(By.xpath(seller_name_path)).getText().toLowerCase();
       seller2=string_manipule_et(seller2);

    }else {
            seller3=driver.findElement(By.xpath(seller_name_path)).getText().toLowerCase();
            seller3=string_manipule_et(seller3);
            }
        driver.findElement(By.xpath(path)).click();
        ReusableMethods.waitForClickablility(productPage_afterAddCart_closePopUp_button,15);
        driver.findElement(productPage_afterAddCart_closePopUp_button).click();
    }
    @Step("Sepet Sayfasina Gidilir")
    public void sepet_sayfasina_git(){
        ReusableMethods.scrollIntoView(driver.findElement(header_cartPage_button));
        ReusableMethods.waitForClickablility(driver.findElement(header_cartPage_button),5);
        driver.findElement(header_cartPage_button).click();

    }
    @Step("Secilen Urunu Satan Yeterli Satıcı Olmadigindan Başka Bir Urun Secmek Icin {0} Araması Yapılan Sayfaya Geri Donulur")
    public void arama_sayfasina_don(String arananUrun){
        driver.close();
        driver.switchTo().window(originalWindowHandle);
    }
    @Step("Sepetteki Urunler Satici Adi ve Urun Title ile Dogrulanir")
    public void sepet_urun_onaylama(){
        String actualThirdSellerName=driver.findElement(By.xpath("(//section[@id=\"onboarding_item_list\"]//span/a)[1]")).getText();;
        actualThirdSellerName=string_manipule_et(actualThirdSellerName);
       // System.out.println(actualThirdSellerName);
        String actualSeconSellerName=driver.findElement(By.xpath("(//section[@id=\"onboarding_item_list\"]//span/a)[2]")).getText();;
        actualSeconSellerName=string_manipule_et(actualSeconSellerName);
       // System.out.println(actualSeconSellerName);
        String actualFirstSellerName=driver.findElement(By.xpath("(//section[@id=\"onboarding_item_list\"]//span/a)[3]")).getText();;
        actualFirstSellerName=string_manipule_et(actualFirstSellerName);
      //  System.out.println(actualFirstSellerName);
        String actualProductTitle=driver.findElement(By.xpath("(//div[@class=\"product_name_3Lh3t\"]/a)[1]")).getText().replaceAll("i̇","i");;
        ReusableMethods.waitForVisibility(driver.findElement(By.xpath("(//div[@class=\"product_name_3Lh3t\"]/a)[1]")),5);
        Assert.assertEquals(actualFirstSellerName,seller1);
      Assert.assertEquals(actualProductTitle,productTitle1);
        Assert.assertEquals(actualSeconSellerName,seller2);
        Assert.assertEquals(actualThirdSellerName,seller3);
       // Assert.assertTrue(actualFirstSellerName.contains(seller1));
       // Assert.assertTrue(actualSeconSellerName.contains(seller2));
       // Assert.assertTrue(actualThirdSellerName.contains(seller3));


    }
    private static String string_manipule_et(String str) {
        str = str.toUpperCase().trim();
        String arr[] = str.split("");
        List<String>liste=new ArrayList<>(Arrays.asList(arr));
        System.out.println(liste.toString());
        for (int i = 0; i < liste.size()-1 ; i++) {
            System.out.println(liste.get(i));
            liste.get(i).replaceAll("İ","I").
                    replaceAll("Ş", "S").
                    replaceAll("Ç","C").
                    replaceAll("Ö","O").
                    replaceAll("Ü","U");

            if((liste.get(i)+liste.get(i+1)).equals("İ ̇")){
              System.out.println(liste.toString());
             liste.remove(i);
              System.out.println(">"+liste.toString());
        }
          //  if(liste.get(i).equalsIgnoreCase(" ̇")){liste.remove(i);}
            //System.out.println(">>"+liste.toString());
      // for (String a:liste ) {
      //       if(a.equals("i")) {
      //           System.out.println(liste.toString());
      //           liste.remove(liste.indexOf("i")+1);
      //        //   a.
      //        //           replaceAll("İ", "I").
      //        //           replaceAll("Ş", "S").
      //        //           replaceAll("Ç", "C").
      //        //           replaceAll("Ö", "O").
      //        //           replaceAll("Ü", "U").
      //        //           replaceAll("i ", "i");
      //           System.out.println(a);
      //           System.out.println(liste.toString());
//
      //       }
       }
        String depo = str.substring(0, 1).toUpperCase();

        for (int i = 1; i < arr.length; i++) {
            if (liste.get(i).equals(" ")||liste.get(i).equals("-")) {
                depo += str.substring(i, i + 2).toUpperCase();
                i++;
            } else {
                depo += str.substring(i, i + 1);
            }
        }
        return depo;

    }
    @Step("Otomasyonla Siteye Girilemediginden Facebook Baglantisi Ile Login Olundu.")
    public void facebookla_login_ol(){
        ReusableMethods.scrollIntoView(driver.findElement(loginPage_facebook_button));
        ReusableMethods.waitForClickablility(driver.findElement(loginPage_facebook_button),3);
          driver.findElement(loginPage_facebook_button).click();
          driver.findElement(facebookPage_email_inputBox).
                  sendKeys(ConfigReader.getProperty("username"));
          driver.findElement(facebookPage_password_inputBox).
                  sendKeys(ConfigReader.getProperty("fpassword"));
          driver.findElement(facebookPage_girisYap_button).click();
    }
    public void kullanici_logout_olur(){

        driver.switchTo().window(originalWindowHandle);
        ReusableMethods.scrollIntoView(driver.findElement(headerAccount_button));
        ReusableMethods.hover(driver.findElement(headerAccount_button));
        driver.findElement(header_logout_button).click();
    }
    public void kullanici_logout_islemini_dogrular(){
        ReusableMethods.waitFor(5);
        Assert.assertTrue(driver.findElement(header_myAccount_button).isDisplayed());
        driver.quit();
    }
    public void sepetteki_urunleri_sil(){
        ReusableMethods.waitFor(3);
        urunler=driver.findElements(cartPage_delete_product_button);
        if (urunler.size()!=0){
            urunler.get(0).click();
            sepetteki_urunleri_sil();
        }

    }


}
