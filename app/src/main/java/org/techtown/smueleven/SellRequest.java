package org.techtown.smueleven;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SellRequest extends StringRequest{

    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://stock5424.dothome.co.kr/Sell.php";
    private Map<String, String> parameters;

    //생성자
    public SellRequest(String productNum2, String productName2, int productCount2, String productDate2, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("productNum2", productNum2);
        parameters.put("productName2", productName2);
        parameters.put("productCount2", productCount2 + "");
        parameters.put("productDate2", productDate2);

    }

    //추후 사용을 위한 부분
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}