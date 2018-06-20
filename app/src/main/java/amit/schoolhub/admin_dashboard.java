package amit.schoolhub;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class admin_dashboard extends AppCompatActivity {
    ImageButton editschool,editstudent,viewadmission,viewfeedback,announcement,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        editschool=(ImageButton) findViewById(R.id.bt_editschool);
        editstudent=(ImageButton) findViewById(R.id.bt_editstudent);
        viewadmission=(ImageButton) findViewById(R.id.bt_viewadmission);
        viewfeedback=(ImageButton) findViewById(R.id.bt_viewfeedback);
       // announcement=(ImageButton) findViewById(R.id.bt_announcement);
        logout=(ImageButton) findViewById(R.id.bt_logout);


        //intent for editschool
        editschool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_dashboard.this,edit_school.class);
                startActivity(i);


            }
        });
        //intent for editstudent
        editstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_dashboard.this,edit_student.class);
                startActivity(i);

            }
        });
        //intent for viewadmission
        viewadmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_dashboard.this,search_admission.class);
                startActivity(i);

            }
        });
        //intent for feedback
        viewfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_dashboard.this,view_feedback.class);
                startActivity(i);

            }
        });
        //intent for announcement
/*        announcement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin_dashboard.this,announcement.class);
                startActivity(i);

            }
        });
*/
        //logout dialog
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(admin_dashboard.this);
                dialog.setCancelable(false);
                dialog.setTitle(getString(R.string.logout));
                dialog.setMessage(getString(R.string.logoutmessage ));
                dialog.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                        Intent intent=new Intent(admin_dashboard.this,admin_sudent.class);
                        startActivity(intent);
                        finish();
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

