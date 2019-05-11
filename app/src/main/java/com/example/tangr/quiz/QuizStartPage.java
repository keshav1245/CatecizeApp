package com.example.tangr.quiz;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizStartPage extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Button instruc,start;
    static int attempt;
    private long backpressedtime;
    int subchos;
    private String subval;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start_page);

        instruc = (Button)findViewById(R.id.instructionbutton);
        start = (Button)findViewById(R.id.startquizbut);
        spinner =(Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(QuizStartPage.this,R.array.subname,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getBaseContext(),"Position is : "+i,Toast.LENGTH_SHORT).show();
                subval = String.valueOf(adapterView.getItemAtPosition(i));
                subchos = i+1;

//                Toast.makeText(QuizStartPage.this,subval,Toast.LENGTH_SHORT).show();
//
// Toast.makeText(getBaseContext(),"Subchose is : "+subchos,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference dbref = firebaseDatabase.getReference(firebaseAuth.getUid());

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserProfile obj = dataSnapshot.getValue(UserProfile.class);
                attempt = obj.getAttemptofquiz();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(QuizStartPage.this,"CANNOT ACCESS DATABASE !",Toast.LENGTH_SHORT).show();
            }
        });

//        scoreup = (TextView)findViewById(R.id.scoredisplay);
//        loadhigh();


        instruc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizStartPage.this,InstructionPage2.class));
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline()){
//                    if(attempt <= 0){
//
//                        Toast.makeText(QuizStartPage.this,"ALREADY ATTEMPTED QUIZ ONCE !",Toast.LENGTH_SHORT).show();
//                    }else {



                        final DatabaseReference dbref = firebaseDatabase.getReference(firebaseAuth.getUid());
                        dbref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                UserProfile obj = dataSnapshot.getValue(UserProfile.class);
                                obj.setAttemptofquiz(0);
                                dbref.setValue(obj);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        Intent nact = new Intent(QuizStartPage.this, Quiz.class);
                        nact.putExtra("subchosen",subchos+"");
                        nact.putExtra("subvalue",subval+"");

//                    Toast.makeText(getBaseContext(),"value of sub choosen : "+subchos,Toast.LENGTH_LONG).show();
                        startActivity(nact);
//                    }
                }else{
                    Toast.makeText(QuizStartPage.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logOut:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(QuizStartPage.this,MainActivity.class));

                break;
            case R.id.viewprofile:
                startActivity(new Intent(QuizStartPage.this,ProfileActivity.class));
                break;
            case R.id.about:
                startActivity(new Intent(QuizStartPage.this,AboutPage.class));
                break;
            case R.id.result:
                startActivity(new Intent(QuizStartPage.this,viewmarks.class));
                break;

        }

        return true;
    }


    @Override
    public void onBackPressed() {
        if (backpressedtime+2000>System.currentTimeMillis()){
            firebaseAuth.signOut();
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }else {
            Toast.makeText(QuizStartPage.this,"Press Back again to finish !",Toast.LENGTH_SHORT).show();
        }
        backpressedtime = System.currentTimeMillis();


    }


    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
