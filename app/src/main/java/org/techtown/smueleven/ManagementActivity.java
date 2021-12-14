package org.techtown.smueleven;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ManagementActivity extends AppCompatActivity {
    private ListView listView;

    private ContentListAdapter adapter;

    private List<Content> contentList;

    private List<Content> saveList;//상품검색 기능 복사본


    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.table);
        Intent intent = getIntent();

        ListView listView = (ListView) findViewById(R.id.listView);
        //ArrayList<Content> contentList = new ArrayList<Content>();
        contentList = new ArrayList<Content>();
        saveList = new ArrayList<Content>();
        //어댑터 초기화부분 userList와 어댑터를 연결해준다.
        // ContentListAdapter adapter = new ContentListAdapter(getApplicationContext(), contentList);
        adapter = new ContentListAdapter(getApplicationContext(), contentList, this, saveList);//로 수정됨
        listView.setAdapter(adapter);
        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("contentList"));
            //Table.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String productName, productNum, productDate;
            int productCount;
            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);
                productNum = object.getString("productNum");
                productName = object.getString("productName");
                productCount = Integer.parseInt(String.valueOf(object.getInt("productCount")));
                productDate = object.getString("productDate");
                //값들을 User클래스에 묶어준다
                Content content1 = new Content(productNum,productName,productCount,productDate);
                contentList.add(content1);//리스트뷰에 값을 추가
                saveList.add(content1);//바코드 검색 기능
                count++;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        EditText search = (EditText)findViewById(R.id.search);

        search.addTextChangedListener(new TextWatcher() {

            @Override

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                searchContent(charSequence.toString());//바코드 검색 기능

            }

            @Override

            public void afterTextChanged(Editable editable) {

            }

        });

    }

    public void searchContent(String search){

        contentList.clear();

        for(int i = 0; i < saveList.size(); i++){

            if(saveList.get(i).getNum().contains(search)){//contains메소드로 search 값이 있으면 true를 반환함

                contentList.add(saveList.get(i));

            }

        }

        adapter.notifyDataSetChanged();//어댑터에 값일 바뀐것을 알려줌

    }

}