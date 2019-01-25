package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if ( app.db().contacts().size() == 0) {
      ContactData contact = new ContactData().withFirstname("as").withLastname("sadc").withMiddlename("ssssq");
      app.contact().create(contact);
    }

  }

  @Test(enabled = true)
  public void ContactModification() {
    app.goTo().contactPage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("11").withLastname("11").withMiddlename("11");
    app.goTo().contactPage();
    app.contact().modify(contact);
    app.goTo().contactPage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after,equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}