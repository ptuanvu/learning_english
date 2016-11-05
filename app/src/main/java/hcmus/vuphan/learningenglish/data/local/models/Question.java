package hcmus.vuphan.learningenglish.data.local.models;

import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverType;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

import hcmus.vuphan.learningenglish.data.local.DatabaseContract;

/**
 * Created by vuphan on 05/11/2016.
 */

@StorIOSQLiteType(table = DatabaseContract.Question.TABLE_NAME)
@StorIOContentResolverType(uri = DatabaseContract.Question.CONTENT_URI_STRING)
public class Question {

    @StorIOSQLiteColumn(name = DatabaseContract.Question.COLUMN_ID, key = true)
    @StorIOContentResolverColumn(name = DatabaseContract.Question.COLUMN_ID, key = true)
    public Integer id;

    @StorIOSQLiteColumn(name = DatabaseContract.Question.COLUMN_LESSON)
    @StorIOContentResolverColumn(name = DatabaseContract.Question.COLUMN_LESSON)
    public Integer lessonId;

    @StorIOSQLiteColumn(name = DatabaseContract.Question.COLUMN_QUEST)
    @StorIOContentResolverColumn(name = DatabaseContract.Question.COLUMN_QUEST)
    public String quest;

    @StorIOSQLiteColumn(name = DatabaseContract.Question.COLUMN_ANS)
    @StorIOContentResolverColumn(name = DatabaseContract.Question.COLUMN_ANS)
    public String ans;

    public Question(Integer id, Integer lessonId, String quest, String ans) {
        this.id = id;
        this.lessonId = lessonId;
        this.quest = quest;
        this.ans = ans;
    }

    public Question() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
