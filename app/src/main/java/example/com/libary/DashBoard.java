package example.com.libary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class DashBoard extends AppCompatActivity {
    private TextView tv,delete ,add,view,update,viewst;
    FirebaseAuth firebaseAuth;
//ContactRecord ct=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        tv=(TextView)findViewById(R.id.log);
        firebaseAuth=FirebaseAuth.getInstance();
        update=(TextView)findViewById(R.id.update);
        delete=(TextView)findViewById(R.id.rem);
        add=(TextView)findViewById(R.id.add);
        view=(TextView)findViewById(R.id.View1);
        viewst=(TextView)findViewById(R.id.ViewA);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(DashBoard.this,Main2Activity.class);
                startActivity(intent);

          //    ct=new ContactRecord(getBaseContext(),null,1);
             // if(ct.insertData("Seema","8978687687","s@gmail.com"))
                //  Toast.makeText(DashBoard.this, "Saved Data", Toast.LENGTH_SHORT).show();
            //  else
                //  Toast.makeText(DashBoard.this, "Not Saved ", Toast.LENGTH_SHORT).show();

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashBoard.this,SearchActivity.class);
                startActivity(intent);

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashBoard.this,Update.class);
                startActivity(intent);


            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(DashBoard.this,RemoveBook.class);
                startActivity(intent1);


            }
        });
        viewst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(DashBoard.this,ViewStatus.class);
                startActivity(intent1);


            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logOut();
            }
        });
    }

    private void logOut()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(DashBoard.this,MainActivity.class));
    }



}
