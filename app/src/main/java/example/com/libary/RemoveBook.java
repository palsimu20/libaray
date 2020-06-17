package example.com.libary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RemoveBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_book);
    }
    public  void del(View v)
    {
        EditText editText=(EditText)findViewById(R.id.rid);
        ConnectionManager connectionManager1=new ConnectionManager(this);
        boolean r= connectionManager1.deletedbook(editText.getText().toString());

        { if(r==true)
           {
               Toast.makeText(this," remove book successfully :Id Of Book= "+editText.getText().toString(),Toast.LENGTH_LONG).show();
           }
           else
           {
               Toast.makeText(this," book not remove ",Toast.LENGTH_LONG).show();
           }
        }
    }

}
