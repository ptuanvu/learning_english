package hcmus.vuphan.learningenglish.data.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.Query;

import java.util.List;

import hcmus.vuphan.learningenglish.data.AppDataStore;
import hcmus.vuphan.learningenglish.data.local.models.Lesson;
import hcmus.vuphan.learningenglish.data.local.models.LessonStorIOContentResolverDeleteResolver;
import hcmus.vuphan.learningenglish.data.local.models.LessonStorIOContentResolverGetResolver;
import hcmus.vuphan.learningenglish.data.local.models.LessonStorIOContentResolverPutResolver;
import hcmus.vuphan.learningenglish.data.local.models.Question;
import hcmus.vuphan.learningenglish.data.local.models.QuestionStorIOContentResolverDeleteResolver;
import hcmus.vuphan.learningenglish.data.local.models.QuestionStorIOContentResolverGetResolver;
import hcmus.vuphan.learningenglish.data.local.models.QuestionStorIOContentResolverPutResolver;
import rx.Observable;

/**
 * Created by vuphan on 05/11/2016.
 */

public class AppLocalDataStore implements AppDataStore {

    private StorIOContentResolver mStorIOContentResolver;

    public AppLocalDataStore(@NonNull Context context) {
        mStorIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(context.getContentResolver())
                .addTypeMapping(Lesson.class, ContentResolverTypeMapping.<Lesson>builder()
                        .putResolver(new LessonStorIOContentResolverPutResolver())
                        .getResolver(new LessonStorIOContentResolverGetResolver())
                        .deleteResolver(new LessonStorIOContentResolverDeleteResolver())
                        .build()
                )
                .addTypeMapping(Question.class, ContentResolverTypeMapping.<Question>builder()
                        .putResolver(new QuestionStorIOContentResolverPutResolver())
                        .getResolver(new QuestionStorIOContentResolverGetResolver())
                        .deleteResolver(new QuestionStorIOContentResolverDeleteResolver())
                        .build()
                ).build();
    }

    @Override
    public Observable<List<Lesson>> getAllLesson() {
        Log.d("LOCAL","Loaded from local");
        return mStorIOContentResolver.get()
                .listOfObjects(Lesson.class)
                .withQuery(Query.builder().uri(DatabaseContract.Lesson.CONTENT_URI).build())
                .prepare()
                .asRxObservable();
    }

    public void saveLessonToLocal(List<Lesson> lessons) {
        mStorIOContentResolver.put().objects(lessons).prepare().executeAsBlocking();
    }

    public void saveQuestionToLocal(List<Question> questions) {
        mStorIOContentResolver.put().object(questions).prepare().executeAsBlocking();
    }
}
