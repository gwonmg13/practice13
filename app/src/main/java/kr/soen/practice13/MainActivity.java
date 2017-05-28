package kr.soen.practice13;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageView imageButton,food_image;
    TextView text_count;
    myTask task1;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton = (ImageView)findViewById(R.id.imageButton);
        food_image = (ImageView)findViewById(R.id.food_image);
        text_count = (TextView)findViewById(R.id.text_count);
        et =  (EditText)findViewById(R.id.editText);

    }

    public void onClick(View v){

        if(v.getId() == R.id.imageButton){

            text_count.setVisibility(View.VISIBLE);
            imageButton.setVisibility(View.GONE);
            food_image.setVisibility(View.VISIBLE);
            task1 = new myTask();
            task1.execute(1);


        }else if(v.getId() == R.id.food_image){

            task1.cancel(true);
            task1 = null;

        }else if(v.getId() == R.id.button){


            et.setText("");
            text_count.setVisibility(View.GONE);
            imageButton.setVisibility(View.VISIBLE);
            food_image.setVisibility(View.GONE);
            //task1.cancel(true);
            //task1 = null;
        }

    }

    class myTask extends AsyncTask<Integer,Integer,Void>{

        //UI를 건드릴수 있는 영역
        String count = et.getText().toString();
        int count_num =  Integer.parseInt(count);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        //얘가 실행이 다되면 doInBackgroud실행

        @Override
        protected Void doInBackground(Integer... params) {

            int i = 0;
            while(true){
                try {

                    if(isCancelled()==true){
                        return null;
                    }

                        Thread.sleep(1000);
                        publishProgress(i);
                    // 이게 프로세스로 넘어간다.
                    i++;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }

        int select_count = 0;
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            text_count.setText("시작부터 "+values[0]+" 초");

            if(values[0] / count_num % 5 == 0) {
                food_image.setImageResource(R.drawable.burger);
            }else if(values[0] / count_num % 5==1){
                food_image.setImageResource(R.drawable.macaron);
            }else if(values[0] / count_num % 5==2){
                food_image.setImageResource(R.drawable.donut);
            }else if(values[0] / count_num % 5==3){
                food_image.setImageResource(R.drawable.fries);
            }else if(values[0] / count_num % 5==4){
                food_image.setImageResource(R.drawable.pizza);
            }
            select_count = values[0];
        }

        // iscancled함수가 true가 아니면 여기로 오게 된다.
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onCancelled() {

            text_count.setText("피자선택 "+select_count+"초)");
            
            if(select_count / count_num % 5 == 0) {
                  text_count.setText("햄버거선택 ("+select_count+" 초)");
            }else if(select_count / count_num % 5==1){
                  text_count.setText("마카롱선택 ("+select_count+" 초)");
            }else if(select_count / count_num % 5==2){
                  text_count.setText("도넛선택 ("+select_count+" 초)");
            }else if(select_count / count_num % 5==3){
                  text_count.setText("감튀선택 ("+select_count+" 초)");
            }else if(select_count / count_num % 5==4){
                  text_count.setText("피자선택 ("+select_count+" 초)");
            }
            super.onCancelled();
        }
    }
}
