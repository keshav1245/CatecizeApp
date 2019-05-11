package com.example.tangr.quiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Password_reset extends AppCompatActivity {
    private EditText mailc;
    private Button links;
    FirebaseAuth fbresetobject;
    private ProgressDialog reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        mailc = (EditText)findViewById(R.id.email_collect);
        links = (Button)findViewById(R.id.send_link);
        reset = new ProgressDialog(Password_reset.this);
        fbresetobject = FirebaseAuth.getInstance();
        links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset.setMessage("Please Wait While We are Processing Your Data !");
                reset.show();
                String useremail = mailc.getText().toString().trim();
                if(useremail.isEmpty()){
                    reset.dismiss();
                    Toast.makeText(Password_reset.this,"Please Enter your E-mail.",Toast.LENGTH_SHORT).show();
                }else{
                    fbresetobject.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                reset.dismiss();
                                Toast.makeText(Password_reset.this,"Password reset email sent !",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Password_reset.this,MainActivity.class));


                            }else{
                                reset.dismiss();
                                Toast.makeText(Password_reset.this,"Password reset email not sent !",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.passforgetmenu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.passforgetgohome:
                fbresetobject.signOut();
                finish();
                startActivity(new Intent(Password_reset.this,MainActivity.class));
                break;
            case R.id.passforgetgoabout:
                startActivity(new Intent(Password_reset.this,AboutPage.class));
                break;
        }
        return true;
    }
}
