package android.weatherapp.ie.weatherapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by allan on 31/03/2016.
 */
public class Forecastday_ {


    @SerializedName("date")
    @Expose
    private TenDayDate mTenDayDate;
    @SerializedName("period")
    @Expose
    private long period;
    @SerializedName("high")
    @Expose
    private TenDayHigh mTenDayHigh;
    @SerializedName("low")
    @Expose
    private TenDayLow mTenDayLow;
    @SerializedName("conditions")
    @Expose
    private String conditions;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("icon_url")
    @Expose
    private String iconUrl;
    @SerializedName("skyicon")
    @Expose
    private String skyicon;


    /**
     *
     * @return
     * The mTenDayDate
     */
    public TenDayDate getTenDayDate() {
        return mTenDayDate;
    }

    /**
     *
     * @param tenDayDate
     * The mTenDayDate
     */
    public void setTenDayDate(TenDayDate tenDayDate) {
        this.mTenDayDate = tenDayDate;
    }

    /**
     *
     * @return
     * The period
     */
    public long getPeriod() {
        return period;
    }

    /**
     *
     * @param period
     * The period
     */
    public void setPeriod(long period) {
        this.period = period;
    }

    /**
     *
     * @return
     * The mTenDayHigh
     */
    public TenDayHigh getTenDayHigh() {
        return mTenDayHigh;
    }

    /**
     *
     * @param tenDayHigh
     * The mTenDayHigh
     */
    public void setTenDayHigh(TenDayHigh tenDayHigh) {
        this.mTenDayHigh = tenDayHigh;
    }

    /**
     *
     * @return
     * The mTenDayLow
     */
    public TenDayLow getTenDayLow() {
        return mTenDayLow;
    }

    /**
     *
     * @param tenDayLow
     * The mTenDayLow
     */
    public void setTenDayLow(TenDayLow tenDayLow) {
        this.mTenDayLow = tenDayLow;
    }

    /**
     *
     * @return
     * The conditions
     */
    public String getConditions() {
        return conditions;
    }

    /**
     *
     * @param conditions
     * The conditions
     */
    public void setConditions(String conditions) {
        this.conditions = conditions;
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
     * The skyicon
     */
    public String getSkyicon() {
        return skyicon;
    }

    /**
     *
     * @param skyicon
     * The skyicon
     */
    public void setSkyicon(String skyicon) {
        this.skyicon = skyicon;
    }


    @Override
    public String toString() {
        return "Forecastday_{" +
                "mTenDayDate=" + mTenDayDate +
                ", period=" + period +
                ", mTenDayHigh=" + mTenDayHigh +
                ", mTenDayLow=" + mTenDayLow +
                ", conditions='" + conditions + '\'' +
                ", icon='" + icon + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", skyicon='" + skyicon + '\'' +
                '}';
    }
}
