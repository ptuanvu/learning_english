package hcmus.vuphan.learningenglish.data.local;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by vuphan on 05/11/2016.
 */

public class Provider extends ContentProvider {

    private static final int LESSON_ITEM = 100;
    private static final int LESSON_DIR = 101;
    private static final int QUEST_DIR = 102;
    private static final int QUEST_ITEM = 103;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DatabaseHelper mDbHelper;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DatabaseContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, DatabaseContract.PATH_LESSON + "/#", LESSON_ITEM);
        matcher.addURI(authority, DatabaseContract.PATH_LESSON, LESSON_DIR);
        matcher.addURI(authority, DatabaseContract.PATH_QUESTION + "/#", QUEST_ITEM);
        matcher.addURI(authority, DatabaseContract.PATH_QUESTION, QUEST_DIR);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case LESSON_DIR:
            case LESSON_ITEM:
                retCursor = mDbHelper.getReadableDatabase().query(
                        DatabaseContract.Lesson.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case QUEST_DIR:
            case QUEST_ITEM:
                retCursor = mDbHelper.getReadableDatabase().query(
                        DatabaseContract.Question.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case LESSON_DIR:
                return DatabaseContract.Lesson.CONTENT_USER_TYPE;
            case LESSON_ITEM:
                return DatabaseContract.Lesson.CONTENT_USER_ITEM_TYPE;
            case QUEST_DIR:
                return DatabaseContract.Question.CONTENT_USER_TYPE;
            case QUEST_ITEM:
                return DatabaseContract.Question.CONTENT_USER_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Uri returnUri;
        long _id;
        switch (sUriMatcher.match(uri)) {
            case LESSON_DIR:
                _id = db.insert(DatabaseContract.Lesson.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = DatabaseContract.Lesson.buildUserUri(_id);
                else
                    throw new SQLException("Failed to insert row " + uri);
                break;
            case QUEST_DIR:
                _id = db.insert(DatabaseContract.Question.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = DatabaseContract.Question.buildUserUri(_id);
                else
                    throw new SQLException("Failed to insert row " + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int rowsDeleted;
        switch (sUriMatcher.match(uri)) {
            case LESSON_DIR:
                rowsDeleted = db.delete(DatabaseContract.Lesson.TABLE_NAME, selection, selectionArgs);
                break;
            case QUEST_DIR:
                rowsDeleted = db.delete(DatabaseContract.Question.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri " + uri);
        }
        if (selection == null || 0 != rowsDeleted)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int update;
        switch (sUriMatcher.match(uri)) {
            //Case for User
            case LESSON_DIR:
                update = db.update(DatabaseContract.Lesson.TABLE_NAME, values, selection, selectionArgs);
                break;
            case QUEST_DIR:
                update = db.update(DatabaseContract.Question.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI " + uri);
        }
        if (update > 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return update;
    }
}
