package com.blogspot.atifsoftwares.firebaseapp.models;

public class ModelClubPeople {
    String department_number, Name;

    public ModelClubPeople(){}


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDepartment_number() {
        return department_number;
    }

    public void setDepartment_number(String department_number) {
        this.department_number = department_number;
    }

    public ModelClubPeople(String name, String department_number) {
        Name = name;
        this.department_number = department_number;
    }
}
