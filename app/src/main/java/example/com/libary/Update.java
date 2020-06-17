package example.com.libary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }
    public  void update( View v)
    {
       ConnectionManager db=new ConnectionManager(this);
      // RegistrationDB connectionManager=new RegistrationDB(this);
        EditText id=(EditText)findViewById(R.id.uid);
        EditText title=(EditText)findViewById(R.id.uTitle);
        EditText author=(EditText)findViewById(R.id.uAuthor);
        EditText code=(EditText)findViewById(R.id.uCode);
        EditText price=(EditText)findViewById(R.id.uPrice);

        boolean y=  db.UpdateData(id.getText().toString(),title.getText().toString(),author.getText().toString(),code.getText().toString(),price.getText().toString());
      if(y==true) {
          Toast.makeText(this, "Book is updated", Toast.LENGTH_LONG).show();
      }
      else
      {
          Toast.makeText(this, "Book is not updated", Toast.LENGTH_LONG).show();
      }
    }
}
