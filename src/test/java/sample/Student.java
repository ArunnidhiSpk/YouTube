package sample;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Student {

    public static void main(String[] args) {
        // Set up WebDriver for Edge
        WebDriverManager.edgedriver();
        WebDriver driver = new EdgeDriver();
        
        try {
            // Navigate to YouTube
            String url = "https://www.youtube.com/";
            driver.navigate().to(url);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Find all links on the page
            List<WebElement> links = driver.findElements(By.tagName("a"));

            for (WebElement link : links) {
                String href = link.getAttribute("href");

                if (href == null || href.isEmpty()) {
                    System.out.println("Link is empty or not valid: " + link.getText());
                    continue;
                }

                if (!href.startsWith("http")) {
                    System.out.println("Skipping non-HTTP link: " + href);
                    continue;
                }

                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(href).openConnection();
                    connection.setRequestMethod("HEAD");
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    if (responseCode >= 200 && responseCode < 400) {
                        System.out.println("Valid link: " + href);
                    } else {
                        System.err.println("Broken link: " + href);
                    }

                    connection.disconnect();
                } catch (Exception e) {
                    System.err.println("Error processing link: " + href + " - " + e.getMessage());
                }
            }
        } finally {
            // Quit the browser
            driver.quit();
        }
    }
}
