package android.weatherapp.ie.weatherapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by allan on 30/03/2016.
 */
@Generated("org.jsonschema2pojo")
public class HourlyTemp {

    @SerializedName("english")
    @Expose
    private String english;

    @SerializedName("metric")
    @Expose
    private String metric;



    /**
     *
     * @return
     * The english
     */
    public String getEnglish() {
        return english;
    }

    /**
     *
     * @param english
     * The english
     */
    public void setEnglish(String english) {
        this.english = english;
    }

    /**
     *
     * @return
     * The metric
     */
    public String getMetric() {
        return metric;
    }

    /**
     *
     * @param metric
     * The metric
     */
    public void setMetric(String metric) {
        this.metric = metric;
    }


}
