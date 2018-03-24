package sdk;

import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.GenericArrayType;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.print.DocFlavor.STRING;
import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.http.HttpServletRequest;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Java SDK
 * 
 */
public class GeetestLib {

	protected final String verName = "4.0";
	protected final String sdkLang = "java";

	protected final String apiUrl = "http://api.geetest.com"; 
	
	protected final String registerUrl = "/register.php"; 
	protected final String validateUrl = "/validate.php";
	
	protected final String json_format = "1";
     
	/**
	 * 鏋侀獙楠岃瘉浜屾楠岃瘉琛ㄥ崟鏁版嵁 chllenge
	 */
	public static final String fn_geetest_challenge = "geetest_challenge";
	
	/**
	 * 鏋侀獙楠岃瘉浜屾楠岃瘉琛ㄥ崟鏁版嵁 validate
	 */
	public static final String fn_geetest_validate = "geetest_validate";
	
	/**
	 * 鏋侀獙楠岃瘉浜屾楠岃瘉琛ㄥ崟鏁版嵁 seccode
	 */
	public static final String fn_geetest_seccode = "geetest_seccode";

	/**
	 * 鍏挜
	 */
	private String captchaId = "";

	/**
	 * 绉侀挜
	 */
	private String privateKey = "";
	
	/**
	 * 鏄惁寮�鍚柊鐨刦ailback
	 */
	private boolean newFailback = false;
	
	/**
	 * 杩斿洖瀛楃涓�
	 */
	private String responseStr = "";
	
	/**
	 * 璋冭瘯寮�鍏筹紝鏄惁杈撳嚭璋冭瘯鏃ュ織
	 */
	public boolean debugCode = true;
	
	/**
	 * 鏋侀獙楠岃瘉API鏈嶅姟鐘舵�丼ession Key
	 */
	public String gtServerStatusSessionKey = "gt_server_status";
	
	/**
	 * 甯﹀弬鏁版瀯閫犲嚱鏁�
	 * 
	 * @param captchaId
	 * @param privateKey
	 */
	public GeetestLib(String captchaId, String privateKey, boolean newFailback) {
		
		this.captchaId = captchaId;
		this.privateKey = privateKey;
		this.newFailback = newFailback;
	}
	
	/**
	 * 鑾峰彇鏈楠岃瘉鍒濆鍖栬繑鍥炲瓧绗︿覆
	 * 
	 * @return 鍒濆鍖栫粨鏋�
	 */
	public String getResponseStr() {
		
		return responseStr;
		
	}
	
	public String getVersionInfo() {
		
		return verName;
		
	}

	/**
	 * 棰勫鐞嗗け璐ュ悗鐨勮繑鍥炴牸寮忎覆
	 * 
	 * @return
	 */
	private String getFailPreProcessRes() {

		long rnd1 = Math.round(Math.random() * 100);
		long rnd2 = Math.round(Math.random() * 100);
		String md5Str1 = md5Encode(rnd1 + "");
		String md5Str2 = md5Encode(rnd2 + "");
		String challenge = md5Str1 + md5Str2.substring(0, 2);
		
		JSONObject jsonObject = new JSONObject();
		try {
			
			jsonObject.put("success", 0);
			jsonObject.put("gt", this.captchaId);
			jsonObject.put("challenge", challenge);
			jsonObject.put("new_captcha", this.newFailback);
			
		} catch (JSONException e) {
			
			gtlog("json dumps error");
			
		}
		
		return jsonObject.toString();
		
	}

	/**
	 * 棰勫鐞嗘垚鍔熷悗鐨勬爣鍑嗕覆
	 * 
	 */
	private String getSuccessPreProcessRes(String challenge) {
		
		gtlog("challenge:" + challenge);
		
		JSONObject jsonObject = new JSONObject();
		try {
			
			jsonObject.put("success", 1);
			jsonObject.put("gt", this.captchaId);
			jsonObject.put("challenge", challenge);
			
		} catch (JSONException e) {
			
			gtlog("json dumps error");
			
		}
		
		return jsonObject.toString();
		
	}
	
	/**
	 * 楠岃瘉鍒濆鍖栭澶勭悊
	 *
	 * @return 1琛ㄧず鍒濆鍖栨垚鍔燂紝0琛ㄧず鍒濆鍖栧け璐�
	 */
	public int preProcess(HashMap<String, String> data) {

		if (registerChallenge(data) != 1) {
			
			this.responseStr = this.getFailPreProcessRes();
			return 0;
			
		}
		
		return 1;

	}

