import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class OpenLending {
    public OpenLending() {
    }

    public static void main(String[] args) throws InterruptedException {

        //if is not running please download chrome binnary
        //ChromeOptions opt = new ChromeOptions();
        //opt.setBinary("src/main/resources/chrome-win64/chrome.exe");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        WebElement searchGoogle = driver.findElement(By.xpath("(//textarea[@id='APjFqb'])[1]"));
        searchGoogle.sendKeys("Open Lending");
        searchGoogle.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//h3[contains(text(),'Automated Lending Platform | Open Lending | United')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement resource = (WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[normalize-space()='Resources'])[1]")));
        resource.click();
//while loop for button
        while(driver.findElement(By.className("facetwp-load-more")).isDisplayed()) {
            WebElement loadMoreButton = driver.findElement(By.className("facetwp-load-more"));
            loadMoreButton.click();
            Thread.sleep(3000);
        }

        //adding the Blog Titles to a List
        List<WebElement> testing = driver.findElements(By.xpath("(//div[@class='paragraph-p2'])"));
        int size = testing.size();
        List<String> blogTitle = new ArrayList();

        for(int i = 0; i < size; ++i) {
            String options = ((WebElement)testing.get(i)).getText();
            System.out.println(options);
            blogTitle.add(options);
        }

//moving list to a Set (set doesn't allot duplicates)
        HashSet<String> blogTitleSet = new HashSet();
        for (String duplicateBlog : blogTitle) {
            if (blogTitleSet.add(duplicateBlog) == false) {
                System.out.println("list has duplicate elements");
            }
            System.out.println("no duplicate");
        }

        System.out.println(blogTitle);
        System.out.println(blogTitleSet);
    }
}
