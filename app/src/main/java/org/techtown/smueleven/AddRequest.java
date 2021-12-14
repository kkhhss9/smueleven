package org.techtown.smueleven;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddRequest extends StringRequest{

    final static private String URL = "http://stock5424.dothome.co.kr/Add.php";//서버 URL 설정 ( PHP 파일 연동 )
    private Map<String, String> parameters;

    //생성자
    public AddRequest(String productNum, String productName, int productCount, String productDate, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("productNum", productNum);
        parameters.put("productName", productName);
        parameters.put("productCount", productCount + "");
        parameters.put("productDate", productDate);

    }

    //추후 사용을 위한 부분
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}