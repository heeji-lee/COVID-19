package com.example.covid_19application.ui.mypage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19application.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    List<ChatData> chatData;
    String nickname;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nickname,msg;
        View rootView;

        public MyViewHolder(View view){
            super(view);
            nickname=view.findViewById(R.id.nickname);
            msg=view.findViewById(R.id.msg);
            rootView=view;

            view.setClickable(true);
            view.setEnabled(true);
        }
    }

    public ChatAdapter(List<ChatData> mychatData, ChatActivity context, String mynickname){
        chatData=mychatData;
        this.nickname=mynickname;
    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout=(LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_msgbox,parent,false);
        MyViewHolder vh=new MyViewHolder(linearLayout);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {
        ChatData chat= chatData.get(position);

        holder.nickname.setText(chat.getNickname());
        holder.msg.setText(chat.getMsg());

        if(chat.getNickname().equals(this.nickname)){
            holder.nickname.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            holder.msg.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        }else{
            holder.nickname.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            holder.msg.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        }
    }

    @Override
    public int getItemCount() {
        return chatData==null?0:chatData.size();
    }

    public ChatData getChat(int position){
        return chatData!=null?chatData.get(position):null;
    }

    public void addChat(ChatData chat){
        chatData.add(chat);
        notifyItemInserted(chatData.size()-1);
    }
}