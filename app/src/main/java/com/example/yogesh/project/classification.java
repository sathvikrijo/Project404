package com.example.yogesh.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class classification extends AppCompatActivity {

    private Button customer;
    private Button vendor;
    private Button wholesalers;

    private Button button_logout;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);
        try {
            customer = (Button) findViewById(R.id.customer);
            vendor = (Button) findViewById(R.id.vendor);
            //wholesalers = (Button) findViewById(R.id.wholesalers);

            firebaseAuth = FirebaseAuth.getInstance();
            customer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(firebaseAuth.getCurrentUser() == null) {
                        Intent i = new Intent(classification.this, customerlogin.class);
                        startActivity(i);
                    }
                    else
                    {
                        Intent i = new Intent(classification.this, custo_home_page.class);
                        startActivity(i);
                    }
                }
            });
            vendor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(firebaseAuth.getCurrentUser() == null) {
                        Intent i = new Intent(classification.this, vendor_login.class);
                        startActivity(i);
                    }
                    else
                    {
                        Intent i = new Intent(classification.this, Welcome_screen.class);
                        startActivity(i);
                    }
                }
            });
            /*wholesalers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(classification.this, wholesalerlogin.class);
                    startActivity(i);
                }
            });*/


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
