package varshath.cip;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener,Animation.AnimationListener{
Animation animation,animation1,animation2,animation3;
    private TextView textView;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textView = (TextView) findViewById(R.id.textView);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.sequential_animation);
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out);
        animation.setAnimationListener(this);
        animation1.setAnimationListener(this);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);
        animation2.setAnimationListener(this);
        animation3 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        animation3.setAnimationListener(this);
        b=(Button)findViewById(R.id.butto);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");
        textView.setText("Current User: " + email);
        textView.startAnimation(animation);
        //textView.startAnimation(animation1);
        //b.startAnimation(animation3);
        //b.startAnimation(animation2);
        b.startAnimation(animation);
        b.setOnClickListener(this);
    }

    private void logout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = preferences.edit();

                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        editor.putString(Config.EMAIL_SHARED_PREF, "");

                        editor.commit();

                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent i=new Intent(ProfileActivity.this,totalmain.class);
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
