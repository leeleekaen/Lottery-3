import java.io.File;
import java.net.URL;

/**
 * �����࣬Ŀǰ��Ҫ��;�ǿ�Mac��Windows��ϵͳ�ҳ���ĿbinĿ¼��Ȼ�������ļ����������λ�ã���д��ΪD:\\�����ģ�
 * @author klq26
 *
 */
public class ClassPathTools {
	
//	/**
//     * �õ���ǰ���·�� 
//     * @param clazz
//     * @return 
//     */
//    public static String getClassFilePath(Class clazz){
//        try{
//            return java.net.URLDecoder.decode(getClassFile(clazz).getAbsolutePath(),"UTF-8");
//        }catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//            return "";
//        }
//    }
    
	/**
     * ȡ�õ�ǰ�����ڵ�ClassPath·����һ����\bin�ļ��У���Ϊclass�������˶��Ƕ��������
     * �����Ǳ���ĿǰΨһ�����Խӵĺ���
     * @param clazz ������һ�����main.class�����֣�
     * @return class���ڵ��ļ���ϵͳĿ¼
     */
    public static String getClassPath(Class clazz){
        try{
            return java.net.URLDecoder.decode(getClassPathFile(clazz).getAbsolutePath(),"UTF-8");
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "";
        }
    }
	
    /**
     * ȡ�õ�ǰ�����ڵ��ļ�
     * @param clazz
     * @return 
     */
    private static File getClassFile(Class clazz){
        URL path = clazz.getResource(clazz.getName().substring(clazz.getName().lastIndexOf(".")+1)+".classs");
        if(path == null){
            String name = clazz.getName().replaceAll("[.]", "/");
            path = clazz.getResource("/"+name+".class");
        }
        return new File(path.getFile());
    }
    
    /**
     * ȡ�õ�ǰ�����ڵ�ClassPathĿ¼������tomcat�µ�classes·��
     * @param clazz
     * @return
     */
    private static File getClassPathFile(Class clazz){
        File file = getClassFile(clazz);
        for(int i=0,count = clazz.getName().split("[.]").length; i<count; i++)
            file = file.getParentFile();
        if(file.getName().toUpperCase().endsWith(".JAR!")){
            file = file.getParentFile();
        }
        return file;
    }
    
    
}
