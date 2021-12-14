package org.techtown.smueleven;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class ContentListAdapter extends BaseAdapter{

    private Context context;
    private List<Content> contentList;
    private Activity parentActivity;//삭제 강의때 추가
    private List<Content> saveList;
    //여기서 Actvitivy parentActivity가 추가됨 삭제 및 관리자기능 예제

    public ContentListAdapter(Context context, List<Content> contentList, Activity parentActivity, List<Content> saveList){
        this.context=context;
        this.contentList=contentList;
        this.parentActivity=parentActivity;//삭제용
        this.saveList=saveList;//검색용
    }


    //출력할 총갯수를 설정하는 메소드
    @Override
    public int getCount() {
        return contentList.size();
    }
    //상품들을 반환하는 메소드
    @Override
    public Object getItem(int i) {
        return contentList.get(i);
    }
    //아이템별 아이디를 반환하는 메소드
    @Override
    public long getItemId(int i) {
        return i;
    }

    //가장 중요한 부분
    @Override
    //int i 에서 final int i 로 바뀜 이유는 deleteButton.setOnClickListener에서 이 값을 참조하기 때문
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.table_content, null);

        //뷰에 다음 컴포넌트들을 연결시켜줌
        //final추가 안붙이면 에러남 리스너로 전달하고 싶은 지역변수는 final로 처리해야됨
        final TextView productNum = (TextView)v.findViewById(R.id.productNum);
        TextView productName = (TextView)v.findViewById(R.id.productName);
        TextView productDate = (TextView)v.findViewById(R.id.productDate);
        TextView productCount = (TextView)v.findViewById(R.id.productCount);
        productName.setText(contentList.get(i).getContent());
        productNum.setText(contentList.get(i).getNum());
        productCount.setText(String.valueOf(contentList.get(i).getProductCount()));
        productDate.setText(contentList.get(i).getDate());

        //이렇게하면 findViewWithTag를 쓸 수 있음 없어도 되는 문장임
        v.setTag(contentList.get(i).getContent());
        //만든뷰를 반환함
        return v;
    }
}
