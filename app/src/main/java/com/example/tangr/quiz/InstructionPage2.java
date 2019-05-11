package com.example.tangr.quiz;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class InstructionPage2 extends AppCompatActivity {
ViewPager viewPager;
CustomSwipeAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_page2);
        Toast.makeText(InstructionPage2.this,"Swipe Pictures !",Toast.LENGTH_SHORT).show();
        viewPager = (ViewPager)findViewById(R.id.viewpageslide);
        adapter = new CustomSwipeAdapter(InstructionPage2.this);
        viewPager.setAdapter(adapter);

    }
}
