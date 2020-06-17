package example.com.libary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewStatus extends AppCompatActivity {
    ListView listView;
    Button show;
    SQLiteDatabase db;
    BookNewData bookNewData;
    ArrayList<String>listItems;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_status);
        listView = (ListView) findViewById(R.id.list);
        bookNewData = new BookNewData(this);
        show=(Button)findViewById(R.id.btn);
        listItems=new ArrayList<>();
        viewStatus();


    }

    public void viewStatus() {


        db = bookNewData.getReadableDatabase();
        Cursor cursor = bookNewData.getAll();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No book show", Toast.LENGTH_LONG).show();
        }
        else {
            while (cursor.moveToNext()) {
                listItems.add("Stname:\t" + cursor.getString(0) );
                listItems.add("BookName:\t" + cursor.getString(1) );
                listItems.add("BookID:\t" + cursor.getString(2) );
                listItems.add("FromDate:\t" + cursor.getString(3) );
                listItems.add("ToDate:\t" + cursor.getString(4) );
                listItems.add("status:\t" + cursor.getString(5));

            }
        }

adapter=new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,listItems);
        listView.setAdapter(adapter);

    }
}
