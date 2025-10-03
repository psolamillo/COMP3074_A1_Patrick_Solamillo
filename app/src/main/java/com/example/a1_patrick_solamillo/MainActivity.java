package com.example.a1_patrick_solamillo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public float hoursWorked;
    public double hourlyRate;
    EditText userPayInput;
    EditText userHoursInput;
    Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        userPayInput = findViewById(R.id.edit1);
        userHoursInput = findViewById(R.id.hourInput);
        enterButton = findViewById(R.id.enterbutton);

        enterButton.setOnClickListener(v->{

                Double userPay = Double.parseDouble(userPayInput.getText().toString());
                int userHours = Integer.parseInt(userHoursInput.getText().toString());
                double totalPay = calculatePay(userHours,userPay);
                String msg = "Users TotalPay is: " + totalPay;

                Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

        });



    }

    public double calculatePay(int numHours,double rate)
    {
        double regularPay=0;
        double overtimePay=0;
        double totalPay;
        if(numHours <= 40)
        {
            regularPay = numHours * rate;
        }
        else
        {
            overtimePay=(numHours-40)*rate*1.5 + 40*rate;
        }

        totalPay=regularPay+overtimePay;
        return totalPay;
    };

    public double calculateTax(double pay)
    {
        return pay * 0.18;
    }

    public void testToast()
    {
        String msg = String.valueOf(calculatePay(5,10));
        String taxToast = String.valueOf(calculateTax(calculatePay(5,10)));
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, taxToast, Toast.LENGTH_SHORT).show();
    }
}