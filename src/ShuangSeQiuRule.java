/**
 * 
 */

/**
 * @author klq26
 * 双色球中奖规则查询
 */
public class ShuangSeQiuRule implements IRule {

	/* (non-Javadoc)
	 * @see IRule#awardLevel(ResultInfo)
	 * 一等奖：7个号码相符（6个红色球号码和1个蓝色球号码）（红色球号码顺序不限，下同）
	 * 二等奖：6个红色球号码相符；
	 * 三等奖：5个红色球号码和1个蓝色球号码相符；
	 * 四等奖：5个红色球号码，或4个红色球号码和1个蓝色球号码相符；
	 * 五等奖：4个红色球号码，或3个红色球号码和1个蓝色球号码相符；
	 * 六等奖：1个蓝色球号码相符（有无红色球号码相符均可）。
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
