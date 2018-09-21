package varshath.cip;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReserveBook extends Activity implements View.OnClickListener {
TextView t1,t2;
    Button b;
    String email,book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_book);
        Intent i=getIntent();
       email=i.getStringExtra("email");
        book=i.getStringExtra("book");
        t1=(TextView)findViewById(R.id.textView3);
        t2=(TextView)findViewById(R.id.textView4);
        t1.setText(email);
        t2.setText(book);
        b=(Button)findViewById(R.id.button3);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ReserveBook1 reserveBook1=new ReserveBook1(this);
        reserveBook1.execute(email,book);
    }
}
