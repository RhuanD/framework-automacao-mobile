package reports;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.StatusConfigurator;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.JavascriptExecutor;

public class Report {
    private static ExtentReports extentReport;
    private static ExtentTest logger;

    public Report() {
    }

    public static void create(String title, String description) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(getNewReportPath());
        htmlReporter.config().setDocumentTitle(title);
        htmlReporter.config().setReportName(description);
        htmlReporter.config().setEncoding("UTF-8");
        htmlReporter.config().setTheme(Theme.DARK);
        defineStatusHierarchy();
        extentReport = new ExtentReports();
        extentReport.attachReporter(new ExtentReporter[]{htmlReporter});
    }

    public static void startTest(String testDescription) {
        if (extentReport == null) {
            create("TEST SUITE", testDescription);
        }

        logger = extentReport.createTest(testDescription);
    }

    public static void log(Status logStatus, String message, String imagePath) {
        try {
            if (logger == null) {
                startTest("TEST SUITE");
            }

            logger.log(logStatus, message, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
        } catch (IOException var4) {
            logger.log(logStatus, message + " (Screenshot fail)");
        }

    }

    public static void log(Status logStatus, String message) {
        if (logger == null) {
            startTest("TEST SUITE");
        }

        logger.log(logStatus, message);
    }

    public static void log(String message, String imagePath) {
        log(Status.INFO, message, imagePath);
    }

    public static void log(String message) {
        log(Status.INFO, message);
    }

    public static void logPass(String message, String imagePath) {
        log(Status.PASS, message, imagePath);
    }

    public static void logPass(String message) {
        log(Status.PASS, message);
    }

    public static void logFail(String message, String imagePath) {
        log(Status.FAIL, message, imagePath);
    }

    public static void logFail(String message) {
        log(Status.FAIL, message);
    }

    public static void close() {
        if (extentReport != null) {
            extentReport.flush();
        } else {
            startTest("O TestCase passou!");
            log(Status.INFO, "O teste encerrou.");
            close();
        }

    }

    private static void defineStatusHierarchy() {
        List<Status> statusHierarchy = Arrays.asList(Status.FATAL, Status.FAIL, Status.ERROR, Status.PASS, Status.WARNING, Status.SKIP, Status.INFO, Status.DEBUG);
        StatusConfigurator.getInstance().setStatusHierarchy(statusHierarchy);
    }

    private static String getNewReportPath() {
        return String.format("./reports/report_%s.html", System.currentTimeMillis());
    }
}
