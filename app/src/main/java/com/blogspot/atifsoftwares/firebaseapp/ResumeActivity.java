package com.blogspot.atifsoftwares.firebaseapp;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import com.blogspot.atifsoftwares.firebaseapp.MainActivity;


class Activity_Resume extends AppCompatActivity {

    private EditText Title, Name, Grade, Old, Department_number, Department, hobby, speciality;
    private CheckBox Check1, Check2,Check3,Check4,Check5,Check6;
    private Button btn_back, btn_save;
    private RadioButton Sex_female, Sex_male;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        Title = findViewById(R.id.Title);
        Name = findViewById(R.id.Name);
        Grade = findViewById(R.id.Grade);
        Old = findViewById(R.id.Old);
        Department = findViewById(R.id.Department);
        Department_number = findViewById(R.id.Department_number);
        hobby = findViewById(R.id.hobby);
        speciality = findViewById(R.id.speciality);
        Check1 = findViewById(R.id.Check1);
        Check2 = findViewById(R.id.Check2);
        Check3 = findViewById(R.id.Check3);
        Check4 = findViewById(R.id.Check4);
        Check5 = findViewById(R.id.Check5);
        Check6 = findViewById(R.id.Check6);
        btn_back = findViewById(R.id.btn_back);
        btn_save =findViewById(R.id.btn_save);
        Sex_female = findViewById(R.id.Sex_female);
        Sex_male = findViewById(R.id.Sex_male);


        Button button = findViewById(R.id.btn_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}