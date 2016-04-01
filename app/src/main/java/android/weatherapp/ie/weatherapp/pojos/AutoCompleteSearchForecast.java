package android.weatherapp.ie.weatherapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by allan on 31/03/2016.
 */
@Generated("org.jsonschema2pojo")
public class AutoCompleteSearchForecast {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("c")
    @Expose
    private String c;
    @SerializedName("zmw")
    @Expose
    private String zmw;
    @SerializedName("tz")
    @Expose
    private String tz;
    @SerializedName("tzs")
    @Expose
    private String tzs;
    @SerializedName("l")
    @Expose
    private String l;

    @SerializedName("ll")
    @Expose
    private String ll;

    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The c
     */
    public String getC() {
        return c;
    }

    /**
     *
     * @param c
     * The c
     */
    public void setC(String c) {
        this.c = c;
    }

    /**
     *
     * @return
     * The zmw
     */
    public String getZmw() {
        return zmw;
    }

    /**
     *
     * @param zmw
     * The zmw
     */
    public void setZmw(String zmw) {
        this.zmw = zmw;
    }

    /**
     *
     * @return
     * The tz
     */
    public String getTz() {
        return tz;
    }

    /**
     *
     * @param tz
     * The tz
     */
    public void setTz(String tz) {
        this.tz = tz;
    }

    /**
     *
     * @return
     * The tzs
     */
    public String getTzs() {
        return tzs;
    }

    /**
     *
     * @param tzs
     * The tzs
     */
    public void setTzs(String tzs) {
        this.tzs = tzs;
    }

    /**
     *
     * @return
     * The l
     */
    public String getL() {
        return l;
    }

    /**
     *
     * @param l
     * The l
     */
    public void setL(String l) {
        this.l = l;
    }

    /**
     *
     * @return
     * The ll
     */
    public String getLl() {
        return ll;
    }

    /**
     *
     * @param ll
     * The ll
     */
    public void setLl(String ll) {
        this.ll = ll;
    }

    /**
     *
     * @return
     * The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The lon
     */
    public String getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

}
