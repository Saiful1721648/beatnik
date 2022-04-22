package com.example.beatnik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class CpcmemberSignUpActivity extends AppCompatActivity implements View.OnClickListener {
    TextView cpcMemberSignUpactivityLogintext;
    EditText cpcMemberSignUpEmailAddress,cpcMemberSignUpPassword ;
    Button cpcMemberSignUpButton ;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpcmember_sign_up);
        cpcMemberSignUpactivityLogintext=(TextView)findViewById(R.id.cpcMemberSignUpactivityLogintext);
        cpcMemberSignUpEmailAddress=(EditText)findViewById(R.id.cpcMemberSignUpEmailAddress);
        cpcMemberSignUpPassword=(EditText)findViewById(R.id.cpcMemberSignUpPassword);
        cpcMemberSignUpButton=(Button)findViewById(R.id.cpcMemberSignUpButton);
        mAuth=FirebaseAuth.getInstance();
        cpcMemberSignUpButton.setOnClickListener(this);
        cpcMemberSignUpactivityLogintext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cpcMemberSignUpactivityLogintext:
                startActivity(new Intent(this,logIn.class));
                break;
            case R.id.cpcMemberSignUpButton:
                cpcSignUp();
                break;
        }

    }
    private void cpcSignUp() {
        String cpcEmailAddress=cpcMemberSignUpEmailAddress.getText().toString().trim();
        String cpcPassward=cpcMemberSignUpPassword.getText().toString().trim();
        if(cpcEmailAddress.isEmpty()){
            cpcMemberSignUpEmailAddress.setError("Nsu Mail id is Required");
            cpcMemberSignUpEmailAddress.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(cpcEmailAddress).matches()){
            cpcMemberSignUpEmailAddress.setError("Please input valid mail address");
            cpcMemberSignUpEmailAddress.requestFocus();
            return;

        }
        if(cpcPassward.isEmpty()){
            cpcMemberSignUpPassword.setError("Fill Up passward field");
            cpcMemberSignUpPassword.requestFocus();
            return;
        }
        if(cpcPassward.length()<6){
            cpcMemberSignUpPassword.setError("Min passward length should be 6 characters");
            cpcMemberSignUpPassword.requestFocus();
            return;

        }
        mAuth.createUserWithEmailAndPassword(cpcEmailAddress, cpcPassward).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    finish();
                    Intent intent=new Intent(getApplicationContext(),cHomePage.class);
                    Toast.makeText(getApplicationContext(),"User has been Registered successfully",Toast.LENGTH_SHORT).show();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else if(task.getException() instanceof FirebaseAuthUserCollisionException)

                {
                    Toast.makeText(getApplicationContext(),"User is already Registered",Toast.LENGTH_SHORT).show();
                }

                else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(),"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

}