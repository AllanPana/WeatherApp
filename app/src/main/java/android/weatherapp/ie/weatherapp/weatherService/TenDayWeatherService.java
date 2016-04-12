package android.weatherapp.ie.weatherapp.weatherService;

import android.weatherapp.ie.weatherapp.network.GsonRequest;
import android.weatherapp.ie.weatherapp.network.VolleySingleton;
import android.weatherapp.ie.weatherapp.pojos.Forecastday_;
import android.weatherapp.ie.weatherapp.pojos.Location;
import android.weatherapp.ie.weatherapp.pojos.TenDayForeCastResponse;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by allan on 01/04/2016.
 */
public class TenDayWeatherService {

    private static final String WUNDERGROUND_10DAY_FORECAST_URL =  "http://api.wunderground.com/api/a9ad5218009bdc9a/geolookup/forecast10day";
    private static final String WUNDERGROUND_REQUEST_FORMAT = ".json";

    private static Location mLocation;
    private static List<Forecastday_> mForecastday_s;


    /**
     *
     * @param query a string location url
     */
    public static void setTenDayForecastResponse(final String query){
        GsonRequest<TenDayForeCastResponse> request = new GsonRequest<>(WUNDERGROUND_10DAY_FORECAST_URL +query+WUNDERGROUND_REQUEST_FORMAT,
                TenDayForeCastResponse.class,
                new Response.Listener<TenDayForeCastResponse>() {
                    @Override
                    public void onResponse(TenDayForeCastResponse response) {


                        setLocation(response.getLocation());
                        setForecastday_s(response.getTenDayForecast().getTenDaySimpleforecast().getForecastday());

                        //Log.d("setTen","url = "+WUNDERGROUND_10DAY_FORECAST_URL +query+WUNDERGROUND_REQUEST_FORMAT);
                        //Log.d("setTen","country = "+mLocation.getCountryName());
                        //Log.d("setTen","city = "+mLocation.getCity());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        VolleySingleton.getVolleySingletonInstance().addRequestQueue(request);
    }


    public static Location getLocation() {
        return mLocation;
    }

    public static void setLocation(Location location) {
        mLocation = location;
    }

    public static List<Forecastday_> getForecastday_s() {
        return mForecastday_s;
    }

    public static void setForecastday_s(List<Forecastday_> forecastday_s) {
        mForecastday_s = forecastday_s;
    }
}
