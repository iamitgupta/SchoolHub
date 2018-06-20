package amit.schoolhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class announcement extends AppCompatActivity {

    EditText et_announcement;
    Button bt_submit;
    String announcement;
    DatabaseHelper myDb;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        myDb=new DatabaseHelper(this);


        et_announcement=(EditText) findViewById(R.id.et_announcement);
        bt_submit=(Button) findViewById(R.id.bt_register);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (announcement.isEmpty()) {
                    et_announcement.setError("Announcement message can not be empty");
                } else {
                    et_announcement.setError(null);
                }
                myDb.addannouncement(announcement);

                bt_submit.setEnabled(true);
                setResult(RESULT_OK, null);
                Toast.makeText(getBaseContext(), "Submitted Successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),admin_dashboard.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
