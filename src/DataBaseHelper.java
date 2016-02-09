import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper {
	public static final String DB_NAME_SSQ = "caipiao";	//彩票数据库
	
	protected Statement _statement;
	protected Connection _conn;
	
	private String DB_NAME = ""; 
	
	public DataBaseHelper(String dbName)
	{
		assert(dbName == null && dbName == "");	
		
		DB_NAME = dbName;
		try {
			connectToDB();
		} catch(Exception e)
		{}
	}
	
	private void connectToDB() throws SQLException
	{
		_conn = null;
		
	     String url = String.format("jdbc:mysql://localhost:3306/%s?user=%s&password=%s&useUnicode=true&characterEncoding=UTF8", DB_NAME, "root", "1234");
//	     System.out.println("database connect URL: " + url);
	     try {
	    	 //add mySQL database java driver.
	         Class.forName("com.mysql.jdbc.Driver");
	         //connecting...
	         _conn = DriverManager.getConnection(url);
	         _statement = _conn.createStatement();
             
	     } catch (SQLException e) {
	         System.out.println("MySQL operation Error");
	         e.printStackTrace();
	     } catch (Exception e) {
	         e.printStackTrace();
	     } finally {
//	    	 _conn.close();
	     }
	}
	
	public void closeDataBase()
	{
		try {
			_conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int createTable(String tbName)
	{
		int statusCode = -1;
		if(tbName == null) {
			return statusCode;
		}
		try {
			java.sql.DatabaseMetaData meta = _conn.getMetaData();
			// check if "tbName" table is there
			ResultSet tables = meta.getTables(DB_NAME, null, tbName, null);
			if (tables.next()) {
			  // Table exists
//				statusCode = openTable(tbName);
				statusCode = 0;
			}
			else {
			  // Table does not exist
				String sql = String.format("CREATE TABLE %s " +
		                   "(period INT(7), " + 
		                   " red1 INT(2), " +
		                   " red2 INT(2),  " +
		                   " red3 INT(2),  " +	             
		                   " red4 INT(2),  " + 
		                   " red5 INT(2),  " + 
		                   " red6 INT(2),  " + 
		                   " blue INT(2),  " + 
		                   " PRIMARY KEY ( period ))", tbName) ; 
				statusCode = _statement.executeUpdate(sql);
			}
		} catch(Exception e) {
			e.printStackTrace();
			}
		System.out.println(String.format("\n\n\n\nCreate Table for:  %s", tbName));
		return statusCode;
	}
	
	public List<ResultInfo> getAllData()
	{
		List<ResultInfo> results = new ArrayList<ResultInfo>();
		ResultInfo info = null;
		String sql = String.format("SELECT * from ssq"); //WHERE period = \'%s\'", period);
		try {
			ResultSet result = _statement.executeQuery(sql);
			while(result.next()) {
				List<Integer> reds = new ArrayList<Integer>();
				reds.add(result.getInt(2));
				reds.add(result.getInt(3));
				reds.add(result.getInt(4));
				reds.add(result.getInt(5));
				reds.add(result.getInt(6));
				reds.add(result.getInt(7));
				info = new ResultInfo(reds, result.getInt(8));
				info.period = result.getInt(1);
				results.add(info);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public void executeSQL(String sql, Boolean isQuery)
	{
		try {
			if (isQuery) {
				_statement.executeQuery(sql);	//SELECT专用
			} else {
				_statement.executeUpdate(sql);	//UPDATE DELECT INSERT专用
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//从data.txt中更新双色球数据
	public void updateDataBaseViaFile(String dir)
	{
		File files = new File(dir);	//"/Users/klq26/cn"   or "D:\\cn"
		if(files.exists() && files.isDirectory()) {
			for(String file : files.list()) {
				if(file.startsWith(".")) {	//skip .DS_Store
					continue;
				}
				BufferedReader br = FileTools.getBufferedReader(dir, file);
				try {
					String row = null;
					while((row = br.readLine()) != null) {
//						System.out.println(row);
						String[] values = row.split(",");
						String sql = String.format("INSERT INTO %s(period, red1,red2,red3,red4,red5,red6,blue) VALUES"
								+ "(%d,%d,%d,%d,%d,%d,%d,%d)","ssq",Integer.valueOf(values[0]),Integer.valueOf(values[1]),
								Integer.valueOf(values[2]),Integer.valueOf(values[3]),Integer.valueOf(values[4]),
								Integer.valueOf(values[5]),Integer.valueOf(values[6]),Integer.valueOf(values[7]));
						this.executeSQL(sql, false);
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
