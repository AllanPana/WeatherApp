package android.weatherapp.ie.weatherapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by allan on 31/03/2016.
 */
public class TenDayForeCastResponse {

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("forecast")
    @Expose
    private TenDayForecast mTenDayForecast;


    /**
     *
     * @return
     * The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The mTenDayForecast
     */
    public TenDayForecast getTenDayForecast() {
        return mTenDayForecast;
    }

    /**
     *
     * @param tenDayForecast
     * The mTenDayForecast
     */
    public void setTenDayForecast(TenDayForecast tenDayForecast) {
        this.mTenDayForecast = tenDayForecast;
    }

}
