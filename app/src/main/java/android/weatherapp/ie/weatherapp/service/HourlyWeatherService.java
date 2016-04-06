package android.weatherapp.ie.weatherapp.service;

import android.util.Log;
import android.weatherapp.ie.weatherapp.network.GsonRequest;
import android.weatherapp.ie.weatherapp.network.VolleySingleton;
import android.weatherapp.ie.weatherapp.pojos.AutoCompleteForecastResponse;
import android.weatherapp.ie.weatherapp.pojos.HourlyWeatherResponse;
import android.weatherapp.ie.weatherapp.pojos.TenDayForeCastResponse;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by allan on 01/04/2016.
 */
public class HourlyWeatherService {

    private static final String WUNDERGROUND_HOURLY_FORECAST_URL = "http://api.wunderground.com/api/a9ad5218009bdc9a/geolookup/hourly/q/";
    private static final String WUNDERGROUND_REQUEST_FORMAT = ".json";

    private HourlyWeatherResponse mHourlyWeatherResponse;





    /**
     *
     * @param query a string location url
     */
    private HourlyWeatherResponse getHourlyForecastResponse(String query){
        GsonRequest<HourlyWeatherResponse> request = new GsonRequest<>(WUNDERGROUND_HOURLY_FORECAST_URL +query,
                HourlyWeatherResponse.class,
                new Response.Listener<HourlyWeatherResponse>() {
                    @Override
                    public void onResponse(HourlyWeatherResponse response) {

                        Log.d("hourly", "city :"+response.getLocation().getCity());
                        mHourlyWeatherResponse = response;
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        VolleySingleton.getVolleySingletonInstance().addRequestQueue(request);
        return mHourlyWeatherResponse;
    }




}
