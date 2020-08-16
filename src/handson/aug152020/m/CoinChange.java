package handson.aug152020.m;

public class CoinChange {

	public static void main(String[] args) {

		int[] coins = {2, 5, 3};
		int amount = 5;

		CoinChange coinChange = new CoinChange();
		System.out
				.println("total coin:" + coinChange.coinChange(coins, amount));
	}

	public int coinChange(int[] coins, int amount) {
		return coinChange(coins, amount, new int[amount + 1]);
	}

	public int coinChange(int[] coins, int amount, int coinCountForAmount[]) {
		if (amount < 0)
			return -1;
		if (amount == 0)
			return 0;
		if (coinCountForAmount[amount] != 0)
			return coinCountForAmount[amount];

		int minCoinCount = Integer.MAX_VALUE;

		for (int curCoin : coins) {
			int remAmount = amount - curCoin;
			int coinCount = coinChange(coins, remAmount, coinCountForAmount);

			if (coinCount >= 0 && coinCount < minCoinCount) {
				minCoinCount = coinCount + 1;
			}
		}
		minCoinCount = minCoinCount == Integer.MAX_VALUE ? -1 : minCoinCount;
		coinCountForAmount[amount] = minCoinCount;
		return coinCountForAmount[amount];
	}
}
