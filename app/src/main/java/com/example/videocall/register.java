package com.example.videocall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class register extends AppCompatActivity {
    private EditText n;
    private EditText ee;
    private EditText ep;
    private Button lb;
    private Button Rb;
    FirebaseAuth auth;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        n = (EditText) findViewById(R.id.n);
        ee = (EditText) findViewById(R.id.ee);
        ep = (EditText) findViewById(R.id.ep);
        lb = (Button) findViewById(R.id.lb);
        Rb = (Button) findViewById(R.id.Rb);
       auth=FirebaseAuth.getInstance();
       database=FirebaseFirestore.getInstance();

        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password,name;
                email=ee.getText().toString();
                password=ep.getText().toString();
                name=n.getText().toString();

                User user=new User();
                user.setName(name);
                user.setEmail(email);
                user.setPass(password);

                //auth

                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(register.this, "account created successfully", Toast.LENGTH_SHORT).show();
                            database.collection("user")
                                    .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused)
                                {
                                    Toast.makeText(register.this, "A/c is created", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(register.this,login_and_register.class);
                                    startActivity(intent);

                                }
                            });
                        }

                        else 
                        {
                            Toast.makeText(register.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });
    }
}