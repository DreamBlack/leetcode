package linearlist;

public class PlusOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] digits = { 9, 9, 9 };
		digits = plusOneMain(digits);
		for (int i = 0; i < digits.length; i++) {
			System.out.println(digits[i]);
		}

	}

	public static int[] plusOne(int[] digits, int n) {
		if (n < 0)
			return digits;
		if (digits[n] < 9) {
			digits[n] += 1;
		} else {
			if (n == 0) {
				int[] digit = new int[digits.length + 1];
				digit[0] = 1;
				digit[1] = 0;
				for (int i = 1; i < digits.length; i++) {
					digit[i + 1] = digits[i];
				}
				return digit;
			}
			digits[n] = 0;
			return plusOne(digits, n - 1);
		}

		return digits;
	}

	public static int[] plusOneMain(int[] digits) {
		return plusOne(digits, digits.length - 1);
	}

}
