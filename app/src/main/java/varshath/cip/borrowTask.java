package varshath.cip;

import android.content.Context;
import android.content.SharedPreferences;
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
 * Created by vishnu on 02-04-2016.
 */
public class borrowTask extends AsyncTask<String ,Void,String> {
    String format,content,email,data,result,link;
    Context context;
    public borrowTask(Context context)
    {
        this.context=context;
    }
    @Override
    protected String doInBackground(String... params) {
        try
        {
            format=params[0];
            content=params[1];
            email=params[2];
            data ="?format=" + URLEncoder.encode(format, "UTF-8");
            data+="&content=" + URLEncoder.encode(content, "UTF-8");
            data+="&email=" + URLEncoder.encode(email, "UTF-8");
            link="http://selvakanesh07.esy.es/vborrow.php"+data;
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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        String jsonStr =s;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("query_result");
                if (query_result.equals("Thanks for confirming")) {
                    Toast.makeText(context,query_result+"\n"+"You are the owner of this new book", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Toast.makeText(context,query_result, Toast.LENGTH_SHORT).show();
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
