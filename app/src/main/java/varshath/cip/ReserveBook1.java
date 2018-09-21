package varshath.cip;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by vishnu on 29-03-2016.
 */
public class ReserveBook1 extends AsyncTask<String,Void,String> {
    Context context;
    String email,book,data,link,result,query_result="";
    public ReserveBook1(Context context)
    {
        this.context=context;
    }
    @Override
    protected String doInBackground(String... params) {
        try
        {
            email=params[0];
            book=params[1];
            data ="?email=" + URLEncoder.encode(email, "UTF-8");
            data+="&book=" + URLEncoder.encode(book, "UTF-8");
            link="http://selvakanesh07.esy.es/vreserve.php"+data;
            URL url=new URL(link);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
            result=br.readLine();
            //Toast.makeText(context,result,Toast.LENGTH_LONG).show();
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
        String jsonStr=s;
        if (jsonStr!= null) {
            try {
                //Toast.makeText(context,s,Toast.LENGTH_LONG).show();
                JSONObject jsonObj = new JSONObject(s);
                query_result=jsonObj.getString("query_result");
                if (query_result.equals("Reservation is done successfully")) {
                    Toast.makeText(context, "Your reservation is recorded"+"\n"+"Please press the back button", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        Toast.makeText(context,jsonStr, Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
    }
}
