package cn.edu.ncu.newmedia.util.message;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiServlet {

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    /**
     * 单条短信发送，智能匹配短信模板
     *
     * @param apikey 后台查看
     * @param text
     * @param mobile 接收的手机号码
     * @return json格式字符串
     */

    public static String sendMessage(String apikey, String text, String mobile) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post("https://sms.yunpian.com/v2/sms/single_send.json", params);
    }

    /**
     * 基于HttpClient 4.3的通用post方法
     *
     * @param url    提交的URL
     * @param params 提交<参数,值>Map
     * @return 提交响应
     */
    private static String post(String url, Map<String, String> params) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";//响应内容
        CloseableHttpResponse response = null;

        try {
            HttpPost method = new HttpPost(url);
            if (params != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();

                for (Map.Entry<String, String> param : params.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));

            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity, ENCODING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }

    /*随机生成6位数的验证码*/
    public static String newverification() {
        String s = "";
        for (int i = 0; i < 6; i++) {
            int n = (int) (Math.random() * 10);
            s += n;
        }
        return s;
    }

}
