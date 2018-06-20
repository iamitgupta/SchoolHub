package amit.schoolhub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class school_detail_edit extends AppCompatActivity {

    DatabaseHelper myDb;
    Button bt_submit,bt_delete;
    EditText et_scname;
    EditText et_saddress;
    EditText et_smobile;
    EditText et_semail;
    EditText et_description;
    public String sc_id,sc_name, sc_address, sc_mobile, sc_email, sc_description;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail_edit);
        myDb = new DatabaseHelper(this);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        et_scname = (EditText) findViewById(R.id.et_schoolname);
        et_saddress = (EditText) findViewById(R.id.et_saddress);
        et_smobile = (EditText) findViewById(R.id.et_smobile);
        et_semail = (EditText) findViewById(R.id.et_semail);
        et_description = (EditText) findViewById(R.id.et_description);
        bt_submit = (Button) findViewById(R.id.bt_submit);
        bt_delete=(Button) findViewById(R.id.bt_delete);

        Intent intent = getIntent();
        sc_name = intent.getStringExtra("sc_name");

        cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_SCHOOL+" WHERE "+DatabaseHelper.SCCOLUMN_2+" =?", new String[]{sc_name});
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

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.deleteschool(sc_id);

                Intent intent = new Intent(getApplicationContext(), edit_school.class);
                Toast.makeText(getBaseContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

                bt_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addschool();
                    }
                });
            }

            public void addschool () {

                String schoolname = et_scname.getText().toString();
                String saddress = et_saddress.getText().toString();
                String semail = et_semail.getText().toString();
                String smobile = et_smobile.getText().toString();
                String description = et_description.getText().toString();


                if (validate()) {
                    myDb.updateschool(sc_id,schoolname, smobile, semail, saddress, description);
                    bt_submit.setEnabled(true);
                    setResult(RESULT_OK, null);
                    Toast.makeText(getBaseContext(), "Successfuly added", Toast.LENGTH_LONG).show();
                  Intent  intent = new Intent(getApplicationContext(), edit_school.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getBaseContext(), "Couldn't Updated", Toast.LENGTH_LONG).show();

                }
            }


            public boolean validate () {
                boolean valid = true;

                String schoolname = et_scname.getText().toString();
                String saddress = et_saddress.getText().toString();
                String semail = et_semail.getText().toString();
                String smobile = et_smobile.getText().toString();
                String description = et_description.getText().toString();
                if (schoolname.isEmpty() || schoolname.length() < 3) {
                    et_scname.setError("At least 3 characters");
                    valid = false;
                } else {
                    et_scname.setError(null);
                }

                if (saddress.isEmpty()) {
                    et_description.setError("Enter a valid address");
                    valid = false;
                } else {
                    et_saddress.setError(null);
                }


                if (description.isEmpty()) {
                    et_description.setError("Enter Description");
                    valid = false;
                } else {
                    et_description.setError(null);
                }


                if (semail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(semail).matches()) {
                    et_semail.setError("Enter a valid email address");
                    valid = false;
                } else {
                    et_semail.setError(null);
                }

                if (smobile.isEmpty() || smobile.length() != 10) {
                    et_smobile.setError("Enter Valid Mobile Number");
                    valid = false;
                } else {
                    et_smobile.setError(null);
                }
                return valid;
            }

        }