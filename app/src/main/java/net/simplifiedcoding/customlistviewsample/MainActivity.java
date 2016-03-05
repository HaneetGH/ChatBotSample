package net.simplifiedcoding.customlistviewsample;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";

    public ListView listView;

    public ArrayList<String> names = new ArrayList<String>();

    final Handler handler = new Handler();

    int i = 0;


    public ArrayList<String> ArrNextQuestion = new ArrayList<String>();



    int time = 5000;


    ArrayList<Mode> myLibrary = new ArrayList<>();

    ArrayList<Mode> ArrHelpingInsertingValuesToMainmyLibrary = new ArrayList<>();

    ArrayList<ansModel> ArrOldAnswerRecever = new ArrayList<>();

    ArrayList<Mode> ArrRecever = new ArrayList<>();

    CustomList customList;

    private void databaseopen() {
        // TODO Auto-generated method stub
        boolean isDatabaseInitialized = Database.init(this);

        if (!isDatabaseInitialized) {

           // Toast.makeText(this, "Unable to Initialized Database" + isDatabaseInitialized,

              //      Toast.LENGTH_SHORT).show();
            return;
        } else {
            //Toast.makeText(this, "Done" + isDatabaseInitialized,
             //       Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        //databaseopen();

        headdbclass db = new headdbclass(getApplicationContext());

        ArrOldAnswerRecever = db.FetchALLOldQuestionsIds();

        if (ArrOldAnswerRecever.size() != 0) {

            for (int counter = 0; counter < ArrOldAnswerRecever.size(); counter++) {

                Log.d("Ids", ArrOldAnswerRecever.get(counter).answer + "|" + ArrOldAnswerRecever.get(counter).questionId + "|" + ArrOldAnswerRecever.size());

                ArrHelpingInsertingValuesToMainmyLibrary = db.FetchOldQuestionWithQid(ArrOldAnswerRecever.get(counter).questionId);

                for (int cpuntInsertrr = 0; cpuntInsertrr < ArrHelpingInsertingValuesToMainmyLibrary.size(); cpuntInsertrr++) {

                    myLibrary.add(ArrHelpingInsertingValuesToMainmyLibrary.get(cpuntInsertrr));
                }
                Log.d("Idss", myLibrary.size() + "");


            }
        } else {
            ArrRecever = db.FetchBasicQuestion();
        }
        try {
            ArrNextQuestion = db.FetchNextQuestionId(ArrOldAnswerRecever.get(ArrOldAnswerRecever.size() - 1).questionId, ArrOldAnswerRecever.get(ArrOldAnswerRecever.size() - 1).answer);

            ArrRecever = db.FetchNewQuestion(Integer.parseInt(ArrNextQuestion.get(1)));

        } catch (Exception e) {

        }




        customList = new CustomList(MainActivity.this, myLibrary);
        // new CustomList(MainActivity.this, customList);
        listView = (ListView) findViewById(R.id.listView);

        AnimationSet set = new AnimationSet(true);

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.out);

        animation1.setDuration(3000);

        set.addAnimation(animation1);

        LayoutAnimationController controller = new LayoutAnimationController(set, 2.0f);

        //listView.setLayoutAnimation(controller);
        listView.setAdapter(customList);
        //listView.setSelection(listView.getCount());
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //listView.scrollTo(0, listView.getHeight());


        delaycall();





    }






    public void delaycall() {
        if (i < ArrRecever.size()) {

            handler.postDelayed(new Runnable() {

                @Override
                public void run() {

                    myLibrary.add(ArrRecever.get(i));

                    customList.notifyDataSetChanged();

                    i++;


                    delaycall();
                }
            }, 1000);


        } else {


        }

    }

    @Override
    public void onResume() {

        super.onResume();

        customList.notifyDataSetChanged();


        //scrollMyListViewToBottom();
    }


}
