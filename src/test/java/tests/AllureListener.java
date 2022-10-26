package tests;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.Driver;
import utilities.Log;

public class AllureListener implements ITestListener {


private static String getTestMethodName(ITestResult iTestResult){
    return iTestResult.getMethod().getConstructorOrMethod().getName();
}
    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver){
    Log.info("ScreenShot alindi");
    return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
}
    @Attachment(value = "{0}",type = "text/plain")
    public static String saveTextLog(String message){
    return message;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    Log.startTestCase(iTestContext.getName());
       // System.out.println(iTestContext.getName()+" Test Baslatildi.");
        iTestContext.setAttribute("Webdriver", Driver.getDriver());
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
    Log.endTestCase(iTestContext.getName());
       // System.out.println(iTestContext.getName()+" Test Sonlandirildi.");
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
    Log.info("Test içindeki" + getTestMethodName(iTestResult)+" Testi Baslatildi.");
       // System.out.println("Test içindeki " + getTestMethodName(iTestResult)+" Testi Baslatildi.");
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    Log.info("Test içindeki " + getTestMethodName(iTestResult)+" Testin Çalışmasi Başarili Bir Şekilde Sonladirildi" );
       // System.out.println("Test içindeki " + getTestMethodName(iTestResult)+" Testin Çalışmasi Başarili Bir Şekilde Sonladirildi" );
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
    Log.info(getTestMethodName(iTestResult) + " test is failed.");
        //System.out.println(getTestMethodName(iTestResult) + " test is failed.");
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver driver = Driver.getDriver();

        if (driver instanceof WebDriver) {
            //Take base64Screenshot screenshot for extent reports
            Log.info(getTestMethodName(iTestResult) + " Testi için ScrrenShot Alindi ");
           // System.out.println(getTestMethodName(iTestResult) + " Testi için ScrrenShot Alindi ");
            saveFailureScreenShot(driver);
        }
        saveTextLog(getTestMethodName(iTestResult)+ " test fail oldu ve Screen Shot Alindi");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult){
    Log.info("getTestMethodName(iTestResult) + \"Bu Test Çalıştırılmadan Atlanıldı\"");
       // System.out.println(getTestMethodName(iTestResult) + "Bu Test Çalıştırılmadan Atlanıldı");
       }

       @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult){
    Log.info(getTestMethodName(iTestResult) + "test başarısız Oldu Ancak Tanımlanmış Başarı Oranında");
          // System.out.println(getTestMethodName(iTestResult) + "test başarısız Oldu Ancak Tanımlanmış Başarı Oranında");
       }








}


