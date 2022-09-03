package ExtrentReports;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class ExtentReport {
    ExtentReports extent;
    ExtentTest test;
    @BeforeTest
    public void startReport() {
        extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Myownreport.html", true);
        extent.addSystemInfo("Host Name","LocalHost");
        extent.addSystemInfo("Environemrnt","QA");
        extent.addSystemInfo("User Name","Shakya");
        extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-report-config.xml"));
    }
    @Test
    public void DemoReportPass(){
        test=extent.startTest("DemoReportPass");
        Assert.assertTrue(true);
        test.log(LogStatus.PASS,"Assert true as Condition is True");

    }
    @Test
    public void DemoReportFail(){
        test=extent.startTest("DemoReportFail");
        Assert.assertTrue(false);
        test.log(LogStatus.FAIL,"Assert False as Condition is False");
    }
    @AfterMethod
    public void xgetResult(ITestResult result){
        if(result.getStatus()==ITestResult.FAILURE){
            test.log(LogStatus.FAIL,result.getThrowable());
        }
        extent.endTest(test);

    }
    @AfterTest
    public void endReport() {
        extent.flush();
    }
}

