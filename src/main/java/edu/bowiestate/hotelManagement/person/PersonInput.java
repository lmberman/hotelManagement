package edu.bowiestate.hotelManagement.person;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PersonInput {
    @NotNull
    @Length(min = 2, max = 50)
    private String firstname;
    @NotNull
    private char middle;
    @NotNull
    @Length(min = 2, max = 50)
    private String lastname;
    @NotNull
    @Length(min = 12, max = 12)
    @Pattern(regexp = "(^$|[0-9]{3}-[0-9]{3}-[0-9]{4})")
    private String telephone;

    @NotNull
    @Length(min = 8, max = 50)
    private String address;

    @NotNull
    @Length(min = 3, max = 50)
    private String city;
    @NotNull
    @Length(min = 2, max = 2)
    private String state;
    @NotNull
    @Length(min = 5, max = 5)
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private String zipcode;

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
