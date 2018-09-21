package varshath.cip;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Borrow extends Activity implements View.OnClickListener{

    private Button scan;
    String format;
    String contents;
    TextView t,t1;
    Animation animation;
    public borrowTask b=new borrowTask(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);
        scan= (Button)findViewById(R.id.button5);
        /*animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        animation.setAnimationListener(this);
        scan.startAnimation(animation);*/
        scan.setOnClickListener(this);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            contents = scanningResult.getContents();
            format = scanningResult.getFormatName();
            SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            String email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");
            b.execute(format,contents,email);
        }
        else
        {
            Toast.makeText(this,"Unsuccesfull",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button5)
        {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }

    }
}
