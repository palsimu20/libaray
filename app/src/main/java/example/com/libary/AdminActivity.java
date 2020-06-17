package example.com.libary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {
  private EditText username,userpass;


  private Button login;
     private  TextView info;
    private int counter=5;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
       setupView();
       firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        info.setText("attempts number::");
       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(validate())
               {
                   final String user_Email=username.getText().toString().trim();
                   final String user_pass=userpass.getText().toString().trim();
                   progressDialog.setMessage("waiting sometime when your login is not complemeted !");
                   progressDialog.show();
                   firebaseAuth.createUserWithEmailAndPassword(user_Email,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful())
                           {
                               progressDialog.dismiss();

                               Toast.makeText(AdminActivity.this,"login successfully",Toast.LENGTH_LONG).show();
                               startActivity(new Intent(AdminActivity.this,DashBoard.class));
                           }
                           else
                           {
                               Toast.makeText(AdminActivity.this,"login error",Toast.LENGTH_LONG).show();
                               counter--;
                               info.setText(" No of attempts remaning:: "+counter);
                               progressDialog.dismiss();
                               if(counter ==0)
                               {
                                   login.setEnabled(false);
                               }
                           }
                       }
                   });
               }
           }
       });
    }
    private  void  setupView()
    {

        username=(EditText)findViewById(R.id.userid);
        userpass=(EditText)findViewById(R.id.password1);
        login=(Button)findViewById(R.id.login);
        info=(TextView)findViewById(R.id.tv);
    }
    private Boolean validate()
    {
        String userId=username.getText().toString();
        String userPass=userpass.getText().toString();
        if (userId.isEmpty() || userPass.isEmpty()) {
            progressDialog.dismiss();
            username.setError("plz enter a valid email");
            userpass.setError("plz enter a valid password");
            Toast.makeText(this, "plz enter the details", Toast.LENGTH_LONG).show();
        }



            else {
                    progressDialog.dismiss();
        return true;

    }

       return false;
    }
}
