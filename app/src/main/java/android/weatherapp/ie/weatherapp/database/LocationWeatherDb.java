package android.weatherapp.ie.weatherapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.weatherapp.ie.weatherapp.pojos.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allan on 07/04/2016.
 */
public class LocationWeatherDb {

    private CurrentWeatherDbHelper mDbHelper;
    private SQLiteDatabase mSQLiteDatabase;

    public LocationWeatherDb(Context context) {
        mDbHelper = new CurrentWeatherDbHelper(context);
        mSQLiteDatabase = mDbHelper.getWritableDatabase();
    }


    /**
     * INSERT
     *
     * @param url       url of the object
     * @param placeName of the object
     * @return
     */
    public boolean insertWeatherData(String url, String placeName, String latitude, String longitude) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(mDbHelper.COLUMN_LOCATION_URL, url);
        contentValues.put(mDbHelper.COLUMN_PLACE_NAME, placeName);
        contentValues.put(mDbHelper.COLUMN_LATITUDE,latitude);
        contentValues.put(mDbHelper.COLUMN_LONGITUDE,longitude);
        long result = mSQLiteDatabase.insert(mDbHelper.TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * READ
     *
     * @return list of all city that have been save in db
     */
    public List<Location> getAllSavedWeatherData() {
        List<Location> forecastList = new ArrayList<>();
        String[] columns = {
                mDbHelper.COLUMN_ID,
                mDbHelper.COLUMN_LOCATION_URL,
                mDbHelper.COLUMN_PLACE_NAME,
                mDbHelper.COLUMN_LATITUDE,
                mDbHelper.COLUMN_LONGITUDE};

        Cursor cursor = mSQLiteDatabase.query(mDbHelper.TABLE_NAME, columns, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Location forecast = new Location();
                forecast.setL(cursor.getString(cursor.getColumnIndex(mDbHelper.COLUMN_LOCATION_URL)));
                forecast.setCity(cursor.getString(cursor.getColumnIndex(mDbHelper.COLUMN_PLACE_NAME)));
                forecast.setLat(cursor.getString(cursor.getColumnIndex(mDbHelper.COLUMN_LATITUDE)));
                forecast.setLon(cursor.getString(cursor.getColumnIndex(mDbHelper.COLUMN_LONGITUDE)));

                forecastList.add(forecast);

            } while (cursor.moveToNext());
        }

        return forecastList;
    }


    /**
     * DELETE
     * Delete * from TABLE_NAME WHERE COLUMN_LOCATION_URL = locationURL
     *
     * @param placeName  of the  object to be deleted
     * @return
     */
    public boolean deleteWeatherData(String placeName) {
        String[] whereArgs = {placeName};
        return mSQLiteDatabase.delete(CurrentWeatherDbHelper.TABLE_NAME,CurrentWeatherDbHelper.COLUMN_PLACE_NAME + "=?",whereArgs)>0;  //prevent sql injection
        //return mSQLiteDatabase.delete(CurrentWeatherDbHelper.TABLE_NAME,CurrentWeatherDbHelper.COLUMN_PLACE_NAME + "='"+placeName+"'",null);
    }


    //db helper class===============================================================================
    private class CurrentWeatherDbHelper extends SQLiteOpenHelper {


        private static final String DATABASE_NAME = "locationWeatherDb";
        private static final String TABLE_NAME = "locationWeatherTable";
        private static final int DATABASE_VERSION = 3;

        private static final String COLUMN_ID = "id";
        private static final String COLUMN_PLACE_NAME = "placeName";
        private static final String COLUMN_LOCATION_URL = "locationURL";
        private static final String COLUMN_LATITUDE = "latitude";
        private static final String COLUMN_LONGITUDE = "longitude";

        private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_LOCATION_URL + " VARCHAR(255), "
                + COLUMN_PLACE_NAME + " VARCHAR(255), "
                + COLUMN_LATITUDE + " VARCHAR(255), "
                + COLUMN_LONGITUDE + " VARCHAR(255));";

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
            } catch (SQLException sqle) {

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException sqle) {

            }
        }
    }
}
