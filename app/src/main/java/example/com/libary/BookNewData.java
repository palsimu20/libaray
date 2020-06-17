package example.com.libary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookNewData extends SQLiteOpenHelper {
    public static String Database_Name1 = "IssuseData.db";
    public static String Table_Name1 = "BookIssuse_table";
    public BookNewData( Context context) {
        super(context, Database_Name1, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Name1 + "(Stname text ,BookName text,BookId text,FromDate text,ToDate text,status text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name1);

    }
    public boolean insertissuseData(String Stname, String BookName, String BookId, String FromDate, String ToDate,String st) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Stname", Stname);
        cv.put("BookName", BookName);
        cv.put("BookId", BookId);
        cv.put("FromDate", FromDate);
        cv.put("ToDate", ToDate);
        cv.put("status", st);
        long result = sqLiteDatabase.insert(Table_Name1, null, cv);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Cursor getAll()
    {
        SQLiteDatabase db2=this.getWritableDatabase();
        Cursor res1=db2.rawQuery("select *from "+Table_Name1,null);
        return res1;

    }
}
