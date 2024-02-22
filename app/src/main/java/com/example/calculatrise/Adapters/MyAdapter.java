package com.example.calculatrise.Adapters;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculatrise.Database.Archive;
import com.example.calculatrise.Listeners.onButtonArciveDeleteClicked;
import com.example.calculatrise.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomArchive> {
 private List<Archive>archive ;

   private onButtonArciveDeleteClicked listener ;


    public MyAdapter() {
    }

    public MyAdapter(List<Archive> archive, onButtonArciveDeleteClicked listener) {
        this.archive = archive;
        this.listener = listener;

    }

    public List<Archive> getArchive() {
        return archive;
    }

    public void setArchive(List<Archive> archive) {
        this.archive = archive;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CustomArchive onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomArchive(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_archive_item , viewGroup, false));
    }
    @Override
    public void onBindViewHolder(@NonNull CustomArchive customArchive, int i) {
        Archive    archive1 = archive.get(i);
        customArchive.tv_archive.setText(archive1.getProcess()+ " " + archive1.getEqualSymbol()+ " " + archive1.getResult());
customArchive.btn_remove.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onArchiveDeleted(archive1);
        }
    }
});


    }



    @Override
    public int getItemCount() {
        return archive.size();
    }


    class CustomArchive extends RecyclerView.ViewHolder {
        TextView tv_archive;
        ImageButton btn_remove ;
        public CustomArchive(@NonNull View itemView) {
            super(itemView);
            tv_archive = itemView.findViewById(R.id.cus_tv_pro);

            btn_remove = itemView.findViewById(R.id.btn_remove);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    btn_remove.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            btn_remove.setVisibility(View.INVISIBLE);
                        }
                    };
                    handler.postDelayed(runnable,2000);

                    return true;
                }
            });
        }

    }}


