package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ApiServlet {

	 //�����ʽ�����ͱ����ʽͳһ��UTF-8
    private static String ENCODING = "UTF-8";
	/**
	 * �������ŷ��ͣ�����ƥ�����ģ��
	 * 
	 * @param apikey ��̨�鿴
	 * @param text	
	 * @param mobile ���յ��ֻ�����
	 * @return json��ʽ�ַ���
	 */
	
	public static String sendMessage(String apikey,String text,String mobile){
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("apikey", apikey);
		params.put("text", text);
		params.put("mobile", mobile);
		return post("https://sms.yunpian.com/v2/sms/single_send.json",params);
	}

	/**
	 * ����HttpClient 4.3��ͨ��post����
	 * 
	 * @param url 		�ύ��URL
	 * @param params	�ύ<����,ֵ>Map
	 * @return			�ύ��Ӧ
	 */
	private static String post(String url, Map<String, String> params) {
		CloseableHttpClient client = HttpClients.createDefault();
		String responseText = "";//��Ӧ����
		CloseableHttpResponse response = null;
		
		try{
			HttpPost method = new HttpPost(url);
			if(params != null){
				List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				
				for(Map.Entry<String, String> param : params.entrySet()){
					NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
					paramList.add(pair);
				}
				method.setEntity(new UrlEncodedFormEntity(paramList,ENCODING));
				
			}
			response = client.execute(method);
			HttpEntity entity = response.getEntity();
			if(entity != null){
				responseText = EntityUtils.toString(entity,ENCODING);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				response.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return responseText;
	}
	
	/*�������6λ������֤��*/
	public static String newverification(){
		String s = "";
		for(int i = 0;i < 6;i++){
			int n = (int) (Math.random() * 10); 
			s += n;
		}
		return s;
	}
	
}
