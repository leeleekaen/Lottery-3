import java.util.List;

/**
 * ˫ɫ�򿪽���Ϣ
 * @author klq26
 *
 */
public class ResultInfo 
{
	public int period;	//��������2015151
	public List<Integer> reds;
	public int blue;	//����
	
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
		return String.format("����: %d  ����%s ����%3d", this.period,
				sb.toString(),this.blue);
	}
}
