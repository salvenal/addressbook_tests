package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "address_in_groups")
public class AddressInGroupData {
    @Id
    @Column(name = "id")
    @Type(type = "integer")
    private int id;
    @Expose
    @Column(name = "group_id")
    @Type(type = "integer")
    private int group_id;

    public int getId() {
        return id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public AddressInGroupData withId(Integer id) {
        this.id = id;
        return this;
    }

    public AddressInGroupData withGroup_id(Integer group_id) {
        this.group_id = group_id;
        return this;
    }

  /*public void setId(int id) {
    this.id = id;
  }

  public void setGroup_id(int group_id) {
    this.group_id = group_id;
  }*/

    @Override
    public String toString() {
        return "AddressInGroupData{" +
                "id=" + id +
                ", group_id=" + group_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressInGroupData that = (AddressInGroupData) o;
        return id == that.id &&
                group_id == that.group_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group_id);
    }
}
