package example.com.libary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentLogin extends AppCompatActivity {
    Button login;
    EditText userid, pass;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        userid = (EditText) findViewById(R.id.us);
        pass = (EditText) findViewById(R.id.pass);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser User = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);
       login = (Button) findViewById(R.id.logins);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               validate(userid.getText().toString(),pass.getText().toString());
            }
        });


    }

    private void validate(String StudentId, String pass) {
        progressDialog.setMessage("you can wait some time when your login is successfully");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(StudentId, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    checkverfication();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(StudentLogin.this, "Login failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private   void checkverfication()
    {
        FirebaseUser User=firebaseAuth.getInstance().getCurrentUser();
        Boolean emailverfication=User.isEmailVerified();
        // startActivity(new Intent(StudentRegistration.this,StudentDashboard.class));
        if(emailverfication)
        {
            finish();
            startActivity(new Intent(StudentLogin.this,StudentDashboard.class));
        }
        else {
            Toast.makeText(StudentLogin.this,"Verify email",Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
        }
    }
}