	/**
	 * 鐢╟aptchaID杩涜娉ㄥ唽锛屾洿鏂癱hallenge
	 * 
	 * @return 1琛ㄧず娉ㄥ唽鎴愬姛锛�0琛ㄧず娉ㄥ唽澶辫触
	 */
	private int registerChallenge(HashMap<String, String>data) {
		
		try {
			String userId = data.get("user_id");
			String clientType = data.get("client_type");
			String ipAddress = data.get("ip_address");
			
			String getUrl = apiUrl + registerUrl + "?";
			String param = "gt=" + this.captchaId + "&json_format=" + this.json_format;
			
			if (userId != null){
				param = param + "&user_id=" + userId;
			}
			if (clientType != null){
				param = param + "&client_type=" + clientType;
			}
			if (ipAddress != null){
				param = param + "&ip_address=" + ipAddress;
			}
			
			gtlog("GET_URL:" + getUrl + param);
			String result_str = readContentFromGet(getUrl + param);
			if (result_str == "fail"){
				
				gtlog("gtServer register challenge failed");
				return 0;
				
			}
			
			gtlog("result:" + result_str);
			JSONObject jsonObject = new JSONObject(result_str);
		    String return_challenge = jsonObject.getString("challenge");
		
			gtlog("return_challenge:" + return_challenge);
			
			if (return_challenge.length() == 32) {
				
				this.responseStr = this.getSuccessPreProcessRes(this.md5Encode(return_challenge + this.privateKey));
				
			    return 1;
			    
			}
			else {
				
				gtlog("gtServer register challenge error");
					
				return 0;
				
			}
		} catch (Exception e) {
			
			gtlog(e.toString());
			gtlog("exception:register api");
			
		}
		return 0;
	}
	
	/**
	 * 鍒ゆ柇涓�涓〃鍗曞璞″�兼槸鍚︿负绌�
	 * 
	 * @param gtObj
	 * @return
	 */
	protected boolean objIsEmpty(Object gtObj) {
		
		if (gtObj == null) {
			
			return true;
			
		}

		if (gtObj.toString().trim().length() == 0) {
			
			return true;
			
		}

		return false;
	}

