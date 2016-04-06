package android.weatherapp.ie.weatherapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by allan on 30/03/2016.
 */
@Generated("org.jsonschema2pojo")
public class HourlyForecast {

    @SerializedName("FCTTIME")
    @Expose
    private HourlyFCTTIME HourlyFCTTIME;

    @SerializedName("temp")
    @Expose
    private HourlyTemp mHourlyTemp;

    @SerializedName("condition")
    @Expose
    private String condition;

    @SerializedName("icon")
    @Expose
    private String icon;

    @SerializedName("icon_url")
    @Expose
    private String iconUrl;

    @SerializedName("fctcode")
    @Expose
    private String fctcode;





    /**
     *
     * @return
     * The HourlyFCTTIME
     */
    public HourlyFCTTIME getHourlyFCTTIME() {
        return HourlyFCTTIME;
    }

    /**
     *
     * @param hourlyFCTTIME
     * The HourlyFCTTIME
     */
    public void setHourlyFCTTIME(HourlyFCTTIME hourlyFCTTIME) {
        this.HourlyFCTTIME = hourlyFCTTIME;
    }

    /**
     *
     * @return
     * The mHourlyTemp
     */
    public HourlyTemp getHourlyTemp() {
        return mHourlyTemp;
    }

    /**
     *
     * @param hourlyTemp
     * The mHourlyTemp
     */
    public void setHourlyTemp(HourlyTemp hourlyTemp) {
        this.mHourlyTemp = hourlyTemp;
    }



    /**
     *
     * @return
     * The condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     *
     * @param condition
     * The condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     *
     * @return
     * The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     * The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     *
     * @return
     * The iconUrl
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     *
     * @param iconUrl
     * The icon_url
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     *
     * @return
     * The fctcode
     */
    public String getFctcode() {
        return fctcode;
    }

    /**
     *
     * @param fctcode
     * The fctcode
     */
    public void setFctcode(String fctcode) {
        this.fctcode = fctcode;
    }


}
