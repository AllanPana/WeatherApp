package android.weatherapp.ie.weatherapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by allan on 30/03/2016.
 */
public class WeatherApp extends Application {

    private static WeatherApp sWeatherAppInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sWeatherAppInstance = this;
    }


    /**
     *
     * @return an instance of this application class
     */
    public static WeatherApp getWeatherAppInstance() {
        return sWeatherAppInstance;
    }


    /**
     *
     * @return this application context
     */
    public static Context getAppContext(){
        return sWeatherAppInstance.getApplicationContext();
    }
}
