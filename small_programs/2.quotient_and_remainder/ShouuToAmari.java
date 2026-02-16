
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
      Num n = new Num(InputControl.getNumNoText("[ " + (list.size() + 1) + "番目 ]   : ",
          "[ " + (list.size() + 1) + "番目 ] テキストではなく数字の入力をお願いします   : ", Double.class));
      list.add(n);
      if (list.size() > 1 && list.get(list.size() - 1).getNumber() == list.get(list.size() - 2).getNumber()) {
        stop = true;
      }
    } while (!stop);
    println("\nご入力ありがとうございました。");
    println("それぞれの商と余りは下記となります。\n");

    int count = 0;
    for (Num num : list) {
      println("[ " + ++count + "番目 ]  : " + num);
    }

  }

}

// 数字のクラス
class Num {

  final private double number;
  final private int quotient;
  final private int remainder;

  Num(double number) {
    this.number = number;
    this.quotient = Double.valueOf(this.number).intValue();
    this.remainder = findTheRemainder(this);
  }

  public double getNumber() {
    return this.number;
  }

  @Override
  public String toString() {
    return "数字：" + this.number + ",  商：" + this.quotient + ",   余り：" + this.remainder;
  }

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
