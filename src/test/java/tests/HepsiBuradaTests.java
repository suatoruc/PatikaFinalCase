package tests;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.Weblocates;
import java.lang.reflect.Method;


@Listeners({AllureListener.class})
public class HepsiBuradaTests extends Weblocates {

@Story("HepsiBuradaTest Senaryo 1")
@Test(description = "Login olma,ürün secme ve doğrulama senaryosu")
@Description("Test Description:Login olup urun secme ve dogrulama")
public void testSenario01(Method method){
    hepsiburada_sayfasini_ac();
    kullanici_girisi_yap();
    kullanici_girisin_onayla();
    urun_aramasi_yap("laptop");
    arama_islemini_dogrulama("laptop");
    sayfadan_rastgeleUrun_sec();
    urun_sayfasina_gecis_yap();
    diger_satici_sayisini_kontrolEt_urunSec();
    sepet_sayfasina_git();
    sepet_urun_onaylama();
    sepetteki_urunleri_sil();
    kullanici_logout_olur();
    kullanici_logout_islemini_dogrular();

}
@Story("HepsiBuradaTest senaryosu 2")
@Test(description = "Login olmadan ürün secme ve doğrulama senaryosu")
@Description("Test Description:Login olmadan urun secme ve dogrulama")
public void testSenario02(Method method){

    hepsiburada_sayfasini_ac();
    urun_aramasi_yap("laptop");
    arama_islemini_dogrulama("laptop");
    sayfadan_rastgeleUrun_sec();
    urun_sayfasina_gecis_yap();
    diger_satici_sayisini_kontrolEt_urunSec();
    sepet_sayfasina_git();
    sepet_urun_onaylama();

}
}
