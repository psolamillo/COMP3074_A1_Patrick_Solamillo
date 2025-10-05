package com.example.a1_patrick_solamillo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Locale;


public class DetailActivity extends AppCompatActivity {

    ImageView backButton;
    public double userPayInput;
    public double userHourInput;

    ListView details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Toast.makeText(this,"enteredDetails",Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.details);
        //Toast.makeText(this,"Details2",Toast.LENGTH_LONG).show();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });



        userPayInput = getIntent().getDoubleExtra("userpayinput",0.0);
        userHourInput = getIntent().getDoubleExtra("userhourinput",0.0);

        details = findViewById(R.id.detailinfo);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,new ArrayList<>());
        details.setAdapter(adapter);

        adapter.add(String.format(Locale.getDefault(), "Pay: %.2f  Hours: %.2f", userPayInput, userHourInput));







        backButton=findViewById(R.id.backbutton);
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this,MainActivity.class));
        });

    }


}
