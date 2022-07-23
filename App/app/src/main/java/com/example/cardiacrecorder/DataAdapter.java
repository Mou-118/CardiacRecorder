package com.example.cardiacrecorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    Context context;
    ArrayList<DataModel> dmArrayList;
    static RecyclerViewClickListener listener;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;


    /**
     * It takes parameter for DataAdapter
     * @param context
     * @param dmArrayList
     * @param listener
     */
    public DataAdapter(Context context, ArrayList<DataModel> dmArrayList , RecyclerViewClickListener listener){
        this.context=context;
        this.dmArrayList=dmArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recyclerrow,parent, false);
        return new DataAdapter.MyViewHolder(view);
    }

    /**
     * It defines the workings of all the elements of cardview
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull DataAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvDate.setText(dmArrayList.get(position).getDate().toString());
        holder.tvDia.setText(dmArrayList.get(position).getDiastolic().toString());
        holder.tvSys.setText(dmArrayList.get(position).getSystolic().toString());

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("index",position);
                context.startActivity(intent);

            }
        });
/**
 * Defines the working method of delete button
 */
        holder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dmArrayList.remove(position);
                sharedPreferences = context.getSharedPreferences("uday",Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                gson = new Gson();
                String jsonString = gson.toJson(dmArrayList);
                editor.putString("record",jsonString);
                editor.apply();
                HomeScreen.dataAdapter.notifyDataSetChanged();
            }
        });


    }

    /**
     * It returns size of dmArrayList
     * @return
     */
    @Override
    public int getItemCount() {
        return dmArrayList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvDate, tvSys, tvDia;
        Button editButton,delButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate=itemView.findViewById(R.id.datetext);
            tvSys=itemView.findViewById(R.id.sys);
            tvDia=itemView.findViewById(R.id.dia);
            editButton = itemView.findViewById(R.id.editDataButton);
            delButton = itemView.findViewById(R.id.deleteDataButton);
            itemView.setOnClickListener(this);


        }

        /**
         * It returns the position of the clicked element
         * @param v
         */
        @Override
        public void onClick(View v) {
            listener.onClick(v, getAbsoluteAdapterPosition());
        }


    }

    /**
     * It returns the position when RecyclerView is clicked
     */
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }


}
