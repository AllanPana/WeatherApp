package android.weatherapp.ie.weatherapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.weatherapp.ie.weatherapp.database.CurrentWeatherDb;
import android.weatherapp.ie.weatherapp.network.GsonRequest;
import android.weatherapp.ie.weatherapp.network.VolleySingleton;
import android.weatherapp.ie.weatherapp.pojos.AutoCompleteSearchForecast;
import android.weatherapp.ie.weatherapp.pojos.CurrentObservation;
import android.weatherapp.ie.weatherapp.pojos.CurrentWeatherResponse;
import android.weatherapp.ie.weatherapp.service.AutoCompleteService;
import android.weatherapp.ie.weatherapp.service.CurrentConditionWeatherService;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String WUNDERGROUND_CURRENT_CONDITION_URL = "http://api.wunderground.com/api/38ef208cc476fe4b/conditions";
    private static final String WUNDERGROUND_HOURLY_FORECAST_URL = "http://api.wunderground.com/api/a9ad5218009bdc9a/geolookup/hourly/q/";
    private static final String WUNDERGROUND_10DAY_FORECAST_URL = "http://api.wunderground.com/api/a9ad5218009bdc9a/geolookup/forecast10day/q";
    private static final String WUNDERGROUND_AUTO_COMPLETE_URL = "http://autocomplete.wunderground.com/aq?query=";
    private static final String WUNDERGROUND_REQUEST_FORMAT = ".json";
    private List<CurrentObservation> mCurrentObservations = new ArrayList<>();

    private ListView mListViewSearchResult;
    private ArrayAdapter<String> mArrayAdapter;
    private List<String> placeName = new ArrayList<>();
    List<String> locationUrls = new ArrayList<>();
    private List<AutoCompleteSearchForecast> mAutoCompleteSearchForecastsFromDB = new ArrayList<>();

    private LinearLayout mLinearLayout;
    private EditText mEditTextSearch;
    private Handler mHandler;
    private RecyclerView mRecyclerView;
    private WeatherRecyclerViewAdapter mWeatherRecyclerViewAdapter;

    private static final int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean controlsVisible = true;


    private CurrentWeatherDb mCurrentWeatherDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideSoftKeyBoard();

        mCurrentWeatherDb = new CurrentWeatherDb(this);
        setAllForecastFromDb();
        addDefaultLocationForecast();


        mHandler = new Handler(getMainLooper());
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_search_container);
        mListViewSearchResult = (ListView) findViewById(R.id.lv_search_results);
        mListViewSearchResult.setOnItemClickListener(this);
        mEditTextSearch = (EditText) findViewById(R.id.et_search);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        setEditTextSearch();
        setCurrentWeatherCondition(locationUrls);
        hideSearch();
    }


    void setUpCurrentWeather() {
        List<String> urls = new ArrayList<>();
        if (!(urls.isEmpty())) {
            urls.clear();
        }
        urls.add("/q/zmw:00000.1.03969.json");//dublin
        urls.add("/q/zmw:00000.17.98426.json");//olongapo
        urls.add("/q/locid:EIXX0027.json");//longford
        urls.add("/q/zmw:00000.1.94767.json");//sydney
        urls.add("/q/zmw:00000.37.07156.json");//paris
        urls.add("/q/zmw:00000.1.03772.json");//london

        for (String url : urls) {
            CurrentConditionWeatherService.setCurrentWeatherResponse(url);
        }

        //mHandler.postDelayed(new Runnable() {
        //    @Override
        //    public void run() {

        mWeatherRecyclerViewAdapter = new WeatherRecyclerViewAdapter(MainActivity.this, CurrentConditionWeatherService.getCurrentObservations());
        mRecyclerView.setAdapter(mWeatherRecyclerViewAdapter);
        //    }
        //}, 2000);

        hideSearch();
    }


    void setCurrentWeatherCondition(List<String> query) {
        GsonRequest<CurrentWeatherResponse> request = null;
        for (String url : query) {
            request = new GsonRequest<>(WUNDERGROUND_CURRENT_CONDITION_URL + url,
                    CurrentWeatherResponse.class,
                    new Response.Listener<CurrentWeatherResponse>() {
                        @Override
                        public void onResponse(CurrentWeatherResponse response) {

                            mCurrentObservations.add(response.getCurrentObservation());
                            mWeatherRecyclerViewAdapter = new WeatherRecyclerViewAdapter(MainActivity.this, mCurrentObservations);
                            mRecyclerView.setAdapter(mWeatherRecyclerViewAdapter);
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

            VolleySingleton.getVolleySingletonInstance().addRequestQueue(request);
        }
    }


    public void setEditTextSearch() {
        mEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mListViewSearchResult.setVisibility(View.VISIBLE);

                if (s.length() > 2) {
                    AutoCompleteService.setAutoCompleteResponse(s.toString());
                }

                List<AutoCompleteSearchForecast> list = AutoCompleteService.getAutoCompleteSearchForecasts();
                for (AutoCompleteSearchForecast result : list) {
                    placeName.add(result.getName());
                }

                mArrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, placeName);
                mListViewSearchResult.setAdapter(mArrayAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 0 || s.equals("")) {
                    placeName.clear();
                    hideSoftKeyBoard();
                    mListViewSearchResult.setVisibility(View.INVISIBLE);
                    mArrayAdapter.notifyDataSetChanged();
                }

            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        mListViewSearchResult.setVisibility(View.INVISIBLE);
        mEditTextSearch.setText("");

        addForecastDataToDb(position);
        setAllForecastFromDb();
        setCurrentWeatherCondition(locationUrls);
        mWeatherRecyclerViewAdapter.notifyDataSetChanged();


        hideSoftKeyBoard();
        placeName.clear();
        locationUrls.clear();
    }


    /**
     * Add new location/place/city  forecast data into the db
     *
     * @param position the position of object to be added
     */
    private void addForecastDataToDb(int position) {

        List<AutoCompleteSearchForecast> forecastList = AutoCompleteService.getAutoCompleteSearchForecasts();
        String url = forecastList.get(position).getL();
        String placeName = forecastList.get(position).getName();

        boolean inserted = mCurrentWeatherDb.insertWeatherData(url, placeName);
        if (inserted == true) {
            locationUrls.add(url + WUNDERGROUND_REQUEST_FORMAT);
        } else {
            return;
        }
    }


    private void setAllForecastFromDb() {
        mAutoCompleteSearchForecastsFromDB = mCurrentWeatherDb.getAllSavedWeatherData();
        if (mAutoCompleteSearchForecastsFromDB != null) {
            for (AutoCompleteSearchForecast forecast : mAutoCompleteSearchForecastsFromDB) {

                locationUrls.add(forecast.getL() + WUNDERGROUND_REQUEST_FORMAT);
            }
        } else {
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        }
    }


    //add/insert default city
    private void addDefaultLocationForecast() {
        List<AutoCompleteSearchForecast> forecastList = new ArrayList<>();
        forecastList.add(new AutoCompleteSearchForecast("/q/zmw:00000.1.03969", "Dublin"));
        forecastList.add(new AutoCompleteSearchForecast("/q/zmw:00000.1.03772", "London"));
        //forecastList.add(new AutoCompleteSearchForecast("/q/zmw:00000.1.94767", "Sydney"));
        //forecastList.add(new AutoCompleteSearchForecast("/q/zmw:00000.1.WZBAA", "Beijing"));
        //forecastList.add(new AutoCompleteSearchForecast("/q/zmw:00000.37.07156", "Paris"));
        //forecastList.add(new AutoCompleteSearchForecast("/q/zmw:00000.17.98426", "Olongapo"));

        for (AutoCompleteSearchForecast forecast : forecastList) {
            String url = forecast.getL();
            String placeName = forecast.getName();

            if (!(mAutoCompleteSearchForecastsFromDB.contains(forecast))) {

                boolean inserted = mCurrentWeatherDb.insertWeatherData(url, placeName);
                if (inserted == true) {
                    //locationUrls.add(url + ".json");
                    Log.d("success", "success");
                } else {
                    Log.d("allan", "no object added===================================================================");

                }

            }

        }
    }


    //hide the search edittext when recyclerview scrolled
    private void hideSearch() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
                    //onHide();
                    mLinearLayout.animate().translationY(-mLinearLayout.getHeight() * 2).setInterpolator(new AccelerateInterpolator(2));
                    //mLinearLayout.setVisibility(View.INVISIBLE);
                    controlsVisible = false;
                    scrolledDistance = 0;
                } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
                    //onShow();
                    mLinearLayout.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
                    //mLinearLayout.setVisibility(View.VISIBLE);
                    controlsVisible = true;
                    scrolledDistance = 0;
                }

                if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
                    scrolledDistance += dy;
                }

            }
        });
    }


    //hide the softkeyboard
    private void hideSoftKeyBoard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            //imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }
}