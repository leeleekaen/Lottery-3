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
 * 数据输出工具类，允许把输出内容写入文件，量少时，也可以直接打印到控制台
 * @author klq26
 *
 */
public class FileTools {
	
	private static BufferedWriter writer;
	private static BufferedReader reader;
	
	/**
	 * 获取写入文件的BufferedWriter对象。★使用完毕后，记得调用BufferedWriter.flush()和.close()，否则可能写入失效。
	 * @param fileName 指定输出的文件名，如果为null，则会以log加当前输出时刻为文件名，如：log_2015-05-21_20:42:03.txt
	 * @return 写入文件接口对象
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
			//不要直接用FileWriter生成BufferedWriter，那样无法指定编码，中文会输出为乱码
			OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream(logFile), "gb2312");
			writer = new BufferedWriter(osr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return writer;
	}
	
	/**
	 * 获取读取文件的BufferedReader对象。★使用完毕后，记得调用BufferedReader.close()。
	 * @param path 被读取文件所在的路径
	 * @param fileName 文件名称
	 * @return 读取文件接口对象
	 */
	public static BufferedReader getBufferedReader(String path, String fileName)
	{
		try {
			if(reader != null)
			{
				reader.close();
			}
			
			File file = new File(path, fileName);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GB2312");	//不要直接用FileReader生成BufferedReader，那样无法指定编码，中文会识别为乱码
			reader = new BufferedReader(isr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}
}
