package varshath.cip;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by vishnu on 28-03-2016.
 */
public class requestb extends AsyncTask <String,Void,String> {
    private Context context;
    public Smsinterface smsinterface=null;
    public reserveInterface reserveInterface=null;
    String genre,book,data,link,result,query_result=" ";
    SharedPreferences sharedPreferences;
    public requestb(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        try
        {
            book=params[0];
            data = "?book=" + URLEncoder.encode(book, "UTF-8");
            link="http://selvakanesh07.esy.es/vgetstud.php"+data;
            URL url=new URL(link);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
            result=br.readLine();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Toast.makeText(context,s,Toast.LENGTH_LONG).show();
        String jsonStr = result;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                query_result = jsonObj.getString("query_result");
                if (query_result.equals("Your reservation is recorded")) {
                    //Toast.makeText(context, "Your reservation is recorded", Toast.LENGTH_SHORT).show();
                    reserveInterface.reserve(book);
                } else {
                    try {
                        //Toast.makeText(context,query_result, Toast.LENGTH_SHORT).show();
                        smsinterface.processSms(query_result);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
    }
}
