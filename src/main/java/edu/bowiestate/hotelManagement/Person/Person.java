package edu.bowiestate.hotelManagement.Person;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="PERSON")
public class Person {
    @Id
    @TableGenerator(name = "Person_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 5, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE,  generator = "Person_Gen")
    @Column(name="PERSON_ID")
    private long personId;

    @Column(name="FIRSTNAME")
    private String firstname;

    @Column(name="MIDDLE")
    private char middle;

    @Column(name="LASTNAME")
    private char lastname;

    @Column(name = "TELEPHONE", length = 12)
    private String telephone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP")
    private String zip;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public char getMiddle() {
        return middle;
    }

    public void setMiddle(char middle) {
        this.middle = middle;
    }

    public char getLastname() {
        return lastname;
    }

    public void setLastname(char lastname) {
        this.lastname = lastname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && middle == person.middle && lastname == person.lastname && firstname.equals(person.firstname) && telephone.equals(person.telephone) && address.equals(person.address) && city.equals(person.city) && state.equals(person.state) && zip.equals(person.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstname, middle, lastname, telephone, address, city, state, zip);
    }
}
