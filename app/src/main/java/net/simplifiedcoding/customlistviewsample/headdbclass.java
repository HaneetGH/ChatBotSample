package net.simplifiedcoding.customlistviewsample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class headdbclass extends SQLiteOpenHelper {
    // public String ProjecName;
    public static final String DATABASE_NAME = "deivce_db";

    public static final String Question_Table = "QuestionTable";

    public static final String Answer_Reply = "Answer_Reply";


    public static final String QAE = "QAE";

    // String question=null;
    private HashMap hp;

    ContentValues contentValues;


    public headdbclass(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        onCreate(db);
    }

	
    public ArrayList<Mode> FetchBasicQuestion() {
        // TODO Auto-generated method stub
        ArrayList<Mode> BaseQuestionArray = new ArrayList<Mode>();
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM QuestionTable Where `Question_id`='0'", null);

        res.moveToFirst();

        if (res.getString(res.getColumnIndex("Question_Type")).equalsIgnoreCase("text")) {
            e = MessageType.Text;
        }
        while (res.isAfterLast() == false) {
            //Log.d("question", res.getString(res.getColumnIndex("Question")));

            BaseQuestionArray.add(new Mode(res.getString(res.getColumnIndex("Question")), e, R.drawable.cnc, Integer.parseInt(res.getString(res.getColumnIndex("Question_id"))), res.getString(res.getColumnIndex("Answer_Option")), AnswerType.Image,""));
            res.moveToNext();


        }


        
        return BaseQuestionArray;


    }

    MessageType e;


    public ArrayList<String> FetchNextQuestionId(int questionId, String s) {


        ArrayList<String> NextQuestionArray = new ArrayList<String>();
        
        SQLiteDatabase db = this.getReadableDatabase();
        //Log.d("what", questionId + "" + s);

        Cursor res = db.rawQuery("SELECT * FROM Answer_Reply Where Question_id =" + questionId + " AND Answer ='" + s + "'", null);

        //SELECT * FROM Answer_Reply Where Question_id = 0 AND Answer = 'Yes'
        //Log.d("question-", res.getCount() + "");
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            //Log.d("question", res.getString(res.getColumnIndex("ansString")));


            NextQuestionArray.add(res.getString(res.getColumnIndex("ansString")));

            NextQuestionArray.add(res.getString(res.getColumnIndex("Next_Qid")));

            res.moveToNext();


        }
        return NextQuestionArray;

    }

    public ArrayList<Mode> FetchNextQuestionFromBaseTable(String qid) {

        ArrayList<Mode> NextQuestionArray = new ArrayList<Mode>();


        SQLiteDatabase db = this.getReadableDatabase();

        //Log.d("question_id", qid + "");

        Cursor res = db.rawQuery("SELECT * FROM QuestionTable Where Question_id = " + qid + "", null);

       
        res.moveToFirst();
        
        if (res.getString(res.getColumnIndex("Question_Type")).equalsIgnoreCase("text"))
        {
            
            e = MessageType.Text;
            
        }
        while (res.isAfterLast() == false) {
            //Log.d("question_id", res.getString(res.getColumnIndex("Question_id")));

            NextQuestionArray.add(new Mode(res.getString(res.getColumnIndex("Question")), e, R.drawable.cnc, Integer.parseInt(res.getString(res.getColumnIndex("Question_id"))), res.getString(res.getColumnIndex("Answer_Option")), AnswerType.Image,""));
            
            res.moveToNext();


        }

        //Log.d("size", NextQuestionArray.size() + " ");
        
        return NextQuestionArray;
    }

    public void EnterQuestionAnswerToDb(int questionId, String s) {

        SQLiteDatabase db = this.getWritableDatabase();

        //Cursor res = db.execSQL("INSERT into `Answer_Table` (`Question_id`,`Answer_By_user`) Values('" + questionId + "','" + s + "')");
        db.execSQL("INSERT into `Answer_Table` (`Question_id`,`Answer_By_user`) Values('" + questionId + "','" + s + "')");
    }

    public ArrayList<ansModel> FetchALLOldQuestionsIds() {

        ArrayList<ansModel> NextQuestionArray = new ArrayList<ansModel>();


        SQLiteDatabase db = this.getReadableDatabase();


        Cursor res = db.rawQuery("SELECT * FROM Answer_Table", null);

        //SELECT * FROM Answer_Reply Where Question_id = 0 AND Answer = 'Yes'
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            //Log.d("question_id", res.getString(res.getColumnIndex("Question_id")));
            NextQuestionArray.add(new ansModel(Integer.parseInt(res.getString(res.getColumnIndex("Question_id"))), res.getString(res.getColumnIndex("Answer_By_user"))));
            res.moveToNext();


        }

        //Log.d("size", NextQuestionArray.size() + " ");
        return NextQuestionArray;
    }


    public ArrayList<Mode> FetchOldQuestionWithQid(int questionId) {

        ArrayList<Mode> NextQuestionArray = new ArrayList<Mode>();


        SQLiteDatabase db = this.getReadableDatabase();

        ////Log.d("question_id", qid + "");
        Cursor res = db.rawQuery("SELECT * FROM QuestionTable Where Question_id = " + questionId + "", null);

        //SELECT * FROM Answer_Reply Where Question_id = 0 AND Answer = 'Yes'
        res.moveToFirst();
        if (res.getString(res.getColumnIndex("Question_Type")).equalsIgnoreCase("text")) {
            e = MessageType.Text;
        }
        while (res.isAfterLast() == false) {
            //Log.d("question_id", res.getString(res.getColumnIndex("Question_id")));
            NextQuestionArray.add(new Mode(res.getString(res.getColumnIndex("Question")), e, R.drawable.cnc, Integer.parseInt(res.getString(res.getColumnIndex("Question_id"))), res.getString(res.getColumnIndex("Answer_Option")), AnswerType.Image,""));
            res.moveToNext();


        }

        //Log.d("size", NextQuestionArray.size() + " ");
        return NextQuestionArray;
    }

    public ArrayList<Mode> FetchNewQuestion(int i) {

        ArrayList<Mode> NextQuestionArray = new ArrayList<Mode>();


        SQLiteDatabase db = this.getReadableDatabase();

        ////Log.d("question_id", qid + "");
        Cursor res = db.rawQuery("SELECT * FROM QuestionTable Where Question_id = " + i + "", null);

        //SELECT * FROM Answer_Reply Where Question_id = 0 AND Answer = 'Yes'
        res.moveToFirst();
        if (res.getString(res.getColumnIndex("Question_Type")).equalsIgnoreCase("text")) {
            e = MessageType.Text;
        }
        while (res.isAfterLast() == false) {
            //Log.d("question_id", res.getString(res.getColumnIndex("Question_id")));

            NextQuestionArray.add(new Mode(res.getString(res.getColumnIndex("Question")), e, R.drawable.cnc, Integer.parseInt(res.getString(res.getColumnIndex("Question_id"))), res.getString(res.getColumnIndex("Answer_Option")), AnswerType.Image,""));
            res.moveToNext();


        }

        //Log.d("size", NextQuestionArray.size() + " ");
        return NextQuestionArray;
    }
}