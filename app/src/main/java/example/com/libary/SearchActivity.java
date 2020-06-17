package example.com.libary;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    RegistrationDB cm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void search(View v) {

       RegistrationDB cm = new RegistrationDB(this);
        EditText editText = (EditText) findViewById(R.id.edt1);
        TextView tv = (TextView) findViewById(R.id.tv);
       Cursor cursor=cm.getAllData(editText.getText().toString());
        StringBuffer stringBuffer=new StringBuffer();
        if(cursor.getCount()==0) {
            showmsg(" Book Data", "nothing found");
            return;
        }
            while (cursor.moveToNext())
            {
                 stringBuffer.append("id:=" + cursor.getString(0) + "\n");
                stringBuffer.append("Title:=" + cursor.getString(1) + "\n");
                stringBuffer.append("Author:=" + cursor.getString(2) + "\n");
                stringBuffer.append("Code:=" + cursor.getString(3) + "\n");
                stringBuffer.append("Price:=" + cursor.getString(4) + "\n\n\n");

            }
            tv.setText(stringBuffer.toString());
            showmsg(" Book Data",stringBuffer.toString());
            Toast.makeText(this,"search succefully",Toast.LENGTH_LONG).show();
        }




    public  void showmsg( String  title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    }
