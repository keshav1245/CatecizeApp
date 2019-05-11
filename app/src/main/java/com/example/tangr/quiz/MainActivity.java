package com.example.tangr.quiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button log;
    private Button sig;
    private FirebaseAuth fbobj2;
    private ProgressDialog updatin_user;
    private TextView pass_forgot;
    private long backpressedtime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        username = (EditText)findViewById(R.id.T1);
        password = (EditText)findViewById(R.id.T2);
        log = (Button)findViewById(R.id.B1);
        sig = (Button)findViewById(R.id.B2);

        fbobj2 = FirebaseAuth.getInstance();
        updatin_user = new ProgressDialog(MainActivity.this);
        pass_forgot = (TextView)findViewById(R.id.forgot_pass);
        FirebaseUser checking_user_already_logged_in = fbobj2.getCurrentUser();
        if(checking_user_already_logged_in != null){
            finish();
            Toast.makeText(MainActivity.this,"ALREADY LOGGED IN ! ",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, QuizStartPage.class));


        }


        pass_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Password_reset.class));
            }
        });


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valid())
                    newaction(username.getText().toString().trim(),password.getText().toString().trim());

            }
        });
        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sigaction();
            }
        });

    }
    public void newaction(String Username,String Password){
        updatin_user.setMessage("Please Wait While We are Processing Your Data !");
        updatin_user.show();

        fbobj2.signInWithEmailAndPassword(Username,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(task.isSuccessful()){
                    updatin_user.dismiss();
                    checkmail();
                }else{
                    updatin_user.dismiss();
                    Toast.makeText(MainActivity.this,"LOGIN FAILED !",Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

    public void sigaction(){
        Intent inv = new Intent(MainActivity.this,Signinact.class);
        startActivity(inv);
    }
    private Boolean valid(){
        Boolean result = false;
        String mail = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if(mail.isEmpty()||pass.isEmpty()){
            Toast.makeText(MainActivity.this,"Enter Valid Credentials !",Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }
    public void checkmail(){
        FirebaseUser fbuser = FirebaseAuth.getInstance().getCurrentUser();
        Boolean flag = fbuser.isEmailVerified();
        if(flag){
            finish();
            Toast.makeText(MainActivity.this,"LOGIN SUCCESSFUL !",Toast.LENGTH_SHORT).show();

            startActivity(new Intent(MainActivity.this,QuizStartPage.class));
        }else{
            Toast.makeText(MainActivity.this,"VERIFY EMAIL FIRST !",Toast.LENGTH_SHORT).show();
            fbobj2.signOut();
        }
    }

    @Override
    public void onBackPressed() {
        if (backpressedtime+2000>System.currentTimeMillis()){
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }else {
            Toast.makeText(MainActivity.this,"Press Back again to finish !",Toast.LENGTH_SHORT).show();
        }
        backpressedtime = System.currentTimeMillis();


    }
}