package com.blogspot.atifsoftwares.firebaseapp.models;

public class ModelClub {
    String Club_Title, Club_Name, Club_Introduce, Club_Rule, Club_Harmoney;

    public ModelClub() {}

    public ModelClub(String club_Title, String club_Name, String club_Introduce, String club_Rule, String club_Harmoney) {
        Club_Title = club_Title;
        Club_Name = club_Name;
        Club_Introduce = club_Introduce;
        Club_Rule = club_Rule;
        Club_Harmoney = club_Harmoney;
    }

    public String getClub_Title() {
        return Club_Title;
    }

    public void setClub_Title(String club_Title) {
        Club_Title = club_Title;
    }

    public String getClub_Name() {
        return Club_Name;
    }

    public void setClub_Name(String club_Name) {
        Club_Name = club_Name;
    }

    public String getClub_Introduce() {
        return Club_Introduce;
    }

    public void setClub_Introduce(String club_Introduce) {
        Club_Introduce = club_Introduce;
    }

    public String getClub_Rule() {
        return Club_Rule;
    }

    public void setClub_Rule(String club_Rule) {
        Club_Rule = club_Rule;
    }

    public String getClub_Harmoney() {
        return Club_Harmoney;
    }

    public void setClub_Harmoney(String club_Harmoney) {
        Club_Harmoney = club_Harmoney;
    }
}
