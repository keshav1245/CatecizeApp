package com.example.tangr.quiz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class viewmarks extends AppCompatActivity {

    Spinner spinner;
    int subchos;
    ArrayAdapter<CharSequence> adapter;
    private String subval;
    Button getmarks;
    FirebaseAuth profile_logout;
    FirebaseDatabase fbdbobj;
    String roll;
    JSONObject serverdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmarks);
        profile_logout = FirebaseAuth.getInstance();
        fbdbobj = FirebaseDatabase.getInstance();
        DatabaseReference myref = fbdbobj.getReference(profile_logout.getUid());
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userprof = dataSnapshot.getValue(UserProfile.class);
                roll = userprof.getRollnos();
              //  Toast.makeText(viewmarks.this,"Roll : "+roll,Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(viewmarks.this,"CANNOT ACCESS DATABASE !",Toast.LENGTH_SHORT).show();
                profile_logout.signOut();
                finish();
                startActivity(new Intent(viewmarks.this,MainActivity.class));
            }
        });
        getmarks = (Button)findViewById(R.id.marksfetch);
        spinner =(Spinner)findViewById(R.id.spinnerres);
        adapter = ArrayAdapter.createFromResource(viewmarks.this,R.array.subresult,android.R.layout.simple_spinner_item);
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


        getmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.MARKSCHECK, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {

                            serverdata = new JSONObject(response);
//                                    Log.d("JSON Data : ", serverdata.toString());
//                                    Log.d("Server Size Response", String.valueOf(serverdata.length()));
                            //Toast.makeText(Quiz.this,serverdata.toString(),Toast.LENGTH_SHORT).show();
                           Toast.makeText(viewmarks.this,serverdata.toString(),Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "Response recieve Error !" , Toast.LENGTH_LONG).show();

                    }}){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("sub", subchos+"");
                        params.put("roll", roll);

                        return params;
                    }
                };

                RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });


    }
}
