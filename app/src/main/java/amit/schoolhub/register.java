package amit.schoolhub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText et_studentsname,et_city,et_mobile,et_email,et_username,et_password,et_confirmpassword;
    Button bt_register;
    String studentsname,city,mobile,email,username,password,confirmpassword;
    DatabaseHelper myDb;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb=new DatabaseHelper(this);


        et_studentsname=(EditText) findViewById(R.id.et_studentsname);
        et_city=(EditText) findViewById(R.id.et_city);
        et_mobile=(EditText) findViewById(R.id.et_mobile);
        et_email=(EditText) findViewById(R.id.et_email);
        et_username=(EditText) findViewById(R.id.et_usernamereg);
        et_password=(EditText) findViewById(R.id.et_passwordreg);
        et_confirmpassword=(EditText) findViewById(R.id.et_confirmpasswordreg);
        bt_register=(Button) findViewById(R.id.bt_register);


        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
                finish();
            }
        });
    }


    public void register() {
            Log.d(TAG, "Signup");

            if (!validate()) {
                onSignupFailed();
                return;
            }

            bt_register.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(register.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();


            // TODO: Implement your own signup logic here.

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onSignupSuccess or onSignupFailed
                            // depending on success
                            onSignupSuccess();
                            // onSignupFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }


        public void onSignupSuccess() {

            String studentsname = et_studentsname.getText().toString();
            String city = et_city.getText().toString();
            String email = et_email.getText().toString();
            String mobile = et_mobile.getText().toString();
            String password = et_password.getText().toString();
            String username=et_username.getText().toString();

            myDb.addstudent(username,password,studentsname,city,mobile,email);

            bt_register.setEnabled(true);
            setResult(RESULT_OK, null);
            Toast.makeText(getBaseContext(), "Registration Successful", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }

        public void onSignupFailed() {
            Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_LONG).show();

            bt_register.setEnabled(true);
        }

        public boolean validate() {
            boolean valid = true;

            String studentsname = et_studentsname.getText().toString();
            String city = et_city.getText().toString();
            String email = et_email.getText().toString();
            String mobile = et_mobile.getText().toString();
            String password = et_password.getText().toString();
            String reEnterPassword = et_confirmpassword.getText().toString();
            String username=et_username.getText().toString();

            if (studentsname.isEmpty() || studentsname.length() < 3) {
                et_studentsname.setError("At least 3 characters");
                valid = false;
            } else {
                et_studentsname.setError(null);
            }

            if (username.isEmpty()) {
                et_username.setError("UserId cannot be empty");
                valid = false;
            } else {
                et_username.setError(null);
            }

            if (city.isEmpty()) {
                et_city.setError("Enter Valid City");
                valid = false;
            } else {
                et_city.setError(null);
            }



            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                et_email.setError("Enter a valid email address");
                valid = false;
            } else {
                et_email.setError(null);
            }

            if (mobile.isEmpty() || mobile.length()!=10) {
                et_mobile.setError("Enter Valid Mobile Number");
                valid = false;
            } else {
                et_mobile.setError(null);
            }

            if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                et_password.setError("Between 4 and 10 alphanumeric characters");
                valid = false;
            } else {
                et_password.setError(null);
            }

            if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
                et_confirmpassword.setError("Password Do not match");
                valid = false;
            } else {
                et_confirmpassword.setError(null);
            }

            return valid;
        }

}