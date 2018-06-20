package amit.schoolhub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class displayschool extends AppCompatActivity {

    Button bt_submit, bt_delete;
    EditText et_scname;
    EditText et_saddress;
    EditText et_smobile;
    EditText et_semail;
    EditText et_description;
    public String sc_id, sc_name, sc_address, sc_mobile, sc_email, sc_description;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayschool);
        myDb = new DatabaseHelper(this);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        et_scname = (EditText) findViewById(R.id.et_schoolname);
        et_saddress = (EditText) findViewById(R.id.et_saddress);
        et_smobile = (EditText) findViewById(R.id.et_smobile);
        et_semail = (EditText) findViewById(R.id.et_semail);
        et_description = (EditText) findViewById(R.id.et_description);
        bt_submit = (Button) findViewById(R.id.bt_submit);
        bt_delete = (Button) findViewById(R.id.bt_delete);

        Intent intent = getIntent();
        sc_name = intent.getStringExtra("sc_name");

        cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_SCHOOL + " WHERE " + DatabaseHelper.SCCOLUMN_2 + " =?", new String[]{sc_name});
        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                sc_id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SCCOLUMN_1));
                sc_address = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SCCOLUMN_3));
                sc_mobile = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SCCOLUMN_5));
                sc_email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SCCOLUMN_6));
                sc_description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.SCCOLUMN_4));
            }
        }
        et_scname.setText(sc_name);
        et_saddress.setText(sc_address);
        et_smobile.setText(sc_mobile);
       et_description.setText(sc_description);
        et_semail.setText(sc_email);
    }
}