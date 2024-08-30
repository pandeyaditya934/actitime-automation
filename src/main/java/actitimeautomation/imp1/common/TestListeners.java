package actitimeautomation.imp1.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import java.io.File;
import java.io.IOException;

public class TestListeners implements ISuiteListener, ITestListener {
    ExtentReports extent;
    ExtentTest extentTest;
    ExtentReportUtil reportUtil;
    public void onStart(ISuite suite) {

        //get the path of target folder
        String reportFolderPath = System.getProperty("user.dir")+ "/report";
        File file= new File(reportFolderPath);
        //check the file/folder is present or not
        if (!file.exists()){
            // if the given path dosent present then create a folder/file accordingly
            file.mkdir();
            System.out.println("report folder created successfully...");
        }
        //create a html file
        ExtentSparkReporter spark = new ExtentSparkReporter(reportFolderPath+"/AutomationReport.html");
        //to save html file, we have create instance of ExtentReports class
        reportUtil = new ExtentReportUtil();
        extent = reportUtil.extent;
        extent.attachReporter(spark);
        extent.setSystemInfo("user", "cyber success");
        extent.setSystemInfo("os", "macOS");
        extent.setSystemInfo("browser", "chrome");
    }
    public void onFinish(ISuite suite) {
        System.out.println("This is onFinish of ISuiteListener");
        extent.flush();
    }

    public void onStart(ITestContext result) {
        System.out.println("This is onStart of ITestListener");
    }
    public void onFinish(ITestContext result) {
        System.out.println("This is onFinish of ITestListener");
    }

    public void onTestStart(ITestResult result){
        System.out.println("This is a onTestStart method");
        System.out.println("Test :" + result.getMethod().getMethodName() + " started executing......");

        //create a section for each test through extentReport reference
        extentTest = reportUtil.createTest(result.getMethod().getMethodName());
        ExtentReportUtil.objectMap.put("extentTest", extentTest);
    }
    public void onTestSuccess(ITestResult result) {

        System.out.println("Test "+ result.getMethod().getMethodName() + "  executed successfully....");
        extentTest.pass("Test " + result.getMethod().getMethodName() + " is passed");
    }
    public void onTestFailure(ITestResult result) {
        String methodName= result.getMethod().getMethodName();
        System.out.println("Test "+ methodName  + " failed due to below reason...");
        System.out.println(result.getThrowable().getMessage());
        WebDriver driver = (WebDriver)result.getTestContext().getAttribute("webDriver");
        if(driver != null){
            CommonUtil commonUtil= new CommonUtil(driver);
            //take screenshot
            try {
                commonUtil.takeScreenShot(methodName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        extentTest.fail(result.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("The Test"+ result.getMethod().getMethodName()+" got skipped");
        extentTest.pass("Test " + result.getMethod().getMethodName() + " is skipped");
    }
    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("Test failed due to defined timeout exceded....");
        onTestFailure(result);
    }
}