
public interface IRule {
	/**
	 * 彩票是否中奖判断d
	 * @param myLottery 你选择的号码组合
	 * @param result 开奖结果
	 * @return 中奖等级（建议未中奖用0表示，其余看喜好）
	 */
	public int awardLevel(ResultInfo myLottery, ResultInfo result);
}
