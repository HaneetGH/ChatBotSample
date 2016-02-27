package net.simplifiedcoding.customlistviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by haneetsingh on 23/02/16.
 */
public class GridViewAdapter extends BaseAdapter {
    Context cs;
    int value;
    public GridViewAdapter(Context customList, int gridimage, String s) {
this.cs=customList;
        this.value=gridimage;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listViewItem;
       // LayoutInflater inflater = cs.getLayoutInflater();
        return null;
    }
}
