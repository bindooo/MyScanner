package com.faveoffate.myscanner;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        Intent i = getIntent();
        resultString = i.getStringExtra("extra_resultString");

        db = (new DataBaseHelper(this)).getReadableDatabase();

        productList = (ListView) findViewById (R.id.productList);

        search(resultString);

    }

    void search(String resultString) {
        // || is the concatenation operation in SQLite
        cursor = db.rawQuery("SELECT _id, barcode, product FROM Products WHERE barcode LIKE ?",
                new String[]{resultString});
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.product_list_item,
                cursor,
                new String[] {"barcode", "product"},
                new int[] {R.id.barcode, R.id.product});
        productList.setAdapter(adapter);

        if (cursor==null || cursor.getCount()==0) setContentView(R.layout.no_result);

    }

}
