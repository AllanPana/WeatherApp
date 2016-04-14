package android.weatherapp.ie.weatherapp;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.weatherapp.ie.weatherapp.pojos.CurrentObservation;
import android.weatherapp.ie.weatherapp.pojos.Location;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by allan on 04/04/2016.
 */
public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherRecyclerViewAdapter.WeatherDataViewHolder> {


    private static final String DEGREE_CELCIUS = "\u00b0";
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private int mHolderViewType;
    private Context mContext;
    private List<CurrentObservation> mList = new ArrayList<>();
    List<Location>mLocations = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private static ViewHolderCallback mViewHolderCallback;

    private List<WeatherDataViewHolder> mWeatherDataViewHolderList = new ArrayList<>();


    public WeatherRecyclerViewAdapter(Context context, List<CurrentObservation> list, List<Location>locations) {
        mContext = context;
        mList = list;
        mLocations = locations;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public WeatherDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WeatherDataViewHolder weatherDataViewHolder;
        mHolderViewType = viewType;
        if (viewType == TYPE_HEADER) {
            View view = mLayoutInflater.inflate(R.layout.weather_recyclerview_top_item, parent, false);
            weatherDataViewHolder = new WeatherDataViewHolder(view);
        } else {
            View view = mLayoutInflater.inflate(R.layout.weather_recyclerview_item, parent, false);
            weatherDataViewHolder = new WeatherDataViewHolder(view);
        }

        return weatherDataViewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherDataViewHolder holder, final int position) {
        CurrentObservation currentWeather = mList.get(position);
        final Location location = mLocations.get(position);

        //need this string tz for setting up the date/time
        String tz = currentWeather.getLocalTzLong();
        final String fullName = currentWeather.getCurrentWeatherDisplayLocation().getFull();
        String latitude = currentWeather.getCurrentWeatherDisplayLocation().getLatitude();
        String longitude = currentWeather.getCurrentWeatherDisplayLocation().getLongitude();
        String city = currentWeather.getCurrentWeatherDisplayLocation().getCity();
        String time = currentWeather.getLocalTimeRfc822();
        double tempCelsius = currentWeather.getTempC();
        String stringTemp = String.format("%.0f", tempCelsius);

        holder.tv_city.setText(city);
        holder.tv_time.setText(setTime(time, tz));
        holder.tv_temp.setText(stringTemp + DEGREE_CELCIUS);
        //holder.im_.setImageDrawable(context.getResources().getDrawable(R.drawable.remove, null));

        String image = "http://assets.fodors.com/destinations/2869/tower-bridge-london-england_main.jpg";
        String image2 = "http://m2.her.ie/YToyOntzOjQ6ImRhdGEiO3M6MTU5OiJhOjM6e3M6MzoidXJsIjtzOjk4OiJodHRwOi8vbWVkaWEtaGVyLm1heGltdW1tZWRpYS5pZS5zMy5hbWF6b25hd3MuY29tL3dwLWNvbnRlbnQvdXBsb2Fkcy8yMDE1LzA0LzE5MjMyODE5L2R1Ymxpbi0yLmpwZyI7czo1OiJ3aWR0aCI7aTo2NDc7czo2OiJoZWlnaHQiO2k6MzQwO30iO3M6NDoiaGFzaCI7czo0MDoiMDQxOWI1YzI1YTU4ZDIyY2MyYTU4OTJhN2E0ZDEzYzY2ZjNiZGFiZiI7fQ==/dublin-2.jpg";
        String image3 = null;
        if (mHolderViewType == TYPE_HEADER){
            image3=image;
        }else {
            image3=image2;
        }
        Glide.with(holder.iv_city_background.getContext())
                .load(image3)
                .into(holder.iv_city_background);



        holder.im_cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAt(position);
                mViewHolderCallback.deleteData(location.getL());

            }
        });
        holder.im_cloud.setEnabled(false);

        mWeatherDataViewHolderList.add(holder);
    }


    public List<WeatherDataViewHolder> getHolderList(){
        return mWeatherDataViewHolderList;
    }

    public WeatherDataViewHolder getHolderItem(int position){
        return mWeatherDataViewHolderList.get(position);
    }



    @Override
    public int getItemCount() {
        return (mList != null ? mList.size() : 0);
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }


    //removed item from the adapter by position
    public void removeAt(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mList.size());
    }



    /**
     * @param rfc822DateToParse rfc date from the response
     * @return string time format HH:mm
     */
    private String setTime(String rfc822DateToParse, String tz) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
        sdf.setTimeZone(TimeZone.getTimeZone(tz));
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeFormat.setTimeZone(TimeZone.getTimeZone(tz));
        String stringTime = null;
        java.util.Date date = null;
        try {
            //parse the string to date
            date = sdf.parse(rfc822DateToParse);
            Log.d("time", "parse =========" + date.toString());
            //format the and parse the date to string
            stringTime = timeFormat.format(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return stringTime;
    }


    /**
     * custom ViewHolder class
     */
    public static class WeatherDataViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_city_background;
        public TextView tv_city;
        private TextView tv_time;
        private TextView tv_temp;
        private ImageView im_cloud;


        public WeatherDataViewHolder(View itemView) {
            super(itemView);
            iv_city_background = (ImageView) itemView.findViewById(R.id.iv_city_background);
            tv_city = (TextView) itemView.findViewById(R.id.tv_city);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_temp = (TextView) itemView.findViewById(R.id.tv_temp);
            im_cloud = (ImageView) itemView.findViewById(R.id.im_cloud);
        }


        public ImageView getIm_cloud() {
            return im_cloud;
        }


        //show/hide the remove icon
        public void showRemoveIcon(Context context, boolean isEnabled) {
            if(isEnabled) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    im_cloud.setImageDrawable(context.getResources().getDrawable(R.drawable.remove, null));
                } else {
                    im_cloud.setImageDrawable(context.getResources().getDrawable(R.drawable.remove));
                }
                im_cloud.setEnabled(true);
            }
            else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    im_cloud.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_rain, null));
                } else {
                    im_cloud.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_rain));
                }
                im_cloud.setEnabled(false);
            }
        }
    }




    //setter for the interface callback
    public void setViewHolderCallback(ViewHolderCallback viewHolderCallback) {
        mViewHolderCallback = viewHolderCallback;
    }

    /**
     *
     */
    interface ViewHolderCallback{
        void deleteData(String city);
    }

}
