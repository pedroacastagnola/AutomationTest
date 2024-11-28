package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        /*String baseURL = "https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_login_page/9a83bda125cd7398f9f482a3d6d45ea4/static/attachments/reference_page.html";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseURL);
        System.out.println(driver.getTitle());

        driver.quit();*/

        Pattern pattern = java.util.regex.Pattern.compile("^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$");
        /*
        ^ - The beginning of the string.
        ( - Start of a capturing group.
        [a-zA-Z0-9._%-]+ - Matches one or more of the following characters: letters (both uppercase and lowercase), numbers, periods, underscores, percent signs, and hyphens.
        @ - Matches the “@” symbol.
        [a-zA-Z0-9.-]+ - Matches one or more of the following characters: letters (both uppercase and lowercase), numbers, periods, and hyphens.
        \. - Matches a literal period character.
        [a-zA-Z]{2,} - Matches two or more letters (both uppercase and lowercase).
        ) - End of the capturing group.
        $ - The end of the string.
        */
        Matcher matcher = pattern.matcher("pac@mail.com.ar");

        System.out.println(matcher.matches());
    }
}