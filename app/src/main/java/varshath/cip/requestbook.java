package varshath.cip;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class requestbook extends Activity implements View.OnClickListener,Smsinterface,reserveInterface,Animation.AnimationListener{
EditText e1,e2;
    Animation animation;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestbook);
        e2=(EditText)findViewById(R.id.bedit);
        b=(Button)findViewById(R.id.gbutton);
        //animation = AnimationUtils.loadAnimation(getApplicationContext(),
               // R.anim.together);
        //animation.setAnimationListener(this);
        e2.setAnimation(animation);
        b.setAnimation(animation);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String s2=e2.getText().toString();
        requestb r=new requestb(this);
        r.smsinterface=this;
        r.reserveInterface=this;
        r.execute(s2);
    }

    @Override
    public void processSms(String no) {
        Intent i=new Intent(requestbook.this,sendsms.class);
        i.putExtra("Number",no);
        startActivity(i);
    }

    @Override
    public void reserve(String book) {
        SharedPreferences sharedPreferences=requestbook.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email=sharedPreferences.getString(Config.EMAIL_SHARED_PREF, " ");
        Intent i=new Intent(requestbook.this,ReserveBook.class);
        i.putExtra("email",email);
        i.putExtra("book",book);
        startActivity(i);

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
