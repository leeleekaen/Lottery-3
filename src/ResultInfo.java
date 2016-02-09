import java.util.List;

/**
 * 双色球开奖信息
 * @author klq26
 *
 */
public class ResultInfo 
{
	public int period;	//期数，如2015151
	public List<Integer> reds;
	public int blue;	//蓝球
	
	public ResultInfo(final List<Integer> reds, int blue) {
		assert reds != null && reds.size() > 0;
		this.reds = reds;
		this.blue = blue;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		ResultInfo ri = (ResultInfo)obj;
//		for(int num : ri.reds) {
//			this.reds.contains(num);
//		}
//	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.reds.indexOf(0));
		for(int i = 1; i < reds.size(); i++) {
			sb.append(" " + reds.indexOf(i));
		}
		return String.format("期数: %d  红球：%s 蓝球：%3d", this.period,
				sb.toString(),this.blue);
	}
}
