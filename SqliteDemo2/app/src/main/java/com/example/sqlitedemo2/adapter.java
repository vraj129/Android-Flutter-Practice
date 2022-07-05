package com.example.sqlitedemo2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapter extends RecyclerView.Adapter<adapter.FruitViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    Activity activity;
    public adapter(Activity activity,Context context, Cursor cursor)
    {
        this.mContext = context;
        this.mCursor = cursor;
        this.activity = activity;
    }

    public class FruitViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public LinearLayout mLinearLayout;
        public FruitViewHolder( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            mLinearLayout = itemView.findViewById(R.id.updatelayout);
        }
    }

    @Override
    public FruitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_view,parent,false);
        return new FruitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FruitViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))
        {
            return;
        }
        String name1 = mCursor.getString(mCursor.getColumnIndex(fruitlist.fruitadd.COLUMN_NAME));
        long id = mCursor.getLong(mCursor.getColumnIndex(fruitlist.fruitadd._ID));
        holder.name.setText(name1);
        holder.itemView.setTag(id);
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp_id = String.valueOf(id);
                Intent i = new Intent(mContext,UpdateItem.class);
                i.putExtra("id",temp_id);
                i.putExtra("name",name1);
                activity.startActivityForResult(i,1);
            }
        });
    }



    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor)
    {
        if(mCursor != null)
        {
            mCursor.close();
        }
        mCursor = newCursor;
        if(newCursor != null)
        {
            notifyDataSetChanged();
        }
    }
}
