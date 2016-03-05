package net.simplifiedcoding.customlistviewsample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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

    private ArrayList<String> sQuestions = new ArrayList<String>();

    private String[] type;

    final Handler handler = new Handler();

    private Integer[] data;

    private Activity context;

    ArrayList<Mode> myLibraryModel;

    ArrayList<Mode> NextQuestionData;

    ArrayList<String> NextQuestionArr;

    ArrayList<ansModel> ArrOldAnswerRecever = new ArrayList<>();

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


    LayoutInflater inflater;

    Mode model;
    int pos;
    TextView tvresult;
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        inflater = context.getLayoutInflater();
        //convertView.setBackgroundColor(Color.WHITE);
        headdbclass db = new headdbclass(getContext());

        pos = position;

        model = myLibraryModel.get(position);

        Log.d("Type--", myLibraryModel.size() + " " + myLibraryModel.get(position).questionId);


        /*Animation an = AnimationUtils.loadAnimation(context, R.anim.out);
        an.reset();
        listViewItem.startAnimation(an);*/


        if (model.Type == MessageType.Text) {

            if (model.question.equalsIgnoreCase("1")) {

                ShowAnswerOption(position);

                tvresult = (TextView) listViewItem.findViewById(R.id.idforresultshow);

//                Log.d("qSize",sQuestions.size()+"");
                if (sQuestions.size() > 0) {
                    ////tvresult.setText("You Select:"+sQuestions.get(position));
                }
                //selectedAnser="";

            } else {

                listViewItem = inflater.inflate(R.layout.testui, null, true);

                TextView tv = (TextView) listViewItem.findViewById(R.id.idfortext);

                tv.setText(model.question);

                // //tvresult.setText("You Selected:" + str[0]);
                MainActivity mn = new MainActivity();
                // mn.scrollMyListViewToBottom();
                // tv.scrollTo(position-1,position);

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

    //String selectedAse="";

    public void ShowAnswerOption(final int position) {

        //selectedAse="";
        listViewItem = inflater.inflate(R.layout.answer_ui, null, true);
        //TextView tvresult = (TextView) listViewItem.findViewById(R.id.idforresultshow);

        final TextView aOptionOne = (TextView) listViewItem.findViewById(R.id.idforone);

        final TextView aOptionTwo = (TextView) listViewItem.findViewById(R.id.idfortwo);
        final TextView aOptionThree = (TextView) listViewItem.findViewById(R.id.idforthreee);

        final TextView aOptionFour = (TextView) listViewItem.findViewById(R.id.idforfour);
        //TextView tvresult = (TextView) listViewItem.findViewById(R.id.idforresultshow);
        final String[] str = model.answerOption.split(",");
        Log.d("str", "ShowAnswerOption: " + str.length);
        if (str.length == 10) {
            aOptionOne.setText(str[0]);
            aOptionTwo.setVisibility(View.GONE);
            aOptionThree.setVisibility(View.GONE);
            aOptionFour.setVisibility(View.GONE);


        } else if (str.length == 2) {
            aOptionOne.setText(str[0]);
            aOptionTwo.setText(str[1]);
            aOptionThree.setVisibility(View.GONE);
            aOptionFour.setVisibility(View.GONE);

        } else if (str.length == 3) {
            aOptionOne.setText(str[0]);
            aOptionTwo.setText(str[1]);
            aOptionThree.setText(str[2]);
            aOptionFour.setVisibility(View.GONE);

        } else if (str.length == 4)

        {
            aOptionOne.setText(str[0]);
            aOptionTwo.setText(str[1]);
            aOptionThree.setText(str[2]);
            aOptionFour.setText(str[3]);
        }




///-------------------------Buttons Click Events For Answer Start-------------------------------///
        aOptionOne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                aOptionOne.setSelected(true);
                //aOptionOne.setBackgroundResource(R.color.primary_text_default_material_dark);
                //tvresult.setText("You Reply:"+str[0]);
                headdbclass db = new headdbclass(getContext());
//                NextQuestionArr.clear();
                try {
                    //selectedAse=str[0];
                    sQuestions.add(str[0]);
                    db.EnterQuestionAnswerToDb(myLibraryModel.get(position).questionId, str[0]);


                    Log.d("INSERT in db", myLibraryModel.get(position).questionId + str[0]);

                    NextQuestionArr = new ArrayList<String>();
                    Log.d("-->size", myLibraryModel.get(position).questionId + "|" + str[0]);
                    NextQuestionArr = db.FetchNextQuestionId(myLibraryModel.get(position).questionId, str[0]);

                    //NextQuestionArr=db.FetchNextQuestionId(model.questionId,str[1]);
                    MessageType e = MessageType.Text;
//                Toast.makeText(context,NextQuestionArr.size(),Toast.LENGTH_LONG).show();
                    Log.d("-->size", NextQuestionArr.size() + "");
                    Log.d("-->size", NextQuestionArr.size() + " " + Integer.parseInt(NextQuestionArr.get(1)));

                    if (NextQuestionArr.size() == 0) {

                    } else {

                        if (NextQuestionArr.get(0).equalsIgnoreCase(" ")) {

                        } else {
                            myLibraryModel.add(new Mode(NextQuestionArr.get(0), e, R.drawable.cnc, Integer.parseInt(NextQuestionArr.get(1)), model.answerOption, AnswerType.Image,""));
                            notifyDataSetChanged();
                        }

                    }
                    try {
                        //model.questionId = Integer.parseInt(NextQuestionArr.get(1));
                        nextQuestionCallMethod(NextQuestionArr.get(1));

                    } catch (Exception ee) {

                    }

                } catch (Exception e) {

                }
            }

        });
        aOptionTwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("-->", myLibraryModel.get(position).questionId + "");
                // TextView tvresult = (TextView) listViewItem.findViewById(R.id.idforresultshow);
                ////tvresult.setText("You Selected:" + str[1]);
                headdbclass db = new headdbclass(getContext());
                // NextQuestionArr.clear();
                try {
                    sQuestions.add(str[1]);
                    db.EnterQuestionAnswerToDb(myLibraryModel.get(position).questionId, str[1]);


                    NextQuestionArr = new ArrayList<String>();

                    NextQuestionArr = db.FetchNextQuestionId(myLibraryModel.get(position).questionId, str[1]);

                    MessageType e = MessageType.Text;
//                Toast.makeText(context,NextQuestionArr.size(),Toast.LENGTH_LONG).show();
                    //  Log.d("-->", NextQuestionArr.size() + "");
//                Log.d("-->", NextQuestionArr.get(0) + "");

                    notifyDataSetChanged();

                    if (NextQuestionArr.size() == 0) {

                    } else {
                        if (NextQuestionArr.get(0).equalsIgnoreCase(" ")) {

                        } else {
                            myLibraryModel.add(new Mode(NextQuestionArr.get(0), e, R.drawable.cnc, Integer.parseInt(NextQuestionArr.get(1)), model.answerOption, AnswerType.Image,""));

                            notifyDataSetChanged();
                        }
                        try {
                            // model.questionId = Integer.parseInt(NextQuestionArr.get(1));

                            nextQuestionCallMethod(NextQuestionArr.get(1));

                        } catch (Exception ee) {

                        }


                    }


                } catch (Exception e) {

                }
                //tvresult.setText("You Reply:"+str[1]);
            }
        });


        aOptionThree.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //tvresult.setText("You Reply:"+str[2]);
                Log.d("-->", myLibraryModel.get(position).questionId + "");
                aOptionThree.setSelected(true);
                headdbclass db = new headdbclass(getContext());

                // //tvresult.setText("You Selected:" + str[2]);
                // NextQuestionArr.clear();
                sQuestions.add(str[2]);
                Log.d("Values", "|" + myLibraryModel.get(position).questionId + "|" + str[2]);
                try {
                    db.EnterQuestionAnswerToDb(myLibraryModel.get(position).questionId, str[2]);
                    Log.d("Values in try", "|" + myLibraryModel.get(position).questionId + "|" + str[2]);


                    NextQuestionArr = new ArrayList<String>();
                    Log.d("321", "|" + myLibraryModel.get(position).questionId + "|" + str[2]);
                    NextQuestionArr = db.FetchNextQuestionId(myLibraryModel.get(position).questionId, str[2]);
                    Log.d("321", NextQuestionArr.size() + " ");
                    MessageType e = MessageType.Text;
//                Toast.makeText(context,NextQuestionArr.size(),Toast.LENGTH_LONG).show();
                    Log.d("--> size", NextQuestionArr.size() + " ");
//                Log.d("-->", NextQuestionArr.get(0) + "");


                    if (NextQuestionArr.size() == 0) {

                    } else {

                        if (NextQuestionArr.get(0).equalsIgnoreCase(" ")) {

                        } else {
                            myLibraryModel.add(new Mode(NextQuestionArr.get(0), e, R.drawable.cnc, Integer.parseInt(NextQuestionArr.get(1)), model.answerOption, AnswerType.Image,""));
                            notifyDataSetChanged();
                        }
                        try {
                            //model.questionId = Integer.parseInt(NextQuestionArr.get(1));
                            Log.d("va", NextQuestionArr.get(1));
                            nextQuestionCallMethod(NextQuestionArr.get(1));
                            Log.d("va", NextQuestionArr.get(1));

                        } catch (Exception ee) {

                            Log.d("Values error 2", ee.toString());

                        }

                    }
                } catch (Exception e) {
                    Log.d("Values error", "|" + myLibraryModel.get(position).questionId + "|" + str[2]);
                }


                // NextQuestionArr.clear();
            }

        });

        aOptionFour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //tvresult.setText("You Reply:"+str[3]);
                Log.d("-->", myLibraryModel.get(position).questionId + "");
                //TextView tvresult = (TextView) listViewItem.findViewById(R.id.idforresultshow);
                ////tvresult.setText("You Selected:" + str[3]);
                headdbclass db = new headdbclass(getContext());
                // NextQuestionArr.clear();
                try {
                    db.EnterQuestionAnswerToDb(myLibraryModel.get(position).questionId, str[3]);


                    NextQuestionArr = new ArrayList<String>();

                    NextQuestionArr = db.FetchNextQuestionId(myLibraryModel.get(position).questionId, str[3]);

                    MessageType e = MessageType.Text;
//                Toast.makeText(context,NextQuestionArr.size(),Toast.LENGTH_LONG).show();
                    //  Log.d("-->", NextQuestionArr.size() + "");
//                Log.d("-->", NextQuestionArr.get(0) + "");

                    notifyDataSetChanged();
                    sQuestions.add(str[3]);
                    if (NextQuestionArr.size() == 0) {

                    } else {
                        if (NextQuestionArr.get(0).equalsIgnoreCase(" ")) {

                        } else {

                            myLibraryModel.add(new Mode(NextQuestionArr.get(0), e, R.drawable.cnc, Integer.parseInt(NextQuestionArr.get(1)), model.answerOption, AnswerType.Image,""));

                            notifyDataSetChanged();
                        }
                        try {
                            //model.questionId = Integer.parseInt(NextQuestionArr.get(1));

                            nextQuestionCallMethod(NextQuestionArr.get(1));

                        } catch (Exception ee) {

                        }


                    }

                } catch (Exception e) {

                }
            }
        });
        // Log.d("qSize", sQuestions.size() + "");
///-------------------------Buttons Click Events For Answer End-------------------------------///

    }

    public void nextQuestionCallMethod(String Qid) {

        nQuestionCounter = 0;

        headdbclass db = new headdbclass(getContext());

        Log.d("qid", Qid + "");

        NextQuestionData = new ArrayList<Mode>();

        NextQuestionData = db.FetchNextQuestionFromBaseTable(Qid);
        Log.d("valuess", NextQuestionData.get(nQuestionCounter).questionId + "");


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










