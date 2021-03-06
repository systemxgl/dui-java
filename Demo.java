package open;

import net.sf.json.JsonConfig;

public class Demo {

	public static void main(String[] args) {
		String uuid = "您的设备编号";
		// TODO Auto-generated method stub
		/*
		 * 用户绑定 uuid:设备编号 userid:您系统的用户编号（自己定义）最好是数字
		 * 返回格式{"OpenUserId":160715,"Code":200,"Message":"成功"}
		 */
		String result = PrintHelper.userBind(uuid, "100","设备名称");//100 您系统的用户编号（自己定义）最好是数字
		System.out.println(result);
		/*
		 * 获取设备状态 uuid:设备编号；返回格式：{"State":0,"Code":200,"Message":"成功"}
		 */
		String result1 = PrintHelper.getDeviceState(uuid);
		System.out.println(result1);

		String content = "测试打印\n大发送的";
		//格式详见 http://www.mstching.com/home/openapi
		//=====
		//注意：Utils.StringToBase64方法第二个参数 D1传GBK，D2传UTF-8
		//=====
		String jsonContent = "[{\"Alignment\":0,\"BaseText\":\"" + Utils.StringToBase64(content,"GBK")
				+ "\",\"Bold\":0,\"FontSize\":0,\"PrintType\":0}]";		
		String result2 = PrintHelper.printContent(uuid, jsonContent, "0");//改成用户设备绑定返回的OpenUserId
		System.out.println(result2);
		/*
		 * 打印网页信息
		 */
		String printUrl="您要打印的网页地址";//例：http://www.open.mstching.com/print-demo.html
		String result4 = PrintHelper.printHtmlContent(uuid, printUrl, "0");//改成用户设备绑定返回的OpenUserId
		/*
		 * 获取打印任务状态 taskId:任务编号 返回格式 {"State":1,"Code":200,"Message":"成功"}
		 */
		String result3 = PrintHelper.getPrintTaskState(0); //0改成任务编号
		System.out.println(result3);
	}
}
