package android.weatherapp.ie.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.weatherapp.ie.weatherapp.pojos.CurrentObservation;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by allan on 04/04/2016.
 */
public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherRecyclerViewAdapter.WeatherDataViewHolder> {


    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private int mHolderViewType;
    private Context mContext;
    private List<CurrentObservation>mList = new ArrayList<>();
    private LayoutInflater mLayoutInflater;


    public WeatherRecyclerViewAdapter(Context context, List<CurrentObservation> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public WeatherDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WeatherDataViewHolder weatherDataViewHolder;
        mHolderViewType = viewType;
        if(viewType==TYPE_HEADER){
            View view = mLayoutInflater.inflate(R.layout.weather_recyclerview_top_item,parent,false);
            weatherDataViewHolder = new WeatherDataViewHolder(view);
        }else {
            View view = mLayoutInflater.inflate(R.layout.weather_recyclerview_item,parent,false);
            weatherDataViewHolder = new WeatherDataViewHolder(view);
        }

        return weatherDataViewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherDataViewHolder holder, int position) {
        CurrentObservation currentWeather = mList.get(position);

        //need this string tz for setting up the date/time
        String tz = currentWeather.getLocalTzLong();
        holder.tv_city.setText(currentWeather.getCurrentWeatherDisplayLocation().getCity());
        holder.tv_time.setText(setTime(currentWeather.getObservationTimeRfc822(),tz));
        holder.tv_temp.setText(currentWeather.getTempC()+"");
        Log.d("time",currentWeather.getObservationTimeRfc822());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }


    /**
     * custom ViewHolder class
     */
    public class WeatherDataViewHolder extends RecyclerView.ViewHolder{

        TextView tv_city;
        TextView tv_time;
        TextView tv_temp;
        ImageView im_cloud;

        public WeatherDataViewHolder(View itemView) {
            super(itemView);

            tv_city = (TextView) itemView.findViewById(R.id.tv_city);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_temp = (TextView) itemView.findViewById(R.id.tv_temp);
            im_cloud = (ImageView) itemView.findViewById(R.id.im_cloud);
        }
    }


    /**
     *
     * @param rfc822DateToParse rfc date from the response
     * @return string time format HH:mm
     */
    private String setTime(String rfc822DateToParse,String tz) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
        sdf.setTimeZone(TimeZone.getTimeZone(tz));
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setTimeZone(TimeZone.getTimeZone(tz));
        String stringTime = null;
        java.util.Date date = null;
        try {
            //parse the string to date
            date = sdf.parse(rfc822DateToParse);
            Log.d("time","parse ========="+date.toString());
            //format the and parse the date to string
            stringTime =  timeFormat.format(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return stringTime;
    }
}
