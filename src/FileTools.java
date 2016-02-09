import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

/**
 * ������������࣬������������д���ļ�������ʱ��Ҳ����ֱ�Ӵ�ӡ������̨
 * @author klq26
 *
 */
public class FileTools {
	
	private static BufferedWriter writer;
	private static BufferedReader reader;
	
	/**
	 * ��ȡд���ļ���BufferedWriter���󡣡�ʹ����Ϻ󣬼ǵõ���BufferedWriter.flush()��.close()���������д��ʧЧ��
	 * @param fileName ָ��������ļ��������Ϊnull�������log�ӵ�ǰ���ʱ��Ϊ�ļ������磺log_2015-05-21_20:42:03.txt
	 * @return д���ļ��ӿڶ���
	 */
	public static BufferedWriter getBufferedWriter(String fileName)
	{
		try {
			if(writer != null)
			{
				writer.flush();
				writer.close();
			}
			//output to file.
			String tempFileName = null;
			if(fileName == null || fileName.isEmpty())
			{
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
				tempFileName = String.format("log_%s", df.format(new java.util.Date()));	//log_2015-05-21_20:42:00
			} else {
				tempFileName = fileName;
			}
			
			File logFile = new File(ClassPathTools.getClassPath(FileTools.class), String.format("%s.txt", tempFileName));
			FileWriter logFw = new FileWriter(logFile);
			//��Ҫֱ����FileWriter����BufferedWriter�������޷�ָ�����룬���Ļ����Ϊ����
			OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream(logFile), "gb2312");
			writer = new BufferedWriter(osr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return writer;
	}
	
	/**
	 * ��ȡ��ȡ�ļ���BufferedReader���󡣡�ʹ����Ϻ󣬼ǵõ���BufferedReader.close()��
	 * @param path ����ȡ�ļ����ڵ�·��
	 * @param fileName �ļ�����
	 * @return ��ȡ�ļ��ӿڶ���
	 */
	public static BufferedReader getBufferedReader(String path, String fileName)
	{
		try {
			if(reader != null)
			{
				reader.close();
			}
			
			File file = new File(path, fileName);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GB2312");	//��Ҫֱ����FileReader����BufferedReader�������޷�ָ�����룬���Ļ�ʶ��Ϊ����
			reader = new BufferedReader(isr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}
}
