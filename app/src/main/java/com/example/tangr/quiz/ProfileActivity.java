package com.example.tangr.quiz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth profile_logout;
    FirebaseDatabase fbdbobj;
    private ImageView pro_pic;
    private TextView proname,proemail,proroll,prostream,prouser;
    private TextView promarks,attemptleft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pro_pic = (ImageView)findViewById(R.id.PP);
        proname = (TextView) findViewById(R.id.nameupdate);
        proemail = (TextView) findViewById(R.id.mailupdate);
        proroll = (TextView) findViewById(R.id.rollupdate);
        prostream = (TextView) findViewById(R.id.streamupdate);
        prouser = (TextView) findViewById(R.id.usernameupdate);
//        promarks =(TextView) findViewById(R.id.marksupdate);
//        attemptleft = (TextView)findViewById(R.id.attemptleftupdate);


        profile_logout = FirebaseAuth.getInstance();
        fbdbobj = FirebaseDatabase.getInstance();
        DatabaseReference myref = fbdbobj.getReference(profile_logout.getUid());
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userprof = dataSnapshot.getValue(UserProfile.class);
                proname.setText("\tName: "+(userprof.getFname()+" "+userprof.getLname()));
                proemail.setText("\tE-Mail: "+userprof.getMail());
                proroll.setText("\tRoll No.: "+userprof.getRollnos());
                prostream.setText("\tStream: "+userprof.getStream());
//                promarks.setText("\tMarks: "+userprof.getMarks()+"");
                prouser.setText("\tUsername: "+userprof.getUsername());
//                attemptleft.setText("\tAttempt Left: "+userprof.getAttemptofquiz()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this,"CANNOT ACCESS DATABASE !",Toast.LENGTH_SHORT).show();
                profile_logout.signOut();
                finish();
                startActivity(new Intent(ProfileActivity.this,MainActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profileviewmenu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profileviewhome:
                finish();
                startActivity(new Intent(ProfileActivity.this,QuizStartPage.class));
                break;
            case R.id.profileviewabout:
                startActivity(new Intent(ProfileActivity.this,AboutPage.class));
                break;
        }

        return true;
    }




}


