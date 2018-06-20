package amit.schoolhub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class search extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    DatabaseHelper dbhelper;
    Cursor data;
    ListView lv_school;
    String sc_name, sc_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        dbhelper = new DatabaseHelper(this);
        lv_school = (ListView) findViewById(R.id.lv_school);
        //getting school list
        data = dbhelper.getDataSchool();

        ArrayList<String> listdata = new ArrayList<>();
        while (data.moveToNext()) {
            listdata.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listdata);
        lv_school.setAdapter(adapter);


        lv_school.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                sc_name = lv_school.getItemAtPosition(i).toString();
                Intent intent = new Intent(search.this, displayschool.class);
                intent.putExtra("sc_name", sc_name);
                Toast.makeText(getBaseContext(), "Welcome " + sc_name, Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }
        });
    }
}

