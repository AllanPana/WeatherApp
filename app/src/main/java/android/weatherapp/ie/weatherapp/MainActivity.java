package android.weatherapp.ie.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.weatherapp.ie.weatherapp.network.GsonRequest;
import android.weatherapp.ie.weatherapp.network.VolleySingleton;
import android.weatherapp.ie.weatherapp.pojos.AutoCompleteForecastResponse;
import android.weatherapp.ie.weatherapp.pojos.HourlyForecast;
import android.weatherapp.ie.weatherapp.pojos.HourlyWeatherResponse;
import android.weatherapp.ie.weatherapp.pojos.AutoCompleteSearchForecast;
import android.weatherapp.ie.weatherapp.pojos.TenDayForeCastResponse;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{



    private static final String WUNDERGROUND_HOURLY_FORECAST_URL = "http://api.wunderground.com/api/a9ad5218009bdc9a/geolookup/hourly/q/";
    private static final String WUNDERGROUND_10DAY_FORECAST_URL =  "http://api.wunderground.com/api/a9ad5218009bdc9a/geolookup/forecast10day/q";
    private static final String WUNDERGROUND_AUTO_COMPLETE_URL = "http://autocomplete.wunderground.com/aq?query=";
    private static final String WUNDERGROUND_REQUEST_FORMAT = ".json";
    List<HourlyForecast> mHourlyForecasts = new ArrayList<>();

    private ListView mListViewSearchResult;
    private ArrayAdapter<String> mArrayAdapter;
    private List<String> placeName = new ArrayList<>();
    private List<String> location_url = new ArrayList<>();

    private EditText mEditTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewSearchResult = (ListView) findViewById(R.id.lv_search_results);
        mListViewSearchResult.setOnItemClickListener(this);
        mEditTextSearch = (EditText) findViewById(R.id.et_search);


        setEditTextSearch();

        //getHourlyForecastResponse();

    }




    private void setFunctionSearch(String query){
        //GsonRequest<AutoCompleteForecastResponse> request = new GsonRequest<>("http://autocomplete.wunderground.com/aq?query=olong",
        GsonRequest<AutoCompleteForecastResponse> request = new GsonRequest<>(WUNDERGROUND_AUTO_COMPLETE_URL+query,


                AutoCompleteForecastResponse.class,

                new Response.Listener<AutoCompleteForecastResponse>() {
                    @Override
                    public void onResponse(AutoCompleteForecastResponse response) {
                        List<AutoCompleteSearchForecast> list = response.getForecastResults();
                        for(AutoCompleteSearchForecast result : list){
                            placeName.add(result.getName());
                            location_url.add(result.getL());
                        }

                        mArrayAdapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,placeName);
                        mListViewSearchResult.setAdapter(mArrayAdapter);


                        // Toast.makeText(getBaseContext(),list.get(0).getName(),Toast.LENGTH_LONG).show();


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        VolleySingleton.getVolleySingletonInstance().addRequestQueue(request);
    }




    private void getTenDayForecastResponse(String query){
        GsonRequest<TenDayForeCastResponse> request = new GsonRequest<>(WUNDERGROUND_10DAY_FORECAST_URL +query,
                //GsonRequest<HourlyWeatherResponse> request = new GsonRequest<>("http://api.wunderground.com/api/a9ad5218009bdc9a/hourly/q/Ireland/Kilkenny.json",

                TenDayForeCastResponse.class,

                new Response.Listener<TenDayForeCastResponse>() {
                    @Override
                    public void onResponse(TenDayForeCastResponse response) {

                        Log.d("tenday",response.toString());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        VolleySingleton.getVolleySingletonInstance().addRequestQueue(request);
    }





   private void getHourlyForecastResponse(String query){
        GsonRequest<HourlyWeatherResponse> request = new GsonRequest<>(WUNDERGROUND_HOURLY_FORECAST_URL +query,
        //GsonRequest<HourlyWeatherResponse> request = new GsonRequest<>("http://api.wunderground.com/api/a9ad5218009bdc9a/hourly/q/Ireland/Kilkenny.json",

                HourlyWeatherResponse.class,

                new Response.Listener<HourlyWeatherResponse>() {
                    @Override
                    public void onResponse(HourlyWeatherResponse response) {

                        mHourlyForecasts = response.getHourlyForecast();
                        Log.d("hourly", "city :"+response.getLocation().getCity());
                        Log.d("hourly", "hour  :"+mHourlyForecasts.get(0).getHourlyFCTTIME().getHour());
                        Log.d("hourly", "epoch :"+mHourlyForecasts.get(0).getHourlyFCTTIME().getEpoch());



                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        VolleySingleton.getVolleySingletonInstance().addRequestQueue(request);
    }









    private List<String> getLocationNamesFromResponse(String query){

        GsonRequest<AutoCompleteForecastResponse> request = new GsonRequest<>(WUNDERGROUND_AUTO_COMPLETE_URL+query,

                AutoCompleteForecastResponse.class,

                new Response.Listener<AutoCompleteForecastResponse>() {
                    @Override
                    public void onResponse(AutoCompleteForecastResponse response) {
                        List<AutoCompleteSearchForecast> mlist = response.getForecastResults();
                        for(AutoCompleteSearchForecast result : mlist){
                            placeName.add(result.getName());
                        }                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        VolleySingleton.getVolleySingletonInstance().addRequestQueue(request);
        return placeName;
    }



    public void setEditTextSearch() {

        mEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>2){
                    setFunctionSearch(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,location_url.get(position),Toast.LENGTH_LONG).show();
        Toast.makeText(this,placeName.get(position),Toast.LENGTH_LONG).show();
        Log.d("location",location_url.get(position));
        String url = location_url.get(position) + WUNDERGROUND_REQUEST_FORMAT;
        getHourlyForecastResponse(url);
        getTenDayForecastResponse(url);
    }
}