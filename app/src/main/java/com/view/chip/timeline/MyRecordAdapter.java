package com.view.chip.timeline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by DavidLee on 2016/3/9.
 */
public class MyRecordAdapter extends BaseAdapter {

    private Context context;
    private final String balance;
    private final String cardId;
    private ArrayList<RecordData.RecordListData> dataList = new ArrayList<>();
    private RecordData.RecordListData firstList = new RecordData.RecordListData();
    private String year;
    private String month;
    private String day;
    private String events = "";
    boolean isFirst = true;
    boolean isLast = true;

    public MyRecordAdapter(Context context, String balance, String cardId) {
        this.context = context;
        this.balance = balance;
        this.cardId = cardId;

        initFirst();
    }


    private void initFirst() {
        RecordData.RecordListData data = new RecordData.RecordListData();
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String operateTime =format.format(date);
        year = operateTime.substring(0, 4);
        month = operateTime.substring(5, 7);
        month = Integer.parseInt(month)+"";
        day = operateTime.substring(8, 10);

        data.isLocal = true;
        data.itemlist = new ArrayList<RecordData.RecordListData.ItemData>();
        RecordData.RecordListData.ItemData itemData = new RecordData.RecordListData.ItemData();
        data.itemlist.add(itemData);
        data.itemlist.get(0).employee = "";
        data.itemlist.get(0).item = "";
        data.itemlist.get(0).count = "";

//        firstList.add(0, data);
    }


    public void setData(ArrayList<RecordData.RecordListData> data) {
        if (data == null) {
            return;
        }
        dataList = new ArrayList<>();
        dataList.add(firstList);
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    public void addToTail(ArrayList<RecordData.RecordListData> data) {
        if (data == null) {
            return;
        }
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public RecordData.RecordListData getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.record_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.viewTop = (ImageView) convertView.findViewById(R.id.view_top);
            viewHolder.record_layout = (RelativeLayout) convertView.findViewById(R.id.record_layout);
            viewHolder.showTime = (TextView) convertView.findViewById(R.id.show_time);
            viewHolder.showTimeYear = (TextView) convertView.findViewById(R.id.show_time_year);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.viewBottom = (ImageView) convertView.findViewById(R.id.view_bottom);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.title_cost = (TextView) convertView.findViewById(R.id.title_cost);
            viewHolder.title_content = (TextView) convertView.findViewById(R.id.title_content);
            viewHolder.share_btn = (Button) convertView.findViewById(R.id.share_friend_btn);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        final RecordData.RecordListData data = dataList.get(position);


        //去掉头尾的线，更换中间的图标,第一条数据手动添加，其他数据从接口调
        int pad = DeviceUtils.dip2px(context, 10);
        if (position == 0 && isFirst) {
            isFirst = false;
            viewHolder.title_cost.setText(balance + "元");
            viewHolder.viewTop.setVisibility(View.INVISIBLE);
            viewHolder.image.setImageResource(R.drawable.balance_list_icon);
            viewHolder.title.setText("余额");
            viewHolder.record_layout.setPadding(pad, 0, 0, 0);
            viewHolder.title_content.setVisibility(View.GONE);
            viewHolder.viewBottom.setVisibility(View.VISIBLE);
            viewHolder.share_btn.setVisibility(View.VISIBLE);

            viewHolder.showTimeYear.setText(year);
            viewHolder.showTime.setText(month + "月" + day + "日");


            isFirst = true;
        } else if ((position == dataList.size() - 1) && isLast) {
            isLast = false;
            viewHolder.record_layout.setPadding(pad, pad, 0, 0);
            viewHolder.title.setText("支付");
            viewHolder.viewBottom.setVisibility(View.INVISIBLE);
            viewHolder.viewTop.setVisibility(View.VISIBLE);
            viewHolder.image.setImageResource(R.drawable.cost_pay);
            viewHolder.title_content.setVisibility(View.VISIBLE);
            viewHolder.share_btn.setVisibility(View.GONE);

            fillAdapterContent(viewHolder, data);

            isLast = true;
        } else {
            viewHolder.record_layout.setPadding(pad, pad, 0, 0);
            viewHolder.viewBottom.setVisibility(View.VISIBLE);
            viewHolder.viewTop.setVisibility(View.VISIBLE);
            viewHolder.image.setImageResource(R.drawable.cost_pay);
            viewHolder.title.setText("支付");
            viewHolder.title_content.setVisibility(View.VISIBLE);
            viewHolder.share_btn.setVisibility(View.GONE);

            fillAdapterContent(viewHolder, data);
        }



        if (dataList.size() == 1) {
            viewHolder.record_layout.setPadding(pad, 0, 0, 0);
            viewHolder.viewBottom.setVisibility(View.GONE);
            viewHolder.title_content.setVisibility(View.GONE);
        }




        return convertView;
    }

    private void fillAdapterContent(ViewHolder viewHolder, RecordData.RecordListData data) {
        if(data!=null&&data.operateTime!=null){
            year = data.operateTime.substring(0, 4);
            viewHolder.showTimeYear.setText(year);
            month = data.operateTime.substring(5, 7);
            day = data.operateTime.substring(8, 10);
            month = Integer.parseInt(month) + "";
            viewHolder.showTime.setText(month + "月" + day + "日");
        }


        String employee = "";

        viewHolder.title_content.setText(events);
        events = "";
        viewHolder.title_cost.setText(data.totalpay + "元");
    }

    private static class ViewHolder {


        public ImageView viewTop;
        public TextView showTime;
        public TextView showTimeYear;
        public ImageView image;
        public ImageView viewBottom;
        public TextView title;
        public TextView title_content;
        public TextView title_cost;
        public RelativeLayout record_layout;
        public Button share_btn;
    }

}
