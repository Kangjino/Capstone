package com.blogspot.atifsoftwares.firebaseapp.models;

import android.provider.MediaStore;
import android.widget.RadioButton;

public class ModelResume {
    String Title;
    String Name;
    String Grade;
    String Department;
    String Old;
    String Department_Number;
    String Hobby;
    String Speciality;

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    String Sex;

    public ModelResume(){}

    public String getTitle() {
        return Title;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGrade() {
        return Grade;
    }



    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getOld() {
        return Old;
    }

    public void setOld(String old) {
        Old = old;
    }

    public String getDepartment_Number() {
        return Department_Number;
    }

    public void setDepartment_Number(String department_Number) {
        Department_Number = department_Number;
    }

    public String getHobby() {
        return Hobby;
    }

    public void setHobby(String hobby) {
        Hobby = hobby;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

    public ModelResume(String title, String name, String grade, String department, String old, String department_Number, String hobby, String speciality
                       ,String sex) {
        Title = title;
        Name = name;
        Grade = grade;
        Department = department;
        Old = old;
        Department_Number = department_Number;
        Hobby = hobby;
        Speciality = speciality;
        Sex = sex;

    }
}
