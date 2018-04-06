package com.example.lenovo.volley_task;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView lv_data,lv_in,lv_out;
    Button btn_all, btn_in, btn_out;

    public RequestQueue requestQueue;

    Volley volley;
    Gson gson;

    List<IncomingList> incomingListList;
    List<OutgoingList> outgoingLists ;
    List<All> allList ;

    MyAdapter myAdapter;

    ArrayList<DataProvider> arrayList =new ArrayList<>();
    ArrayList<DataProvider> in_arraylist=new ArrayList<>();
    ArrayList<DataProvider> out_arraylist=new ArrayList<>();

//    ArrayList<String> i_billers=new ArrayList<>();
//    ArrayList<String> i_amount=new ArrayList<>();
//    ArrayList<String> i_time=new ArrayList<>();
//
//    ArrayList<String> o_billers=new ArrayList<>();
//    ArrayList<String> o_amount=new ArrayList<>();
//    ArrayList<String> o_time=new ArrayList<>();
//
//    ArrayList<String> a_billers=new ArrayList<>();
//    ArrayList<String> a_amount=new ArrayList<>();
//    ArrayList<String> a_time=new ArrayList<>();


    public static final String api = "http://giftzay.vmgtelecoms.com/api/History";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_data = (ListView) findViewById(R.id.lv_data_main);
        lv_in=(ListView)findViewById(R.id.lv_data1_main);
        lv_out=(ListView)findViewById(R.id.lv_data2_main);
        btn_all = (Button) findViewById(R.id.btn_all_main);
        btn_in = (Button) findViewById(R.id.btn_incoming_main);
        btn_out = (Button) findViewById(R.id.btn_out_main);

        final Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("data",response);

                gson=new Gson();

                Data data = gson.fromJson(response,Data.class);

                incomingListList = data.getIncomingLists();

                for (IncomingList incomingList:incomingListList){
                    String biller= incomingList.getBiller();
//                    i_billers=new ArrayList<>();
//                    i_billers.add(biller);
//
//                    Log.e("biilers",i_billers.toString());

                    String amount=incomingList.getAmount();

//                    i_amount=new ArrayList<>();
//                    i_amount.add(amount);
                    String time = incomingList.getTimeStamp();
//                    i_time=new ArrayList<>();
//                    i_time.add(time);

                    in_arraylist.add(new DataProvider(biller,amount,time));
                }

                Log.e("incoming",incomingListList.toString());
                outgoingLists = data.getOutgoingLists();

                for (OutgoingList outgoingList:outgoingLists){
                    String biller = outgoingList.getBiller();
//                    o_billers=new ArrayList<>();
//                    o_billers.add(biller);
//
//                    Log.e("datatata",o_billers.toString());

                    String amount = outgoingList.getAmount();

//                    o_amount=new ArrayList<>();
//                    o_amount.add(amount);

//                    Log.e("amounts",o_amount.toString());


                    String time = outgoingList.getTimeStamp();
//                    o_time=new ArrayList<>();
//                    o_time.add(time);
                    out_arraylist.add(new DataProvider(biller,amount,time));
                }
                Log.e("outcoming",outgoingLists.toString());
                allList = data.getAllList();

                Log.e("all",allList.toString());

                for (All all:allList){
                    String biller = all.getBiller();
//                    a_billers=new ArrayList<>();
//                    a_billers.add(biller);

                    String amont = all.getAmount();
//                    a_amount=new ArrayList<>();
//                    a_amount.add(amont);

                    String time = all.getTimeStamp();
//                    a_time=new ArrayList<>();
//                    a_time.add(time);
                    arrayList.add(new DataProvider(biller,amont,time));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            protected Map<String, String> getParams()
            {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("Uid","25");
                return MyData;
            }
        };

        requestQueue.add(stringRequest);


        myAdapter = new MyAdapter(MainActivity.this,arrayList);
        lv_data.setAdapter(myAdapter);
        lv_data.setVisibility(View.VISIBLE);
        btn_in.setTextColor(Color.parseColor("#FF4D80"));
        btn_out.setTextColor(Color.parseColor("#FF4D80"));


        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                lv_data.setVisibility(View.VISIBLE);
                lv_in.setVisibility(View.GONE);
                lv_out.setVisibility(View.GONE);

                btn_all.setBackgroundResource(R.drawable.btn_bg);
                btn_in.setBackgroundResource(R.drawable.btn_bg1);
                btn_out.setBackgroundResource(R.drawable.btn_bg1);

                btn_all.isEnabled();

                btn_in.setTextColor(Color.parseColor("#FF4D80"));
                btn_out.setTextColor(Color.parseColor("#FF4D80"));
                btn_all.setTextColor(Color.parseColor("#ffffff"));

                myAdapter = new MyAdapter(getApplicationContext(),arrayList);
                lv_data.setAdapter(myAdapter);



            }
        });
        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                lv_out.setVisibility(View.VISIBLE);
                lv_data.setVisibility(View.GONE);
                lv_in.setVisibility(View.GONE);
                btn_all.setBackgroundResource(R.drawable.btn_bg1);
                btn_in.setBackgroundResource(R.drawable.btn_bg1);
                btn_out.setBackgroundResource(R.drawable.btn_bg);

                btn_in.setTextColor(Color.parseColor("#FF4D80"));
                btn_out.setTextColor(Color.parseColor("#ffffff"));
                btn_all.setTextColor(Color.parseColor("#FF4D80"));
                myAdapter = new MyAdapter(getApplicationContext(),out_arraylist);
                lv_out.setAdapter(myAdapter);
            }
        });
        btn_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lv_in.setVisibility(View.VISIBLE);
                lv_data.setVisibility(View.GONE);
                lv_out.setVisibility(View.GONE);

                btn_all.setBackgroundResource(R.drawable.btn_bg1);
                btn_in.setBackgroundResource(R.drawable.btn_bg);
                btn_out.setBackgroundResource(R.drawable.btn_bg1);

                btn_in.setTextColor(Color.parseColor("#ffffff"));
                btn_out.setTextColor(Color.parseColor("#FF4D80"));
                btn_all.setTextColor(Color.parseColor("#FF4D80"));
                myAdapter = new MyAdapter(getApplicationContext(),in_arraylist);
                lv_in.setAdapter(myAdapter);
            }
        });


    }

    public void MyThread() {


    }
}
