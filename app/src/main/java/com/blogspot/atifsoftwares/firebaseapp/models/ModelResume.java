package com.blogspot.atifsoftwares.firebaseapp.models;

import android.provider.MediaStore;
import android.widget.RadioButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ModelResume {

    String Title;
    String Name;
    String Grade;
    String Department;
    String Old;
    String Department_Number;
    String Hobby;
    String uid;
    String dongari;

    public String getDongari() {
        return dongari;
    }

    public void setDongari(String dongari) {
        this.dongari = dongari;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    String Speciality;
    String Sex;

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }



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
                       ,String sex, String uid) {
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance(); // 파이어베이스 객체 선언
        final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        //get path of database named "Users" containing users info
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");

        Title = title;
        Name = name;
        Grade = grade;
        Department = department;
        Old = old;
        Department_Number = department_Number;
        Hobby = hobby;
        Speciality = speciality;
        Sex = sex;
        uid = fUser.getUid();


    }
}
