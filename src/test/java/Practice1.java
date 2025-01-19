import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class Practice1 {

    public void findDuplicate(String name){
        Map<Character, Integer> output = new LinkedHashMap<>();
        for(int i=0; i<name.length(); i++){
            char ch = name.charAt(i);
            if(output.containsKey(ch)){
                output.put(ch, output.get(ch)+1);
            }else{
                output.put(ch, 1);
            }
        }
        System.out.println(output);
    }

    public static int secondLargest(int[] arr){
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for(int num : arr){
            if(num > largest){
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }

        return secondLargest;
    }

    public void exceptionHandle() throws ElementNotInteractableException {
        WebDriver driver = new ChromeDriver();

        try{
            WebElement element = driver.findElement(By.xpath(""));
        } catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("")));

        int attempts = 0;

        while(attempts < 3){
            try{
                driver.findElement(By.xpath(""));
                break;
            } catch(Exception e){
                attempts++;
            }
        }
    }

    public static void main(String[] args) {
        Practice1 practice = new Practice1();
        practice.findDuplicate("Clinton");

        int[] arr = {1, 2, 3, 4, 5};
        int second = secondLargest(arr);
        System.out.println(second);
    }
}
