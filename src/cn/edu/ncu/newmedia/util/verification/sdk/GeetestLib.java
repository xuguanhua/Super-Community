package cn.edu.ncu.newmedia.util.verification.sdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Java SDK
 */
public class GeetestLib {

    /**
     * 閺嬩線鐛欐宀冪槈娴滃本顐兼宀冪槈鐞涖劌宕熼弫鐗堝祦 chllenge
     */
    public static final String fn_geetest_challenge = "geetest_challenge";
    /**
     * 閺嬩線鐛欐宀冪槈娴滃本顐兼宀冪槈鐞涖劌宕熼弫鐗堝祦 validate
     */
    public static final String fn_geetest_validate = "geetest_validate";
    /**
     * 閺嬩線鐛欐宀冪槈娴滃本顐兼宀冪槈鐞涖劌宕熼弫鐗堝祦 seccode
     */
    public static final String fn_geetest_seccode = "geetest_seccode";
    protected final String verName = "4.0";
    protected final String sdkLang = "java";
    protected final String apiUrl = "http://api.geetest.com";
    protected final String registerUrl = "/register.php";
    protected final String validateUrl = "/validate.php";
    protected final String json_format = "1";
    /**
     * 鐠嬪啳鐦锟介崗绛圭礉閺勵垰鎯佹潏鎾冲毉鐠嬪啳鐦弮銉ョ箶
     */
    public boolean debugCode = true;
    /**
     * 閺嬩線鐛欐宀冪槈API閺堝秴濮熼悩鑸碉拷涓糴ssion Key
     */
    public String gtServerStatusSessionKey = "gt_server_status";
    /**
     * 閸忣剟鎸�
     */
    private String captchaId = "";
    /**
     * 缁変線鎸�
     */
    private String privateKey = "";
    /**
     * 閺勵垰鎯佸锟介崥顖涙煀閻ㄥ垿ailback
     */
    private boolean newFailback = false;
    /**
     * 鏉╂柨娲栫�涙顑佹稉锟�
     */
    private String responseStr = "";

    /**
     * 鐢箑寮弫鐗堢�柅鐘插毐閺侊拷
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
     * 閼惧嘲褰囬張顒侇偧妤犲矁鐦夐崚婵嗩潗閸栨牞绻戦崶鐐茬摟缁楋缚瑕�
     *
     * @return 閸掓繂顫愰崠鏍波閺嬶拷
     */
    public String getResponseStr() {

        return responseStr;

    }

    public String getVersionInfo() {

        return verName;

    }

    /**
     * 妫板嫬顦╅悶鍡椼亼鐠愩儱鎮楅惃鍕箲閸ョ偞鐗稿蹇庤
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
     * 妫板嫬顦╅悶鍡樺灇閸旂喎鎮楅惃鍕垼閸戝棔瑕�
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
     * 妤犲矁鐦夐崚婵嗩潗閸栨牠顣╂径鍕倞
     *
     * @return 1鐞涖劎銇氶崚婵嗩潗閸栨牗鍨氶崝鐕傜礉0鐞涖劎銇氶崚婵嗩潗閸栨牕銇戠拹锟�
     */
    public int preProcess(HashMap<String, String> data) {

        if (registerChallenge(data) != 1) {

            this.responseStr = this.getFailPreProcessRes();
            return 0;

        }

        return 1;

    }

