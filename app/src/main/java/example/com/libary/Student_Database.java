package example.com.libary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Student_Database extends SQLiteOpenHelper {
    public  static  String Database_Name="StudentRegister.db";
    public  static  String Table_Name="Student_table";
    public  static  String Table_login="Login_table";

    public Student_Database(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Table_Name+"(name text,email text primary key, password text ,enrollement text,gender text,brance text,year text)");
        db.execSQL("create table "+Table_login+"(email text primary key ,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        db.execSQL("DROP TABLE IF EXISTS "+Table_login);
        onCreate(db);

    }
    public Cursor loginUser(String pass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select* from "+Table_Name,null);
        return res;
    }
    public boolean  insertDatastudent( String name,String email ,String pass,String enrolloment,String gender,String brance,String year)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("email",email);
        cv.put("password",pass);
        cv.put("enrollement",enrolloment);
        cv.put("gender",gender);
        cv.put("brance",brance);
        cv.put("year",year);
       long result =  sqLiteDatabase.insert(Table_Name,null,cv);
       if (result>0)
       {
          SQLiteDatabase sqLiteDatabase1=this.getWritableDatabase();
          ContentValues cv1=new ContentValues();
          cv1.put("email",email);
          cv1.put("password",pass);
          long x=sqLiteDatabase1.insert(Table_login,null,cv1);
          if(x>0)
           {
               return true;
           }
           else
          {
              return false;
          }
       }
       return false;
    }


}
