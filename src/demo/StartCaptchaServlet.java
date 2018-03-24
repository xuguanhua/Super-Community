package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdk.GeetestLib;


/**
 * ʹ��Get�ķ�ʽ����challenge��capthca_id,�˷�ʽ��ʵ��ǰ�����ȫ����Ŀ���ģʽ
 *
 */
public class StartCaptchaServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), 
				GeetestConfig.isnewfailback());

		String resStr = "{}";
		
		//�Զ���userid
		String userid = "test";
		
		//�Զ������,��ѡ�����
		HashMap<String, String> param = new HashMap<String, String>(); 
		param.put("user_id", userid); //��վ�û�id
		param.put("client_type", "web"); //web:�����ϵ��������h5:�ֻ��ϵ�������������ƶ�Ӧ������ȫ���õ�web_view��native��ͨ��ԭ��SDKֲ��APPӦ�õķ�ʽ
		param.put("ip_address", "127.0.0.1"); //�����û�������֤ʱ��Я����IP

		//������֤Ԥ����
		int gtServerStatus = gtSdk.preProcess(param);
		
		//��������״̬���õ�session��
		request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
		//��userid���õ�session��
		request.getSession().setAttribute("userid", userid);
		
		resStr = gtSdk.getResponseStr();

		PrintWriter out = response.getWriter();
		out.println(resStr);

	}
}