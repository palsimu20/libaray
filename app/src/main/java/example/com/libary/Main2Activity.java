package example.com.libary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText  id,title,author,code,price;
    Button add ,view;
    RegistrationDB registrationDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        id = (EditText) findViewById(R.id.id);
        title = (EditText) findViewById(R.id.title);
        author = (EditText) findViewById(R.id.bookname);
        code = (EditText) findViewById(R.id.code);
        price = (EditText) findViewById(R.id.code1);
        registrationDB=new RegistrationDB(this);
    }
public void f1(View view)
        {
            String Id = id.getText().toString();
            String Title = title.getText().toString();
            String Author = author.getText().toString();
            String Code = code.getText().toString();
            String Price=price.getText().toString();
            //if (ct.insert(Id, Title, Author,Code,Price))
            if (registrationDB.insert(Id,Title,Author,Code,Price))

            {
                Toast.makeText(Main2Activity.this, "data is insert", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Main2Activity.this, "data is not insert", Toast.LENGTH_LONG).show();
            }
        }
    }

