package com.view.chip.timeline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView timeList;
    protected MyRecordAdapter recordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<RecordData.RecordListData> data = new ArrayList<RecordData.RecordListData>();
        RecordData.RecordListData item = new RecordData.RecordListData();
       item.shopname = "ceshi";
        data.add(item);
        data.add(item);
        data.add(item);
        recordAdapter = new MyRecordAdapter(MainActivity.this,"10","20");
        recordAdapter.setData(data);
        timeList = (ListView) findViewById(R.id.timeList);
        timeList.setAdapter(recordAdapter);


    }
}
