package zlm.SQLite;

import android.provider.BaseColumns;

import java.util.List;

import zlm.user.Stories;

/**
 * Created by zhao on 2016/3/29.
 */
public class UserMata {

    public static abstract class UserTable implements BaseColumns {
        public static final String  TABLE_NAME="user_table";
        public static final String  DATE="data";
    }
    public static abstract class Stories implements  BaseColumns{
        public static final String TABLE_NAME="stories_table";
        public static final String IMAGE="image";
        public static final String TYPE="type";
        public static final String ID="id";
        public static final String GA_PREFIX="ga_prefix";
        public static final String TITLE="title";
    }
}
