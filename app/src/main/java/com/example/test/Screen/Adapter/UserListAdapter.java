package com.example.test.Screen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.RoomDB.Entity.entityClass;
import com.example.test.ViewModel.viewModelClass;

import java.util.*;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {
    private List<entityClass> users;
    Context context;

    public UserListAdapter(Context context) {
        this.context = context;
        this.users = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_details_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(users.get(position).getName());
        holder.dob.setText("DOB: " + users.get(position).getDob());
        holder.address.setText("Address: " + users.get(position).getAddress());
        holder.age.setText("Age: " + String.valueOf(users.get(position).getAge()));
        holder.removeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelClass viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(viewModelClass.class);

                entityClass user = users.get(holder.getAdapterPosition());

                // Call the delete method from the ViewModel
                viewModel.delete(user);

                Toast.makeText(context, "User removed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public void setUsers(List<entityClass> users) {
        this.users = users;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, dob, address, age;
        LinearLayout removeUser;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            dob = itemView.findViewById(R.id.dob);
            address = itemView.findViewById(R.id.address);
            age = itemView.findViewById(R.id.age);
            removeUser = itemView.findViewById(R.id.remove_data);
        }
    }
}
