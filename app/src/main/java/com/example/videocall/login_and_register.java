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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_and_register extends AppCompatActivity {
    private EditText ee;
    private EditText ep;
    private Button lb;
    private Button Rb;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);
        ee = (EditText) findViewById(R.id.ee);
        ep = (EditText) findViewById(R.id.ep);
        lb = (Button) findViewById(R.id.lb);
        Rb = (Button) findViewById(R.id.Rb);
        auth=FirebaseAuth.getInstance();

      Rb.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(login_and_register.this,register.class);
              startActivity(intent);
          }
      });

      lb.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v)
          {

              String email,pass;
              email=ee.getText().toString();
              pass=ep.getText().toString();
              auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task)
                  {
                      if (task.isSuccessful())
                      {
                          Toast.makeText(login_and_register.this, "log in", Toast.LENGTH_SHORT).show();
                          Intent intent=new Intent(login_and_register.this,dashboard.class);
                          startActivity(intent);

                      }
                      else
                      {
                          Toast.makeText(login_and_register.this,task.getException().toString(), Toast.LENGTH_SHORT).show();
                      }

                  }
              });

          }
      });
    }
}