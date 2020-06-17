package example.com.libary;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class IssuseBook extends AppCompatActivity {
    EditText stname, Bookname, Bookid;
    Button request;
    TextView Fdate, duedate;
    String fDate,LDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener tDateSetListener;
    BookNewData db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuse_book);
        stname = (EditText) findViewById(R.id.stname);
        Bookname = (EditText) findViewById(R.id.bookname);
        Bookid = (EditText) findViewById(R.id.bookId);
        Fdate = (TextView) findViewById(R.id.date);
       db=new BookNewData(this);
        duedate = (TextView) findViewById(R.id.date1);
        request = (Button) findViewById(R.id.request);
        Fdate.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Calendar cal = Calendar.getInstance();
                                         int year = cal.get(Calendar.YEAR);
                                         int month = cal.get(Calendar.MONTH);
                                         int day = cal.get(Calendar.DAY_OF_MONTH);
                                         DatePickerDialog dialog = new DatePickerDialog(IssuseBook.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                                         dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                         dialog.show();
                                     }
                                 }
        );
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                fDate = dayOfMonth + "/" + month + "/" + year;
                Fdate.setText(fDate);
            }

        };
duedate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Calendar calendar=Calendar.getInstance();
        int year1=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH);
        int day1=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog1= new DatePickerDialog(IssuseBook.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,tDateSetListener,year1,month1,day1);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog1.show();
    }
});
        tDateSetListener=new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int mn, int dayOfMonth) {
        mn=mn+1;
        LDate=dayOfMonth+"/"+mn+"/"+year;
        duedate.setText(LDate);
    }
};


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=stname.getText().toString();
                String BookName1=Bookname.getText().toString();
                String BookId1=Bookid.getText().toString();
                String FromDate=Fdate.getText().toString();
                String ToDate=duedate.getText().toString();
                String st="Not Confirmed";
                Toast.makeText(IssuseBook.this, "Name : "+name+" Book Name "+BookName1+"Book Id : "+BookId1+" From Date : "+FromDate+" toDate : "+ToDate, Toast.LENGTH_SHORT).show();
               if(db.insertissuseData(name,BookName1,BookId1,FromDate,ToDate,st)==true)
                {
                    Toast.makeText(IssuseBook.this,"Your request is sent",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(IssuseBook.this,"Your request is not sent ",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}