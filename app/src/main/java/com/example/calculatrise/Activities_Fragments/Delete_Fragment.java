package com.example.calculatrise.Activities_Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculatrise.R;


public class Delete_Fragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_TITLE = "title";
    private static final String ARG_MESSAGE = "message";
    private String Message;
    private String Title;
    onPositiveBtnClickeListener PositiveBtnClick;



    public Delete_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onPositiveBtnClickeListener){
            PositiveBtnClick = (onPositiveBtnClickeListener) context; 
        }else {
            Toast.makeText(context, "Please Impliment listener positiveClickListner", Toast.LENGTH_SHORT).show();
        }
    }

    public static Delete_Fragment newInstance(String title, String message) {
        Delete_Fragment fragment = new Delete_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Message = getArguments().getString(ARG_MESSAGE);
            Title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.custom_delete_fragment,container, false);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_message = view.findViewById(R.id.tv_Messages);
        TextView tv_title = view.findViewById(R.id.tv_Titles);
        Button  positive_btn = view.findViewById(R.id.btn_positive_custom);
        Button  negative_btn = view.findViewById(R.id.btn_negetive_custom);
        tv_message.setText(Message);
        tv_title.setText(Title);
        positive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PositiveBtnClick.onPositveBtnCliked();
                dismiss();
            }
        });
        negative_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();

            }
        });

    }

    public interface onPositiveBtnClickeListener{
        void onPositveBtnCliked();
    }

}