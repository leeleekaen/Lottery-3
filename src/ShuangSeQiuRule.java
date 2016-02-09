/**
 * 
 */

/**
 * @author klq26
 * ˫ɫ���н������ѯ
 */
public class ShuangSeQiuRule implements IRule {

	/* (non-Javadoc)
	 * @see IRule#awardLevel(ResultInfo)
	 * һ�Ƚ���7�����������6����ɫ������1����ɫ����룩����ɫ�����˳���ޣ���ͬ��
	 * ���Ƚ���6����ɫ����������
	 * ���Ƚ���5����ɫ������1����ɫ����������
	 * �ĵȽ���5����ɫ����룬��4����ɫ������1����ɫ����������
	 * ��Ƚ���4����ɫ����룬��3����ɫ������1����ɫ����������
	 * ���Ƚ���1����ɫ�������������޺�ɫ�����������ɣ���
	 */
	@Override
	public int awardLevel(ResultInfo myLottery, ResultInfo result) {
		if(myLottery == null || result == null) {
			throw new ArithmeticException();
		}
		
		int sameCount = 0;
		for(Integer num : myLottery.reds) {
			if(result.reds.contains(num)) {
				sameCount++;
			}
		}
		
		if(sameCount == 6 && myLottery.blue == result.blue) {
			return 1;
		} else if(sameCount == 6) {
			return 2;
		} else if(sameCount == 5 && myLottery.blue == result.blue) {
			return 3;
		} else if(sameCount == 5 || (sameCount == 4 && myLottery.blue == result.blue)) {
			return 4;
		} else if(sameCount == 4 || (sameCount == 3 && myLottery.blue == result.blue)) {
			return 5;
		} else if(myLottery.blue == result.blue) {
			return 6;	
		}
		return 0;
	}

}
