package com.dat.spinnernothingselected;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by DAT on 29-Mar-16.
 */
public class SpinnerAdapter extends BaseAdapter {

    private Context context;
    private String[] data;

    int selectedPos = -1;

    public SpinnerAdapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.length + 1;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == 0) {
            return getNothingSelectedView(parent);
        }
        convertView = LayoutInflater.from(context).inflate(R.layout.spinner_layout, parent, false);
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(data[position - 1]);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (position == 0) {
            return getDropdownNothingSelectedView(parent);
        }
        convertView = LayoutInflater.from(context).inflate(R.layout.spinner_dropdown_layout, parent, false);
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        Log.d("pos -1 ", position - 1 + "");
        textView.setText(data[position - 1]);
        if (position == getSelectedPos()) {
            textView.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            textView.setTextColor(context.getResources().getColor(android.R.color.black));
        }
        return convertView;
    }

    protected View getNothingSelectedView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.spinner_header_layout, parent, false);
    }

    protected View getDropdownNothingSelectedView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.spinner_dropdown_header_layout, parent, false);
    }

    public int getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(int selectedPos) {
        this.selectedPos = selectedPos;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return position != 0; // Don't allow the 'nothing selected'
        // item to be picked.
    }
}
