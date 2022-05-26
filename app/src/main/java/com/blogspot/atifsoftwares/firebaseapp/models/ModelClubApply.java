package com.blogspot.atifsoftwares.firebaseapp.models;

public class ModelClubApply {
    String club_name, name, department_name;

    public ModelClubApply(){};

    public ModelClubApply(String club_name, String name, String department_name) {
        this.club_name = club_name;
        this.name = name;
        this.department_name = department_name;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
}
