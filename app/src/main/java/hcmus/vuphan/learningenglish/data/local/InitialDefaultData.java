package hcmus.vuphan.learningenglish.data.local;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hcmus.vuphan.learningenglish.data.AppRepository;
import hcmus.vuphan.learningenglish.data.local.models.Lesson;
import hcmus.vuphan.learningenglish.data.local.models.Question;
import rx.Subscription;

/**
 * Created by vuphan on 05/11/2016.
 */

public class InitialDefaultData {
    private Subscription mSubscription;
    private AppRepository mAppRepository;
    private Context context;

    public InitialDefaultData(AppRepository mAppRepository, Context context) {
        this.mAppRepository = mAppRepository;
        this.context = context;
    }

    public void initData() {
        List<Lesson> lessons = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        int count = 0;
        for (int i = 1; i <= 20; i ++) {
            lessons.add(new Lesson(i, "Lesson " + i, "null"));
            for (int j = 0; j < 5; j++) {
                count ++;
                questions.add(new Question(count, i, "How old are you? ", "I'm 22 year old."));
            }
        }
        AppLocalDataStore localDataStore = new AppLocalDataStore(context);
        localDataStore.saveLessonToLocal(lessons);
        localDataStore.saveQuestionToLocal(questions);
    }
}
