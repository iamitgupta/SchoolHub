package amit.schoolhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_sudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sudent);


        Button bt_admin=(Button)findViewById(R.id.bt_admin);
        Button bt_student=(Button)findViewById(R.id.bt_student);

        bt_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_sudent.this,admin_login.class);
                startActivity(i);
                finish();
            }
        });
        bt_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_sudent.this,login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
