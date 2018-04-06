package com.example.lenovo.volley_task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LENOVO on 21-03-2018.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<DataProvider> biller;
    LayoutInflater inflater;
    DataProvider dataProvider;

    public MyAdapter(Context context, ArrayList<DataProvider> biller) {
        this.context = context;
        this.biller = biller;
        this.dataProvider=dataProvider;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return biller.size();
    }

    @Override
    public Object getItem(int position) {
        return biller.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=new ViewHolder();
        if (convertView==null){
            convertView=inflater.inflate(R.layout.customrow,parent,false);
            viewHolder.tv_biller=(TextView)convertView.findViewById(R.id.tv_biller_custom);
            viewHolder.tv_amount=(TextView)convertView.findViewById(R.id.tv_amount_custom);
            viewHolder.tv_time=(TextView)convertView.findViewById(R.id.tv_timestamp_custom);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        dataProvider=biller.get(position);

        viewHolder.tv_biller.setText(""+dataProvider.getBiller());
        viewHolder.tv_amount.setText("$"+dataProvider.getAmount());
        viewHolder.tv_time.setText(dataProvider.getTime());
        return convertView;
    }
    public class ViewHolder{
        TextView tv_biller,tv_amount,tv_time;
    }
}
