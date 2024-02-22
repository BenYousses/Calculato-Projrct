package com.example.calculatrise.Activities_Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.calculatrise.Adapters.MyAdapter;
import com.example.calculatrise.Database.Archive;
import com.example.calculatrise.Database.ViewsModels.MyViewModel;
import com.example.calculatrise.R;
import com.example.calculatrise.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String RES_NAME = "Result";
    public static final String PRO_NAME = "Process";
   private String newResut = "";
   private ActivityMainBinding binding ;
private String outPut = "" ;
private String newoutPut = "" ;
private String input = "" ;
private int DotCounter = 1 ;
private int NumCounter = 0;
private int Counter = 1;
private String process ="";
private String FinalResult = "";
private MyViewModel mvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        mvm = new ViewModelProvider(this).get(MyViewModel.class);
        setSupportActionBar(binding.toolbar);
        binding.ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.inPutTv.setText("Calculator");
                binding.result.setText("");
                input = "";
            }
        });

     binding.off.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             binding.inPutTv.setVisibility(View.INVISIBLE);
             binding.result.setVisibility(View.INVISIBLE);
             DotCounter=0 ;
             DotCounter++;
         }
     });
        binding.on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.inPutTv.setVisibility(View.VISIBLE);
                binding.inPutTv.setText("Calculator");
                binding.result.setText("");
                input = "";
                binding.result.setVisibility(View.VISIBLE);
            }
        });

        ArrayList<Button> btnNum = new ArrayList<>();
        btnNum.add(binding.num0);
        btnNum.add(binding.num1);
        btnNum.add(binding.num2);
        btnNum.add(binding.num3);
        btnNum.add(binding.num4);
        btnNum.add(binding.num5);
        btnNum.add(binding.num6);
        btnNum.add(binding.num7);
        btnNum.add(binding.num8);
        btnNum.add(binding.num9);
        ArrayList<Button> oper = new ArrayList<>();
        oper.add(binding.plus);
        oper.add(binding.Miltply);
        oper.add(binding.min);
        oper.add(binding.div);



        for (Button numBtn : btnNum){
            numBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NumCounter >=0 && NumCounter <= 7){
                        String numData = numBtn.getText().toString();
                        input += numData;
                        binding.inPutTv.setText(input);
                        Counter++;
                        NumCounter++;
                    }else {
                        Toast.makeText(MainActivity.this, "We Don't Have A Posiibility For This Quantity Of This Numbers", Toast.LENGTH_SHORT).show();
                 return;
                   }


                }
            });
        }
        for (Button opers : oper){
            opers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {String operData = opers.getText().toString();
                    if ( Counter >=1){
                        if (!input.contains(operData) || Counter==0){
                            input += operData ;
                            outPut = "";
                            binding.inPutTv.setText(input);
                      }else {
                            Toast.makeText(MainActivity.this, "We Don't Have Ability For Calculat a Long Proess", Toast.LENGTH_SHORT).show();
                        }
                    }

                    DotCounter++;
                    Counter = 0 ;
                    NumCounter = 1;
                }
            });
        }

binding.equal.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        solve();

        if (TextUtils.isEmpty(process) && TextUtils.isEmpty(newResut)) {
            Toast.makeText(MainActivity.this, "The Process Is empty", Toast.LENGTH_SHORT).show();
        return;
        }else {
            Archive archive = new Archive(process,"=",newResut);

            mvm.insertArchive(archive);
            process ="";
            FinalResult = "";
            newResut = "";
        }

    }

});



        binding.dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DotCounter >= 1 ){
                    String dotData = binding.dot.getText().toString();
                    input += dotData ;
                    binding.inPutTv.setText(input);
                    DotCounter=0;
                }

            }
        });
        binding.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = binding.inPutTv.getText().toString();
                if (num.length()>1){
                    binding.inPutTv.setText(num.substring(0, num.length()-1));


                }else if (num.length() == 1 || num.equals("0")){
                    binding.inPutTv.setText("Calclator");
                    input = "";
                }
            }
        });

    }

    private String positiveNumber(String reNum) {

     String n[] = reNum.split("\\.");
     if (n[1].length() > 0){
         if (n[1].equals("0")){
             reNum = n[0];
         }


     }
     return reNum ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
getMenuInflater().inflate(R.menu.archive_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.Archive_item);
        Intent intent = new Intent(getBaseContext(),Archive_Layout.class);

        startActivity(intent);

        return true;
    }
    private void solve() {

        if (input.split("\\+").length == 2){
            String []n = input.split("\\+");
            try {
                double d = Double.parseDouble(n[0]) + Double.parseDouble(n[1]);
                outPut += String.valueOf(d);
                process  += binding.inPutTv.getText().toString();
                FinalResult = String.valueOf(d);
                newResut = positiveNumber(FinalResult);
                newoutPut = positiveNumber(outPut);
                binding.result.setText(newoutPut);
                input = binding.inPutTv.getText().toString();
                binding.inPutTv.setText(newoutPut);
                input = binding.inPutTv.getText().toString();
                Counter++;
            }catch (Exception e){
                binding.inPutTv.setError(e.getMessage());
            }
        }
        if (input.split("\\-").length == 2){
            String []n = input.split("\\-");
            try {
                double d = Double.parseDouble(n[0]) - Double.parseDouble(n[1]);
                outPut = String.valueOf(d);
                process  = binding.inPutTv.getText().toString();
                FinalResult = String.valueOf(d);
                newResut = positiveNumber(FinalResult);
                newoutPut = positiveNumber(outPut);
                binding.result.setText(newoutPut);
                input = binding.inPutTv.getText().toString();
                binding.inPutTv.setText(newoutPut);
                input = binding.inPutTv.getText().toString();
                Counter++;
            }catch (Exception e){
                binding.inPutTv.setError(e.getMessage());
            }
        }
        if (input.split("\\*").length == 2){
            String []n = input.split("\\*");
            try {
                double d = Double.parseDouble(n[0]) * Double.parseDouble(n[1]);
                outPut = String.valueOf(d);
                FinalResult = String.valueOf(d);
                newResut = positiveNumber(FinalResult);
                process  = binding.inPutTv.getText().toString();
                newoutPut = positiveNumber(outPut);
                binding.result.setText(newoutPut);
                input = binding.inPutTv.getText().toString();
                binding.inPutTv.setText(newoutPut);
                input = binding.inPutTv.getText().toString();
                Counter++;
            }catch (Exception e){
                binding.inPutTv.setError(e.getMessage());
            }

        }
        if (input.split("\\/").length == 2){
            String []n = input.split("\\/");
            try {
                double d = Double.parseDouble(n[0]) / Double.parseDouble(n[1]);
                outPut = String.valueOf(d);
                FinalResult = String.valueOf(d);
                newResut = positiveNumber(FinalResult);
                process  = binding.inPutTv.getText().toString();
                newoutPut = positiveNumber(outPut);
                binding.result.setText(newoutPut);
                input = binding.inPutTv.getText().toString();
                binding.inPutTv.setText(newoutPut);
                input = binding.inPutTv.getText().toString();
                Counter++;
            }catch (Exception e){
                binding.inPutTv.setError(e.getMessage());
            }
        }



    }

}