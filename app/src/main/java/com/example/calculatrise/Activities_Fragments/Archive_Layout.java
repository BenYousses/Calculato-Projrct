package com.example.calculatrise.Activities_Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.calculatrise.Adapters.MyAdapter;
import com.example.calculatrise.Database.Archive;
import com.example.calculatrise.Database.ViewsModels.MyViewModel;
import com.example.calculatrise.Listeners.onButtonArciveDeleteClicked;
import com.example.calculatrise.R;
import com.example.calculatrise.databinding.ActivityArchiveLayoutBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Archive_Layout extends AppCompatActivity implements Delete_Fragment.onPositiveBtnClickeListener ,
        onButtonArciveDeleteClicked{
   private ActivityArchiveLayoutBinding binding;
   private MyAdapter adapter;
private onButtonArciveDeleteClicked listener ;

  private  MyViewModel mvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityArchiveLayoutBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        adapter = new MyAdapter(new ArrayList<>(),this);
        setSupportActionBar(binding.toolbar);
        mvm = new ViewModelProvider(this).get(MyViewModel.class);
        binding.rv.setAdapter(adapter);
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        mvm.getAllArchives().observe(this, new Observer<List<Archive>>() {
            @Override
            public void onChanged(List<Archive> archives) {
                adapter.setArchive(archives);
            }
        });

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.archive_delete_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            if (adapter.getItemCount() <= 0) {

                Toast.makeText(this, "No Data For Deleting", Toast.LENGTH_SHORT).show();

            } else {

                Delete_Fragment fragment = new Delete_Fragment().newInstance("DELETE ALL", "Clear Archive and Memory ?");
                fragment.show(getSupportFragmentManager(), null);
            }

        }
        return true;
    }

//if pressed in Positie Button
    @Override
    public void onPositveBtnCliked() {
        mvm.deleteAllArchive();

        Snackbar.make(binding.archiveScreen, "All deleted successfully...", Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onArchiveDeleted(Archive archive) {
        mvm.deleteArchiveById(archive);
        Snackbar.make(binding.archiveScreen, "item deleted successfully...", Snackbar.LENGTH_SHORT)
                .show();




    }


    }


