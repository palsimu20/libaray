package example.com.libary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StudentDashboard extends AppCompatActivity {
    TextView serach,issuse,ret,lo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        serach=(TextView)findViewById(R.id.se);
        issuse=(TextView)findViewById(R.id.iss);
        ret=(TextView)findViewById(R.id.ret);
        lo=(TextView)findViewById(R.id.logout);
        serach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentDashboard.this,StudentSearchBook.class);
                startActivity(intent);

            }
        });
        issuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentDashboard.this,IssuseBook.class);
                startActivity(intent);

            }
        });
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentDashboard.this,Return.class);
                startActivity(intent);
            }
        });
        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentDashboard.this,StudentSearchBook.class);
                startActivity(intent);
            }
        });
    }
}
