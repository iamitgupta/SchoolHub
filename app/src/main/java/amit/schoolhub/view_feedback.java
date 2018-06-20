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
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class view_feedback extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    DatabaseHelper dbhelper;
    Cursor data;
    ListView lv_feedback;
    String fb_stusername,fb_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);
        dbhelper=new DatabaseHelper(this);
        lv_feedback=(ListView) findViewById(R.id.lv_feedback);
        //getting school list

        data= dbhelper.getFeedback();

        ArrayList<String> listdata=new ArrayList<>();
        while (data.moveToNext()){
            listdata.add(data.getString(3));
        }
        ListAdapter adapter1=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listdata);
        lv_feedback.setAdapter(adapter1);

    }

}

