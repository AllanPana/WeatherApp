package android.weatherapp.ie.weatherapp.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by allan on 31/03/2016.
 */
public class TenDayDate {


    @SerializedName("epoch")
    @Expose
    private String epoch;
    @SerializedName("pretty")
    @Expose
    private String pretty;
    @SerializedName("day")
    @Expose
    private long day;
    @SerializedName("month")
    @Expose
    private long month;
    @SerializedName("year")
    @Expose
    private long year;
    @SerializedName("yday")
    @Expose
    private long yday;
    @SerializedName("hour")
    @Expose
    private long hour;
    @SerializedName("min")
    @Expose
    private String min;
    @SerializedName("sec")
    @Expose
    private long sec;
    @SerializedName("isdst")
    @Expose
    private String isdst;
    @SerializedName("monthname")
    @Expose
    private String monthname;
    @SerializedName("monthname_short")
    @Expose
    private String monthnameShort;
    @SerializedName("weekday_short")
    @Expose
    private String weekdayShort;
    @SerializedName("weekday")
    @Expose
    private String weekday;
    @SerializedName("ampm")
    @Expose
    private String ampm;
    @SerializedName("tz_short")
    @Expose
    private String tzShort;
    @SerializedName("tz_long")
    @Expose
    private String tzLong;

    /**
     *
     * @return
     * The epoch
     */
    public String getEpoch() {
        return epoch;
    }

    /**
     *
     * @param epoch
     * The epoch
     */
    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    /**
     *
     * @return
     * The pretty
     */
    public String getPretty() {
        return pretty;
    }

    /**
     *
     * @param pretty
     * The pretty
     */
    public void setPretty(String pretty) {
        this.pretty = pretty;
    }

    /**
     *
     * @return
     * The day
     */
    public long getDay() {
        return day;
    }

    /**
     *
     * @param day
     * The day
     */
    public void setDay(long day) {
        this.day = day;
    }

    /**
     *
     * @return
     * The month
     */
    public long getMonth() {
        return month;
    }

    /**
     *
     * @param month
     * The month
     */
    public void setMonth(long month) {
        this.month = month;
    }

    /**
     *
     * @return
     * The year
     */
    public long getYear() {
        return year;
    }

    /**
     *
     * @param year
     * The year
     */
    public void setYear(long year) {
        this.year = year;
    }

    /**
     *
     * @return
     * The yday
     */
    public long getYday() {
        return yday;
    }

    /**
     *
     * @param yday
     * The yday
     */
    public void setYday(long yday) {
        this.yday = yday;
    }

    /**
     *
     * @return
     * The hour
     */
    public long getHour() {
        return hour;
    }

    /**
     *
     * @param hour
     * The hour
     */
    public void setHour(long hour) {
        this.hour = hour;
    }

    /**
     *
     * @return
     * The min
     */
    public String getMin() {
        return min;
    }

    /**
     *
     * @param min
     * The min
     */
    public void setMin(String min) {
        this.min = min;
    }

    /**
     *
     * @return
     * The sec
     */
    public long getSec() {
        return sec;
    }

    /**
     *
     * @param sec
     * The sec
     */
    public void setSec(long sec) {
        this.sec = sec;
    }

    /**
     *
     * @return
     * The isdst
     */
    public String getIsdst() {
        return isdst;
    }

    /**
     *
     * @param isdst
     * The isdst
     */
    public void setIsdst(String isdst) {
        this.isdst = isdst;
    }

    /**
     *
     * @return
     * The monthname
     */
    public String getMonthname() {
        return monthname;
    }

    /**
     *
     * @param monthname
     * The monthname
     */
    public void setMonthname(String monthname) {
        this.monthname = monthname;
    }

    /**
     *
     * @return
     * The monthnameShort
     */
    public String getMonthnameShort() {
        return monthnameShort;
    }

    /**
     *
     * @param monthnameShort
     * The monthname_short
     */
    public void setMonthnameShort(String monthnameShort) {
        this.monthnameShort = monthnameShort;
    }

    /**
     *
     * @return
     * The weekdayShort
     */
    public String getWeekdayShort() {
        return weekdayShort;
    }

    /**
     *
     * @param weekdayShort
     * The weekday_short
     */
    public void setWeekdayShort(String weekdayShort) {
        this.weekdayShort = weekdayShort;
    }

    /**
     *
     * @return
     * The weekday
     */
    public String getWeekday() {
        return weekday;
    }

    /**
     *
     * @param weekday
     * The weekday
     */
    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    /**
     *
     * @return
     * The ampm
     */
    public String getAmpm() {
        return ampm;
    }

    /**
     *
     * @param ampm
     * The ampm
     */
    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    /**
     *
     * @return
     * The tzShort
     */
    public String getTzShort() {
        return tzShort;
    }

    /**
     *
     * @param tzShort
     * The tz_short
     */
    public void setTzShort(String tzShort) {
        this.tzShort = tzShort;
    }

    /**
     *
     * @return
     * The tzLong
     */
    public String getTzLong() {
        return tzLong;
    }

    /**
     *
     * @param tzLong
     * The tz_long
     */
    public void setTzLong(String tzLong) {
        this.tzLong = tzLong;
    }


    @Override
    public String toString() {
        return "TenDayDate{" +
                "epoch='" + epoch + '\'' +
                ", pretty='" + pretty + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", yday=" + yday +
                ", hour=" + hour +
                ", min='" + min + '\'' +
                ", sec=" + sec +
                ", isdst='" + isdst + '\'' +
                ", monthname='" + monthname + '\'' +
                ", monthnameShort='" + monthnameShort + '\'' +
                ", weekdayShort='" + weekdayShort + '\'' +
                ", weekday='" + weekday + '\'' +
                ", ampm='" + ampm + '\'' +
                ", tzShort='" + tzShort + '\'' +
                ", tzLong='" + tzLong + '\'' +
                '}';
    }
}
