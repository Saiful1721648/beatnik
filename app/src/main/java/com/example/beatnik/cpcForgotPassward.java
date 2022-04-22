package com.example.beatnik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class cpcForgotPassward extends AppCompatActivity {
    private EditText forgotPassCpcEmailAddress;
    private Button cpcResetPassButton;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpc_forgot_passward);
        forgotPassCpcEmailAddress=(EditText)findViewById(R.id.cpcResEmailAddress);
        cpcResetPassButton=(Button)findViewById(R.id.cpcResPasswardButton);

        auth = FirebaseAuth.getInstance();
        cpcResetPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpcMemberResetPassward();

            }
        });


    }
    private void cpcMemberResetPassward(){
        String cpcMail =forgotPassCpcEmailAddress.getText().toString().trim();
        if(cpcMail.isEmpty()){
            forgotPassCpcEmailAddress.setError("Nsu Mail id is Required");
            forgotPassCpcEmailAddress.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(cpcMail).matches()){
            forgotPassCpcEmailAddress.setError("Please input valid mail address");
            forgotPassCpcEmailAddress.requestFocus();
            return;

        }
        auth.sendPasswordResetEmail(cpcMail).addOnCompleteListener(new OnCompleteListener<Void>() {


            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Check Your email to reset Passward",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Try again ! something wrong happened",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