    /**
     * 閻⑩暉aptchaID鏉╂稖顢戝▔銊ュ斀閿涘本娲块弬鐧県allenge
     *
     * @return 1鐞涖劎銇氬▔銊ュ斀閹存劕濮涢敍锟�0鐞涖劎銇氬▔銊ュ斀婢惰精瑙�
     */
    private int registerChallenge(HashMap<String, String> data) {

        try {
            String userId = data.get("user_id");
            String clientType = data.get("client_type");
            String ipAddress = data.get("ip_address");

            String getUrl = apiUrl + registerUrl + "?";
            String param = "gt=" + this.captchaId + "&json_format=" + this.json_format;

            if (userId != null) {
                param = param + "&user_id=" + userId;
            }
            if (clientType != null) {
                param = param + "&client_type=" + clientType;
            }
            if (ipAddress != null) {
                param = param + "&ip_address=" + ipAddress;
            }

            gtlog("GET_URL:" + getUrl + param);
            String result_str = readContentFromGet(getUrl + param);
            if (result_str == "fail") {

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

            } else {

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
     * 閸掋倖鏌囨稉锟芥稉顏囥�冮崡鏇烆嚠鐠炩�筹拷鍏兼Ц閸氾缚璐熺粚锟�
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
     * 濡拷閺屻儱顓归幋椋庮伂閻ㄥ嫯顕Ч鍌涙Ц閸氾箑鎮庡▔锟�,娑撳閲滈崣顏囶洣閺堝绔存稉顏冭礋缁岀尨绱濋崚娆忓灲閺傤厺绗夐崥鍫熺《
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
     * 閺堝秴濮熷锝呯埗閻ㄥ嫭鍎忛崘鍏哥瑓娴ｈ法鏁ら惃鍕崣鐠囦焦鏌熷锟�,閸氭吇t-server鏉╂稖顢戞禍灞绢偧妤犲矁鐦�,閼惧嘲褰囨宀冪槈缂佹挻鐏�
     *
     * @param challenge
     * @param validate
     * @param seccode
     * @return 妤犲矁鐦夌紒鎾寸亯, 1鐞涖劎銇氭宀冪槈閹存劕濮�0鐞涖劎銇氭宀冪槈婢惰精瑙�
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

        if (userId != null) {
            param = param + "&user_id=" + userId;
        }
        if (clientType != null) {
            param = param + "&client_type=" + clientType;
        }
        if (ipAddress != null) {
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
     * failback娴ｈ法鏁ら惃鍕崣鐠囦焦鏌熷锟�
     *
     * @param challenge
     * @param validate
     * @param seccode
     * @return 妤犲矁鐦夌紒鎾寸亯, 1鐞涖劎銇氭宀冪槈閹存劕濮�0鐞涖劎銇氭宀冪槈婢惰精瑙�
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
     * 鏉堟挸鍤璬ebug娣団剝浼呴敍宀勬付鐟曚礁绱戦崥鐥檈bugCode
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
     * 閸欐垿锟戒笩ET鐠囬攱鐪伴敍宀冨箯閸欐牗婀囬崝鈥虫珤鏉╂柨娲栫紒鎾寸亯
     *
     * @param getURL
     * @return 閺堝秴濮熼崳銊ㄧ箲閸ョ偟绮ㄩ弸锟�
     * @throws IOException
     */
    private String readContentFromGet(String URL) throws IOException {

        URL getUrl = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) getUrl
                .openConnection();

        connection.setConnectTimeout(2000);// 鐠佸墽鐤嗘潻鐐村复娑撶粯婧�鐡掑懏妞傞敍鍫濆礋娴ｅ稄绱板В顐ゎ潡閿涳拷
        connection.setReadTimeout(2000);// 鐠佸墽鐤嗘禒搴濆瘜閺堥缚顕伴崣鏍ㄦ殶閹诡喛绉撮弮璁圭礄閸楁洑缍呴敍姘嚑缁夋帪绱�

        // 瀵よ櫣鐝涙稉搴㈡箛閸斺�虫珤閻ㄥ嫯绻涢幒銉礉楠炶埖婀崣鎴︼拷浣规殶閹癸拷
        connection.connect();

        if (connection.getResponseCode() == 200) {
            // 閸欐垿锟戒焦鏆熼幑顔煎煂閺堝秴濮熼崳銊ヨ嫙娴ｈ法鏁eader鐠囪褰囨潻鏂挎礀閻ㄥ嫭鏆熼幑锟�
            StringBuffer sBuffer = new StringBuffer();

            InputStream inStream = null;
            byte[] buf = new byte[1024];
            inStream = connection.getInputStream();
            for (int n; (n = inStream.read(buf)) != -1; ) {
                sBuffer.append(new String(buf, 0, n, "UTF-8"));
            }
            inStream.close();
            connection.disconnect();// 閺傤厼绱戞潻鐐村复

            return sBuffer.toString();
        } else {

            return "fail";
        }
    }

    /**
     * 閸欐垿锟戒赋OST鐠囬攱鐪伴敍宀冨箯閸欐牗婀囬崝鈥虫珤鏉╂柨娲栫紒鎾寸亯
     *
     * @param getURL
     * @return 閺堝秴濮熼崳銊ㄧ箲閸ョ偟绮ㄩ弸锟�
     * @throws IOException
     */
    private String readContentFromPost(String URL, String data) throws IOException {

        gtlog(data);
        URL postUrl = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) postUrl
                .openConnection();

        connection.setConnectTimeout(2000);// 鐠佸墽鐤嗘潻鐐村复娑撶粯婧�鐡掑懏妞傞敍鍫濆礋娴ｅ稄绱板В顐ゎ潡閿涳拷
        connection.setReadTimeout(2000);// 鐠佸墽鐤嗘禒搴濆瘜閺堥缚顕伴崣鏍ㄦ殶閹诡喛绉撮弮璁圭礄閸楁洑缍呴敍姘嚑缁夋帪绱�
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // 瀵よ櫣鐝涙稉搴㈡箛閸斺�虫珤閻ㄥ嫯绻涢幒銉礉楠炶埖婀崣鎴︼拷浣规殶閹癸拷
        connection.connect();

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
        outputStreamWriter.write(data);
        outputStreamWriter.flush();
        outputStreamWriter.close();

        if (connection.getResponseCode() == 200) {
            // 閸欐垿锟戒焦鏆熼幑顔煎煂閺堝秴濮熼崳銊ヨ嫙娴ｈ法鏁eader鐠囪褰囨潻鏂挎礀閻ㄥ嫭鏆熼幑锟�
            StringBuffer sBuffer = new StringBuffer();

            InputStream inStream = null;
            byte[] buf = new byte[1024];
            inStream = connection.getInputStream();
            for (int n; (n = inStream.read(buf)) != -1; ) {
                sBuffer.append(new String(buf, 0, n, "UTF-8"));
            }
            inStream.close();
            connection.disconnect();// 閺傤厼绱戞潻鐐村复

            return sBuffer.toString();
        } else {

            return "fail";
        }
    }

    /**
     * md5 閸旂姴鐦�
     *
     * @param plainText
     * @return
     * @time 2014楠烇拷7閺堬拷10閺冿拷 娑撳宕�3:30:01
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
