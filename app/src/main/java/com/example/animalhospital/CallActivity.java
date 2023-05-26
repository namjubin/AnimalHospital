package com.example.animalhospital;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CallActivity extends AppCompatActivity {

    EditText edit_name, edit_phone, consulting;
    CheckBox check_agree;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);
        consulting = findViewById(R.id.consulting);
        check_agree = findViewById(R.id.check_agree);
        findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:82-02-1234-4567"));
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_name = edit_name.getText().toString();
                String str_phone = edit_phone.getText().toString();
                String str_consulting = consulting.getText().toString();
                String str_msg = "상담신청자:"+str_name+"\n상담내용:"+str_consulting;

                if(str_consulting.trim().length()==0){
                    Toast.makeText(CallActivity.this, "상담내용을 적어주세요", Toast.LENGTH_SHORT).show();
                    consulting.requestFocus();
                }
                else{
                    if(check_agree.isChecked()) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("smsto:" + str_phone));
                        intent.putExtra("sms_body", str_msg);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(CallActivity.this, "개인정보취급방침에 동의해주세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}