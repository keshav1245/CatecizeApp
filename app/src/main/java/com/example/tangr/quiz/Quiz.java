package com.example.tangr.quiz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.text.TextUtils.isEmpty;

public class Quiz extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mdrawerLayout;
    private int imageflag = 0;
    private ActionBarDrawerToggle mToggle;
    public static final long COUNTDOWN_TIME = 420000;
    FirebaseAuth logoutuser;
    private TextView textviewscore,textviewquestioncount,textviewquestion,textViewcountdown;
    private RadioGroup rbgroup;
    private RadioButton rb1,rb2,rb3,rb4;
    private Button confirmnext;
    private RelativeLayout relativeLayout;
    private ImageView imageView;
    //private List<QUESTION> questionList;
    private ColorStateList textcolorDefaultRb;
    private ColorStateList textcolorDefaultCd;
    //private int questioncounter;
    private int questioncounttotal;
   // private QUESTION currentquestion;
    private  double scoreinquiz=0.0;
    public  double checkscore;
    private boolean ans;
    private long timeleftinmillis;
    private CountDownTimer countdowntimer;
    private long backpressedtime;
    private long pausepressedtime =0;
    private int subjectselect;
    private String subjectvalue;
    JSONObject jsObj;
    JSONObject serverdata;
    private int Answernumber;
    private static int[] response_store = new int[25];
    private int response_index;
    public int offset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mdrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mToggle =new ActionBarDrawerToggle(Quiz.this,mdrawerLayout,R.string.open,R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        logoutuser = FirebaseAuth.getInstance();
        textViewcountdown = (TextView)findViewById(R.id.timerclock);
        textviewquestion = (TextView)findViewById(R.id.questview);
        textviewquestioncount =(TextView)findViewById(R.id.questionview);
        imageView = (ImageView)findViewById(R.id.quesimage);
//        textviewscore = (TextView)findViewById(R.id.scoreview);
        final NavigationView navigationView =(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(Quiz.this);

        rbgroup = (RadioGroup)findViewById(R.id.radiogroup);
        rb1 = (RadioButton)findViewById(R.id.radiobut1);
        rb2 = (RadioButton)findViewById(R.id.radiobut2);
        rb3 = (RadioButton)findViewById(R.id.radiobut3);
        rb4 = (RadioButton)findViewById(R.id.radiobut4);
        confirmnext = (Button)findViewById(R.id.nextques);


//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                imageView.requestLayout();
//                if(imageflag==0){
//                    imageflag++;
//                    imageView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
//                    imageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                }else {
//                    imageflag--;
//                    imageView.getLayoutParams().height = 640;
//                    imageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                }
//            }
//        });
       // next_test =findViewById(R.id.test_ques);

        textcolorDefaultRb = rb1.getTextColors();
        textcolorDefaultCd = textViewcountdown.getTextColors();
        timeleftinmillis =COUNTDOWN_TIME;

        Intent i =getIntent();
        subjectselect = Integer.parseInt(i.getStringExtra("subchosen"));
        subjectvalue = i.getStringExtra("subvalue");
//        Toast.makeText(Quiz.this,subjectvalue,Toast.LENGTH_SHORT).show();
////        Toast.makeText(Quiz.this,"value of subject select : "+subjectselect,Toast.LENGTH_SHORT).show();
        //questionList = new ArrayList<>();

//        next_test.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.FETCH_URL, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {

                                try {

                                    serverdata = new JSONObject(response);
//                                    Log.d("JSON Data : ", serverdata.toString());
//                                    Log.d("Server Size Response", String.valueOf(serverdata.length()));
                                    //Toast.makeText(Quiz.this,serverdata.toString(),Toast.LENGTH_SHORT).show();
                                    startCountDown();
                                    ShowNextQues();

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
                                params.put("sub", subjectvalue);

                                return params;
                            }
                        };

                        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

//                    }
//                }
//        );





        confirmnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ShowNextQues();

                if(!ans){
                    if(rb1.isChecked()||rb2.isChecked()||rb3.isChecked()||rb4.isChecked()){
                        MenuItem menuItem = navigationView.getMenu().getItem(offset-1);
                        menuItem.setIcon(ContextCompat.getDrawable(navigationView.getContext(), R.drawable.check));
                        checkAnswer();

                    }else {
                        Toast.makeText(Quiz.this,"Please Select An Option !",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    ShowNextQues();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ShowNextQues(){
questioncounttotal = serverdata.length();
       // Log.d("ServerData Size ", String.valueOf(serverdata.length()));

        if(offset < serverdata.length()){
            try {
//                response_index =offset;
                jsObj = serverdata.getJSONObject(String.valueOf(offset + 1));
                rb1.setTextColor(textcolorDefaultRb);
                rb2.setTextColor(textcolorDefaultRb);
                rb3.setTextColor(textcolorDefaultRb);
                rb4.setTextColor(textcolorDefaultRb);
                rbgroup.clearCheck();

//            currentquestion = questionList.get(questioncounter);
//            textviewquestion.setText(currentquestion.getQuestion());

                if(!(isEmpty(jsObj.getString("question")))){
                    textviewquestion.setVisibility(View.VISIBLE);
                    textviewquestion.setText(jsObj.getString("question"));
                    imageView.setVisibility(View.INVISIBLE);
                }else{
                    imageView.setVisibility(View.VISIBLE);
                    textviewquestion.setVisibility(View.INVISIBLE);
                    String url = Constants.IMAGE_URL + jsObj.getString("imagequestion");
                    Glide.with(Quiz.this).load(url).into(imageView);

                }
                rb1.setText(jsObj.getString("option1"));
                rb2.setText(jsObj.getString("option2"));
                rb3.setText(jsObj.getString("option3"));
                rb4.setText(jsObj.getString("option4"));
            offset++;
                switch (response_store[offset-1]){
                    case 1:
                        rb1.setChecked(true);
                        checkAnswer();
//                rb1.setTextColor(Color.BLACK);
//                textviewquestion.setText("Answer 1 is correct !");
                        break;
                    case 2:
                        rb2.setChecked(true);
                        checkAnswer();
//                rb2.setTextColor(Color.BLACK);
//                textviewquestion.setText("Answer 2 is correct !");
                        break;
                    case 3:
                        rb3.setChecked(true);
                        checkAnswer();
//               rb3.setTextColor(Color.BLACK);
//                textviewquestion.setText("Answer 3 is correct !");
                        break;
                    case 4:
                        rb4.setChecked(true);
                        checkAnswer();
//                rb4.setTextColor(Color.BLACK);
//                textviewquestion.setText("Answer 4 is correct !");
                        break;
                }
            textviewquestioncount.setText("Question: "+offset+"/"+questioncounttotal);
            ans = false;
            confirmnext.setText("Confirm!");


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }else{
            finishQuiz();
        }



//
    }
    private void startCountDown(){
        countdowntimer = new CountDownTimer(timeleftinmillis,1000) {
            @Override
            public void onTick(long l) {
                timeleftinmillis = l;
                updatecdtext();
            }

            @Override
            public void onFinish() {
                timeleftinmillis =0;
                updatecdtext();
                finishQuiz();
            }
        }.start();
    }

    private void updatecdtext(){
        int mins =(int)(timeleftinmillis/1000)/60;
        int secs = (int)(timeleftinmillis/1000)%60;
        String timeformatted = String.format(Locale.getDefault(),"%02d:%02d",mins,secs);
        textViewcountdown.setText(timeformatted);
        if(timeleftinmillis<10000) {
//            Toast.makeText(Quiz.this,"10 seconds to GO !",Toast.LENGTH_SHORT).show();
            textViewcountdown.setTextColor(Color.RED);

        }else
            textViewcountdown.setTextColor(textcolorDefaultCd);
    }

    private void checkAnswer(){
        ans =true;
        //countdowntimer.cancel();
        RadioButton rbchecked = findViewById(rbgroup.getCheckedRadioButtonId());
        int answernr = rbgroup.indexOfChild(rbchecked)+1;
        response_index = offset-1;
        response_store[response_index]=answernr;

//            if(answernr==Answernumber){
//                scoreinquiz+=0.5;
//                checkscore =  scoreinquiz;
//                textviewscore.setText("Score: "+scoreinquiz);
//            }
//        try {
////            jsObj.put("correct",5);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        rb1.setTextColor(textcolorDefaultRb);
        rb2.setTextColor(textcolorDefaultRb);
        rb3.setTextColor(textcolorDefaultRb);
        rb4.setTextColor(textcolorDefaultRb);
        rbchecked.setTextColor(Color.BLACK);
        showsolution();
    }

    private void showsolution(){
//        rb1.setTextColor(Color.RED);
//        rb2.setTextColor(Color.RED);
//        rb3.setTextColor(Color.RED);
//        rb4.setTextColor(Color.RED);
//        switch (Answernumber){
//            case 1:
//                rb1.setTextColor(Color.GREEN);
//                textviewquestion.setText("Answer 1 is correct !");
//                break;
//            case 2:
//                rb2.setTextColor(Color.GREEN);
//                textviewquestion.setText("Answer 2 is correct !");
//                break;
//            case 3:
//                rb3.setTextColor(Color.GREEN);
//                textviewquestion.setText("Answer 3 is correct !");
//                break;
//            case 4:
//                rb4.setTextColor(Color.GREEN);
//                textviewquestion.setText("Answer 4 is correct !");
//                break;
//                default:
//                    Toast.makeText(Quiz.this,"Already response submitted for this question !",Toast.LENGTH_SHORT).show();
//        }
//        Toast.makeText(Quiz.this,"Response Recorded !",Toast.LENGTH_SHORT).show();
        if(offset<questioncounttotal)
            confirmnext.setText("NEXT");

        else
            confirmnext.setText("FINISH !");

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(countdowntimer!=null){
            countdowntimer.cancel();

        }
    }
    private void finishQuiz(){
        for(response_index =0;response_index<25;response_index++){
            try {
                jsObj = serverdata.getJSONObject(String.valueOf(response_index + 1));
                Answernumber = jsObj.getInt("correct");
                if(response_store[response_index]==Answernumber){
                    scoreinquiz+=0.5;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        Intent resultIntent = new Intent(Quiz.this,ResultPage.class);

        resultIntent.putExtra("sub",subjectselect+"");
        resultIntent.putExtra("scooore",scoreinquiz+"");
        for(int i=0;i<25;i++){
            response_store[i]=0;
        }
        finish();
        startActivity(resultIntent);

    }
    @Override
    public void onBackPressed() {
        if (backpressedtime+2000>System.currentTimeMillis()){
            finishQuiz();
        }else {
            Toast.makeText(Quiz.this,"Press Back again to finish !",Toast.LENGTH_SHORT).show();
        }
        backpressedtime = System.currentTimeMillis();
    }


    @Override
    protected void onPause() {
        if(pausepressedtime == 1) {
            finishQuiz();
        }else {
            pausepressedtime++;
        }
        super.onPause();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.question1:
                offset = 0;
                mdrawerLayout.closeDrawers();
                ShowNextQues();
                break;
            case R.id.question2:
                offset = 1;
                mdrawerLayout.closeDrawers();
                ShowNextQues();
                break;
            case R.id.question3:
                offset = 2;
                mdrawerLayout.closeDrawers();
                ShowNextQues();
                break;
            case R.id.question4:
                offset = 3;
                mdrawerLayout.closeDrawers();
                ShowNextQues();
                break;
            case R.id.question5:
                offset = 4;
                mdrawerLayout.closeDrawers();
                ShowNextQues();
                break;
            case R.id.question6:
                offset = 5;
                mdrawerLayout.closeDrawers();
                ShowNextQues();
                break;
            case R.id.question7:
                offset = 6;
                mdrawerLayout.closeDrawers();
                ShowNextQues();
                break;
            case R.id.question8:
                offset = 7;
                mdrawerLayout.closeDrawers();
                ShowNextQues();
                break;
            case R.id.question9:
                offset = 8;
                mdrawerLayout.closeDrawers();
                ShowNextQues();
                break;
            case R.id.question10:
                mdrawerLayout.closeDrawers();
                offset = 9;
                ShowNextQues();
                break;
//            case R.id.question11:
//                mdrawerLayout.closeDrawers();
//                offset = 10;
//                ShowNextQues();
//                break;
//            case R.id.question12:
//                offset = 11;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question13:
//                offset = 12;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question14:
//                offset = 13;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question15:
//                offset = 14;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question16:
//                offset = 15;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question17:
//                offset = 16;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question18:
//                offset = 17;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question19:
//                offset = 18;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question20:
//                offset = 19;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question21:
//                offset = 20;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question22:
//                offset = 21;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question23:
//                offset = 22;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question24:
//                offset = 23;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
//            case R.id.question25:
//                offset = 24;
//                mdrawerLayout.closeDrawers();
//                ShowNextQues();
//                break;
            case R.id.finishnow:
                finishQuiz();
                break;

        }
        return false;
    }
}
