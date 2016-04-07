package android.weatherapp.ie.weatherapp.service;

import android.weatherapp.ie.weatherapp.network.GsonRequest;
import android.weatherapp.ie.weatherapp.network.VolleySingleton;
import android.weatherapp.ie.weatherapp.pojos.CurrentObservation;
import android.weatherapp.ie.weatherapp.pojos.CurrentWeatherResponse;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allan on 04/04/2016.
 */
public class CurrentConditionWeatherService {


    private static final String WUNDERGROUND_CURRENT_CONDITION_URL = "http://api.wunderground.com/api/a9ad5218009bdc9a/conditions";
    private static final String WUNDERGROUND_REQUEST_FORMAT = ".json";

    private static List<CurrentObservation> mCurrentObservations = new ArrayList<>();



    /**
     *
     * @param query a string location url
     */
    public static void setCurrentWeatherResponse(String query){

        GsonRequest<CurrentWeatherResponse> request = new GsonRequest<>(WUNDERGROUND_CURRENT_CONDITION_URL +query,
                CurrentWeatherResponse.class,
                new Response.Listener<CurrentWeatherResponse>() {
                    @Override
                    public void onResponse(CurrentWeatherResponse response) {

                        //setCurrentObservations(response.getCurrentObservation());
                        mCurrentObservations.add(response.getCurrentObservation());
                        //setCurrentObservations(mCurrentObservations);


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        /*request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

        VolleySingleton.getVolleySingletonInstance().addRequestQueue(request);
    }


    public static List<CurrentObservation> getCurrentObservations() {
        return mCurrentObservations;
    }

    public static void setCurrentObservations(List<CurrentObservation> currentObservations) {
        mCurrentObservations = currentObservations;
    }
}
