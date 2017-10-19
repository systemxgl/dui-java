package open;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Utils {
	/*
	 * ��װ����Url
	 */
	public static String getUrl(String action) {
		return String.format("%s%s%s", PrintConfig.baseUrl, action, createParams());
	}

	/*
	 * ����ͨ�ò���
	 */
	public static String createParams() {
		String nonce = getNonce();
		String timestamp = getTimestamp();
		String signStr = SignatureString(PrintConfig.appSecret, timestamp, nonce);
		return String.format("?appid=%s&nonce=%s&timestamp=%s&signature=%s", PrintConfig.appId, nonce, timestamp,
				signStr);
	}

	/*
	 * �ַ���תBase64
	 */
	public static String StringToBase64(String data) {
		try {
			return Base64.getEncoder().encodeToString(data.getBytes("GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/*
	 * �����ַ���
	 */
	public static String SignatureString(String appSecret, String timestamp, String nonce) {
		String[] arrTmp = { appSecret, timestamp, nonce };
		Arrays.sort(arrTmp);
		String tmpStr = String.join("", arrTmp);
		try {
			tmpStr = SHA1(tmpStr).toLowerCase();
		} catch (DigestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmpStr;
	}

	/**
	 * SHA1 ��ȫ�����㷨
	 * 
	 * @data Ҫ���ܵ��ַ���
	 * @return
	 * @throws DigestException
	 */
	public static String SHA1(String data) throws DigestException {
		try {
			// ָ��sha1�㷨
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(data.getBytes());
			// ��ȡ�ֽ�����
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// �ֽ�����ת��Ϊ ʮ������ ��
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString().toUpperCase();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new DigestException("ǩ������");
		}
	}

	/*
	 * ��ȡ��������ַ���
	 */
	public static String getNonce() {
		return String.valueOf((int) ((Math.random() * 9 + 1) * 100000000));
	}

	/*
	 * ��ȡʱ���
	 */
	public static String getTimestamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/*
	 * ����post����
	 */
	public static String sendPost(String url, String data) throws IOException {
		OutputStreamWriter out = null;
		BufferedReader reader = null;
		String response = "";
		try {
			URL httpUrl = null; // HTTP URL�� �����������������
			// ����URL
			httpUrl = new URL(url);
			// ��������
			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("connection", "keep-alive");
			conn.setUseCaches(false);// ���ò�Ҫ����
			conn.setInstanceFollowRedirects(true);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			// POST����
			out = new OutputStreamWriter(conn.getOutputStream());
			out.write(data);
			out.flush();
			// ��ȡ��Ӧ
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String lines;
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				response += lines;
			}
			reader.close();
			// �Ͽ�����
			conn.disconnect();

		} catch (Exception e) {
			System.out.println("���� POST ��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return response;
	}
}
