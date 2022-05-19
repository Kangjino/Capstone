package com.blogspot.atifsoftwares.firebaseapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    GoogleSignInClient mGoogleSignInClient; // 구글 로그인 사용을 위한 코드

    //views
    EditText mEmailEt, mPasswordEt;
    TextView notHaveAccntTv, mRecoverPassTv;
    Button mLoginBtn;
    SignInButton mGoogleLoginBtn;

    //Declare an instance of FirebaseAuth
    private FirebaseAuth mAuth;

    //progress dialog
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Actionbar and its title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("모여라 동아리"); // 타이틀 설정 "모여라동아리"
        //enable back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        //before mAuth
        // 구글 로그인 코드
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        //In the onCreate() method, initialize the FirebaseAuth instance.
        mAuth = FirebaseAuth.getInstance();

        //init
        mEmailEt = findViewById(R.id.emailEt);
        mPasswordEt = findViewById(R.id.passwordEt);
        notHaveAccntTv = findViewById(R.id.nothave_accountTv);
        mRecoverPassTv = findViewById(R.id.recoverPassTv);
        mLoginBtn = findViewById(R.id.loginBtn);
        mGoogleLoginBtn = findViewById(R.id.googleLoginBtn);

        //로그인 버튼 클릭시
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input data
                String email = mEmailEt.getText().toString(); // 작성된 email 가져오기
                String passw = mPasswordEt.getText().toString().trim(); // 작성된 패스워드 가져오기 , trim:공백을 제거
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){ // 기존의 이메일패턴 xxx@xxx.xxx 와 다르다면
                    //올바르지 않은 이메일 패턴
                    mEmailEt.setError("잘못된 형식의 이메일입니다.");
                    mEmailEt.setFocusable(true);
                }
                else {
                    //올바른 이메일 패턴
                    loginUser(email, passw);
                }
            }
        });
        //회원가입 버튼 클릭시
        notHaveAccntTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)); // 로그인창에서 회원가입창으로 이동
                finish(); // startActivity로 시작한 액티비티 종료
            }
        });
        //비밀번호 재설정
        mRecoverPassTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecoverPasswordDialog();
            }
        });

        //구글 로그인
        mGoogleLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //begin google login process
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        //init progress dialog
        pd = new ProgressDialog(this);
    }

    private void showRecoverPasswordDialog() { // 비밀번호 재설정시 나타나는 Dialog
        //AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("비밀번호 재설정을 위해 이메일을 입력해주세요!");

        //set layout linear layout
        LinearLayout linearLayout = new LinearLayout(this);
        //views to set in dialog
        final EditText emailEt = new EditText(this);
        emailEt.setHint("Email");//힌트
        emailEt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS); // 이메일 형식만 입력가능
        /*sets the min width of a EditView to fit a text of n 'M' letters regardless of the actual text
        extension and text size.*/
        emailEt.setMinEms(16);


        linearLayout.addView(emailEt);
        linearLayout.setPadding(10,10,10,10);

        builder.setView(linearLayout);

        //보내기버튼
        builder.setPositiveButton("보내기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //input email
                String email = emailEt.getText().toString().trim();
                beginRecovery(email); // 이메일을 보낸다
            }
        });
        //buttons cancel
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dismiss dialog
                dialog.dismiss(); // dialog 종료
            }
        });

        //show dialog
        builder.create().show();
    }

    private void beginRecovery(String email) {
        //show progresss dialog
        pd.setMessage("이메일 보내는중...");
        pd.show();
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                if (task.isSuccessful()){ // 이메일 전송 성공시
                    Toast.makeText(LoginActivity.this, "전송 완료! 메일을 확인해주세요", Toast.LENGTH_SHORT).show();
                }
                else { // 전송 실패시
                    Toast.makeText(LoginActivity.this, "전송 실패..! 다시 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                //get and show proper error message
                Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUser(String email, String passw) {
        //show progresss dialog
        pd.setMessage("로그인 하는중...");
        pd.show();
        mAuth.signInWithEmailAndPassword(email, passw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //dismiss progress dialog
                            pd.dismiss(); // 다이어로그 종료
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //user is logged in, so start LoginActivity
                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                            finish();
                        } else {
                            //dismiss progress dialog
                            pd.dismiss();
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "인증실패, 모여라 동아리의 비밀번호를 다시 확인해주세요",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //dismiss progress dialog
                pd.dismiss();
                //error, get and show error message
                Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go previous activity
        return super.onSupportNavigateUp();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) { // 구

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();

                            //if user is signing in first time then get and show user info from google account
                            if (task.getResult().getAdditionalUserInfo().isNewUser()){
                                //Get user email and uid from auth
                                String email = user.getEmail();
                                String uid = user.getUid();
                                //When user is registered store user info in firebase realtime database too
                                //using HashMap
                                HashMap<Object, String> hashMap = new HashMap<>();
                                //put info in hasmap
                                hashMap.put("email", email);
                                hashMap.put("uid", uid);
                                hashMap.put("name", ""); //will add later (e.g. edit profile)
                                hashMap.put("onlineStatus", "online"); //will add later (e.g. edit profile)
                                hashMap.put("typingTo", "noOne"); //will add later (e.g. edit profile)
                                hashMap.put("phone", ""); //will add later (e.g. edit profile)
                                hashMap.put("image", ""); //will add later (e.g. edit profile)
                                hashMap.put("cover", ""); //will add later (e.g. edit profile)
                                //firebase database isntance
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                //path to store user data named "Users"
                                DatabaseReference reference = database.getReference("Users");
                                //put data within hashmap in database
                                reference.child(uid).setValue(hashMap);
                            }
                            //show user email in toast
                            Toast.makeText(LoginActivity.this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();
                            //go to profile activity after logged in
                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class)); // 로그인 성공시 대쉬보드액티비티로 화면이동
                            finish();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Login Failed...", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //get and show error message
                Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}