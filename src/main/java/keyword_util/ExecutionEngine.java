package keyword_util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Baseclass.bs_class;

public class ExecutionEngine extends bs_class {

	public static String sheetpath = "C:\\Users\\Dell\\eclipse-workspace\\Keyboardriven\\src\\main\\java\\fb_scenarios\\fb_keyword.xlsx";
	public static Workbook book;
	public static Sheet sheet;
	public static WebDriver driver;
	public static Properties prop;
	public WebElement element;
	public static bs_class Baseclass;

	public void startExecution(String sheetName) throws InterruptedException {
		String locatorName = null;
		String locatorValue = null;

		FileInputStream file = null;
		try {
			file = new FileInputStream(sheetpath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		int k = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {
				String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim(); // id=email
				if (!locatorColValue.equalsIgnoreCase("NA")) {
					locatorName = locatorColValue.split("=")[0].trim(); // id
					locatorValue = locatorColValue.split("=")[1].trim(); // email

				}

				String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
				String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();

				System.out.println(locatorName + ":" + locatorValue);

				switch (action) {
				case "open browser":
					Baseclass = new bs_class();
					prop = Baseclass.initproperties();
					if (value.isEmpty() || value.equals("NA")) {
						driver = Baseclass.initdriver(prop.getProperty("browser"));
					} else {
						driver = Baseclass.initdriver(value);
					}
					break;

				case "enter url":
					if (value.isEmpty() || value.equals("NA")) {
						System.out.println("enter url:   ");
						driver.get(prop.getProperty("url"));
					} else {
						System.out.println("enter url:   " + value);
						driver.get(value);
					}
					break;

				case "exit":
					driver.quit();

					break;

				default:
					break;
				}
				switch (locatorName) {

				case "id":
					element = driver.findElement(By.id(locatorValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						Thread.sleep(3000);
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						Thread.sleep(3000);
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}
					locatorName = null;
					break;

				case "className":
					element = driver.findElement(By.id(locatorValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}
					locatorName = null;
					break;

				case "name":
					element = driver.findElement(By.name(locatorValue));
					element.click();
					break;

				case "xpath":
					element = driver.findElement(By.id(locatorValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						Thread.sleep(3000);
						element.click();
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}
					locatorName = null;
					break;

				case "linkText":
					element = driver.findElement(By.linkText(locatorValue));
					element.click();
					locatorName = null;
					break;

				default:
					break;

				}
			} catch (Exception e) {

			}
		}
	}
}
