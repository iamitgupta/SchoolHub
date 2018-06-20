package amit.schoolhub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class view_admission extends AppCompatActivity {
    TextView et_fullname,et_fathersname,et_occupation,et_lastschool,et_result,et_address;
    TextView sp_caste,sp_gender,sp_passedstd,sp_school1;
    Switch sw1,sw2,sw3,sw4,sw5;
    String st_username,school1,school2,school3,school4,school5,fullname,fathersname,occupation,lastschool,result,address;
    String passedstd,caste,gender;
    int st1,st2,st3,st4,st5;
    String st11,st22,st33,st44,st55;
    Cursor cursor;
    DatabaseHelper myDb;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_admission);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        myDb = new DatabaseHelper(this);

        et_lastschool=(TextView) findViewById(R.id.et_lastschool);
        et_result=(TextView) findViewById(R.id.et_result);
        sp_passedstd=(TextView) findViewById(R.id.sp_passedstd1);

        sw1=(Switch) findViewById(R.id.sw_school1);
        sw2=(Switch) findViewById(R.id.sw_school2);
        sw3=(Switch) findViewById(R.id.sw_school3);
        sw4=(Switch) findViewById(R.id.sw_school4);
        sw5=(Switch) findViewById(R.id.sw_school5);

        Button bt_submit=(Button) findViewById(R.id.bt_submit);
        Button bt_viewprofile=(Button) findViewById(R.id.bt_viewprofile);




        Intent intent = getIntent();
        st_username=intent.getStringExtra("st_username");

        cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_FORM + " WHERE " + DatabaseHelper.APCOLUMN_2 + " =?", new String[]{st_username});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                school1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.APCOLUMN_13));
                school2 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.APCOLUMN_14));
                school3 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.APCOLUMN_15));
                school4 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.APCOLUMN_16));
                school5 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.APCOLUMN_17));
                lastschool = cursor.getString(cursor.getColumnIndex(DatabaseHelper.APCOLUMN_8));
                result = cursor.getString(cursor.getColumnIndex(DatabaseHelper.APCOLUMN_9));
                passedstd = cursor.getString(cursor.getColumnIndex(DatabaseHelper.APCOLUMN_12));

            }
        }

        et_lastschool.setText(lastschool);
        et_result.setText(result);
        sp_passedstd.setText(passedstd);
        sw1.setText(school1);
        sw2.setText(school2);
        sw3.setText(school3);
        sw4.setText(school4);
        sw5.setText(school5);

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked) {
                   st1 = 1;
               }
               else{
                   st1=0;
               }
            }
        });
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    st2 = 1;
                }
                else{
                    st2=0;
                }
            }
        });
        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    st3 = 1;
                }
                else{
                    st3=0;
                }
            }
        });
        sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    st4 = 1;
                }
                else{
                    st4=0;
                }
            }
        });
        sw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    st5 = 1;
                }
                else{
                    st5=0;
                }
            }
        });
bt_submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
 /*
final String status1=Integer.toString(st1);
       final String status2=Integer.toString(st2);
        final String status3=Integer.toString(st3);
        final String status4=Integer.toString(st4);
        final String status5=Integer.toString(st5);


       myDb.addadmissionstatus(st_username, status1, status2, status3, status4, status5); */
      String schoolname=Integer.toString(st1);
        String smobile=Integer.toString(st2);
        String semail=Integer.toString(st3);
        String saddress=Integer.toString(st4);
        String description=Integer.toString(st5);



        myDb.addstatus(st_username,schoolname,semail,smobile, saddress, description);
        Toast.makeText(getBaseContext(), "Sucessfully Submitted for "+st_username, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), search_admission.class);
        startActivity(intent);
        finish();
    }
});
bt_viewprofile.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(view_admission.this, student_detail_edit.class);
        intent.putExtra("st_username",st_username);
        Toast.makeText(getBaseContext(), "Welcome " +st_username, Toast.LENGTH_LONG).show();
        startActivity(intent);
        finish();
    }
});
    }
}