import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LotteryMain {
	
	public static void main(String[] args) {
		DataBaseHelper helper = new DataBaseHelper("caipiao");
//		helper.updateDataBaseViaFile("D:\\ssq");
		
		List<ResultInfo> infos = helper.getAllData();
//		int[] reds = new int[34];	//测试33个红球出的概率
//		int[] blues = new int[17];	//测试16个蓝球出的概率
//		for(ResultInfo info : infos)
//		{
//			reds[info.red1]++;
//			reds[info.red2]++;
//			reds[info.red3]++;
//			reds[info.red4]++;
//			reds[info.red5]++;
//			reds[info.red6]++;
//			blues[info.blue]++;
//		}
//		System.out.println("红球概率");
//		for(int i = 1; i < 34; i++) {
//			System.out.println(String.format("%d的概率: %.2f%%",i, reds[i]/1800.f * 100));
//		}
//		System.out.println("蓝球概率");
//		for(int i = 1; i < 17; i++) {
//			System.out.println(String.format("%d的概率: %.2f%%",i, blues[i]/1800.f * 100));
//		}
		
		try {
			BufferedWriter writer = FileTools.getBufferedWriter(null);
			
			IRule rule = new ShuangSeQiuRule();
			
			List<Integer> reds = new ArrayList<Integer>();
			reds.add(11);
			reds.add(12);
			reds.add(20);
			reds.add(22);
			reds.add(24);
			reds.add(30);
			int blue = 5;
			ResultInfo l = new ResultInfo(reds, blue);
			int[] levels = new int[7];
			for(ResultInfo ri : infos) {
				int i = rule.awardLevel(l, ri);
				if(i > 0) {
					levels[i]++;
//					String str = String.format("期数: %d  %d等奖", ri.period, i);
//					System.out.println(str);
//					writer.write(str);
//					writer.write("\n");
				}
				i = 0;
			}
			String str = String.format("6等奖 %d 次 5等奖 %d 次 4等奖 %d 次 3等奖 %d 次 2等奖 %d 次 1等奖 %d 次",levels[6],
					levels[5],levels[4],levels[3],levels[2],levels[1]);
			System.out.println(str);
//			writer.write(str);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done.");
	}

}
