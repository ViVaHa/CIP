package varshath.cip;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sendsms extends AppCompatActivity implements View.OnClickListener,Animation.AnimationListener {
EditText e;
    Button b;
    String s="";
    Intent i;
    String no;
    TextView t;
    Animation animFadein;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);
        e=(EditText)findViewById(R.id.editTextsms);
        b=(Button)findViewById(R.id.button2);
        t=(TextView)findViewById(R.id.textView2);
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        animFadein.setAnimationListener(this);
        t.startAnimation(animFadein);
        b.setOnClickListener(this);
        i=getIntent();
        no=i.getStringExtra("Number");
    }

    @Override
    public void onClick(View v) {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        s=e.getText().toString();
        Intent intent=new Intent(getApplicationContext(),sendsms.class);
        PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(no, null,s,pi, null);
        Toast.makeText(this,"Your message is sent succesfully",Toast.LENGTH_LONG).show();
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
