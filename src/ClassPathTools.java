import java.io.File;
import java.net.URL;

/**
 * 工具类，目前主要用途是跨Mac和Windows等系统找出项目bin目录，然后把输出文件创建到这个位置（不写死为D:\\这样的）
 * @author klq26
 *
 */
public class ClassPathTools {
	
//	/**
//     * 得到当前类的路径 
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
     * 取得当前类所在的ClassPath路径（一般是\bin文件夹，因为class编译完了都是都放在这里）
     * ★这是本类目前唯一和外界对接的函数
     * @param clazz 类名（一般就是main.class的名字）
     * @return class所在的文件夹系统目录
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
     * 取得当前类所在的文件
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
     * 取得当前类所在的ClassPath目录，比如tomcat下的classes路径
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
