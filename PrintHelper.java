package open;

import java.io.IOException;

import org.apache.commons.logging.Log;

import net.sf.json.JSONObject;

public class PrintHelper {
	/*
	 * �û��豸��
	 */
	public static String userBind(String uuid, String userId) {
		String url = Utils.getUrl("/home/userbind");
		JSONObject obj = new JSONObject();
		obj.put("Uuid", uuid);
		obj.put("UserId", userId);
		String data = obj.toString();
		String result = "";
		try {
			result = Utils.sendPost(url, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * ��ȡ�豸״̬
	 */
	public static String getDeviceState(String uuid) {
		String url = Utils.getUrl("/home/getdevicestate");
		JSONObject obj = new JSONObject();
		obj.put("Uuid", uuid);
		String data = obj.toString();
		String result = "";
		try {
			result = Utils.sendPost(url, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * ��ӡ����
	 */
	public static String printContent(String uuid, String content, String openUserId) {
		String url = Utils.getUrl("/home/printcontent2");
		JSONObject obj = new JSONObject();
		obj.put("Uuid", uuid);
		obj.put("PrintContent", " " + content);
		obj.put("OpenUserId", openUserId);
		String data = obj.toString();
		System.out.println(data);
		String result = "";
		try {
			result = Utils.sendPost(url, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * ��ȡ��ӡ����״̬
	 */
	public static String getPrintTaskState(long taskId) {
		String url = Utils.getUrl("/home/getprinttaskstate");
		JSONObject obj = new JSONObject();
		obj.put("TaskId", taskId);
		String data = obj.toString();
		System.out.println(data);
		String result = "";
		try {
			result = Utils.sendPost(url, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
