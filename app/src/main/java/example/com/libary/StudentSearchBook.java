package example.com.libary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentSearchBook extends AppCompatActivity {
    String listviewTitle []={
            "java ", "operating system", "Dccn", "office Automation tool",
            "Math", "Physics", "Chemistery", "DBMS",
    };


    int[] listviewImage = new int[]{
            R.drawable.java, R.drawable.os, R.drawable.dccn, R.drawable.ot,
            R.drawable.math, R.drawable.phy, R.drawable.download, R.drawable.dbms,
    };

    String[] listviewShortDescription = new String[]{
            "Publication of Book- herber Schildit", "Publication of book -Gallivin", "publication of Book -Frozen", "publication of book-",
            "Publication of book-Rajiv", "Publication of Book- Navin", "Publication of Book-Dinesh ", "Publication of Book- Rajiv Copra",
    };
    ListView listView;

    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search_book);
        EditText adt=(EditText)findViewById(R.id.textBox);
        listView=(ListView)findViewById(R.id.list_view);
        arrayAdapter=new ArrayAdapter(this,R.layout.activity_student_search_book);
        listView.setAdapter(arrayAdapter);

      adt.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {
              arrayAdapter.getFilter().filter(s);
          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {

          }

          @Override
          public void afterTextChanged(Editable s) {

          }
      });
        List<HashMap<String, String>> aList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.activity_student_search_book, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);
    }

    }

