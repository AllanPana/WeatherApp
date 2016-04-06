package android.weatherapp.ie.weatherapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by allan on 04/04/2016.
 */
public class CurrentObservation {


    @SerializedName("display_location")
    @Expose
    private CurrentWeatherDisplayLocation mCurrentWeatherDisplayLocation;

    @SerializedName("station_id")
    @Expose
    private String stationId;
    @SerializedName("observation_time")
    @Expose
    private String observationTime;
    @SerializedName("observation_time_rfc822")
    @Expose
    private String observationTimeRfc822;
    @SerializedName("observation_epoch")
    @Expose
    private String observationEpoch;
    @SerializedName("local_time_rfc822")
    @Expose
    private String localTimeRfc822;
    @SerializedName("local_epoch")
    @Expose
    private String localEpoch;
    @SerializedName("local_tz_short")
    @Expose
    private String localTzShort;
    @SerializedName("local_tz_long")
    @Expose
    private String localTzLong;
    @SerializedName("local_tz_offset")
    @Expose
    private String localTzOffset;
    @SerializedName("weather")
    @Expose
    private String weather;
    @SerializedName("temperature_string")
    @Expose
    private String temperatureString;
    @SerializedName("temp_f")
    @Expose
    private double tempF;
    @SerializedName("temp_c")
    @Expose
    private double tempC;


    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public CurrentWeatherDisplayLocation getCurrentWeatherDisplayLocation() {
        return mCurrentWeatherDisplayLocation;
    }

    public void setCurrentWeatherDisplayLocation(CurrentWeatherDisplayLocation currentWeatherDisplayLocation) {
        mCurrentWeatherDisplayLocation = currentWeatherDisplayLocation;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getObservationTime() {
        return observationTime;
    }

    public void setObservationTime(String observationTime) {
        this.observationTime = observationTime;
    }

    public String getObservationTimeRfc822() {
        return observationTimeRfc822;
    }

    public void setObservationTimeRfc822(String observationTimeRfc822) {
        this.observationTimeRfc822 = observationTimeRfc822;
    }

    public String getObservationEpoch() {
        return observationEpoch;
    }

    public void setObservationEpoch(String observationEpoch) {
        this.observationEpoch = observationEpoch;
    }

    public String getLocalTimeRfc822() {
        return localTimeRfc822;
    }

    public void setLocalTimeRfc822(String localTimeRfc822) {
        this.localTimeRfc822 = localTimeRfc822;
    }

    public String getLocalEpoch() {
        return localEpoch;
    }

    public void setLocalEpoch(String localEpoch) {
        this.localEpoch = localEpoch;
    }

    public String getLocalTzShort() {
        return localTzShort;
    }

    public void setLocalTzShort(String localTzShort) {
        this.localTzShort = localTzShort;
    }

    public String getLocalTzLong() {
        return localTzLong;
    }

    public void setLocalTzLong(String localTzLong) {
        this.localTzLong = localTzLong;
    }

    public String getLocalTzOffset() {
        return localTzOffset;
    }

    public void setLocalTzOffset(String localTzOffset) {
        this.localTzOffset = localTzOffset;
    }

    public String getTemperatureString() {
        return temperatureString;
    }

    public void setTemperatureString(String temperatureString) {
        this.temperatureString = temperatureString;
    }

    public double getTempF() {
        return tempF;
    }

    public void setTempF(double tempF) {
        this.tempF = tempF;
    }

    public double getTempC() {
        return tempC;
    }

    public void setTempC(double tempC) {
        this.tempC = tempC;
    }
}
