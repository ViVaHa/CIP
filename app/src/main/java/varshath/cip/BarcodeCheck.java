package varshath.cip;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by vishnu on 15-03-2016.
 */
public class BarcodeCheck extends AsyncTask<String,Void,String> {
    private Context context;
    String link,data;
    BufferedReader bufferedReader;
    String result,format,content;
    public result_interface inter=null;
    public BarcodeCheck(Context context) {
        this.context = context;
    }
    protected String doInBackground(String... params) {
        try {
            content = params[0];
            format = params[1];
            data= "?content=" + URLEncoder.encode(content, "UTF-8");
            data += "&format=" + URLEncoder.encode(format, "UTF-8");
            link = "http://selvakanesh07.esy.es/vrenew.php"+data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    protected void onPostExecute(String result) {
        //Toast.makeText(context,content,Toast.LENGTH_LONG).show();
        String jsonStr = result;
        String res = "", disp = "";
        //Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray j = jsonObject.optJSONArray("result");
            for (int i = 0; i < j.length(); i++) {
                JSONObject object = j.getJSONObject(i);
                res = object.getString("result1");
                if (res.equalsIgnoreCase("yes")) {
                    Toast.makeText(context, "Successfully renewed", Toast.LENGTH_LONG).show();
                    disp = object.getString("duedate");
                    Toast.makeText(context, "Your new due date is on" + disp, Toast.LENGTH_LONG).show();
                    /*Intent intent=new Intent(context,RenewResult.class);
                    intent.putExtra("result","Succesully renewed");
                    intent.putExtra("duedate","Your new due date is on"+ disp);*/
                    disp="Your new due date is on" + disp;
                } else {
                    disp = object.getString("daysrem");
                    int a = Integer.parseInt(disp);
                    a = a - 10;
                    disp = String.valueOf(a);
                    //Toast.makeText(context, "Try again after " + disp + " days", Toast.LENGTH_LONG).show();
                    disp="Try again after " + disp + " days";
                }
            }
        } catch (JSONException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
        try {
            inter.processFinish(res,disp);
        }
        catch (Exception e)
        {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
