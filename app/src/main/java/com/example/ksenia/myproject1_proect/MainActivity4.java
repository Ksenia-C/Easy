package com.example.ksenia.myproject1_proect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//активность - главное меню
public class MainActivity4 extends AppCompatActivity {
    Button totr1,totr2,todescr, toauthor,tohelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setTitle(getString(R.string.title_of_pr));
        totr1=(Button)findViewById(R.id.totranslate);
        totr2=(Button)findViewById(R.id.totraspon);
        todescr=(Button)findViewById(R.id.toask);
        tohelp=(Button)findViewById(R.id.togivehelp);
        toauthor=(Button)findViewById(R.id.tosendletter);
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v){
                switch(v.getId()){
                    //в переводчик в С++
                    case R.id.totranslate: {
                        startActivity(new Intent(MainActivity4.this, MainActivity2.class));
                        break;
                    }
                    //в переводчик в паскаль
                    case R.id.totraspon: {

                        Toast toast = Toast.makeText(getApplicationContext(),
                                " Вы не можете воспользоваться этой функцией. Она в разработке.", Toast.LENGTH_LONG);

                        toast.show();
                        break;
                    }
                    //в справочник
                    case R.id.toask:{
                        startActivity(new Intent(MainActivity4.this, MainActivity5.class));
                        break;
                    }
                    //в активность для отправки письма
                    case R.id.tosendletter:{
                        startActivity(new Intent(MainActivity4.this, MainActivity6.class));
                        break;
                    }
                    //в активность с небольшими пояснениями про приложение
                    case R.id.togivehelp:{
                        startActivity(new Intent(MainActivity4.this, MainActivity7.class));
                        break;
                    }
                }
            }
        };
        totr1.setOnClickListener(listener);
        totr2.setOnClickListener(listener);
        todescr.setOnClickListener(listener);
        tohelp.setOnClickListener(listener);
        toauthor.setOnClickListener(listener);
    }
}
