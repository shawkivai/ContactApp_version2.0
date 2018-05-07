package com.example.user04.contactapp;

/**
 * Created by USER04 on 2/19/2018.
 */

public class listitem {

    private String name;
    private String email;
    private String company;
    private String number;
    private Integer id;


    public listitem(String name, String email, String company, String number) {
        this.name = name;
        this.email = email;
        this.company= company;
        this.number=number;
//        this.id=id;

    }

    public listitem() {

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
