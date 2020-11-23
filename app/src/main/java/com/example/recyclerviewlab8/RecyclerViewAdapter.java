package com.example.recyclerviewlab8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "test.sliit.recyclerview.RecyclerViewAdapter";
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private Context mContext;

    //Constructor
    public RecyclerViewAdapter(ArrayList<String> mImageNames, ArrayList<String> mImage, Context mContext) {
        this.mImageNames = mImageNames;
        this.mImage = mImage;
        this.mContext = mContext;
    }

    /* RecyclerView calls this method whenever it needs to create a new ViewHolder.
     The method creates and initializes the ViewHolder and its associated View,
      but does not fill in the view's contents
      —the ViewHolder has not yet been bound to specific data. */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /* RecyclerView calls this method to associate a ViewHolder with data.
    The method fetches the appropriate data and uses the data to fill in the view holder's layout.
    For example, if the RecyclerView dislays a list of names,
    the method might find the appropriate name in the list and fill in the view holder's TextView widget. */
    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(mContext)
                .asBitmap().load(mImage.get(position))
                .into(holder.image);
        holder.imageName.setText(mImageNames.get(position));
        holder.imageName.setTextColor(Color.parseColor("#0000ff"));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on"+mImageNames.get(position));

                Toast.makeText(mContext,mImageNames.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*  RecyclerView calls this method to get the size of the data set.
    For example, in an address book app, this might be the total number of addresses.
     RecyclerView uses this to determine when there are no more items that can be displayed */
    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
