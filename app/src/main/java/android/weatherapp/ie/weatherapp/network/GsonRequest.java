package android.weatherapp.ie.weatherapp.network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by allan on 21/03/2016.
 *
 */
public class GsonRequest<T> extends Request<T> {


    private Gson gson = new Gson();
    private Class<T> clazz;
    private Map<String, String> headers;
    private Response.Listener<T> responseListener;


    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection,is the type of the object to be returned
     * @param responseListener is the response listener when request is succesful
     * @param errorListener  is the error listener when request is failed
     */
    public GsonRequest(String url,
                       Class <T> clazz,
                       Response.Listener<T> responseListener,
                       Response.ErrorListener errorListener) {

        super(Method.GET, url, errorListener);

        this.clazz = clazz;
        this.responseListener = responseListener;
    }




    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String json = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            return Response.success(gson.fromJson(json,clazz), HttpHeaderParser.parseCacheHeaders(networkResponse));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));

        }catch (JsonSyntaxException jse){

            return Response.error(new ParseError(jse));
        }
    }




    @Override
    protected void deliverResponse(T response) {
        responseListener.onResponse(response);
    }
}
