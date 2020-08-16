package handson.aug152020.m;

public class CoinChange {

	public static void main(String[] args) {

		int[] coins = {186, 419, 83, 408, 6249};
		int amount = 6249;

		CoinChange coinChange = new CoinChange();
		System.out
				.println("total coin:" + coinChange.coinChange(coins, amount));
	}

	public int coinChange(int[] coins, int amount) {
		if (amount < 1)
			return 0;
		return coinChange(coins, amount, new int[amount + 1]);
	}

	private int coinChange(int[] coins, int amount, int[] count) {
		if (amount < 0)
			return -1;
		if (amount == 0)
			return 0;
		if (count[amount] != 0)
			return count[amount];
		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int coinCount = coinChange(coins, amount - coin, count);
			if (coinCount >= 0 && coinCount < min)
				min = 1 + coinCount;
		}
		count[amount] = (min == Integer.MAX_VALUE) ? -1 : min;
		return count[amount];
	}
}
