package android.weatherapp.ie.weatherapp.weatherService;

import android.weatherapp.ie.weatherapp.network.GsonRequest;
import android.weatherapp.ie.weatherapp.network.VolleySingleton;
import android.weatherapp.ie.weatherapp.pojos.AutoCompleteForecastResponse;
import android.weatherapp.ie.weatherapp.pojos.AutoCompleteSearchForecast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allan on 01/04/2016.
 */
public class AutoCompleteService {

    private static final String WUNDERGROUND_AUTO_COMPLETE_URL = "http://autocomplete.wunderground.com/aq?query=";
    private static List<AutoCompleteSearchForecast> mAutoCompleteSearchForecasts = new ArrayList<>();



    /**
     *
     * @param query a string to be search (location,place, city)
     */
    public static void  setAutoCompleteResponse(String query){

        GsonRequest<AutoCompleteForecastResponse> request = new GsonRequest<>(WUNDERGROUND_AUTO_COMPLETE_URL+query,
                AutoCompleteForecastResponse.class,
                new Response.Listener<AutoCompleteForecastResponse>() {
                    @Override
                    public void onResponse(AutoCompleteForecastResponse response) {
                        //Log.d("hourly", "city :"+response.getForecastResults().get(0).getName());
                        setAutoCompleteSearchForecasts(response.getForecastResults());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        VolleySingleton.getVolleySingletonInstance().addRequestQueue(request);
    }


    public static List<AutoCompleteSearchForecast> getAutoCompleteSearchForecasts() {
        return mAutoCompleteSearchForecasts;
    }

    public static void setAutoCompleteSearchForecasts(List<AutoCompleteSearchForecast> autoCompleteSearchForecasts) {
        mAutoCompleteSearchForecasts = autoCompleteSearchForecasts;
    }
}
