package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdministratorHelper extends HelperBase {

  public AdministratorHelper(ApplicationManager app) {
    super(app);
  }

  public void adminLogInAndChangePass(String changingUser) {
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("username"), app.getProperty("web.adminLogin"));
    type(By.name("password"), app.getProperty("web.adminPassword"));
    click(By.xpath("//input[@value='Login']"));
    click(By.linkText("Manage Users"));
    click(By.linkText(changingUser));
    click(By.xpath("//input[@value='Reset Password']"));
  }

  public void LogIn(String username, String password) {
    type(By.name("username"), app.getProperty(username));
    type(By.name("password"), app.getProperty(password));
    click(By.xpath("//input[@value='Login']"));
  }
}