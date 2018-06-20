package amit.schoolhub;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class dashboard extends AppCompatActivity {
        ImageButton apply,searchscool,checkstatus,profile,feedback,logout;
   public String st_username,st_name,st_city,st_mobile,st_email;
    private String activeuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
       apply=(ImageButton) findViewById(R.id.bt_apply);
        searchscool=(ImageButton) findViewById(R.id.bt_searchschool);
        checkstatus=(ImageButton) findViewById(R.id.bt_checkstatus);
        profile=(ImageButton) findViewById(R.id.bt_profile);
        feedback=(ImageButton) findViewById(R.id.bt_feedback);
        logout=(ImageButton) findViewById(R.id.bt_logout);

        Intent intent=getIntent();
        st_username=intent.getStringExtra("st_username");
        st_name=intent.getStringExtra("st_name");
        st_city=intent.getStringExtra("st_city");
        st_mobile=intent.getStringExtra("st_mobile");
        st_email=intent.getStringExtra("st_email");
        //intent for aplly
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,apply.class);
                intent.putExtra("st_username",st_username);
                intent.putExtra("st_name",st_name);
                intent.putExtra("st_city",st_city);
                intent.putExtra("st_mobile",st_mobile);
                intent.putExtra("st_email",st_email);
                startActivity(intent);

            }
        });
        //intent for searchschool
        searchscool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,search.class);
                startActivity(intent);

            }
        });
        //intent for checkstatus
        checkstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,status.class);
               // Toast.makeText(getBaseContext(), "Welcome " +st_city, Toast.LENGTH_LONG).show();
                intent.putExtra("st_username",st_username);
                intent.putExtra("st_name",st_name);
                intent.putExtra("st_city",st_city);
                intent.putExtra("st_mobile",st_mobile);
                intent.putExtra("st_email",st_email);
                startActivity(intent);


            }
        });
        //intent for profile
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,profile.class);
                intent.putExtra("st_username",st_username);
                intent.putExtra("st_name",st_name);
                intent.putExtra("st_city",st_city);
                intent.putExtra("st_mobile",st_mobile);
                intent.putExtra("st_email",st_email);
                startActivity(intent);
               // Toast.makeText(getBaseContext(), "Welcome " +st_city, Toast.LENGTH_LONG).show();

            }
        });
        //intent for feedback
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,feedback.class);
                intent.putExtra("st_username",st_username);
                intent.putExtra("st_name",st_name);
                intent.putExtra("st_city",st_city);
                intent.putExtra("st_mobile",st_mobile);
                intent.putExtra("st_email",st_email);
                startActivity(intent);

            }
        });

        //logout dialog
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(dashboard.this);
                dialog.setCancelable(false);
                dialog.setTitle(getString(R.string.logout));
                dialog.setMessage(getString(R.string.logoutmessage ));
                dialog.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                        Intent intent=new Intent(dashboard.this,admin_sudent.class);
                        startActivity(intent);

                    }
                })
                        .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();

            }
        });



    }
}
