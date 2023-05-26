package com.example.animalhospital;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Intent intent = getIntent();
        ((TextView)findViewById(R.id.txt_item_name)).setText(intent.getStringExtra("item_name"));
        ((TextView)findViewById(R.id.txt_item_count)).setText(String.valueOf(intent.getIntExtra("item_count", 0))+"개");
        ((TextView)findViewById(R.id.txt_item_pay)).setText(String.valueOf(intent.getIntExtra("item_pay", 0))+"원");
    }
}