	/**
	 * 妫�鏌ュ鎴风鐨勮姹傛槸鍚﹀悎娉�,涓変釜鍙鏈変竴涓负绌猴紝鍒欏垽鏂笉鍚堟硶
	 * 
	 * @param request
	 * @return
	 */
	private boolean resquestIsLegal(String challenge, String validate, String seccode) {

		if (objIsEmpty(challenge)) {
			
			return false;
			
		}

		if (objIsEmpty(validate)) {
			
			return false;
			
		}

		if (objIsEmpty(seccode)) {
			
			return false;
			
		}

		return true;
	}
	
	
	/**
	 * 鏈嶅姟姝ｅ父鐨勬儏鍐典笅浣跨敤鐨勯獙璇佹柟寮�,鍚慻t-server杩涜浜屾楠岃瘉,鑾峰彇楠岃瘉缁撴灉
	 * 
	 * @param challenge
	 * @param validate
	 * @param seccode
	 * @return 楠岃瘉缁撴灉,1琛ㄧず楠岃瘉鎴愬姛0琛ㄧず楠岃瘉澶辫触
	 */
	public int enhencedValidateRequest(String challenge, String validate, String seccode, HashMap<String, String> data) {	
		
		if (!resquestIsLegal(challenge, validate, seccode)) {
			
			return 0;
			
		}
		
		gtlog("request legitimate");
		
		String userId = data.get("user_id");
		String clientType = data.get("client_type");
		String ipAddress = data.get("ip_address");
		
		String postUrl = this.apiUrl + this.validateUrl;
		String param = String.format("challenge=%s&validate=%s&seccode=%s&json_format=%s", 
				                     challenge, validate, seccode, this.json_format);
		
		if (userId != null){
			param = param + "&user_id=" + userId;
		}
		if (clientType != null){
			param = param + "&client_type=" + clientType;
		}
		if (ipAddress != null){
			param = param + "&ip_address=" + ipAddress;
		}
		
		gtlog("param:" + param);
		
		String response = "";
		try {
			
			if (validate.length() <= 0) {
				
				return 0;
				
			}

			if (!checkResultByPrivate(challenge, validate)) {
				
				return 0;
				
			}
			
			gtlog("checkResultByPrivate");
			
			response = readContentFromPost(postUrl, param);

			gtlog("response: " + response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		String return_seccode = "";
		
		try {
			
			JSONObject return_map = new JSONObject(response);
			return_seccode = return_map.getString("seccode");
			gtlog("md5: " + md5Encode(return_seccode));

			if (return_seccode.equals(md5Encode(seccode))) {
				
				return 1;
				
			} else {
				
				return 0;
				
			}
			
		} catch (JSONException e) {
			
		
			gtlog("json load error");
			return 0;
			
		}
		
	}

	/**
	 * failback浣跨敤鐨勯獙璇佹柟寮�
	 * 
	 * @param challenge
	 * @param validate
	 * @param seccode
	 * @return 楠岃瘉缁撴灉,1琛ㄧず楠岃瘉鎴愬姛0琛ㄧず楠岃瘉澶辫触
	 */
	public int failbackValidateRequest(String challenge, String validate, String seccode) {

		gtlog("in failback validate");

		if (!resquestIsLegal(challenge, validate, seccode)) {
			return 0;
		}
		gtlog("request legitimate");

		return 1;
	}

	/**
	 * 杈撳嚭debug淇℃伅锛岄渶瑕佸紑鍚痙ebugCode
	 * 
	 * @param message
	 */
	public void gtlog(String message) {
		if (debugCode) {
			System.out.println("gtlog: " + message);
		}
	}

	protected boolean checkResultByPrivate(String challenge, String validate) {
		String encodeStr = md5Encode(privateKey + "geetest" + challenge);
		return validate.equals(encodeStr);
	}
	
	/**
	 * 鍙戦�丟ET璇锋眰锛岃幏鍙栨湇鍔″櫒杩斿洖缁撴灉
	 * 
	 * @param getURL
	 * @return 鏈嶅姟鍣ㄨ繑鍥炵粨鏋�
	 * @throws IOException
	 */
	private String readContentFromGet(String URL) throws IOException {

		URL getUrl = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();

		connection.setConnectTimeout(2000);// 璁剧疆杩炴帴涓绘満瓒呮椂锛堝崟浣嶏細姣锛�
		connection.setReadTimeout(2000);// 璁剧疆浠庝富鏈鸿鍙栨暟鎹秴鏃讹紙鍗曚綅锛氭绉掞級

		// 寤虹珛涓庢湇鍔″櫒鐨勮繛鎺ワ紝骞舵湭鍙戦�佹暟鎹�
		connection.connect();
		
		if (connection.getResponseCode() == 200) {
			// 鍙戦�佹暟鎹埌鏈嶅姟鍣ㄥ苟浣跨敤Reader璇诲彇杩斿洖鐨勬暟鎹�
			StringBuffer sBuffer = new StringBuffer();

			InputStream inStream = null;
			byte[] buf = new byte[1024];
			inStream = connection.getInputStream();
			for (int n; (n = inStream.read(buf)) != -1;) {
				sBuffer.append(new String(buf, 0, n, "UTF-8"));
			}
			inStream.close();
			connection.disconnect();// 鏂紑杩炴帴
            
			return sBuffer.toString();	
		}
		else {
			
			return "fail";
		}
	}
	
	/**
	 * 鍙戦�丳OST璇锋眰锛岃幏鍙栨湇鍔″櫒杩斿洖缁撴灉
	 * 
	 * @param getURL
	 * @return 鏈嶅姟鍣ㄨ繑鍥炵粨鏋�
	 * @throws IOException
	 */
	private String readContentFromPost(String URL, String data) throws IOException {
		
		gtlog(data);
		URL postUrl = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();

		connection.setConnectTimeout(2000);// 璁剧疆杩炴帴涓绘満瓒呮椂锛堝崟浣嶏細姣锛�
		connection.setReadTimeout(2000);// 璁剧疆浠庝富鏈鸿鍙栨暟鎹秴鏃讹紙鍗曚綅锛氭绉掞級
		connection.setRequestMethod("POST");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
		// 寤虹珛涓庢湇鍔″櫒鐨勮繛鎺ワ紝骞舵湭鍙戦�佹暟鎹�
		connection.connect();
		
		 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "utf-8");  
		 outputStreamWriter.write(data);  
		 outputStreamWriter.flush();
		 outputStreamWriter.close();
		
		if (connection.getResponseCode() == 200) {
			// 鍙戦�佹暟鎹埌鏈嶅姟鍣ㄥ苟浣跨敤Reader璇诲彇杩斿洖鐨勬暟鎹�
			StringBuffer sBuffer = new StringBuffer();

			InputStream inStream = null;
			byte[] buf = new byte[1024];
			inStream = connection.getInputStream();
			for (int n; (n = inStream.read(buf)) != -1;) {
				sBuffer.append(new String(buf, 0, n, "UTF-8"));
			}
			inStream.close();
			connection.disconnect();// 鏂紑杩炴帴
            
			return sBuffer.toString();	
		}
		else {
			
			return "fail";
		}
	}

	/**
	 * md5 鍔犲瘑
	 * 
	 * @time 2014骞�7鏈�10鏃� 涓嬪崍3:30:01
	 * @param plainText
	 * @return
	 */
	private String md5Encode(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}

}
