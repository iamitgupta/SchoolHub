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
import android.widget.ImageView;
import android.widget.Toast;

public class student_detail_edit extends AppCompatActivity {

    EditText et_name, et_city, et_mobile, et_email,et_username;
    public String st_username, st_name, st_city, st_mobile, st_email;
    Button bt_changeprofile,bt_deleteuser;
    DatabaseHelper myDb;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail_edit);

        et_username = (EditText) findViewById(R.id.et_username);
        et_name = (EditText) findViewById(R.id.et_name);
        et_city = (EditText) findViewById(R.id.et_city);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_email = (EditText) findViewById(R.id.et_email);
        bt_changeprofile = (Button) findViewById(R.id.bt_changeprofile);
        bt_deleteuser = (Button) findViewById(R.id.bt_deleteuser);


        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        Intent intent = getIntent();
        st_username=intent.getStringExtra("st_username");



        cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_STUDENT+" WHERE "+DatabaseHelper.STCOLUMN_2+" =?", new String[]{st_username});
        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                st_username=cursor.getString(cursor.getColumnIndex(DatabaseHelper.STCOLUMN_2));
                st_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.STCOLUMN_4));
                st_city= cursor.getString(cursor.getColumnIndex(DatabaseHelper.STCOLUMN_5));
                st_mobile= cursor.getString(cursor.getColumnIndex(DatabaseHelper.STCOLUMN_6));
                st_email= cursor.getString(cursor.getColumnIndex(DatabaseHelper.STCOLUMN_7));
            }
        }

//        cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_STUDENT + " WHERE " + DatabaseHelper.STCOLUMN_2 + " =?", new String[]{st_username});
        et_name.setText(st_name);
        et_city.setText(st_city);
        et_email.setText(st_email);
        et_mobile.setText(st_mobile);
        et_username.setText(st_username);
        bt_changeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addstudent();
            }

            private void addstudent() {
                st_name = et_name.getText().toString();
                st_username=et_username.getText().toString();
                st_city = et_city.getText().toString();
                st_mobile = et_mobile.getText().toString();
                st_email = et_email.getText().toString();

                if(validate()){
                  //  myDb.updatestudent(st_username,st_name, st_city, st_mobile, st_email);

                    setResult(RESULT_OK, null);
                    Toast.makeText(getBaseContext(), "Successfully Updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), edit_student.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(getBaseContext(), "Couldn't Updated", Toast.LENGTH_LONG).show();

                }

            }
            public boolean validate(){
                boolean valid = true;
                if (st_name.isEmpty() || st_name.length() < 3) {
                    et_name.setError("At least 3 characters");
                    valid = false;
                } else {
                    et_name.setError(null);
                }

                if (st_city.isEmpty()) {
                    et_city.setError("Enter Valid City");
                    valid = false;
                } else {
                    et_city.setError(null);
                }

                if (st_email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(st_email).matches()) {
                    et_email.setError("Enter a valid email address");
                    valid = false;
                } else {
                    et_email.setError(null);
                }

                if (st_mobile.isEmpty() || st_mobile.length() != 10) {
                    et_mobile.setError("Enter Valid Mobile Number");
                    valid = false;
                } else {
                    et_mobile.setError(null);
                }
                return valid;
            }
        });
        bt_deleteuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    myDb.deletestudent(st_username);
                db.delete("Studentinfo","st_username=?",new String[]{st_username});
                db.delete("Forminfo","ap_stusername=?",new String[]{st_username});
                Intent  intent = new Intent(getApplicationContext(), admin_dashboard.class);
                Toast.makeText(getBaseContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();

            }
        });
    }
}


