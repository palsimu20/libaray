package example.com.libary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentActivity extends AppCompatActivity {
    EditText sname, stid, stpass, sent;
    Button info;
    String emaliId, Name, user_pass, Roll, user_brance;
    Button btn;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    Button registration;
    Spinner spn ,spn1;
    RadioGroup rg;
    RadioButton rb, rb2;
    int counter = 5;
    String gender;

    String names[] = {"Electronics", "Information Technology", "FDGD", "Web Design", "PGDC", "MOM"};
    String year[]={"First year","Second year","Third Year"};
    Student_Database sd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        spn = (Spinner) findViewById(R.id.spinner);
        spn1 = (Spinner) findViewById(R.id.spinner1);
        sname = (EditText) findViewById(R.id.sname);
        info = (Button) findViewById(R.id.stresult);
        stid = (EditText) findViewById(R.id.semail);
        stpass = (EditText) findViewById(R.id.spass);
        sent = (EditText) findViewById(R.id.sentrol);
        registration = (Button) findViewById(R.id.reg);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rb = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        sd=new Student_Database(this);
        btn = (Button) findViewById(R.id.reg);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        //progressDialog = new ProgressDialog(this);
        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb.setChecked(true);
                rb2.setChecked(false);
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb.setChecked(false);
                rb2.setChecked(true);

            }
        });
        if(rb.isChecked()==true)
        {
            gender="male";
        }
        else
        {
            gender="female";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, year);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn1.setAdapter(adapter1);
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                          @Override
                                          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                          }

                                          @Override
                                          public void onNothingSelected(AdapterView<?> parent) {

                                          }
                                      });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentActivity.this,StudentLogin.class);
                startActivity(intent);

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                     String user_Email = stid.getText().toString().trim();
                     String user_pass = stpass.getText().toString().trim();
                    progressDialog.setMessage("waiting sometime when your login is not complemeted !");
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(user_Email, user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(StudentActivity.this, "login successfully", Toast.LENGTH_LONG).show();
                              //  startActivity(new Intent(StudentActivity.this, StudentDashboard.class));

                                finish();
                                sendEmail();
                            } else {
                                Toast.makeText(StudentActivity.this, "login error", Toast.LENGTH_LONG).show();
                                counter--;
                                info.setText(" No of attempts remaning:: " + counter);
                                progressDialog.dismiss();
                                if (counter == 0) {
                                    btn.setEnabled(false);
                                }
                            }
                        }
                    });
                }
                String name=sname.getText().toString();
                String email=stid.getText().toString();
                String password=stpass.getText().toString();
                String enroll=sent.getText().toString();
                String brance=spn.getSelectedItem().toString();
                String year=spn1.getSelectedItem().toString();
                if(sd.insertDatastudent(name,email,password,enroll,gender,brance,year)==true)
                {
                   Toast.makeText(StudentActivity.this,"data is inserted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(StudentActivity.this,"data is  not inserted",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private Boolean validate() {
        String username = sname.getText().toString();
        String userId = stid.getText().toString();
        String userPass = stpass.getText().toString();
        String userenroll = sent.getText().toString();
        String userbrance = spn.getSelectedItem().toString();
        String useryear = spn1.getSelectedItem().toString();

        if (userId.isEmpty() || userPass.isEmpty()||username.isEmpty() || userenroll.isEmpty()||userbrance.isEmpty() || useryear.isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, "plz enter the details", Toast.LENGTH_LONG).show();
        } else {
            progressDialog.dismiss();
            return true;

        }

        return false;
    }
    private  void  sendEmail()
    {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
           firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task) {
                   if(task.isSuccessful())
                   {
                       Toast.makeText(StudentActivity.this,"Successfully registered,verfication mail sent! your gmail",Toast.LENGTH_LONG).show();
                       firebaseAuth.signOut();
                       finish();
                      // startActivity(new Intent(StudentActivity.this,StudentDashboard.class));
                   }
                   else
                   {
                       Toast.makeText(StudentActivity.this,"verfication mail  has not sent!",Toast.LENGTH_LONG).show();
                   }
               }
           }) ;
        }
    }



}






