package varshath.cip;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RenewResult extends Activity{
    public Intent i;
    public Button b;
    public EditText ta;
    String a="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            setContentView(R.layout.activity_renew_result);
            super.onCreate(savedInstanceState);
            ta = (EditText) findViewById(R.id.tada);
            ta.setVisibility(View.VISIBLE);
            i = getIntent();
            String h=i.getStringExtra("disp");
            ta.setText(h);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
