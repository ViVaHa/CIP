package varshath.cip;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class email extends Activity{
    EditText e1,e2,e3;
    public  static String mail="";
    public  static String sub="";
    public  static String msg="";
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        e1 = (EditText) findViewById(R.id.editText0);
        e2 = (EditText) findViewById(R.id.editText1);
        e3 = (EditText) findViewById(R.id.editText2);
        b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = e1.getText().toString();
                sub = e2.getText().toString();
                msg = e3.getText().toString();
                String mailid[]={mail};
                Intent email = new Intent(android.content.Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,mailid);
                email.putExtra(Intent.EXTRA_SUBJECT,sub);
                email.setType("plain/text");
                email.putExtra(Intent.EXTRA_TEXT,msg);
                startActivity(email);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
