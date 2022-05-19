package com.blogspot.atifsoftwares.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class SettingsActivity extends AppCompatActivity {

    SwitchCompat postSwitch; // swtichCompat : 설정 -> 게시글알림에 있는 on off버튼을 위한 스위치

    //use shared preferences to save the state of Switch
    //SharedPreferences는 데이터를 파일로 저장을 하는데요, 파일이 앱 폴더 내에 저장되므로 앱을 삭제하시면 당연히 데이터도 삭제됩니다.
    SharedPreferences sp;
    SharedPreferences.Editor editor; //to edit value of shared pref

    //constant for topic
    private static final String TOPIC_POST_NOTIFICATION = "POST"; //assign any value but use same for this kind of notifications

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); // activity_settings.xml을 가져온다
        //Actionbar은 액티비티 위젯중 하나로 위에 떠있는것
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("설정");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        postSwitch = findViewById(R.id.postSwitch);

        //init sp
        sp = getSharedPreferences("Notification_SP", MODE_PRIVATE);
        boolean isPostEnabled = sp.getBoolean(""+TOPIC_POST_NOTIFICATION, false);
        //if enabled check switch, otherwise uncheck switch - by default unchecked/false
        if (isPostEnabled){
            postSwitch.setChecked(true);
        }
        else {
            postSwitch.setChecked(false);
        }

        //implement switch change listener
        postSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { // 포스트 알림을 받기위한 코드
                //edit switch state
                editor = sp.edit();
                editor.putBoolean(""+TOPIC_POST_NOTIFICATION, isChecked);
                editor.apply();

                if (isChecked){
                    subscribePostNotification();//call to subscribe
                }
                else {
                    unsubscribePostNotification(); // call to unsubscribe
                }
            }
        });

        // 여기다가 설정 장현이 코드를 가져온다
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void unsubscribePostNotification() { // 스위치컴팻 ON상태
        //unsubscribe to a topic (POST) to disable it's notification
        FirebaseMessaging.getInstance().unsubscribeFromTopic(""+TOPIC_POST_NOTIFICATION)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "모여라 동아리 동아리 홍보 알림을 받지 않습니다.";
                        if (!task.isSuccessful()){
                            msg = "UnSubscription failed";
                        }
                        Toast.makeText(SettingsActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void subscribePostNotification() { // 스위치컴팻 ON상태
        //subscribe to a topic (POST) to enable it's notification
        FirebaseMessaging.getInstance().subscribeToTopic(""+TOPIC_POST_NOTIFICATION)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "모여라 동아리 동아리 홍보 알림을 받습니다.";
                        if (!task.isSuccessful()){
                            msg = "Subscription failed";
                        }
                        Toast.makeText(SettingsActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
