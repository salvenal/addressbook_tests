package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    /*wd.findElement(By.name("searchstring")).click();
    wd.findElement(By.name("searchstring")).clear();
    wd.findElement(By.name("searchstring")).sendKeys();*/
    wd.findElement(By.linkText("groups")).click();
  }

  public void contactPage() {
    wd.findElement(By.linkText("home")).click();
  }



  public boolean findTheText(String text) {
    try {
      wd.findElement(By.name("new_group")).findElement(By.tagName("option"));
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

  public boolean findThegroup(String text) {
    try {
      wd.findElement(By.cssSelector("span.group")).findElement(By.cssSelector(text));
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
