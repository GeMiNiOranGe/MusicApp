package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class CreateDatabase extends SQLiteOpenHelper {

    public static String TB_ACCOUNT = "ACCOUNT";

    public static String TB_ACCOUNT_TEN = "TEN";
    public static String TB_ACCOUNT_SDT = "SDT";
    public static String TB_ACCOUNT_USER = "USER";
    public static String TB_ACCOUNT_PASSWORD = "PASSWORD";

    public CreateDatabase( Context context) {
        super(context,"MusicApp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tblAccount = "CREATE TABLE" + TB_ACCOUNT + " ( " + TB_ACCOUNT_USER + " TEXT PRIMARY KEY, " + TB_ACCOUNT_PASSWORD + " TEXT, " + TB_ACCOUNT_TEN + " TEXT, " + TB_ACCOUNT_SDT + "INTEGER)";
        db.execSQL(TB_ACCOUNT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
