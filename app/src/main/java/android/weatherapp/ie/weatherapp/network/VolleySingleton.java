package android.weatherapp.ie.weatherapp.network;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.weatherapp.ie.weatherapp.WeatherApp;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Created by allan on 21/03/2016.
 */
public class VolleySingleton {

    private static VolleySingleton sVolleySingletonInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;


    /**
     *
     */
    private VolleySingleton(){

        /**
         * The way Volley works is creating requests and adding them to a queue.
         * One queue is enough for the whole application,
         * so each time you want to make a request you'll get the (only) Volley queue to add the request to that queue.
         */
        if(mRequestQueue==null){
            mRequestQueue = Volley.newRequestQueue(WeatherApp.getAppContext());
        }


        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            LruCache<String,Bitmap> lruCache = new LruCache<>((int)(Runtime.getRuntime().maxMemory()/1024)/8);

            @Override
            public Bitmap getBitmap(String s) {
                return lruCache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                lruCache.put(s,bitmap);
            }
        });

    }


    /**
     *
     * @return single instance of this class
     */
    public synchronized static VolleySingleton getVolleySingletonInstance(){
        if(sVolleySingletonInstance == null){
            sVolleySingletonInstance = new VolleySingleton();
        }
        return sVolleySingletonInstance;
    }


    /**
     *
     * @return Returns a Volley request queue for creating network requests
     */
    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }


    /**
     *
     * @return
     */
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }


    /**
     *
     * @param request is the request to add to the Volley queue
     * @param <T> type of object request
     */
    public <T> void addRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
