package android.weatherapp.ie.weatherapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

/**
 * Created by allan on 31/03/2016.
 */
@Generated("org.jsonschema2pojo")
public class AutoCompleteForecastResponse {


    @SerializedName("RESULTS")
    @Expose
    private List<AutoCompleteSearchForecast> mForecastResults = new ArrayList<>();





    /**
     *
     * @return
     * The mForecastResults
     */
    public List<AutoCompleteSearchForecast> getForecastResults() {
        return mForecastResults;
    }

    /**
     *
     * @param forecastResults
     * The mForecastResults
     */
    public void setForecastResults(List<AutoCompleteSearchForecast> forecastResults) {
        this.mForecastResults = forecastResults;
    }


}
