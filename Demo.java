package open;

import net.sf.json.JsonConfig;

public class Demo {

	public static void main(String[] args) {
		String uuid = "�����豸���";
		// TODO Auto-generated method stub
		/*
		 * �û��� uuid:�豸��� userid:��ϵͳ���û���ţ��Լ����壩���������
		 * ���ظ�ʽ{"OpenUserId":160715,"Code":200,"Message":"�ɹ�"}
		 */
		String result = PrintHelper.userBind(uuid, "100");//100 ��ϵͳ���û���ţ��Լ����壩���������
		System.out.println(result);
		/*
		 * ��ȡ�豸״̬ uuid:�豸��ţ����ظ�ʽ��{"State":0,"Code":200,"Message":"�ɹ�"}
		 */
		String result1 = PrintHelper.getDeviceState(uuid);
		System.out.println(result1);

		String content = "���Դ�ӡ\n���͵�";
		String jsonContent = "[{\"Alignment\":0,\"BaseText\":\"" + Utils.StringToBase64(content)
				+ "\",\"Bold\":0,\"FontSize\":0,\"PrintType\":0}]";
		String result2 = PrintHelper.printContent(uuid, jsonContent, "0");//�ĳ��û��豸�󶨷��ص�OpenUserId
		System.out.println(result2);

		/*
		 * ��ȡ��ӡ����״̬ taskId:������ ���ظ�ʽ {"State":1,"Code":200,"Message":"�ɹ�"}
		 */
		String result3 = PrintHelper.getPrintTaskState(0); //0�ĳ�������
		System.out.println(result3);
	}
}
