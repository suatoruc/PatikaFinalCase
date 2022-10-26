
<img src="pngs/icon.png" align="right" />

# A101 **Practicum Final Case**
## *Hepsiburada.com* Sitesinde Yapılan Kullanıcı Test Senaryoları
Hepsiburada sitesi üzerinde iki farklı senaryo ile testlerimizi yaptık
- ***İlk Senaryo*** <br>

       - hepsiburada_sayfasini_ac();<br>
       - kullanici_girisi_yap();<br>
       - kullanici_girisin_onayla();<br>
       - urun_aramasi_yap("laptop");<br>
       - arama_islemini_dogrulama("laptop");<br>
       - sayfadan_rastgeleUrun_sec();<br>
       - urun_sayfasina_gecis_yap();<br>
       - diger_satici_sayisini_kontrolEt_urunSec();<br>
       - sepet_sayfasina_git();<br>
       - sepet_urun_onaylama();<br>
       - sepetteki_urunleri_sil();<br>
       - kullanici_logout_olur();<br>
       - kullanici_logout_islemini_dogrular();<br>
    
- ***İkinci Senaryo***<br>

       - hepsiburada_sayfasini_ac();<br>
       - urun_aramasi_yap("laptop");<br>
       - arama_islemini_dogrulama("laptop");<br>
       - sayfadan_rastgeleUrun_sec();<br>
       - urun_sayfasina_gecis_yap();<br>
       - diger_satici_sayisini_kontrolEt_urunSec();<br>
       - sepet_sayfasina_git();<br>
       - sepet_urun_onaylama();<br>


Testler iki senaryo halinde yapıldı. ilk senaryomuzdaki login olup ürün seçme şeklindeydi.<br>
test yapılan site [Hepsiburada](https://www.hepsiburada.com/)  özellikle login olma fonksiyonunu otomasyona kapatmış olduğu görüldü <br>
test çalıştırıldığında login ekranından yapılan post işleminin response olarak 403 hata kodu aldığı görüldü.  

![Login Olma Sırasında Site Otomasyona İzin Vermediginden Alınan Hatanın Status Kodu!](/pngs/403Hatasi.jpg "Login Olma Sırasında Site Otomasyona İzin Vermediginden Alınan Hatanın Status Kodu" )

 Otomasyonda bu aşamaya gelindeğinde kullandığımız driver üzerinde modifikasyon yapılarak bu aşama geçildi.

      ChromeOptions options=new ChromeOptions();
      options.addArguments("--disable-blink-features");
      options.addArguments("--disable-blink-features=AutomationControlled");
      options.addArguments("--disable-extensions");
      options.addArguments("−−lang=tur");
      driver = new ChromeDriver(options=options);

Bu adımda alternatif olarak facebook ile login olma da kod bloğuna entegre edildi.
![Facebook ile Alternatif Login İşlemi](/pngs/facebook.jpg "Facebook ile Alternatif Login İşlemi")

Her iki test senaryomuzda arama sonrası gelen ekranda rastgele bir ürün secilip secilen ürünün detay sayfasından 
iki farklı satıcıdan daha alınması step yapılırken sayfalar arası geçişler otomatik olarak yapmak için dinamik metotlar yazıldı<br>
rastgele secilen ürünün detay sayfasında alternatif iki satıcı bulana kadar otomasyon şartı sağlayan ürünü bulana kadar <br>
sayfalar arasında driver'ı taşıyıp kontrol edip şart sağlanana kadar geçişleri otomatik olarak yapıyor.

  [Test Ornegi] (https://recordit.co/GGWaKVOsX4 "İstenilen Şart Sağlanan Kadar Secilen Urun Sayfaları Arasında Geçişler" )

Testlerimiz çalışırken konsolda log kayıtlarıda görülmektedir. bu log kayıtlarını aynı zamanda test dosyalarımızın <br>
oldugu yerde de oluşturduğumuz ***Log*** dosyasına da kayıt ettik.






Bu Testler Çalıştırılırken Her Aşamada Doğrulamalar **Assertion** Kullanılarak Yapılmış,
Oluşturulan Metotlar Parametreli Kullanılarak Daha Dinamik Çalışılması Sağlanmıştır.

### Tesler Hazırlanırken

> - Proje **Java** Programlama Dilinde Yazılmıştır.
> - Yazılan Senaryolar da Web de **Selenium** ve **TestNG** Framework'ü Kullanılmıştır.
> - Framework Yapısı **Page Object Model(POM)** ile Dizayn Edilmiştir.
> - Framework Yapısı İçerisinde Yapılan Kodlamalar da **Object Oriented Programming(OOP)** Kullanılmıştır.
> - Raporlama Tool'u Olarak **Allure Report** Kullanılmıştır.
> - Log Kayıtları İçin  Kullanılan **Log4j** Kütüphanesi **Allure Report** İçine Entegre Edilerek Kullanılmıştır.
> - Proje de Her İki Test Senaryosunda da  Locate'ler **By Metod**'la Alınmış _Findby Anotation_'ı Kullanılmamıştır.
> - Her Aşamada **Assertion**'lar la Testin Akışı İle İlgili Doğrulamalar Yapılmıştır.
> - Testlerde Kullanılan Data'lar **Properties** Classı Kullanılarak Alınmıştır.
> - Test Aşamalarında Test Edilen Site Üzerinde Otomasyona İzin Verilmeyen Alanlar Driver'da Motifikasyon Yapılarak Geçilmiştir.


<img src="pngs/java.png" width="100px" height="100px" padding=10px align="left" />
<img src="pngs/selenium.png" width="100px" height="100px" padding=10px align="left" />
<img src="pngs/testng.png" width="150px" height="100px" padding=10px align="left" />
<img src="pngs/allureReport.png" width="200px" height="100px" padding=10px  align="left" />