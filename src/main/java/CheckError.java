import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.logging.Level;

/**
 * Created by sunwh on 2020/5/6.
 */
public class CheckError {
    public static void main(String[] args) {
        try {
            String specialsPath = "/Users/zcool/Documents/specials.text";
            FileReader reader = new FileReader(specialsPath);
            BufferedReader br = new BufferedReader(reader);
            String str =  br.readLine();
            br.close();
            reader.close();
            String[] specials = str.split(",");
            PrintStream ps = new PrintStream("/Users/zcool/Documents/log/专题.txt");
            System.setOut(ps);
            for (String special : specials) {
                outLog(special);
            }
        }catch (Exception e) {

        }
    }

    private static void outLog (String name) throws Exception{
        System.setProperty("webdriver.chrome.driver","/Users/zcool/Documents/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://www.zcool.com.cn/special/" + name;
        driver.get(url);
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        Thread.sleep(1000);
        System.out.println(name);
        System.out.println("");
        for(LogEntry entry : logEntries) {
            if (entry.getLevel() == Level.SEVERE) {
                System.out.println("chrome.console===" + entry.getLevel() + " " + entry.getMessage());
                System.out.println("");
            }
        }
        driver.quit();}
    }

