package android.weatherapp.ie.weatherapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by allan on 07/04/2016.
 */
public class CurrentWeatherDb {

    private CurrentWeatherDbHelper mDbHelper;
    private SQLiteDatabase mSQLiteDatabase;

    public CurrentWeatherDb(Context context) {
        mDbHelper = new CurrentWeatherDbHelper(context);
        mSQLiteDatabase = mDbHelper.getWritableDatabase();
    }




    public boolean insertData(String url, String placeName){

        ContentValues contentValues = new ContentValues();
        contentValues.put(mDbHelper.COLUMN_LOCATION_URL,url);
        contentValues.put(mDbHelper.COLUMN_PLACE_NAME,placeName);
        long result = mSQLiteDatabase.insert(mDbHelper.TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }

    }









    //db helper class===============================================================================
    private class CurrentWeatherDbHelper extends SQLiteOpenHelper{


        private static final String DATABASE_NAME = "currentConditionWeatherDb";
        private static final String TABLE_NAME = "currentConditionWeatherTable";
        private static final int DATABASE_VERSION = 1;

        private static final String COLUMN_ID = "id";
        private static final String COLUMN_PLACE_NAME = "placeName";
        private static final String COLUMN_LOCATION_URL = "locationURL";

        private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PLACE_NAME + " VARCHAR(255), "
                + COLUMN_LOCATION_URL + " VARCHAR(255));";

        private static final String DROP_TABLE = "DROP TABLE IF EXIST " + TABLE_NAME;

        private Context mContext;



        public CurrentWeatherDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            }catch (SQLException sqle){

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (SQLException sqle){

            }
        }
    }
}
