package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initModContact() {
    click(By.name("update"));
  }

  public void initNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }


  public void fillNotAllContact(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    }

  public void goToModificationContact(int index) {
    wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
    //click(By.xpath("//img[@title='Edit']"));
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }


  public void delete(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    deleteSelectedContacts();
    //wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    wd.switchTo().alert().accept();
    wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);



  }

  public void deleteSelectedContacts() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]"));

  }

  public void fillAllContact(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("address"), contactData.getHome());
    type(By.name("home"), contactData.getEmail());
    attach(By.name("photo"), contactData.getPhoto());

  }


  public void goToAddNew() {
    click(By.linkText("add new"));
  }


  public void create(ContactData contact) {
    goToAddNew();
    fillAllContact(contact);
    initNewContact();
    contactCache = null;

  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }



  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> rows = wd.findElements(By.name(("entry")));
    for(WebElement row : rows ) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
      return contacts;

  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name(("entry")));
    for(WebElement row : rows ) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allEmail = cells.get(4).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAddress(address).withAllEmail(allEmail));
    }
    return new Contacts(contactCache);

  }

  public void modify(ContactData contact) {
   initContactModificationById(contact.getId());
   fillAllContact(contact);
   initModContact();
   contactCache = null;
   }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    wd.switchTo().alert().accept();
    contactCache = null;
    wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
  }

  public ContactData infoFromEditform(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");

    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).
            withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).
            withWorkPhone(work).withAddress(address).withEmail(email)
            .withEmail2(email2).withEmail3(email3);


  }
}
