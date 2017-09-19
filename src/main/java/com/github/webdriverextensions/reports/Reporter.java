package com.github.webdriverextensions.reports;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.internal.utils.FileUtils;
import com.github.webdriverextensions.internal.utils.OsUtils;

public class Reporter {

	private ExtentHtmlReporter htmlReporter;

	private ExtentTest extentTest;

	private ExtentTest extentParentTest;

	private ExtentReports extentReports;

	public Reporter() {
	}

	public void createTest(String testName, String testAuthor, String testDescription, String testCategory) {
		extentTest = getReport().createTest(testName, testDescription);
		extentTest.assignAuthor(testAuthor);
		extentTest.assignCategory(testCategory);
		setParentTest(extentTest);
	}

	public void createSection(String testName, String testDescription) {
		extentTest = getParentTest().createNode(testName, testDescription);
	}

	public void createSection(String testDescription) {
		extentTest = getParentTest().createNode(testDescription);
	}

	public void createHeader(String labelText, ExtentColor labelColor) {
		Markup labelMarkup = MarkupHelper.createLabel(labelText, labelColor);
		info(labelMarkup);
	}

	public void info(String detailedmessage) {
		getTest().info(detailedmessage);
		flushReport();
	}

	public void info(Markup markup) {
		getTest().info(markup);
		flushReport();
	}

	public void error(Throwable throwable) {
		getTest().error(throwable);
		flushReport();
	}

	public void pass(String detailedMessage) {
		getTest().pass(detailedMessage);
		flushReport();
	}

	public void pass(String detailedMessage, WebElement webElement) {
		String imagePath = null;
		try {
			imagePath = Bot.getScreenShotOfElement(webElement);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String imageCode = "<img src=\"" + imagePath + "\">";
		pass(imageCode + " " + detailedMessage);
		flushReport();
	}

	public void pass(String detailedMessage, MediaEntityModelProvider mediaModel) {
		getTest().pass(detailedMessage, mediaModel);
		flushReport();
	}

	public void fail(Throwable thrown) {
		getTest().fail(thrown);
		flushReport();
	}

	public void fail(String detailedmessage, String imagePath) {
		try {
			getTest().fail(detailedmessage, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
			flushReport();
		} catch (IOException e) {
		}
	}

	public void fail(Throwable throwable, String imagePath) {
		try {
			getTest().fail(throwable).addScreenCaptureFromPath(imagePath);
			flushReport();
		} catch (IOException e) {
		}
	}

	public void warning(String detailedMessage) {
		getTest().warning(detailedMessage);
		flushReport();
	}

	public void warning(String detailedMessage, String imagePath) {
		try {
			getTest().warning(detailedMessage, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
			flushReport();
		} catch (IOException e) {
		}
	}

	public void debug(String detailedMessage, String imagePath) {
		try {
			getTest().debug(detailedMessage, MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
			flushReport();
		} catch (IOException e) {
		}
	}

	public void debug(String detailedMessage) {
		getTest().debug(detailedMessage);
		flushReport();
	}

	public void debug(String detailedmessage, Throwable throwable) {
		getTest().debug(detailedmessage);
		getTest().debug(throwable);
		flushReport();
	}

	public void fatal(String detailedmessage, Throwable throwable) {
		getTest().fatal(detailedmessage);
		getTest().fatal(throwable);
		flushReport();
	}

	public void fatal(Throwable throwable) {
		getTest().fatal(throwable);
		flushReport();
	}

	public void createLabel(String text, ExtentColor color) {
		Markup labelMarkUp = MarkupHelper.createLabel(text, color);
		info(labelMarkUp);
	}

	public void createCodeBlock(String code) {
		Markup labelMarkUp = MarkupHelper.createCodeBlock(code);
		info(labelMarkUp);
	}

	public void createTableBlock(String[][] table) {
		Markup labelMarkUp = MarkupHelper.createTable(table);
		info(labelMarkUp);
	}

	public static String quoteCodeFont(String code) {
		return "<font face=\"Consolas\" size=\"2\">" + code + "</font>";
	}

	private void setParentTest(ExtentTest parentTest) {
		extentParentTest = parentTest;
	}

	private ExtentTest getParentTest() {
		return extentParentTest;
	}

	public void setReportConfiguration(String reportTitle) {
		String fileName = getReportName();
		backupReports();
		htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setChartVisibilityOnOpen(false);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(reportTitle);
		htmlReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);
		htmlReporter.setAppendExisting(true);
	}

	private void setOperatingSystemInfo(ExtentReports extentReports) {
		extentReports.setSystemInfo("OS", System.getProperty("os.name") + " " + System.getProperty("os.arch"));
		extentReports.setSystemInfo("Host", OsUtils.getComputerHostName());
		extentReports.setSystemInfo("User", OsUtils.getCurrentUserName());
		extentReports.setAnalysisStrategy(AnalysisStrategy.TEST);
	}

	public void createReporter(String reportTitle) {
		setReportConfiguration(reportTitle);
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(getHtmlReporter());
		setOperatingSystemInfo(extentReports);
		setReporter(extentReports);
	}

	private void backupReports() {
		String pathSource = FileUtils.getProjectDirectory() + "\\reports";
		String pathDest = System.getenv("user.home") + "\\reports-backup\\" + FileUtils.getTimeStamp();
		FileUtils.backupDirectory(pathSource, pathDest);
		FileUtils.makeDirectory(pathSource);
	}

	private String getReportName() {
		return FileUtils.getProjectDirectory() + "\\reports\\results.htm";
	}

	private ExtentHtmlReporter getHtmlReporter() {
		return htmlReporter;
	}

	public void flushReport() {
		getReport().flush();
	}

	private ExtentReports getReport() {
		return extentReports;
	}

	private void setReporter(ExtentReports extentInstance) {
		this.extentReports = extentInstance;
	}

	private ExtentTest getTest() {
		return extentTest;
	}
}
