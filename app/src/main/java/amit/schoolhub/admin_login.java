package amit.schoolhub;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin_login extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    DatabaseHelper myDb;

    Button bt_login;
    EditText et_username, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);




        bt_login = (Button) findViewById(R.id.bt_login);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                if (username.isEmpty()) {
                    et_username.setError("Enter a valid Username");
                } else {
                    et_password.setError(null);
                }

                if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                    et_password.setError("Between 4 and 10 alphanumeric characters");
                } else {
                    et_username.setError(null);
                }


                if(username.equals("amitgupta") && password.equals("amitgupta"))
                {
                    final ProgressDialog progressDialog = new ProgressDialog(admin_login.this,
                            R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();
                    Intent intent = new Intent(getApplicationContext(), admin_dashboard.class);
                    Toast.makeText(getBaseContext(), "Welcome Admin", Toast.LENGTH_LONG).show();
                    et_username.setText(null);
                    et_password.setText(null);
                    progressDialog.dismiss();
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_LONG).show();
                    et_username.setError("Enter a valid Username");
                    et_password.setError("Between 4 and 10 alphanumeric characters");
                    et_username.setText(null);
                    et_password.setText(null);
                }
            }
        });
        

    }

}