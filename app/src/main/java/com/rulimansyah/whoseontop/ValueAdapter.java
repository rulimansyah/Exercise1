package com.rulimansyah.whoseontop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rulimansyah.whoseontop.model.Value;

import java.util.List;

public class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.MyViewHolder> {
    List<Value> mValueList;
    RecyclerView mRecyclerView;

    public ValueAdapter(List<Value> valueList){
        mValueList = valueList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        Log.i("ERROR", "Length : " + i);

        holder.mTextView.setText(mValueList.get(i).getJoke());

        if(i == 0){
            holder.mImageView.setImageResource(R.drawable.ic_star_black);
        }else{
            holder.mImageView.setImageResource(R.drawable.ic_arrow_upward_black);
        }

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Up Up", Toast.LENGTH_SHORT).show();
                showDialog(v);
            }
        });

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        // default number of item
        int numOfItem = 3;

        if(MainActivity.numberOfRow != 0) {
            numOfItem += MainActivity.numberOfRow;
        }

        return numOfItem;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ImageView mImageView;

        public MyViewHolder(View itemView){
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textJokes);
            mImageView = (ImageView) itemView.findViewById(R.id.imgJokes);
        }
    }

    private void showDialog(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());

        // set title dialog
        alertDialogBuilder.setTitle("Message");

        // set message
        alertDialogBuilder
                .setMessage("Please drag me to the top!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show alert dialog
        alertDialog.show();
    }
}
