/*
 * package com.qa.opencart.listeners;
 * 
 * import java.io.IOException; import java.nio.file.Files; import
 * java.nio.file.Path; import java.nio.file.Paths;
 * 
 * import org.testng.ITestListener;
 * 
 * import com.aventstack.extentreports.ExtentReports; import
 * com.aventstack.extentreports.ExtentTest; import
 * com.aventstack.extentreports.reporter.ExtentSparkReporter; import
 * com.aventstack.extentreports.MediaEntityBuilder;
 * 
 * 
 * 
 * 
 * public class ExtentReportListener implements ITestListener {
 * 
 * private static final String OUTPUT_FOLDER = "./build/"; private static final
 * String FILE_NAME = "TestExecutionReport.html";
 * 
 * private static ExtentReports extent = init(); public static
 * ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>(); private static
 * ExtentReports extentReports;
 * 
 * private static ExtentReports init() {
 * 
 * Path path = Paths.get(OUTPUT_FOLDER);
 * 
 * if(!Files.exists(path)) { try { Files.createDirectories(path); } catch
 * (IOException e) { e.printStackTrace(); } }
 * 
 * extentReports = new ExtentReports(); ExtentSparkReporter reporter = new
 * ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
 * reporter.config().setReportName("Open Cart Automation Test Results");
 * extentReports.attachReporter(reporter); extentReports.setSystemInfo("System",
 * "Windows"); extentReports.setSystemInfo("Author", "PV");
 * extentReports.setSystemInfo("Build", "1.0.1");
 * extentReports.setSystemInfo("Team", "QA");
 * extentReports.setSystemInfo("Customer", "IBM"); return extentReports;
 * 
 * }
 * 
 * 
 * }
 */