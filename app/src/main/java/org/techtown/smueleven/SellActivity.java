package org.techtown.smueleven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class SellActivity extends AppCompatActivity {

    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        final EditText pNum2 =(EditText)findViewById(R.id.pNum2);
        final EditText pName2 = (EditText)findViewById(R.id.pName2);
        final EditText pCount2 = (EditText)findViewById(R.id.pCount2);
        final EditText setDate2 = (EditText)findViewById(R.id.setDate2);
        Button addBtn = (Button)findViewById(R.id.sellBtn2);

        setDate2.setText(formatDate);
        Intent intent2 = getIntent();

        String re = intent2.getExtras().getString("re");
        pNum2.setText(re);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productNum2 = pNum2.getText().toString();
                String productName2 = pName2.getText().toString();
                int productCount2 = Integer.parseInt(pCount2.getText().toString());
                String productDate2 = setDate2.getText().toString();
                //4. 콜백 처리부분(volley 사용을 위한 ResponseListener 구현 부분)
                //서버로부터 여기서 데이터를 받음
                Response.Listener<String> responseListener = response -> {
                    try {
                        //서버로부터 받는 데이터는 JSON타입의 객체이다.
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");

                        //저장 성공시 success값이 true임
                        if(success){
                            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                            //그리고 첫화면으로 돌아감
                            Intent intent = new Intent(SellActivity.this, MainActivity.class);
                            SellActivity.this.startActivity(intent);
                        }
                        //저장 실패시 success값이 false임
                        else{
                            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                        }
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                };//responseListener 끝
                //volley 사용법
                //1. RequestObject를 생성한다. 이때 서버로부터 데이터를 받을 responseListener를 반드시 넘겨준다.
                SellRequest sellRequest = new SellRequest(productNum2, productName2, productCount2, productDate2, responseListener);
                //2. RequestQueue를 생성한다.
                RequestQueue queue = Volley.newRequestQueue(SellActivity.this);
                //3. RequestQueue에 RequestObject를 넘겨준다.
                queue.add(sellRequest);

            }
        });
    }



}
