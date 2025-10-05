package com.example.a1_patrick_solamillo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

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
        setSupportActionBar(findViewById(R.id.toolbar1));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

        userPayInput = findViewById(R.id.edit1);
        userHoursInput = findViewById(R.id.hourInput);
        enterButton = findViewById(R.id.enterbutton);


        enterButton.setOnClickListener(v->{

            try{
                Double userPay = Double.parseDouble(userPayInput.getText().toString());
                int userHours = Integer.parseInt(userHoursInput.getText().toString());

                UserPayInfo paymentInfo = calculatePay(userPay,userHours);
                double regularPay =  paymentInfo.regularPay;
                double overtimePay = paymentInfo.overtimePay;
                double total = paymentInfo.totalPay;

                TextView displayPayInfo = (TextView)findViewById(R.id.displayPayInfo);

                String info = "Regular Pay: " + regularPay + "\n"
                        + "Overtime Pay: " + overtimePay + "\n"
                        + "Total Pay: " + total;
                displayPayInfo.setText(info);








                //Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                   Toast.makeText(this,"Invalid Input. Enter numbers for pay and hours" + e.getMessage(),Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Toast.makeText(this,"An error occurred: " + e.getMessage(),Toast.LENGTH_LONG).show();
            }
            Toast.makeText(this,"Success!",Toast.LENGTH_SHORT).show();

        });





    }

    public class UserPayInfo{
        public double regularPay;
        public double overtimePay;
        public double totalPay;

        public UserPayInfo(double regPay, double otPay, double total){
            regularPay = regPay;
            overtimePay = otPay;
            totalPay = total;
        }
    }
    public UserPayInfo calculatePay(double numHours,double rate)
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
            regularPay = 40*rate;
            overtimePay=(numHours-40)*rate*1.5;
        }

        totalPay=regularPay+overtimePay;
        return new UserPayInfo(regularPay,overtimePay,totalPay);
    };

    public double calculateTax(double pay)
    {
        return pay * 0.18;
    }



}