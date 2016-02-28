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
    public static final String MyPREFERENCES = "MyPrefs" ;
//    SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    public ListView listView;
    public ArrayList<String> names = new ArrayList<String>();
    final Handler handler = new Handler();
    public ArrayList<String> desc = new ArrayList<String>();
    public ArrayList<String> third = new ArrayList<String>();
    int i=0;

    public ArrayList<Integer> imageid = new ArrayList<Integer>();


    public ArrayList<Integer> question = new ArrayList<Integer>();
    public ArrayList<Integer> question2 = new ArrayList<Integer>();
    int time = 5000;
    //Model[] Model = new Model[100];
    ArrayList<Mode> myLibrary = new ArrayList<>();
    ArrayList<Mode> ArrRecever = new ArrayList<>();
    ArrayList<Mode> ArrReceverForAnswers = new ArrayList<>();
    CustomList customList;
    //MessageType mt;


    //MessageType obj;

    private void databaseopen() {
        // TODO Auto-generated method stub
        boolean isDatabaseInitialized = Database.init(this);
        if (!isDatabaseInitialized) {
            Toast.makeText(this, "Unable to Initialized Database"+isDatabaseInitialized,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Toast.makeText(this, "Done"+isDatabaseInitialized,
                    Toast.LENGTH_SHORT).show();
            return;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  obj = MessageType.Text;

        databaseopen();

        headdbclass db = new headdbclass(getApplicationContext());

        ArrRecever = db.FetchBasicQuestion();
        //ArrReceverForAnswers = db.FetchBasicQuestionAnswer();
        Log.d("Type", "2");

        Log.d("Type", "3");

       customList = new CustomList(MainActivity.this, myLibrary);
        new CustomList(MainActivity.this,customList);
        listView = (ListView) findViewById(R.id.listView);

        AnimationSet set = new AnimationSet(true);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.out);
        animation1.setDuration(3000);
        set.addAnimation(animation1);
        LayoutAnimationController controller = new LayoutAnimationController(set,2.0f);

        //listView.setLayoutAnimation(controller);
        listView.setAdapter(customList);
        listView.setSelection(customList.getCount());




        delaycall();












        //CustomList customList = new CustomList(this, names, desc, imageid,third,question,question2);
        //customList = new CustomList(this, names, desc, imageid,third,question,question2);


    }
    public void GetQuestionAnswerOption(int questionId)
    {

    }
public void delaycall()
{
    if(i<ArrRecever.size())
    {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myLibrary.add(ArrRecever.get(i));

                customList.notifyDataSetChanged();

                i++;
                time = time + 5000;

                delaycall();
            }
        }, 1000);



    }
    else
    {
        /*handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myLibrary.add(ArrRecever.get(i));

                customList.notifyDataSetChanged();

                i++;
                time = time + 5000;

                //delaycall();
            }
        }, 2000);*/

    }
    //GetQuestionAnswerOption();

}
    @Override
    public void onResume() {
        super.onResume();
        customList.notifyDataSetChanged();
    }



}
