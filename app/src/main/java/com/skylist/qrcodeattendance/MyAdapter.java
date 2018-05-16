package com.skylist.qrcodeattendance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<AttendanceModel> dataset;
    private Context context;

    public MyAdapter( List<AttendanceModel> dataset ){
        this.dataset = dataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_recycleview_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AttendanceModel item = dataset.get(position);
        holder.studentName.setText(item.getStudentName());
        holder.registrationNumber.setText(item.getRegistrationNumber());
        holder.subject.setText(item.getSubject());
        holder.date.setText(item.getDate());
        Picasso.get().load( item.getInstituctionURL() ).resize(80,80).into(holder.instituctImage);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView studentName, registrationNumber, subject, instituctionURL, date;
        public ImageView instituctImage;

        public MyViewHolder(View view){
            super(view);
            this.studentName        = view.findViewById(R.id.id_student_name);
            this.registrationNumber = view.findViewById(R.id.id_registration);
            this.subject            = view.findViewById(R.id.id_subject);
            this.instituctImage     = view.findViewById(R.id.id_instituction);
            this.date               = view.findViewById(R.id.id_date);
        }
    }
}
