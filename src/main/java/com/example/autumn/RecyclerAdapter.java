package com.example.autumn;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView .Adapter<RecyclerAdapter.MyViewHolder> {

    List<Player> productList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(String name, Integer position, String age, String appearances, String goals, String form, String position1);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    public void updateProductList(List<Player> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Player player = productList.get(position);
        holder.textView.setText(player.getName());
        holder.textViewAge.setText(player.getAge());
        holder.textViewPosition.setText(player.getPosition());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        private static final String TAG = "MyViewHolder";
        ImageView imageView;
        TextView textView;
        TextView textViewAge;
        TextView textViewPosition;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textViewAge = itemView.findViewById(R.id.textView2);
            textViewPosition = itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();


                    if (onItemClickListener != null && position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClick(
                                String.valueOf(productList.get(position).getName()),
                                position, productList.get(position).getAge(),
                                productList.get(position).getAppearances(),
                                productList.get(position).getGoals(),
                                productList.get(position).getForm(),
                                productList.get(position).getPosition());
                    }
                }
            });
        }

        void bindView(String name, String age, String position) {
            textView.setText(name);
            textViewAge.setText(age);
            textViewPosition.setText(position);
        }

        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }

        private void showPopupMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.popup_add_to_basket:
                    Log.d(TAG, "onMenuItemClick: action_popup_edit @ " + getAdapterPosition());
                    return true;
                case R.id.popup_see_info:
                    Log.d(TAG, "onMenuItemClick: action_popup_delete @ " + getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }


}
