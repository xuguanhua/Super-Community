package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import sdk.GeetestLib;


/**
 * ʹ��post��ʽ��������֤���, request���б������challenge, validate, seccode
 */
public class VerifyLoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), 
				GeetestConfig.isnewfailback());
			
		String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
		String validate = request.getParameter(GeetestLib.fn_geetest_validate);
		String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
		
		//��session�л�ȡgt-server״̬
		int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
		
		//��session�л�ȡuserid
		String userid = (String)request.getSession().getAttribute("userid");
		
		//�Զ������,��ѡ�����
		HashMap<String, String> param = new HashMap<String, String>(); 
		param.put("user_id", userid); //��վ�û�id
		param.put("client_type", "web"); //web:�����ϵ��������h5:�ֻ��ϵ�������������ƶ�Ӧ������ȫ���õ�web_view��native��ͨ��ԭ��SDKֲ��APPӦ�õķ�ʽ
		param.put("ip_address", "127.0.0.1"); //�����û�������֤ʱ��Я����IP
		
		int gtResult = 0;

		if (gt_server_status_code == 1) {
			//gt-server��������gt-server���ж�����֤
				
			gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
			System.out.println(gtResult);
		} else {
			// gt-server����������£�����failbackģʽ��֤
				
			System.out.println("failback:use your own server captcha validate");
			gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
			System.out.println(gtResult);
		}


		if (gtResult == 1) {
			// ��֤�ɹ�
			PrintWriter out = response.getWriter();
			JSONObject data = new JSONObject();
			try {
				data.put("status", "success");
				data.put("version", gtSdk.getVersionInfo());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(data.toString());
		}
		else {
			// ��֤ʧ��
			JSONObject data = new JSONObject();
			try {
				data.put("status", "fail");
				data.put("version", gtSdk.getVersionInfo());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.println(data.toString());
		}

	}
}
