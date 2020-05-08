package com.myapplicationdev.android.p03_classjournal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> moduleList;
    ListView lv;
    ArrayAdapter<String> aa;
    ArrayList<DailyCA> dailyCA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lvModuleCode);

        moduleList = new ArrayList<String>();
        moduleList.add("C347");


        aa = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,moduleList);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), DetailsActivity.class);
                i.putExtra("moduleCode","C347");
                i.putExtra("email","jason_lim@rp.edu.sg");
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_OK){
            DailyCA newDG = (DailyCA) data.getSerializableExtra("newca");
            dailyCA.add(newDG);
            aa.notifyDataSetChanged();
        }
    }
}
