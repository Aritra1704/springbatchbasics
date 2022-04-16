package io.arpaul.ytspringbatch.payload;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class UserAddress {
    @Id
    private String source_id;
    private String first_name;
    private String middle_initial;
    private String last_name;
    private String email_address;
    private String phone_number;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String birthdate;
    private String action;
    private String ssn;

    private Date time;

    public UserAddress() {
    }

    public UserAddress(String source_id, String first_name, String middle_initial, String last_name, String email_address, String phone_number, String street, String city, String state, String zip, String birthdate, String action, String ssn, Date time) {
        this.source_id = source_id;
        this.first_name = first_name;
        this.middle_initial = middle_initial;
        this.last_name = last_name;
        this.email_address = email_address;
        this.phone_number = phone_number;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.birthdate = birthdate;
        this.action = action;
        this.ssn = ssn;
        this.time = time;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_initial() {
        return middle_initial;
    }

    public void setMiddle_initial(String middle_initial) {
        this.middle_initial = middle_initial;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "source_id='" + source_id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", middle_initial='" + middle_initial + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email_address='" + email_address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", action='" + action + '\'' +
                ", ssn='" + ssn + '\'' +
                ", time=" + time +
                '}';
    }
}