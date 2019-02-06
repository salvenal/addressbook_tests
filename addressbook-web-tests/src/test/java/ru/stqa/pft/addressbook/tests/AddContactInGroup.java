package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0) {
      ContactData contact = new ContactData().withFirstname("as").withLastname("sadc").withMiddlename("ssssq");
      app.contact().create(contact);
    }
  }

  @Test
  public void testAddContactInGroup() {
    Groups groups = app.db().groups();
    Contacts beforeContacts = app.db().contacts();
    ContactData modifiedContact = beforeContacts.iterator().next();
    GroupData group = groups.iterator().next();
    AddressInGroups addressInGroupsBefore = app.db().addressInGroups();
    AddressInGroupData addedAddressInGroup = new AddressInGroupData()
            .withGroup_id(group.getId())
            .withId(modifiedContact.getId());
    app.goTo().contactPage();
    app.contact().addGroupInContact(modifiedContact, group.getId());
    AddressInGroups addressInGroupsAfter = app.db().addressInGroups();
    assertThat(addressInGroupsAfter, equalTo(addressInGroupsBefore.withAdded(addedAddressInGroup)));

  }

}
