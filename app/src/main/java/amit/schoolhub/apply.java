package amit.schoolhub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class apply extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final CharSequence[] PASSEDSTD1 = {"Passed Std.:","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    Button bt_apply;
 public    String st_username,st_mobile,st_email;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    DatabaseHelper dbhelper;
    Cursor data;
    DatabaseHelper myDb;
    EditText et_lastschool,et_result;
    Spinner sp_passedstd,sp_school1,sp_school2,sp_school3,sp_school4,sp_school5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        Intent intent = getIntent();
        st_username=intent.getStringExtra("st_username");


       myDb=new DatabaseHelper(this);
        dbhelper=new DatabaseHelper(this);

        sp_passedstd = (Spinner) findViewById(R.id.sp_passedstd);

        bt_apply=(Button) findViewById(R.id.bt_apply);

        et_lastschool=(EditText) findViewById(R.id.et_lastschool);
        et_result=(EditText) findViewById(R.id.et_result);

        sp_school1 = (Spinner) findViewById(R.id.sp_school1);
        sp_school2 = (Spinner) findViewById(R.id.sp_school2);
        sp_school3 = (Spinner) findViewById(R.id.sp_school3);
        sp_school4 = (Spinner) findViewById(R.id.sp_school4);
        sp_school5 = (Spinner) findViewById(R.id.sp_school5);



        data= dbhelper.getDataSchool();

        List<String> listdata=new ArrayList<>();
        while (data.moveToNext()){
            listdata.add(data.getString(1));
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listdata);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_school1.setAdapter(dataAdapter);
        sp_school2.setAdapter(dataAdapter);
        sp_school3.setAdapter(dataAdapter);
        sp_school4.setAdapter(dataAdapter);
        sp_school5.setAdapter(dataAdapter);


        sp_passedstd = (Spinner) findViewById(R.id.sp_passedstd);
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter<CharSequence> (this, android.R.layout.simple_spinner_item, PASSEDSTD1);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Specify the layout to use when the list of choices appears
        sp_passedstd.setAdapter(adapter3); // Apply the adapter to the spinner

/*
        String st_passedstd=sp_passedstd.getSelectedItem().toString();
        String st_lastschoolname=et_lastschool.getText().toString();
        String st_lastyearresult=et_result.getText().toString();
        String st_school1=sp_school1.getSelectedItem().toString();
        String st_school2=sp_school2.getSelectedItem().toString();
        String st_school3=sp_school3.getSelectedItem().toString();
        String st_school4=sp_school4.getSelectedItem().toString();
        String st_school5=sp_school5.getSelectedItem().toString();
*/
        dbhelper=new DatabaseHelper(this);



        //ADDING DATA TO SCHOOL SPINNER
        sp_school1.setOnItemSelectedListener(this);




        bt_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apply();
                finish();
            }
        });



    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
   public void apply(){
       String st_passedstd=sp_passedstd.getSelectedItem().toString();
       String st_lastschoolname=et_lastschool.getText().toString();
       String st_lastyearresult=et_result.getText().toString();
       String st_school1=sp_school1.getSelectedItem().toString();
       String st_school2=sp_school2.getSelectedItem().toString();
       String st_school3=sp_school3.getSelectedItem().toString();
       String st_school4=sp_school4.getSelectedItem().toString();
       String st_school5=sp_school5.getSelectedItem().toString();

       if(validate()){
           myDb.addform(st_username,st_lastschoolname,st_lastyearresult,st_passedstd,st_school1,st_school2,st_school3,st_school4,st_school5);
           setResult(RESULT_OK, null);
           Toast.makeText(getBaseContext(), "Successfuly Applied", Toast.LENGTH_LONG).show();
           Intent intent = new Intent(getApplicationContext(), dashboard.class);
           startActivity(intent);
           finish();
       }
       else{
           Toast.makeText(getBaseContext(), "Couldn't Added", Toast.LENGTH_LONG).show();

       }





   }
    private boolean validate() {
        boolean valid = true;
        String st_passedstd=sp_passedstd.getSelectedItem().toString();
        String st_lastschoolname=et_lastschool.getText().toString();
        String st_lastyearresult=et_result.getText().toString();
        String st_school1=sp_school1.getSelectedItem().toString();
        String st_school2=sp_school2.getSelectedItem().toString();
        String st_school3=sp_school3.getSelectedItem().toString();
        String st_school4=sp_school4.getSelectedItem().toString();
        String st_school5=sp_school5.getSelectedItem().toString();

        if (st_lastschoolname.isEmpty() || st_lastschoolname.length() < 3) {
            et_lastschool.setError("At least 3 characters");
            valid = false;
        } else {
            et_lastschool.setError(null);
        }
        if (st_lastyearresult.isEmpty()) {
            et_result.setError("Enter percentage");
            valid = false;
        } else {
            et_result.setError(null);
        }
        return valid;

    }


}
