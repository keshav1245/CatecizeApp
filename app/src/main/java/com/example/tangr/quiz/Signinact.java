package com.example.tangr.quiz;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signinact extends AppCompatActivity {
    private EditText fname,lname,username,roll,branch,pass,cpass,email;
    private Button signup;
    private TextView tvasB;
    private FirebaseAuth fbobj;
    private ImageView profile_picture;
    String fn,ln,un,rn,br,ps,cps,em;
    private ProgressDialog subuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinact);
        setval();
        fbobj = FirebaseAuth.getInstance();
        tvasB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goinbacktolog = new Intent(Signinact.this,MainActivity.class);
                startActivity(goinbacktolog);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    if(pass.getText().toString().equals(cpass.getText().toString())){
                        subuser.setMessage("Please Wait While We are Processing Your Data !");
                        subuser.show();
                        String user_email = email.getText().toString().trim();
                        String user_pass = pass.getText().toString();
                        //trim function is used to remove all the white spaces user might have entered !
                        fbobj.createUserWithEmailAndPassword(user_email,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    sendverification();
                                }else{
                                    Toast.makeText(Signinact.this,"ERROR OCCURED TRY AGAIN LATER !",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(Signinact.this,"PASSWORD MISMATCH !",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
    private void setval(){
        fname = (EditText)findViewById(R.id.T1);
        lname = (EditText)findViewById(R.id.T2);
        username = (EditText)findViewById(R.id.T3);
        pass = (EditText)findViewById(R.id.T4);
        cpass = (EditText)findViewById(R.id.T5);
        roll = (EditText)findViewById(R.id.T6);
        branch = (EditText)findViewById(R.id.T7);
        signup = (Button)findViewById(R.id.B1);
        tvasB = (TextView)findViewById(R.id.tvB2);
        email = (EditText)findViewById(R.id.T8);
        subuser = new ProgressDialog(Signinact.this);
    }
    private Boolean validate(){
        Boolean res = false;
        em = email.getText().toString().trim();
        fn = fname.getText().toString();
        ln = lname.getText().toString();
        un = username.getText().toString();
        rn = roll.getText().toString();
        br = branch.getText().toString();
        ps = pass.getText().toString();
        cps = cpass.getText().toString();
        if(em.isEmpty()||fn.isEmpty()||ln.isEmpty()||un.isEmpty()||rn.isEmpty()||br.isEmpty()||ps.isEmpty()||cps.isEmpty()){
            Toast.makeText(Signinact.this,"All Fields are Compulsary !",Toast.LENGTH_SHORT).show();
        }else{
            res = true;
        }
        return res;
    }

    private void sendverification(){
        FirebaseUser fbuser = fbobj.getCurrentUser();
        if(fbuser!=null){
            fbuser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendData();
                        subuser.dismiss();
                        Toast.makeText(Signinact.this,"REGISTRATION SUCCESSFUL A Verification Link is Sent To Your Mail !",Toast.LENGTH_SHORT).show();
                        fbobj.signOut();
                        finish();
                        startActivity(new Intent(Signinact.this,MainActivity.class));
                    }else{
                        subuser.dismiss();
                        Toast.makeText(Signinact.this,"REGISTRATION FAILED !",Toast.LENGTH_SHORT).show();

                    }


                }
            });
        }

    }
    private void sendData(){

        FirebaseDatabase fbdb = FirebaseDatabase.getInstance();
        DatabaseReference myref = fbdb.getReference(fbobj.getUid());
        UserProfile user = new UserProfile(em,fn.toUpperCase(),ln.toUpperCase(),rn.toUpperCase(),br.toUpperCase(),un,0,1);
        myref.setValue(user);
    }
}
