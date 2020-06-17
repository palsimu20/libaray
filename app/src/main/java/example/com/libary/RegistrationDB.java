package example.com.libary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class RegistrationDB  extends ConnectionManager{

    public RegistrationDB(Context context) {
        super(context);
    }
    public  boolean insert(String Id,String Title,String Authorname,String Code,String Price)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Id",Id);
        contentValues.put("Title",Title);
        contentValues.put("Authorname",Authorname);
        contentValues.put("Code",Code);
        contentValues.put("Price",Price);
        long x = db.insert(Tablebook,null,contentValues);
        if(x>0)
            return  true;
        else
            return  false;
    }


}
