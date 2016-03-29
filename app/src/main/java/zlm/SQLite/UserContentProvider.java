package zlm.SQLite;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by zhao on 2016/3/29.
 */
public class UserContentProvider extends ContentProvider {
    public static final String AUTHROITY = "com.zlm.news.ContentProvider";
    UserHelper userHelper;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int USER_SINGLE_CODE = 2;
    private static final int USER_MUTIPLE_CODE = 1;
    private static final int STORIES_MUTIPLE_CODE = 3;
    private static final int STORIES_SINGLE_CODE = 4;


    private static final String SINGLE_TYPE = "vnd.android.cursor.item/person";
    private static final String MUTIPLE_TYPE = "vnd.android.cursor.dir/person";

    static {

        uriMatcher.addURI(AUTHROITY, "user", USER_MUTIPLE_CODE);
        uriMatcher.addURI(AUTHROITY, "user/#", USER_SINGLE_CODE);
        uriMatcher.addURI(AUTHROITY, "stories", STORIES_MUTIPLE_CODE);
        uriMatcher.addURI(AUTHROITY, "stories/#", STORIES_SINGLE_CODE);
    }

    @Override
    public boolean onCreate() {
        userHelper = new UserHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db;
        Cursor query;
        switch (uriMatcher.match(uri)) {
            case USER_MUTIPLE_CODE:
                db = userHelper.getReadableDatabase();
                query = db.query(UserMata.UserTable.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                if (query != null) {
                    query.setNotificationUri(getContext().getContentResolver(), uri);
                }
                return query;

            case STORIES_MUTIPLE_CODE:
                db = userHelper.getReadableDatabase();
                query = db.query(UserMata.Stories.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                if (query != null) {
                    query.setNotificationUri(getContext().getContentResolver(), uri);
                }
                return query;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case USER_SINGLE_CODE:
                return SINGLE_TYPE;
            case USER_MUTIPLE_CODE:
                return MUTIPLE_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case STORIES_MUTIPLE_CODE:
                SQLiteDatabase db = userHelper.getWritableDatabase();
                long insert = db.insert(UserMata.Stories.TABLE_NAME, null, values);
                if (insert != -1) {
                    uri = ContentUris.withAppendedId(uri, insert);
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return uri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case STORIES_MUTIPLE_CODE:
                SQLiteDatabase db = userHelper.getWritableDatabase();
                int delete = db.delete(UserMata.Stories.TABLE_NAME, selection, selectionArgs);
                return  delete;
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
