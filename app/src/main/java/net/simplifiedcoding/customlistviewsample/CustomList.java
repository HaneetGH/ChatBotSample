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
    private Integer[] data;
    private Activity context;
    ArrayList<Mode> myLibraryModel;

    public CustomList(MainActivity mainActivity, ArrayList<Mode> myLibrary, String str) {
        super(mainActivity, R.layout.list_layout, myLibrary);
        this.context = mainActivity;
        //this.myLibraryModel=myLibrary;



    }

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


    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        Log.d("Type", "enter");

        Mode model = myLibraryModel.get(position);



        /*Animation an = AnimationUtils.loadAnimation(context, R.anim.out);
        an.reset();
        listViewItem.startAnimation(an);*/
        if (model.Type == MessageType.Text) {
            listViewItem = inflater.inflate(R.layout.testui, null, true);

            TextView tv = (TextView) listViewItem.findViewById(R.id.idfortext);
            tv.setText(model.question);
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


        //View listViewItem = inflater.inflate(R.layout.imagesui, null, true);


        //  textViewName.setText(names[position]);
        //textViewDesc.setText(desc[position]);
        //image.setImageResource(imageid[position]);
        return listViewItem;
    }




}









