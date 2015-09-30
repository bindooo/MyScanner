package com.faveoffate.myscanner;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ScanResultActivity extends Activity {

    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ListAdapter adapter;
    protected ListView productList;
    protected String resultString;
    protected String[] s1 = new String[2];

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        String[] match;
        TextView barcodeView = (TextView)findViewById(R.id.barcodeView);

        Intent i = getIntent();
        resultString = i.getStringExtra("extra_resultString");

        db = (new DataBaseHelper(this)).getReadableDatabase();

//        productList = (ListView) findViewById (R.id.productList);
//        search(resultString);

        match = returnMatchByBarcode(resultString);

        barcodeView.setText(match[0]);

    }

    void search(String resultString) {

        cursor = db.rawQuery("SELECT _id, barcode, product FROM Products WHERE barcode LIKE ?",
                new String[]{resultString});

        if (cursor==null || cursor.getCount()==0) setContentView(R.layout.no_result);

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.product_list_item,
                cursor,
                new String[] {"barcode", "product"},
                new int[] {R.id.barcode, R.id.product});
        productList.setAdapter(adapter);
    }

    public String[] returnMatchByBarcode(String resultString) throws SQLException {

        String[] columns = new String[]{ "barcode", "product" };
        Cursor c = db.query("Products", columns, "barcode" + "=" + resultString, null, null, null, null);

        if (c != null){
            c.moveToFirst();
            for(int i = 0; i<2; i++){
                s1[i] = c.getString(i);
            }
            c.close();
            return s1;
        }

        return null;
    }
}
