package hcmus.vuphan.learningenglish.data.local.models;

import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverType;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

import hcmus.vuphan.learningenglish.data.local.DatabaseContract;

/**
 * Created by vuphan on 05/11/2016.
 */

@StorIOSQLiteType(table = DatabaseContract.Lesson.TABLE_NAME)
@StorIOContentResolverType(uri = DatabaseContract.Lesson.CONTENT_URI_STRING)
public class Lesson {

    @StorIOSQLiteColumn(name = DatabaseContract.Lesson.COLUMN_ID, key = true)
    @StorIOContentResolverColumn(name = DatabaseContract.Lesson.COLUMN_ID, key = true)
    public Integer id;

    @StorIOSQLiteColumn(name = DatabaseContract.Lesson.COLUMN_TITLE)
    @StorIOContentResolverColumn(name = DatabaseContract.Lesson.COLUMN_TITLE)
    public String title;

    @StorIOSQLiteColumn(name = DatabaseContract.Lesson.COLUMN_AUDIO)
    @StorIOContentResolverColumn(name = DatabaseContract.Lesson.COLUMN_AUDIO)
    public String audioName;

    public Lesson(Integer id, String title, String audioName) {
        this.id = id;
        this.title = title;
        this.audioName = audioName;
    }

    public Lesson() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }
}
