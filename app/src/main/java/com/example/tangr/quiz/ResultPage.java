package com.example.tangr.quiz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ResultPage extends AppCompatActivity {
    private TextView rfname,rlname,rroll,rusername,remail,rstream,rmarks;
    FirebaseAuth profile_logout;
    private Button gohome;
    FirebaseDatabase fbdbobj;
    private double resultant_marks;
    private int subselect;
    DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        rfname = (TextView) findViewById(R.id.resname);
        rlname=(TextView) findViewById(R.id.reslastname);
        rroll = (TextView) findViewById(R.id.resroll);
        rusername = (TextView) findViewById(R.id.resusername);
        remail = (TextView) findViewById(R.id.resemail);
        rstream = (TextView) findViewById(R.id.resstream);
        rmarks = (TextView) findViewById(R.id.resmarks);
        gohome = (Button)findViewById(R.id.resulthomebutton);
        profile_logout = FirebaseAuth.getInstance();
        fbdbobj = FirebaseDatabase.getInstance();

        myref = fbdbobj.getReference(profile_logout.getUid());
        Intent i = getIntent();
        resultant_marks = Double.parseDouble(i.getStringExtra("scooore"));
        subselect = Integer.parseInt(i.getStringExtra("sub"));
        rmarks.setText(resultant_marks+"");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userprof = dataSnapshot.getValue(UserProfile.class);
                rfname.setText(userprof.getFname());
                rlname.setText(userprof.getLname());
                remail.setText(userprof.getMail());
                rroll.setText(userprof.getRollnos());
                rstream.setText(userprof.getStream());
                rusername.setText(userprof.getUsername());
                // attemptleft.setText(userprof.getAttemptofquiz()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ResultPage.this,"CANNOT ACCESS DATABASE !",Toast.LENGTH_SHORT).show();
                /*profile_logout.signOut();
                finish();
                startActivity(new Intent(ProfileActivity.this,MainActivity.class));*/
            }
        });
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fname = rfname.getText().toString();
                final String lname = rlname.getText().toString();
                final String email = remail.getText().toString();
                final String rollno = rroll.getText().toString();
                final String stream = rstream.getText().toString();
                final String username = rusername.getText().toString();
                final double marks = Double.parseDouble(rmarks.getText().toString());



                UserProfile userProfile = new UserProfile(email,fname,lname,rollno,stream,username,marks,0);

//                myref.setValue(userProfile);
                StringRequest stringRequest = new StringRequest(Request.Method.POST,

                        Constants.RESULT_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                               // progressDialog.dismiss();
                                try{
                                    JSONObject jsonObject = new JSONObject(response);
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("sub",subselect+"");
                        params.put("name",fname+" "+lname);
                        params.put("email",email);
                        params.put("roll",rollno);
                        params.put("branch",stream);
                        params.put("username",username);
                        params.put("marks1",""+marks);
                        return params;
                    }
                };
                RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
                finish();
                startActivity(new Intent(ResultPage.this,QuizStartPage.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
//        String fname = rfname.getText().toString();
//        String lname = rlname.getText().toString();
//        String email = remail.getText().toString();
//        String rollno = rroll.getText().toString();
//        String stream = rstream.getText().toString();
//        String username = rusername.getText().toString();
//        double marks = Double.parseDouble(rmarks.getText().toString());
//
//        UserProfile userProfile = new UserProfile(email,fname,lname,rollno,stream,username,marks,0);
//        myref.setValue(userProfile);
//
//        finish();
//        startActivity(new Intent(ResultPage.this,QuizStartPage.class));

        gohome.callOnClick();

    }



}


