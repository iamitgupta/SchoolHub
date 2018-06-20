package amit.schoolhub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Schoolhub.db";

    //Student table
    public static final String TABLE_STUDENT="Studentinfo";
    public static final String STCOLUMN_2="st_username";
    public static final String STCOLUMN_3="st_password";
    public static final String STCOLUMN_4="st_name";
    public static final String STCOLUMN_5="st_city";
    public static final String STCOLUMN_6="st_mobile";
    public static final String STCOLUMN_7="st_email";


    //Admin table
    public static final String TABLE_ADMIN="Admininfo";
    public static final String ADCOLUMN_2="ad_username";
    public static final String ADCOLUMN_3="ad_password";


    //APPLYFORM TABLE
    public static final String TABLE_FORM="Forminfo";
    public static final String APCOLUMN_1="ap_id";
    public static final String APCOLUMN_2="ap_stusername";
    public static final String APCOLUMN_8="ap_lastschool";
    public static final String APCOLUMN_9="ap_result";
    public static final String APCOLUMN_12="ap_passedstd";
    public static final String APCOLUMN_13="ap_school1";
    public static final String APCOLUMN_14="ap_school2";
    public static final String APCOLUMN_15="ap_school3";
    public static final String APCOLUMN_16="ap_school4";
    public static final String APCOLUMN_17="ap_school5";

    //status info
    public static final String TABLE_ADMISSIONSTATUS="Admissionstatusinfo";
    public static final String ASCOLUMN_19="as_status1";
    public static final String ASCOLUMN_20="as_status2";
    public static final String ASCOLUMN_21="as_status3";
    public static final String ASCOLUMN_22="as_status4";
    public static final String ASCOLUMN_23="as_status5";
    public static final String ASCOLUMN_24="as_stusername";
    public static final String ASCOLUMN_25="as_status5";

    //School table
    public static final String TABLE_SCHOOL="Schoolinfo";
    public static final String SCCOLUMN_1="sc_id";
    public static final String SCCOLUMN_2="sc_name";
    public static final String SCCOLUMN_3="sc_address";
    public static final String SCCOLUMN_4="sc_description";
    public static final String SCCOLUMN_5="sc_mobile";
    public static final String SCCOLUMN_6="sc_email";


