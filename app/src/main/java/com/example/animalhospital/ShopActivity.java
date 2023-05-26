package com.example.animalhospital;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_product_main;
    EditText edit_count;
    TextView txt_price, txt_delivery, txt_pay;
    CheckBox chk_agree;
    int selected_count=1;
    int selected_price=1500;
    int val_delivery=3000;
    int val_pay=0;
    int val_price=0;
    String selected_name="소고기와 치즈맛";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        img_product_main = findViewById(R.id.img_product_main);
        edit_count = findViewById(R.id.edit_count);
        txt_price = findViewById(R.id.txt_price);
        txt_delivery = findViewById(R.id.txt_delivery);
        txt_pay = findViewById(R.id.txt_pay);
        chk_agree = findViewById(R.id.chk_agree);

        findViewById(R.id.radio1).setOnClickListener(this);
        findViewById(R.id.radio2).setOnClickListener(this);
        findViewById(R.id.radio3).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_pay).setOnClickListener(this);

        findViewById(R.id.radio1).setSelected(true);

    }

    @Override
    public void onClick(View view) {
        selected_count = Integer.parseInt(edit_count.getText().toString());
        switch (view.getId()){
            case R.id.radio1:
                img_product_main.setImageResource(R.drawable.product1);
                selected_price = 1500;
                selected_name = findViewById(R.id.radio1).getTag().toString();
                sumTotal();
                break;
            case R.id.radio2:
                img_product_main.setImageResource(R.drawable.product2);
                selected_price = 2000;
                selected_name = findViewById(R.id.radio2).getTag().toString();
                sumTotal();
                break;
            case R.id.radio3:
                img_product_main.setImageResource(R.drawable.product3);
                selected_price = 3000;
                selected_name = findViewById(R.id.radio3).getTag().toString();
                sumTotal();
                break;
            case R.id.btn_minus:
                if(selected_count==1){
                    Toast.makeText(this, "최소 수량은 1입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    --selected_count;
                    edit_count.setText(String.valueOf(selected_count));
                    sumTotal();
                }
                break;
            case R.id.btn_plus:
                if(selected_count>=5){
                    Toast.makeText(this, "최대 수량은 5입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    ++selected_count;
                    edit_count.setText(String.valueOf(selected_count));
                    sumTotal();
                }
                break;
            case R.id.btn_pay:
                sumTotal();
                if(chk_agree.isChecked()){
                    Intent intent = new Intent(ShopActivity.this, PayActivity.class);
                    intent.putExtra("item_name", selected_name);
                    intent.putExtra("item_count", selected_count);
                    intent.putExtra("item_pay", val_pay);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "결제에 동의 해주세요", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void sumTotal() {
        int selected_count = Integer.parseInt(edit_count.getText().toString());
        val_price = selected_price*selected_count;
        txt_price.setText(String.valueOf(val_price)+"원");

        if(val_price < 10000){
            val_delivery = 3000;
            txt_delivery.setText(String.valueOf(val_delivery)+"원");
        }
        else{
            val_delivery = 0;
            txt_delivery.setText("무료");
        }
        val_pay = val_price + val_delivery;
        txt_pay.setText(String.valueOf(val_pay)+"원");
    }
}