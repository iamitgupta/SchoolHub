package amit.schoolhub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private Button bt_login, bt_register;
    EditText et_username, et_password;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    private static final String TAG = "LoginActivity";

    public String st_name, st_city, st_mobile, st_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();

        bt_register = (Button) findViewById(R.id.bt_register);
        bt_login = (Button) findViewById(R.id.bt_login);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

     //  db.execSQL("CREATE TABLE Statusinfo(st_username PRIMARY KEY,sc_name VARCHAR,sc_email VARCHAR,sc_mobile INTEGER,sc_address VARCHAR,sc_description VARCHAR)");

      //  db.execSQL("CREATE TABLE Forminfo(ap_stusername VARCHAR PRIMARY KEY,ap_lastschool VARCHAR,ap_passedstd VARCHAR,ap_result VARCHAR,ap_school1 VARCHAR,ap_school2 VARCHAR,ap_school3 VARCHAR,ap_school4 VARCHAR,ap_school5 VARCHAR)");
      //  db.execSQL("drop table if exists Statusinfo");

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, register.class);
                startActivity(i);

            }
        });


        bt_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(login.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();


                String st_username = et_username.getText().toString();
                String st_password = et_password.getText().toString();
                cursor = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_STUDENT+" WHERE "+DatabaseHelper.STCOLUMN_2+" =? AND "+DatabaseHelper.STCOLUMN_3+" =?", new String[]{st_username, st_password});


                if(st_username.isEmpty()) {
                    et_username.setError("Enter a valid Username address");
                } else {
                    et_username.setError(null);
                }

                if (st_password.isEmpty() || st_password.length() < 4 || st_password.length() > 10) {
                    et_password.setError("Between 4 and 10 alphanumeric characters");
                } else {
                    et_username.setError(null);
                }

                if (cursor != null) {
                    if (cursor.getCount() > 0) {

                        cursor.moveToFirst();
                        st_username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.STCOLUMN_2));
                        st_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.STCOLUMN_4));
                        st_city = cursor.getString(cursor.getColumnIndex(DatabaseHelper.STCOLUMN_5));
                        st_mobile = cursor.getString(cursor.getColumnIndex(DatabaseHelper.STCOLUMN_6));
                        st_email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.STCOLUMN_7));
                        Toast.makeText(getBaseContext(), "Welcome " +st_name, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(login.this, dashboard.class);
                        intent.putExtra("st_username",st_username);
                        intent.putExtra("st_name",st_name);
                        intent.putExtra("st_city",st_city);
                        intent.putExtra("st_mobile",st_mobile);
                        intent.putExtra("st_email",st_email);

                        progressDialog.dismiss();
                        startActivity(intent);

                        finish();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                    }
                }

            }

        });
    }
}
