package varshath.cip;
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

public class Barcode extends Activity implements View.OnClickListener,result_interface,Animation.AnimationListener{

    private Button scan;
    String format;
    String contents;
    TextView t,t1;
    Animation animation;
    public BarcodeCheck b=new BarcodeCheck(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        t=(TextView)findViewById(R.id.scan_format);
        t1=(TextView)findViewById(R.id.scan_content);
        scan= (Button)findViewById(R.id.scan_button);
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
            b.inter=this;
            b.execute(contents, format);
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
        if(v.getId()==R.id.scan_button)
        {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }

    }

    @Override
    public void processFinish(String result,String disp) {
        try {
            Intent i = new Intent(Barcode.this, RenewResult.class);
            i.putExtra("result", result);
            i.putExtra("disp", disp);
            startActivity(i);
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}