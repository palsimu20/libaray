package example.com.libary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnectionManager extends SQLiteOpenHelper {
    public  final static   String DataBase_Name="DBDemo.db";
    public  final  static  String Tablebook="regtab";
    public static final String IMAGE = "image";
    public ConnectionManager( Context context)
    {
        super(context, DataBase_Name, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Tablebook+"(Id text , Title text, Authorname text, Code text,Price text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP Table if exists "+Tablebook);
        onCreate(db);
    }
    public Cursor getAllData(String s)
    {

        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor res=db1.rawQuery("select *from "+Tablebook,null);
        return res;
    }


    public boolean  deletedbook(String s) {
        SQLiteDatabase db3 = this.getWritableDatabase();
       int n= db3.delete(Tablebook,"Id="+s,null);
       if(n>0)
           return true;
       else
           return false;
    }

    public boolean UpdateData(String Id,String Title,String Authorname,String Code,String Price) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id",Id);
        contentValues.put("title",Title);
        contentValues.put("Authorname",Authorname);
        contentValues.put("code",Code);
        contentValues.put("price",Price);
        db2.update(Tablebook, contentValues, " Id="+Id,null);
        return true;
    }

    public Cursor getRetrive(String id) {
        SQLiteDatabase db2=this.getWritableDatabase();
        Cursor res1=db2.rawQuery("select *from "+Tablebook,null);
        return res1;

    }
}


