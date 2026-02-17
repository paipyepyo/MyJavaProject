
import static util.Printer.print;
import static util.Printer.println;
import java.util.ArrayList;
import java.util.Scanner;

import util.InputControl;

public class ShouuToAmari {

  public static void main(String... args) {

    println("　\n入力をやめたい際は直前の数字と同じ入力してください");
    println("では商と余りを知りたい数字を一つずつ入力してください\n");

    ArrayList<Num> list = new ArrayList<>();
    boolean stop = false;
    do {
      // １.入力処理
      Num n = new Num(InputControl.getNumNoText("[ " + (list.size() + 1) + "番目 ]   : ",
          "[ " + (list.size() + 1) + "番目 ] テキストではなく半角の数字の入力をお願いします   : ", Double.class));
      list.add(n);
      // 数字の個数が一つ以上であり、上記の命令で入力された数字はすぐ前の数字と同じである場合、ループを終了にします。
      if (list.size() > 1 && list.get(list.size() - 1).getNumber() == list.get(list.size() - 2).getNumber()) {
        stop = true;
      }

    } while (!stop);
    println("\nご入力ありがとうございました。");
    println("それぞれの商と余りは下記となります。\n");

    // 2.表示処理
    int count = 0;
    for (Num num : list) {
      println("[ " + ++count + "番目 ]  : " + num);
    }
    // リソースを閉じる
    InputControl.close();
  }

}

// 数字のクラス
class Num {

  final private double number;
  final private int quotient;
  final private int remainder;

  Num(double number) {
    this.number = number;
    this.quotient = Double.valueOf(this.number).intValue();// 商はオブジェクト作成時に決める
    this.remainder = findTheRemainder(this);
  }

  public double getNumber() {
    return this.number;
  }

  @Override
  public String toString() {
    return "数字：" + this.number + ",  商：" + this.quotient + ",   余り：" + this.remainder;
  }

  // 数字を文字列に変換し、小数点を探し、余りを出す
  private static int findTheRemainder(Num n) {
    String s = String.valueOf(n.number);
    int decimalPointIndex = -1;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '.') {
        decimalPointIndex = i;
      }
    }
    s = s.substring(decimalPointIndex + 1, s.length());
    return Integer.valueOf(s).intValue();
  }
}
