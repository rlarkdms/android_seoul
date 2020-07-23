package com.example.crawling2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final Context mContext;
    //데이터 배열 선언
    private ArrayList<ItemObject> mList;

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView_img;
        private TextView textView_title, textView_release, textView_director,textView_detail;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView_img = (ImageView) itemView.findViewById(R.id.imageView_img);
            textView_title = (TextView) itemView.findViewById(R.id.textView_title);
            textView_release = (TextView) itemView.findViewById(R.id.textView_release);
            textView_director = (TextView) itemView.findViewById(R.id.textView_director);
            textView_detail = (TextView) itemView.findViewById(R.id.textView_detail);

        }
    }

    //생성자
    public MyAdapter(Context mContext,ArrayList<ItemObject> list) {
    
        this.mContext=mContext;
        this.mList = list;
    }
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.second_re, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, final int position) {

        holder.textView_title.setText(String.valueOf(mList.get(position).getTitle()));
        holder.textView_release.setText(String.valueOf(mList.get(position).getRelease()));
        holder.textView_director.setText(String.valueOf(mList.get(position).getDirector()));
        //holder.textView_detail.setText(String.valueOf(mList.get(position).getDetail_link()));
        //다 해줬는데도 GlideApp 에러가 나면 rebuild project를 해주자.
        GlideApp.with(holder.itemView).load(mList.get(position).getImg_url())
                .override(300,400)
                .into(holder.imageView_img);

        holder.itemView.setOnClickListener(new View.OnClickListener(){

//씨발 여기 고쳐야함
            @Override
            public void onClick(View v){

                Context context=v.getContext();

                Intent intent=new Intent(v.getContext(),ThirdActivity.class);

                intent.putExtra("title",mList.get(position).getTitle());
                intent.putExtra("event_url",mList.get(position).getDetail_link());

                mContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                Toast.makeText(context, position+"번째 아이템 클릭", Toast.LENGTH_LONG).show();
            }


        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}