package se.wibi.wbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper implements DbStatics{
	SQLiteDatabase db;
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		//Creates the database with tabels, keys and idns
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUESTION
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                + KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT," +KEY_OPTD+" TEXT, " + KEY_CATHEGORYS + " TEXT)");        
        addQuestions();
	}
	 private void addQuestions(){
		 
		 //Should get questions from XML file.
		Questions q1=new Questions("Vad är ett LAN?",
				"En samling ihopkopplade datorer inom ett visst begränsat område",
				"När man kopplar ihop datorer mellan städer",
				"När man kopplar sin dator till internet",
				"När man spelar med sina kompisar", 
				"Kapitel1", 
				"A");
		Questions q2=new Questions("Vad är ett LAN?",
				"När man kopplar ihop datorer mellan städer",
				"En samling ihopkopplade datorer inom ett visst begränsat område",
				"När man kopplar sin dator till internet",
				"När man spelar med sina kompisar", 
				"Kapitel1", 
				"B");
	 	this.addQuestion(q1);
	 	this.addQuestion(q2);
	 	
	 }
	 // Adding new question
	 public void addQuestion(Questions questions) {
		 ContentValues values = new ContentValues();
	     values.put(KEY_QUESTION, questions.getquestion());
	     values.put(KEY_ANSWER, questions.getanswer());
	     values.put(KEY_OPTA, questions.getoptA());
	     values.put(KEY_OPTB, questions.getoptB());
	     values.put(KEY_OPTC, questions.getoptC());
	     values.put(KEY_OPTD, questions.getoptD());
	     values.put(KEY_CATHEGORYS, questions.getcathegory());
	     // Inserting Row
	     db.insert(TABLE_QUEST, null, values);
	 }
	 
	 public List<Questions> getAllQuestions() {
	        List<Questions> quesList = new ArrayList<Questions>();
	        // Select All Query
	        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
	        Cursor cursor = db.rawQuery(selectQuery, null);
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	                Questions quest = new Questions();
	                quest.setid(cursor.getInt(0));
	                quest.setquestion(cursor.getString(1));
	                quest.setanswer(cursor.getString(2));
	                quest.setoptA(cursor.getString(3));
	                quest.setoptB(cursor.getString(4));
	                quest.setoptC(cursor.getString(5));
	                quest.setoptD(cursor.getString(6));
	                quest.setcathegory(cursor.getString(7));
	                quesList.add(quest);
	            } while (cursor.moveToNext());
	        }
	        if(cursor != null && !cursor.isClosed())
	            cursor.close();
	        // return quest list
	        return quesList;
	    }
	 public List<Questions> getQuestionsByCathegory(String cathegory) {
	        List<Questions> quesList = new ArrayList<Questions>();
	        // Select All Query

	        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUEST + " WHERE " + KEY_CATHEGORYS + "='"+ cathegory+ "'", null);
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	                Questions quest = new Questions();
	                quest.setid(cursor.getInt(0));
	                quest.setquestion(cursor.getString(1));
	                quest.setanswer(cursor.getString(2));
	                quest.setoptA(cursor.getString(3));
	                quest.setoptB(cursor.getString(4));
	                quest.setoptC(cursor.getString(5));
	                quest.setoptD(cursor.getString(6));
	                quest.setcathegory(cursor.getString(7));
	                quesList.add(quest);
	            } while (cursor.moveToNext());
	        }
	        if(cursor != null && !cursor.isClosed())
	            cursor.close();
	        // return quest list
	        return quesList;
	    }
	public Questions getQuestionsByID(int id) {
		Questions quest = new Questions();
        Cursor cursor = db.query(TABLE_QUEST, new String[] { KEY_ID, KEY_ANSWER, KEY_OPTA,KEY_OPTB,KEY_OPTC,KEY_OPTD,KEY_CATHEGORYS }, KEY_ID + "=?",
		     new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		quest.setid(cursor.getInt(0));
        quest.setquestion(cursor.getString(1));
        quest.setanswer(cursor.getString(2));
        quest.setoptA(cursor.getString(3));
        quest.setoptB(cursor.getString(4));
        quest.setoptC(cursor.getString(5));
        quest.setoptD(cursor.getString(6));
        quest.setcathegory(cursor.getString(7));
        if(cursor != null && !cursor.isClosed())
            cursor.close();
		return quest;
		
	 }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
		
	}

}
