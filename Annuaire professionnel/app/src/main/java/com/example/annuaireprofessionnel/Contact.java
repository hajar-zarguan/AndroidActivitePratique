package com.example.annuaireprofessionnel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "FirstName")
    String firstName;
    @ColumnInfo(name = "LastName")
    String lastName;
    @ColumnInfo(name = "Job")
    String job;
    @ColumnInfo(name = "Phone")
    String phone;
    @ColumnInfo(name = "Email")
    String email;
    @Ignore
    public Contact() {
    }

    public Contact(Integer ID, String firstName, String lastName, String job, String phone, String email) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
        this.phone = phone;
        this.email = email;
    }
@Ignore
    public Contact(String firstName, String lastName, String job, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
        this.phone = phone;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
