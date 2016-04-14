package android.weatherapp.ie.weatherapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by allan on 04/04/2016.
 */
public class CurrentWeatherResponse {


    @SerializedName("location")
    @Expose
    private Location location;



    @SerializedName("current_observation")
    @Expose
    private CurrentObservation currentObservation;


    /**
     *
     * @return
     * The currentObservation
     */
    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    /**
     *
     * @param currentObservation
     * The current_observation
     */
    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
