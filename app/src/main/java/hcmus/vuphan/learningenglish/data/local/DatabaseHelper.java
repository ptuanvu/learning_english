package hcmus.vuphan.learningenglish.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vuphan on 05/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "LearningEnglish.db";
    public static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.Lesson.getLessonCreateQuery());
        db.execSQL(DatabaseContract.Question.getQuestionCreateQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.Lesson.getLessonDeleteQuery());
        db.execSQL(DatabaseContract.Question.getQuestionDeleteQuery());
        onCreate(db);
    }
}
