package net.simplifiedcoding.customlistviewsample;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

/**
 * Created by Belal on 7/22/2015.
 */
public class CustomList extends ArrayAdapter<Mode> {

    private String[] user;

    private String[] type;

    final Handler handler = new Handler();

    private Integer[] data;

    private Activity context;

    ArrayList<Mode> myLibraryModel;

    ArrayList<Mode> NextQuestionData;

    ArrayList<String> NextQuestionArr;


    public CustomList(MainActivity mainActivity, ArrayList<Mode> myLibrary) {

        super(mainActivity, 0, myLibrary);

        this.myLibraryModel = myLibrary;

        context = mainActivity;
    }

    /* public CuhjstomList(Activity context, String[] names, String[] desc, Integer[] imageid) {
         super(context, R.layout.list_layout, names);
         this.context = context;
         this.names = names;
         this.desc = desc;
         this.imageid = imageid;

     }*/
    View listViewItem;

    int helper = 0;

    CustomList cs;

    public CustomList(MainActivity mainActivity, CustomList customList) {

        super(mainActivity, 0);

        this.cs = customList;
    }

    LayoutInflater inflater;

    Mode model;

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        inflater = context.getLayoutInflater();


        model = myLibraryModel.get(position);

        Log.d("Type--", myLibraryModel.size() + " " + myLibraryModel.get(position).questionId);


        /*Animation an = AnimationUtils.loadAnimation(context, R.anim.out);
        an.reset();
        listViewItem.startAnimation(an);*/


        if (model.Type == MessageType.Text) {

            if (model.question.equalsIgnoreCase("1")) {

                ShowAnswerOption(position);

            } else {

                listViewItem = inflater.inflate(R.layout.testui, null, true);

                TextView tv = (TextView) listViewItem.findViewById(R.id.idfortext);

                tv.setText(model.question);
            }
            // ShowAnswerOption();
            //Toast.makeText(context, "Text", Toast.LENGTH_SHORT).show();


        } else if (model.Type == MessageType.Image) {

            Log.d("Type", "Images");

            listViewItem = inflater.inflate(R.layout.imagesui, null, true);

            ImageView img = (ImageView) listViewItem.findViewById(R.id.idformsgimg);

            img.setImageDrawable(context.getResources().getDrawable(model.fdraw));

            Toast.makeText(context, "Images", Toast.LENGTH_SHORT).show();

        } else if (model.Type == MessageType.result) {

            listViewItem = inflater.inflate(R.layout.inputui, null, true);


            ImageView img = (ImageView) listViewItem.findViewById(R.id.grifid1);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do something when the corky2 is clicked
                    // myLibraryModel.add(new Mode("", MessageType.Text));


                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            //  myLibraryModel.add(new Mode("So do u like ", MessageType.Text, 0));

                            notifyDataSetChanged();

                        }
                    }, 5000);
                }
            });


        }
        // ShowAnswerOption();

        //View listViewItem = inflater.inflate(R.layout.imagesui, null, true);


        //  textViewName.setText(names[position]);
        //textViewDesc.setText(desc[position]);
        //image.setImageResource(imageid[position]);
        return listViewItem;
    }

    public void ShowAnswerOption(final int position) {


        listViewItem = inflater.inflate(R.layout.answer_ui, null, true);

        TextView aOptionOne = (TextView) listViewItem.findViewById(R.id.idforone);

        TextView aOptionTwo = (TextView) listViewItem.findViewById(R.id.idfortwo);

        final String[] str = model.answerOption.split(",");

        aOptionOne.setText(str[0]);

        aOptionTwo.setText(str[1]);


        aOptionOne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("-->", model.questionId + "|" + myLibraryModel.get(position).questionId);


                headdbclass db = new headdbclass(getContext());
//                NextQuestionArr.clear();
                try {

                    db.EnterQuestionAnswerToDb(myLibraryModel.get(position).questionId, str[0]);

                } catch (Exception e) {

                }
                Log.d("INSERT in db", myLibraryModel.get(position).questionId + str[0]);

                NextQuestionArr = new ArrayList<String>();

                NextQuestionArr = db.FetchNextQuestionId(myLibraryModel.get(position).questionId, str[0]);

                //NextQuestionArr=db.FetchNextQuestionId(model.questionId,str[1]);
                MessageType e = MessageType.Text;
//                Toast.makeText(context,NextQuestionArr.size(),Toast.LENGTH_LONG).show();

                Log.d("-->size", NextQuestionArr.size() + " " + Integer.parseInt(NextQuestionArr.get(1)));

                if (NextQuestionArr.size() == 0) {

                } else {
                    myLibraryModel.add(new Mode(NextQuestionArr.get(0), e, R.drawable.cnc, Integer.parseInt(NextQuestionArr.get(1)), model.answerOption, AnswerType.Image));

                    notifyDataSetChanged();

                    try {
                        //model.questionId = Integer.parseInt(NextQuestionArr.get(1));
                        nextQuestionCallMethod(NextQuestionArr.get(1));

                    } catch (Exception ee) {

                    }
                }

            }
        });
        aOptionTwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("-->", myLibraryModel.get(position).questionId + "");

                headdbclass db = new headdbclass(getContext());
                // NextQuestionArr.clear();
                try {
                    db.EnterQuestionAnswerToDb(myLibraryModel.get(position).questionId, str[1]);

                } catch (Exception e) {

                }

                NextQuestionArr = new ArrayList<String>();

                NextQuestionArr = db.FetchNextQuestionId(myLibraryModel.get(position).questionId, str[1]);

                MessageType e = MessageType.Text;
//                Toast.makeText(context,NextQuestionArr.size(),Toast.LENGTH_LONG).show();
                //  Log.d("-->", NextQuestionArr.size() + "");
//                Log.d("-->", NextQuestionArr.get(0) + "");

                notifyDataSetChanged();

                if (NextQuestionArr.size() == 0) {

                } else {
                    myLibraryModel.add(new Mode(NextQuestionArr.get(0), e, R.drawable.cnc, Integer.parseInt(NextQuestionArr.get(1)), model.answerOption, AnswerType.Image));

                    try {
                        model.questionId = Integer.parseInt(NextQuestionArr.get(1));

                        nextQuestionCallMethod(NextQuestionArr.get(1));

                    } catch (Exception ee) {

                    }


                }
            }
        });


    }

    public void nextQuestionCallMethod(String Qid) {

        nQuestionCounter = 0;

        headdbclass db = new headdbclass(getContext());

        Log.d("qid", Qid + "");

        NextQuestionData = new ArrayList<Mode>();

        NextQuestionData = db.FetchNextQuestionFromBaseTable(Qid);

        delaycall();

    }

    int nQuestionCounter = 0;

    public void delaycall() {

        if (nQuestionCounter < NextQuestionData.size()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    myLibraryModel.add(NextQuestionData.get(nQuestionCounter));

                    notifyDataSetChanged();

                    nQuestionCounter++;

                    delaycall();
                }
            }, 1000);

            //delaycall();

        }
    }
    //GetQuestionAnswerOption();

}










