package se.wibi.wbook;

public interface DbStatics {
	static final int DATABASE_VERSION = 1;
    // Database Name
    static final String DATABASE_NAME = "quiz";
    // tasks table name
    static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    static final String KEY_ID = "id";
    static final String KEY_QUESTION = "question";
    static final String KEY_ANSWER = "answer"; //correct option
    static final String KEY_OPTA= "opta"; //option a
    static final String KEY_OPTB= "optb"; //option b
    static final String KEY_OPTC= "optc"; //option c
    static final String KEY_OPTD= "optd"; //option d
    static final String KEY_CATHEGORYS= "cathegorys"; //option c
    
	
}
