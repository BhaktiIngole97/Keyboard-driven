package Baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class bs_class {
	
	public static WebDriver driver;
	  public static Properties prop;
	  public static FileInputStream ip;
	
	public Properties initproperties() {
		try {
		prop = new Properties();
		
		FileInputStream	ip= new FileInputStream("C:\\Users\\Dell\\eclipse-workspace\\Keyboardriven\\src\\main\\java\\keywordconfig\\keywordconfig.properties");
		prop.load(ip);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return prop;
		
	}
	
    public WebDriver initdriver(String browsername) {
  	  System.setProperty("webdriver.chrome.driver","C:\\Users\\Dell\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		String title =driver.getTitle();
		System.out.println("title of the web page: " + title);
      driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
      return driver;
		
	}
}
	
	



