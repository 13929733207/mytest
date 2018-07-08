package cn.itcast.core.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * �Զ�������ת����
 * 
 * @author ldh Converter<S, T>
 * 
 *         S,Source��Դ��ת��֮ǰ�����ݣ��������ַ������͵���Ʒ���� 
 *         T,Target��Ŀ�꣬ת��֮������ݣ�������Date���͵���Ʒ����
 * 
 */
public class DateConverter implements Converter<String, Date> {

	/**
	 * ʵ��ת���߼��ķ��� 2016-02-03 13:22:53
	 */
	public Date convert(String source) {
		// 1.���ڸ�ʽ������
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// ת���ɹ���ֱ�ӷ���
			return sFormat.parse(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ת��ʧ�ܣ�����null
		return null;
	}

}
