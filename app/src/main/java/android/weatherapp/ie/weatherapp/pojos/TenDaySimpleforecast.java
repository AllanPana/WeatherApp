package android.weatherapp.ie.weatherapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allan on 31/03/2016.
 */
public class TenDaySimpleforecast {


    @SerializedName("forecastday")
    @Expose
    private List<Forecastday_> forecastday = new ArrayList<Forecastday_>();

    /**
     *
     * @return
     * The forecastday
     */
    public List<Forecastday_> getForecastday() {
        return forecastday;
    }

    /**
     *
     * @param forecastday
     * The forecastday
     */
    public void setForecastday(List<Forecastday_> forecastday) {
        this.forecastday = forecastday;
    }
}
