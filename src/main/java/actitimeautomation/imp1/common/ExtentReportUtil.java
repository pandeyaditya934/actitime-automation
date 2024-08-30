package actitimeautomation.imp1.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentReportUtil extends BaseClass {

    ExtentReports extent = new ExtentReports();

    public static Map<String, ExtentTest> objectMap = new HashMap<>();

    public static ExtentTest getTest(){
        return objectMap.get("extentTest");
    }

    public ExtentTest createTest(String name) {
        extentTest = extent.createTest(name);
        return extentTest;
    }


}
