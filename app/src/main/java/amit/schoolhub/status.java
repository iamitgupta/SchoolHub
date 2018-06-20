package amit.schoolhub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class status extends AppCompatActivity {

    Cursor cursor,cursor2;
    DatabaseHelper myDb;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
   public TextView tv_school1,tv_school2,tv_school3,tv_school4,tv_school5,tv_school1status,tv_school2status,tv_school3status,tv_school4status,tv_school5status;
    public String st_username,school1,school2,school3,school4,school5,status1,status2,status3,status4,status5;
    int a=0;

    public String sc_id,sc_name, sc_address, sc_mobile, sc_email, sc_description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        myDb = new DatabaseHelper(this);

        sc_name=Integer.toString(a);
        sc_email=Integer.toString(a);
        sc_description=Integer.toString(a);
        sc_mobile=Integer.toString(a);
        sc_address=Integer.toString(a);

        school1="Apply form to get status";
        school2="Apply form to get status";
        school3="Apply form to get status";
        school4="Apply form to get status";
        school5="Apply form to get status";




        tv_school1=(TextView) findViewById(R.id.tv_school1);
        tv_school2=(TextView) findViewById(R.id.tv_school2);
        tv_school3=(TextView) findViewById(R.id.tv_school3);
        tv_school4=(TextView) findViewById(R.id.tv_school4);
        tv_school5=(TextView) findViewById(R.id.tv_school5);
        tv_school1status=(TextView) findViewById(R.id.tv_school1status);
        tv_school2status=(TextView) findViewById(R.id.tv_school2status);
        tv_school3status=(TextView) findViewById(R.id.tv_school3status);
        tv_school4status=(TextView) findViewById(R.id.tv_school4status);
        tv_school5status=(TextView) findViewById(R.id.tv_school5status);

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

            }
        }

        cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_STATUS+" WHERE st_username =?", new String[]{st_username});
        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                sc_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SCCOLUMN_2));
                sc_address = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SCCOLUMN_3));
                sc_mobile = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SCCOLUMN_5));
                sc_email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SCCOLUMN_6));
                sc_description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SCCOLUMN_4));
            }
        }
        status1=sc_name;
        status3=sc_email;
        status2=sc_mobile;
        status4=sc_address;
        status5=sc_description;


        /*
        cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_ADMISSIONSTATUS + " WHERE " + DatabaseHelper.ASCOLUMN_24 + " =?", new String[]{st_username});
        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                status1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ASCOLUMN_19));
                status2 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ASCOLUMN_20));
                status3 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ASCOLUMN_21));
                status4 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ASCOLUMN_22));
                status5 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ASCOLUMN_23));


            }
        }
        */
        tv_school1.setText(school1);
        tv_school2.setText(school2);
        tv_school3.setText(school3);
        tv_school4.setText(school4);
        tv_school5.setText(school5);


        if(status1.equals("0")) {
            tv_school1status.setText("Pending");
            tv_school1status.setTextColor(getResources().getColor(R.color.red));
        }
        else{
            tv_school1status.setText("Selected");
            tv_school1status.setTextColor(getResources().getColor(R.color.green));
        }
        if(status2.equals("0")) {
            tv_school2status.setText("Pending");
            tv_school2status.setTextColor(getResources().getColor(R.color.red));
        }
        else{
            tv_school2status.setText("Selected");
            tv_school2status.setTextColor(getResources().getColor(R.color.green));
        }
        if(status3.equals("0")) {
            tv_school3status.setText("Pending");
            tv_school3status.setTextColor(getResources().getColor(R.color.red));
        }
        else{
            tv_school3status.setText("Selected");
            tv_school3status.setTextColor(getResources().getColor(R.color.green));
        }
        if(status4.equals("0")) {
            tv_school4status.setText("Pending");
            tv_school4status.setTextColor(getResources().getColor(R.color.red));
        }
        else{
            tv_school4status.setText("Selected");
            tv_school4status.setTextColor(getResources().getColor(R.color.green));
        }
        if(status5.equals("0")) {
            tv_school5status.setText("Pending");
            tv_school5status.setTextColor(getResources().getColor(R.color.red));
        }
        else{
            tv_school5status.setText("Selected");
            tv_school5status.setTextColor(getResources().getColor(R.color.green));
        }


    }
}
