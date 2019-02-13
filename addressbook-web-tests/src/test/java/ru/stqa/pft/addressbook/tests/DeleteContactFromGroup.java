package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase {

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
  public void testDeleteContactFromGroup() {
    app.goTo().contactPage();
    Groups beforeGroups = app.db().groups();
    Contacts beforeContacts = app.db().contacts();
    ContactData deletedContact = beforeContacts.iterator().next();
    GroupData group = beforeGroups.iterator().next();
    if (deletedContact.getGroups().size() == 0) {
      app.contact().addGroupInContact(deletedContact, group.getId());
      app.goTo().contactPage();
    }
    AddressInGroupData deletedAddressInGroup = new AddressInGroupData()
            .withGroup_id(group.getId())
            .withId(deletedContact.getId());
    AddressInGroups addressInGroupsBefore = app.db().addressInGroups();
    app.contact().deleteContactFromGroup(deletedContact, group.getId());
    AddressInGroups addressInGroupsAfter = app.db().addressInGroups();
    System.out.println(addressInGroupsAfter);
    System.out.println(addressInGroupsBefore.without(deletedAddressInGroup));
    assertThat(addressInGroupsAfter, equalTo(addressInGroupsBefore.without(deletedAddressInGroup)));

  }

}
