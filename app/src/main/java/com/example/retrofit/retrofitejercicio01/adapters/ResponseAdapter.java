package com.example.retrofit.retrofitejercicio01.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.retrofitejercicio01.R;
import com.example.retrofit.retrofitejercicio01.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResponseAdapter extends RecyclerView.Adapter<ResponseAdapter.ResponseVH> {

    private final List<User> objects;
    private final int resources;
    private final Context context;

    public ResponseAdapter(List<User> objects, int resources, Context context) {
        this.objects = objects;
        this.resources = resources;
        this.context = context;
    }

    @NonNull
    @Override
    public ResponseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResponseVH(LayoutInflater.from(context).inflate(resources, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ResponseVH holder, int position) {
        User u = objects.get(position);
        holder.lblNombre.setText(u.getFirstName()+" "+u.getLastName());
        holder.lblEmail.setText(u.getEmail());
        Picasso.get()
                .load(u.getAvatar())
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgAvatar);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }


    public class ResponseVH extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        TextView lblNombre;
        TextView lblEmail;

        public ResponseVH(@NonNull View itemView) {
            super(itemView);

            imgAvatar = itemView.findViewById(R.id.imgAvatarUser);
            lblNombre = itemView.findViewById(R.id.lblNombreUser);
            lblEmail = itemView.findViewById(R.id.lblEmailUser);
        }
    }
}