//status

    public static final String TABLE_STATUS="Statusinfo";
    //Feedback table
    public static final String TABLE_FEEDBACK="Feedbackinfo";
    public static final String FBCOLUMN_1="fb_id";
    public static final String FBCOLUMN_2="fb_stusername";
    public static final String FBCOLUMN_3="fb_starvalue";
    public static final String FBCOLUMN_4="fb_message";

    //Announcement table
    public static final String TABLE_ANNOUNCEMENT="Announcementinfo";
    public static final String ANCOLUMN_1="an_id";
    public static final String ANCOLUMN_2="an_message";

    int oldVersion=3;
    int newVersion=4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 4);
        SQLiteDatabase db=this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String student="CREATE TABLE Studentinfo(st_username VARCHAR PRIMARY KEY,st_password VARCHAR,st_name CHAR,st_email VARCHAR,st_mobile INTEGER,st_city VARCHAR)";
        String admin="CREATE TABLE Admininfo(ad_username VARCHAR PRIMARY KEY,st_password VARCHAR)";
        String school="CREATE TABLE Schoolinfo(sc_id INTEGER PRIMARY KEY AUTOINCREMENT,sc_name CHAR,sc_email VARCHAR,sc_mobile INTEGER,sc_address VARCHAR,sc_description VARCHAR)";
        String status="CREATE TABLE Statusinfo(st_username PRIMARY KEY,sc_name VARCHAR,sc_email VARCHAR,sc_mobile INTEGER,sc_address VARCHAR,sc_description VARCHAR)";


        String feedback="CREATE TABLE Feedbackinfo(fb_id INTEGER PRIMARY KEY AUTOINCREMENT,fb_stusername VARCHAR UNIQUE,fb_starvalue VARCHAR,fb_message VARCHAR)";
        String admissionstatus="CREATE TABLE Admissionstatusinfo(as_stusername VARCHAR PRIMARY KEY,status1 VARCHAR,status2 VARCHAR,status3 VARCHAR,status4 VARCHAR,status5 VARCHAR)";
        String apply="CREATE TABLE Forminfo(ap_stusername VARCHAR PRIMARY KEY,ap_lastschool VARCHAR,ap_passedstd VARCHAR,ap_result VARCHAR,ap_school1 VARCHAR,ap_school2 VARCHAR,ap_school3 VARCHAR,ap_school4 VARCHAR,ap_school5 VARCHAR)";


        db.execSQL(student);
        db.execSQL(admin);
        db.execSQL(school);
        db.execSQL(apply);
        db.execSQL(feedback);
        db.execSQL(admissionstatus);
        db.execSQL(status);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion<newVersion) {
            db.execSQL("drop table if exists " + TABLE_STUDENT);
            db.execSQL("drop table if exists " + TABLE_ADMIN);
            db.execSQL("drop table if exists " + TABLE_SCHOOL);

            db.execSQL("drop table if exists " + TABLE_FEEDBACK);
            db.execSQL("drop table if exists " + TABLE_ANNOUNCEMENT);
            db.execSQL("drop table if exists " + TABLE_ADMISSIONSTATUS);
            db.execSQL("drop table if exists " + TABLE_FORM);
            db.execSQL("drop table if exists " + TABLE_STATUS);
            onCreate(db);
        }
    }
    public boolean addstudent(String st_username,String st_password,String st_name,String st_city,String st_mobile,String st_email) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STCOLUMN_2, st_username);
        contentValues.put(STCOLUMN_3, st_password);
        contentValues.put(STCOLUMN_4, st_name);
        contentValues.put(STCOLUMN_5, st_city);
        contentValues.put(STCOLUMN_6, st_mobile);
        contentValues.put(STCOLUMN_7, st_email);
        long result=db.insert(TABLE_STUDENT,null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean updatestudent(String st_username,String st_name,String st_city,String st_mobile,String st_email){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(STCOLUMN_2,st_username);
        contentValues.put(STCOLUMN_4,st_name);
        contentValues.put(STCOLUMN_5,st_city);
        contentValues.put(STCOLUMN_6,st_mobile);
        contentValues.put(STCOLUMN_7,st_email);
        db.update("Studentinfo",contentValues,"st_username=?",new String[]{st_username});
        return true;
    }

    public boolean addschool(String sc_name,String sc_mobile,String sc_email,String sc_address,String sc_description){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(SCCOLUMN_2,sc_name);
        contentValues.put(SCCOLUMN_5,sc_mobile);
        contentValues.put(SCCOLUMN_6,sc_email);
        contentValues.put(SCCOLUMN_3,sc_address);
        contentValues.put(SCCOLUMN_4,sc_description);
        long result=db.insert(TABLE_SCHOOL,null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean addstatus(String st_username,String sc_name,String sc_email,String sc_mobile,String sc_address,String sc_description){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(STCOLUMN_2, st_username);
        contentValues.put(SCCOLUMN_2,sc_name);
        contentValues.put(SCCOLUMN_6,sc_email);
        contentValues.put(SCCOLUMN_5,sc_mobile);
        contentValues.put(SCCOLUMN_3,sc_address);
        contentValues.put(SCCOLUMN_4,sc_description);
        long result=db.insert(TABLE_STATUS,null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean addadmissionstatus(String st_username,String status1,String status2,String status3,String status4,String status5){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put( STCOLUMN_2,st_username);
        contentValues.put(ASCOLUMN_19,status1);
        contentValues.put(ASCOLUMN_20,status2);
        contentValues.put(ASCOLUMN_21,status3);
        contentValues.put(ASCOLUMN_22,status4);
        contentValues.put(ASCOLUMN_23,status5);
        long result=db.insert(TABLE_ADMISSIONSTATUS,null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean updateschool(String sc_id,String sc_name,String sc_mobile,String sc_email,String sc_address,String sc_description){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(SCCOLUMN_1,sc_id);
        contentValues.put(SCCOLUMN_2,sc_name);
        contentValues.put(SCCOLUMN_5,sc_mobile);
        contentValues.put(SCCOLUMN_6,sc_email);
        contentValues.put(SCCOLUMN_3,sc_address);
        contentValues.put(SCCOLUMN_4,sc_description);
        db.update("Schoolinfo",contentValues,"sc_id=?",new String[]{sc_id});
        return true;
    }

    public boolean addform(String ap_stusername,String ap_lastschool,String ap_result,String ap_passedstd,String ap_school1,String ap_school2,String ap_school3,String ap_school4,String ap_school5){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(APCOLUMN_2,ap_stusername);
        contentValues.put(APCOLUMN_8,ap_lastschool);
        contentValues.put(APCOLUMN_9,ap_result);
        contentValues.put(APCOLUMN_12,ap_passedstd);
        contentValues.put(APCOLUMN_13,ap_school1);
        contentValues.put(APCOLUMN_14,ap_school2);
        contentValues.put(APCOLUMN_15,ap_school3);
        contentValues.put(APCOLUMN_16,ap_school4);
        contentValues.put(APCOLUMN_17,ap_school5);
        long result=db.insert(TABLE_FORM,null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean addfeedback(String fb_stusername,String fb_starvalue,String fb_message){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FBCOLUMN_2,fb_stusername);
        contentValues.put(FBCOLUMN_3,fb_starvalue);
        contentValues.put(FBCOLUMN_4,fb_message);
        long result=db.insert(TABLE_FEEDBACK,null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean addannouncement(String an_message){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ANCOLUMN_2,an_message);
        long result=db.insert(TABLE_ANNOUNCEMENT,null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean addadmin(String ad_username,String ad_password){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ANCOLUMN_2,ad_username);
        contentValues.put(ANCOLUMN_2,ad_password);
        long result=db.insert(TABLE_ADMIN,null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public void deletestudent(String username){
        SQLiteDatabase db=this.getWritableDatabase();
       db.delete(TABLE_STUDENT,"st_username=?",new String[]{username});
    }

    public Integer deleteschool(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Schoolinfo","sc_id=?",new String[]{id});
    }
    public Integer deleteform(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("Forminfo","st_username=?",new String[]{id});
    }
    public Cursor getDataSchool(){
       SQLiteDatabase db = this.getWritableDatabase();
        String Query1="SELECT * FROM " + TABLE_SCHOOL;
        Cursor data1=db.rawQuery(Query1,null);
        return data1;

    }
    public Cursor getDataStudent(){
        SQLiteDatabase db = this.getWritableDatabase();
        String Query2="SELECT * FROM " + TABLE_STUDENT;
        Cursor data2=db.rawQuery(Query2,null);
        return data2;

    }
    public Cursor getFeedback(){
        SQLiteDatabase db = this.getWritableDatabase();
        String Query2="SELECT * FROM " + TABLE_FEEDBACK;
        Cursor data2=db.rawQuery(Query2,null);
        return data2;

    }
    public Cursor getForm(){
        SQLiteDatabase db = this.getWritableDatabase();
        String Query3="SELECT * FROM " + TABLE_FORM;
        Cursor data3=db.rawQuery(Query3,null);
        return data3;

    }
}
