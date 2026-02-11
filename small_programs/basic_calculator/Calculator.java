import java.util.Scanner;

public class Calculator {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int opNum;
		double num1, num2;
		boolean stop;
		displayMenu();
		do {
			opNum = 0;
			print("　計算機の操作番号(整数)を入力してください : ");
			opNum = enterCorrectOpNum(getInputNum(), 1, 8);
			stop = calculator(opNum);
		} while (!stop); // ユーザーが8を入力したら繰り返し終了になります
		scanner.close();
	}

	//  System.out.println() を綺麗にする
	public static void println(Object o) {
		System.out.println(o);
	}

	public static void print(Object o) {
		System.out.print(o);
	}

	// メニューを表示する
	public static void displayMenu() {
		println(" \n下記は計算機の操作を対応する番号です。 \n");
		println(" [ 1 ] 加算 ( + ) ");
		println(" [ 2 ] 減算 ( - )");
		println(" [ 3 ] 乗算 ( * )");
		println(" [ 4 ] 除算 ( / )　商のみ除算");
		println(" [ 5 ] 除算 ( / )　小数点以下も");
		println(" [ 6 ] 剰余 ( % )　余り");
		println(" [ 7 ] メニューを再表示する");
		println(" [ 8 ] プログラムを終了\n");
	}

	// このメソッドはユーザーの入力が数字である時だけ終了して、整数として返します
	public static int getInputNum() {

		int num = 0;
		boolean again = false;
		do {
			try {
				if (again == true)
					print("　半角整数だけ入れてください : ");
				num = Integer.parseInt(scanner.nextLine());
				again = false;
			} catch (NumberFormatException e) {
				again = true;
			}
		} while (again == true);

		return num;
	}

	// このメソッドはユーザーの入力が数字である時だけ終了して、小数として返します
	public static double getInputDouble() {
		double num = 0;
		boolean again = false;
		do {
			try {
				if (again == true)
					print("　半角数字だけ入れてください : ");
				num = Double.parseDouble(scanner.nextLine());
				again = false;
			} catch (NumberFormatException e) {
				again = true;
			}
		} while (again == true);

		return num;
	}

	// スタートからエンドの間の数字だけが有効です。それ以外の入力したら、再入力を依頼します。
	public static int enterCorrectOpNum(int opNum, int start, int end) {

		while (opNum < start || opNum > end) {
			print("　1から8までの操作番号だけ入力してください！ :  ");
			opNum = getInputNum();
		}
		return opNum;

	}

	// 除算と剰余計算の数字は、0.0が無効ですから、条件を満たしているかチェックするメソッド
	public static double isNotZero(double num, String displayText) {
		while ((num == 0)) {
			println("　0.0　を除く有効な数字だけの入力をお願いします ");
			print(displayText);
			num = getInputDouble();
		}
		return num;
	}

	public static boolean calculator(int opNum) {

		double num1, num2; 
		char operatorChar = ' '; // 後で演算子をコンソールで表示するため
		boolean stop = false;
		switch (opNum) {
		case 1:
			operatorChar = '+';
			break;
		case 2:
			operatorChar = '-';
			break;
		case 3:
			operatorChar = '*';
			break;
		case 4:
			operatorChar = '/';
			break;
		case 5:
			operatorChar = '/';
			break;
		case 6:
			operatorChar = '%';
			break;
		case 7:
			displayMenu();//操作メニューを表示します。
			break;
		default:
			stop = true;//８を入力されたらプログラムが終了になります。
			break;
		}
		if (opNum < 1 || opNum > 6)//1から６以外は計算する必要がないためこれで終わります。
			return stop;
		//下記は入力を依頼します 除算や剰余の場合は、0.0入力禁止しています。
		println("\nアンダースコアの所に"
				+ "数字を入力してください ( _ )");
		print(" [ _ ] " + operatorChar + " [ x ] : ");
		num1 = getInputDouble();
		if (opNum == 4 || opNum == 5 || opNum == 6) // 除算と剰余計算の場合0.0の入力あるかチェック
			num1 = isNotZero(num1, " [ _ ] " + operatorChar + " [ x ] : ");
		print(" [ " + num1 + " ] " + operatorChar + " [ _ ] : ");
		num2 = getInputDouble();
		if (opNum == 4 || opNum == 5 || opNum == 6)// 除算と剰余計算の場合0.0の入力あるかチェック
			num2 = isNotZero(num2, " [ " + num1 + " ] " + operatorChar + " [ _ ] : ");

		// 下記は演算子に応じて計算結果を出す
		// 商のみ除算と剰余計算は小数点以下の部分は不要なため、整数として表示しています
		switch (opNum) {
		case 1:
			println(" [ " + num1 + " ] " + operatorChar + " [ " + num2 + " ] = " + (num1 + num2)); 
			break;
		case 2:
			println(" [ " + num1 + " ] " + operatorChar + " [ " + num2 + " ] = " + (num1 - num2));
			break;
		case 3:
			println(" [ " + num1 + " ] " + operatorChar + " [ " + num2 + " ] = " + (num1 * num2));
			break;
		case 4:
			println(" [ " + num1 + " ] " + operatorChar + " [ " + num2 + " ] = " + ((long) (num1 / num2)));
			break;
		case 5:
			println(" [ " + num1 + " ] " + operatorChar + " [ " + num2 + " ] = " + (num1 / num2));
			break;
		case 6:
			println(" [ " + num1 + " ] " + operatorChar + " [ " + num2 + " ] = " + ((long) (num1 % num2)));
			break;
		}
		return stop; // 続けるかやめるかを決める
	}
}
