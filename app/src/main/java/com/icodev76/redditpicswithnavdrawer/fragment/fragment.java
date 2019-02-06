package com.icodev76.redditpicswithnavdrawer.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.icodev76.redditpicswithnavdrawer.CustomAdapter;
import com.icodev76.redditpicswithnavdrawer.R;
import com.icodev76.redditpicswithnavdrawer.model.Feed;
import com.icodev76.redditpicswithnavdrawer.model.children.Children;
import com.icodev76.redditpicswithnavdrawer.network.RedditAPI;
import com.icodev76.redditpicswithnavdrawer.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment extends Fragment {

    private static final String BASE_URL = "https://www.reddit.com/r/";
    private String currentFeed;
    private static final String TAG = "MainActivity";
    //List<String> childrenList;
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private CardView cardview;
    ProgressDialog progressDoalog;
    private long currentmillis=(System.currentTimeMillis()/1000)-86400;

    //List<Children> children= new ArrayList<>();



    View v;
    /*// TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
*/
    //private Fragment_webview.OnFragmentInteractionListener mListener;

    public fragment() {
        // Required empty public constructor
    }

    /*// TODO: Rename and change types and number of parameters
    public static fragment newInstance(String param1, String param2) {
        fragment fragment = new fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle= getArguments();
        if (bundle !=null){
            currentFeed=bundle.getString("options");
        }
        //Toast.makeText(getActivity(), currentFeed, Toast.LENGTH_SHORT).show();

        //-------Progress Bar
        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        //----------------

        callRetrofit(currentFeed);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.recycle_view,container,false);
        return v;
    }


    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(final List<Children> photoList) {

        /*for (int i = 0; i < photoList.size(); i++) {
            Log.d(TAG, "onResponse: URL " + photoList.get(i) + "\n");
        }*/

        recyclerView = v.findViewById(R.id.customRecyclerView);
        cardview=v.findViewById(R.id.card_view_friend);
        adapter = new CustomAdapter(getActivity(),photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemCLickListener(new CustomAdapter.OnItemCLickListener() {
            @Override
            public void OnItemClick(int position) {
                String url=photoList.get(position).getData().getUrl();
                /*Toast.makeText(getContext(), "item clicked: "+url, Toast.LENGTH_SHORT).show();*/
                callWebViewFragment(url);
            }
        });

    }

    private void callWebViewFragment(String url) {

        Bundle bundle1 = new Bundle();
        bundle1.putString("url_link", url);

        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction fr= fragmentManager.beginTransaction();

        Fragment_webview fragment_webview= new Fragment_webview();
        fragment_webview.setArguments(bundle1);
        fr.replace(R.id.framelayout,fragment_webview);
        fr.commit();


        Toast.makeText(getContext(), "item clicked:budle "+bundle1.getString("url_link"), Toast.LENGTH_SHORT).show();
    }

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void callRetrofit(String currentFeed){

        /*Create handle for the RetrofitInstance interface*/
        RedditAPI service = RetrofitClientInstance.getRetrofitInstance().create(RedditAPI.class);

        Call<Feed> call = service.getFeed(currentFeed, 100);
        call.enqueue(new Callback<Feed>() {

            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                progressDoalog.dismiss();

                // Log.d(TAG, "onResponse: curretmillies " + currentmillis);
                List<Children> childrenList=response.body().getData().getChildren();

                /*for(int i=0; i<temp.size();i++){
                    if(temp.get(i).getData().getCreated_utc()>currentmillis){
                        childrenList.add(temp.get(i).getData().getUrl().toString());
                        //childrenList=temp.get(i).getData().getUrl().toString();
                        Log.d(TAG, "onResponse: temp " + temp.get(i).getData().getUrl());
                    }
                }*/
                //List<Children> childrenList = response.body().getData().getChildren();
                //Log.d(TAG, "onResponse: entrys: " + response.body().getData().getChildren().toString());

                generateDataList(childrenList);
                //Log.d(TAG, "onResponse: childrenlist " + childrenList.size());


                /*for (int i = 0; i < childrenList.size(); i++) {
                    Log.d(TAG, "onResponse: curretmillies " + currentmillis);

                    Log.d(TAG, "onResponse: URL " +childrenList.get(i)+"\n"); //childrenList.get(i).getData() + "\n");
                }*/
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }


}


