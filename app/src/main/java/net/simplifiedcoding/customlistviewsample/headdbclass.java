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
	public static final String Answer_ = "Sher";
	public static final String sher_COLUMN_date = "entry";
	public static final String sher_table_Name = "table_sher";
	public static final String sher_table_fav = "fav";
	public static final String sher_table_most = "most";
	// String question=null;
	private HashMap hp;
	ContentValues contentValues;

	public headdbclass(Context context) {
		super(context, DATABASE_NAME, null, 1);

	}



	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		/*
		 * Log.d(ProjecName, ProjecName); db.execSQL("create table contacts " +
		 * "(id integer primary key, name text,phone text,email text, street text,place text)"
		 * );
		 */
		/*
		 * db.execSQL("CREATE TABLE IF NOT EXISTS  " + ProjecName +
		 * " (LastName VARCHAR, FirstName VARCHAR," +
		 * " Country VARCHAR, Age INT(3));");
		 */

	}

	
	

	public void languagedelete() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS language");
		// db.execSQL("DROP TABLE language;");
		db.execSQL("CREATE TABLE `language` (`name`	TEXT)");

	}

	public void CreatetableFornewPRoect(String project_name) {
		SQLiteDatabase db = this.getWritableDatabase();
		// Log.d(CONTACTS_COLUMN_CName, ProjecName);
		db.execSQL("CREATE TABLE IF NOT EXISTS ProjectNo" + project_name
				+ "(`Sno` INTEGER, `Sname` TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS contacts");
		onCreate(db);
	}

	public boolean insertContact(String name, String phone, String email,
			String street, String place) {
		/*
		 * SQLiteDatabase db = this.getWritableDatabase(); ContentValues
		 * contentValues = new ContentValues();
		 * 
		 * contentValues.put("name", name); contentValues.put("phone", phone);
		 * contentValues.put("email", email); contentValues.put("street",
		 * street); contentValues.put("place", place);
		 * 
		 * db.insert("contacts", null, contentValues);
		 */
		return true;
	}

	public Cursor getData(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db
				.rawQuery("select * from head where id=" + id + "", null);
		return res;
	}

	

	public boolean updateContact(Integer id, String name, String phone,
			String email, String street, String place) {
		/*
		 * SQLiteDatabase db = this.getWritableDatabase(); ContentValues
		 * contentValues = new ContentValues(); contentValues.put("name", name);
		 * contentValues.put("phone", phone); contentValues.put("email", email);
		 * contentValues.put("street", street); contentValues.put("place",
		 * place); db.update("contacts", contentValues, "id = ? ", new String[]
		 * { Integer.toString(id) } );
		 */
		return true;
	}

	public Integer deleteContact(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("contacts", "id = ? ",
				new String[] { Integer.toString(id) });
	}



	

	public void updatefav(String settextvallue) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		
		//ContentValues cv = new ContentValues();
		//cv.put("fav","1"); //These Fields should be your String values of actual column names
		
		//db.update(sher_table_Name, cv, "Sno "+"="+settextvallue, null);
		db.execSQL("UPDATE table_sher set `fav`='1' WHERE `Sher`='"+settextvallue+"'");
		
		//Cursor res = db.rawQuery("UPDATE table_sher SET `fav` = '1' WHERE `Sno` = '"+count+"'", null);
		
	}




	public void createfav(String settextvallue) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		//Cursor res=db.rawQuery("INSERT into `fav` (`Sher`) Values('"+settextvallue+"')", null);
		

		db.execSQL("INSERT into `fav` (`Sher`) Values('"+settextvallue+"')");
	}

	public void deletefav(String settextvallue) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		//Cursor res=db.rawQuery("INSERT into `fav` (`Sher`) Values('"+settextvallue+"')", null);
		

		db.execSQL("DELETE FROM `fav` WHERE sher = '"+settextvallue+"'");
	}

	public void createsecondtable() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("CREATE TABLE `open` (`Sno`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,`Value`	INTEGER)");
		db.execSQL("INSERT into `Open` (`value`) values(1)");
		
		
	}

	public ArrayList<Mode> FetchBasicQuestion() {
		// TODO Auto-generated method stub
		ArrayList<Mode> BaseQuestionArray = new ArrayList<Mode>();
		//List<String> listDatafav = new ArrayList<String>();
		// hp = new HashMap();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("SELECT * FROM QuestionTable Where `Question_id`='0'", null);
		res.moveToFirst();

		if(res.getString(res.getColumnIndex("Question_Type")).equalsIgnoreCase("text"))
		{
			e=MessageType.Text;
		}
		while (res.isAfterLast() == false) {
			Log.d("question",res.getString(res.getColumnIndex("Question")));
			BaseQuestionArray.add(new Mode(res.getString(res.getColumnIndex("Answer_Option")), e, R.drawable.cnc, 1, "", AnswerType.Image));
			res.moveToNext();




		}


		//myLibrary.add(new Mode("Do you Like To Eat", MessageType.Text, R.drawable.cnc));
		return BaseQuestionArray;
	
	
	
	}

	MessageType e;
	public ArrayList<Mode> FetchBasicQuestionAnswer() {
		// TODO Auto-generated method stub
		ArrayList<Mode> BaseQuestionArray = new ArrayList<Mode>();
		//List<String> listDatafav = new ArrayList<String>();
		// hp = new HashMap();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("SELECT * FROM QuestionTable Where `Question_id`='0'", null);
		res.moveToFirst();
		while (res.isAfterLast() == false) {
			Log.d("question", res.getString(res.getColumnIndex("Question")));


			BaseQuestionArray.add(new Mode(res.getString(res.getColumnIndex("Answer_Option")),e,R.drawable.cnc,1,"",AnswerType.Image));
			res.moveToNext();




		}


		//myLibrary.add(new Mode("Do you Like To Eat", MessageType.Text, R.drawable.cnc));
		return BaseQuestionArray;



	}




	


	




	



	

	

	
	
	

}