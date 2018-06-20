
package amit.schoolhub;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class edit_student extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    DatabaseHelper dbhelper;
    Cursor data;
    ListView lv_st;
    String st_name,st_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);


        dbhelper=new DatabaseHelper(this);
        lv_st=(ListView) findViewById(R.id.lv_st);
        //getting school list

        data= dbhelper.getDataStudent();

        ArrayList<String> listdata=new ArrayList<>();
        while (data.moveToNext()){
            listdata.add(data.getString(0));
        }
        ListAdapter adapter1=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listdata);
        lv_st.setAdapter(adapter1);


        lv_st.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                st_username=lv_st.getItemAtPosition(i).toString();
                Intent intent = new Intent(edit_student.this, student_detail_edit.class);
                intent.putExtra("st_username",st_username);
                Toast.makeText(getBaseContext(), "Welcome " +st_username, Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }
        });
    }

}

