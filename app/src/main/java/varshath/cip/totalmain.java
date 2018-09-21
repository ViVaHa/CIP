package varshath.cip;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class totalmain extends ListActivity {
    String a[] = {"Scan a barcode","request a book","Lend","Borrow"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(totalmain.this, android.R.layout.simple_list_item_multiple_choice, a));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String cheese = a[position];
        if (cheese.contentEquals("Scan a barcode")) {
            Intent i = new Intent(totalmain.this,Barcode.class);
            startActivity(i);
        }
        else if (cheese.contentEquals("request a book")){
            Intent i = new Intent(totalmain.this,requestbook.class);
            startActivity(i);
        }
        else if (cheese.contentEquals("Lend")){
            Intent i = new Intent(totalmain.this,Share.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(totalmain.this,Borrow.class);
            startActivity(i);
        }
    }
}