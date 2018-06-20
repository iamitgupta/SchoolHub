package amit.schoolhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class feedback extends AppCompatActivity {
    RatingBar mRatingBar;
    TextView mRatingScale;
    Button bt_sendfeedback;
    EditText et_yourfeedback;
    DatabaseHelper myDb;
  public   String st_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Intent intent = getIntent();
        st_username=intent.getStringExtra("st_username");
        myDb=new DatabaseHelper(this);
        mRatingBar = (RatingBar) findViewById(R.id.rb_value);
        mRatingScale = (TextView) findViewById(R.id.tv_scale);
        bt_sendfeedback=(Button) findViewById(R.id.bt_sendfeedback);
        et_yourfeedback=(EditText) findViewById(R.id.et_yourfeedback);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });
        bt_sendfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String srvalue=(String) String.valueOf(mRatingBar.getRating());
                String yourfeedback=(String)  et_yourfeedback.getText().toString();
                if (yourfeedback.isEmpty() || yourfeedback.length() < 10)

                {
                    et_yourfeedback.setError("At least 10 characters");
                }
                else{

                        myDb.addfeedback(st_username,srvalue,yourfeedback);
                    Intent intent = new Intent(getApplicationContext(), dashboard.class);
                    Toast.makeText(getApplicationContext(),"Submitted Successfully",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    finish();

                    }
        }

        });
    }
}