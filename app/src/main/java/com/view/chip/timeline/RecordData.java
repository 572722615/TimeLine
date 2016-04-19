package com.view.chip.timeline;

import java.util.ArrayList;

/**
 * Created by DavidLee on 2016/3/9.
 */
public class RecordData{

    /**
     * operateTime : 2016-03-24 09:46:16
     * OrderType : 普通订单
     * totalpay : 349.20
     * shopname : wifi点点测试商户2
     * itemlist : [{"employee":"发型师李","item":"烫发","count":"1"}]
     */


    public String totalUse;
    /**
     * employee : 发型师李
     * item : 烫发
     * count : 1
     */

    public ArrayList<RecordListData> list;


    public static class RecordListData {
        public boolean isLocal;
        public String operateTime;
        public String OrderType;
        public String totalpay;
        public String shopname;
        public ArrayList<ItemData> itemlist;

        public static class ItemData{
            public String employee;
            public String item;
            public String count;
        }
    }
}
