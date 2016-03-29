package zlm.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by zhao on 2016/3/29.
 */
public class UserHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="user.db";
    public static final int VERSION=1;
    public UserHelper(Context context){
        super(context, DB_NAME, null, VERSION);
    }
    public static final String CREATE_TABLE_USER="CREATE TABLE IF NOT EXISTS user_table(_id INTEGER PRIMARY KEY AUTOINCREMENT,date Text)";
    public static final String CREATE_TABLE_STORIES="CREATE TABLE IF NOT EXISTS stories_table(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "image Text,type Integer,id Integer,ga_prefix Text,title Text)";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_STORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
