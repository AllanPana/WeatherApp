package android.weatherapp.ie.weatherapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by allan on 31/03/2016.
 */
public class TenDayForecast {


    @SerializedName("simpleforecast")
    @Expose
    private TenDaySimpleforecast mTenDaySimpleforecast;


    /**
     *
     * @return
     * The mTenDaySimpleforecast
     */
    public TenDaySimpleforecast getTenDaySimpleforecast() {
        return mTenDaySimpleforecast;
    }

    /**
     *
     * @param tenDaySimpleforecast
     * The mTenDaySimpleforecast
     */
    public void setTenDaySimpleforecast(TenDaySimpleforecast tenDaySimpleforecast) {
        this.mTenDaySimpleforecast = tenDaySimpleforecast;
    }

}
