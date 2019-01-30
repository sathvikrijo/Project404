package com.example.yogesh.project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class customer_signup extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReferenceCustomerId;
    private EditText editText_email,editText_password, editText_username, editText_phoneNumber;
    private String email,password, username;
    private long phNumber;
    private Button editText_btn;
    private Button createacc;
    private TextView tvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        editText_email = findViewById(R.id.c_email_id);
        editText_password = findViewById(R.id.c_password);
        editText_btn = findViewById(R.id.btn_signup);
        editText_username = findViewById(R.id.c_username);
        editText_phoneNumber = findViewById(R.id.c_phone_number);

        editText_btn.setOnClickListener(this);
        createacc=(Button)findViewById(R.id.btn_sign);
        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(customer_signup.this,custo_home_page.class);
                startActivity(i);
            }
        });
        tvv=(TextView)findViewById(R.id.tvv);
        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(customer_signup.this,customerlogin.class);
                startActivity(i);
            }
        });
    }

    public void customerInformationAdd() {
        String userId = firebaseAuth.getCurrentUser().getUid();
        databaseReferenceCustomerId = FirebaseDatabase.getInstance().getReference("customers").child(userId);

        username = editText_username.getText().toString();
        phNumber = Integer.parseInt(editText_phoneNumber.getText().toString());

        personalInformation vendorInfo = new personalInformation(username, phNumber);
        databaseReferenceCustomerId.setValue(vendorInfo);
    }

    @Override
    public void onClick(View v) {
        if(v == editText_btn){
            email = editText_email.getText().toString();
            password = editText_password.getText().toString();

            if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                Toast.makeText(this, "E-mail ID and Password cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(email)) {
                Toast.makeText(this, "E-mail ID cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        customerInformationAdd();
                        finish();
                        startActivity(new Intent(getApplicationContext(), custo_home_page.class));
                    }
                }
            });
        }
    }
}
