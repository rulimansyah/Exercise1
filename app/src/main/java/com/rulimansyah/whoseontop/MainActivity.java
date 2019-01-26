package com.rulimansyah.whoseontop;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rulimansyah.whoseontop.api.APIClient;
import com.rulimansyah.whoseontop.api.APIInterface;
import com.rulimansyah.whoseontop.model.Model;
import com.rulimansyah.whoseontop.model.Value;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout mSwipeRefreshLayout;
    APIInterface mAPIInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static int numberOfRow = 0;
    Button addData;
    List<Value> valueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("ERROR", "AAA");

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperfresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //mSwipeRefreshLayout.setRefreshing(true);
                numberOfRow = 0;
                addData.setVisibility(View.VISIBLE);
                getListData();

                mSwipeRefreshLayout.setRefreshing(false);
            }

        });

        Log.i("ERROR", "BBB");

        addData = (Button) findViewById(R.id.addData);
        //addData.setOnClickListener();

        if(numberOfRow >= 2){
            addData.setVisibility(View.GONE);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAPIInterface = APIClient.getClient().create(APIInterface.class);

        Log.i("ERROR", "CCC");

        getListData();

        Log.i("ERROR", "DDD");



    }

    public void addNewData(View view){
        Log.i("Error", "EEE");
        numberOfRow++;
        mAdapter.notifyDataSetChanged();


        // Hidden button if has added two row data
        if(numberOfRow == 2) {
            view.setVisibility(View.GONE);
        }

    }

    public void getListData(){
        Log.i("ERROR", "CCC0");

        // set up progress before call
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Log.i("ERROR", "CCC1");

        Call<Model> modelCall = mAPIInterface.getValue();

        Log.i("ERROR", "CCC1_1");

        modelCall.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {

                Log.i("ERROR", "CCC2");

                // close progress dialog
                progressDialog.dismiss();

                valueList = response.body().getValue();

                Log.d("TAG", "Jumlah data Kontak: " +
                        String.valueOf(valueList.size()));

                mAdapter = new ValueAdapter(valueList);
                mRecyclerView.setAdapter(mAdapter);

                Log.i("ERROR", "CCC3");

                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP, 0) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        int oldPos = viewHolder.getAdapterPosition();
                        int newPos = target.getAdapterPosition();

                        Value valueItem = valueList.get(oldPos);

                        valueList.remove(oldPos);
                        valueList.add(newPos, valueItem);
                        mAdapter.notifyItemMoved(oldPos, newPos);

                        // call re-bind if oldPos || newPos change
                        if (oldPos == 0 || newPos == 0) {
                            mAdapter.notifyItemChanged(oldPos, Boolean.FALSE);
                            mAdapter.notifyItemChanged(newPos, Boolean.FALSE);
                        }

                        //mAdapter.notifyDataSetChanged(); // crash error
                        return true;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                    }

                });

                itemTouchHelper.attachToRecyclerView(mRecyclerView);

                // comment

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                // close progress dialog
                progressDialog.dismiss();

                // show error Toast
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();

                Log.d("TAG", t.toString());
            }
        });

    }


}
