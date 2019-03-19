package ru.stqa.pft.sandbox;
import java.lang.Math;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.net.SocketTimeoutException;

public class MyFirstProgram1 {
  public static void main(String[] args) {
    String se = "ff";
    System.out.println("broken arrows");
    System.out.println("V");
    System.out.println(se);
    double m = 2.0;
    double f = 3.9;
    System.out.println(m);
    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());
    Rectangle r = new Rectangle(4,6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
    Point p1 = new Point(2,0);
    Point p2 = new Point(1,0);
    System.out.println(Point.distance(p1,p2));
  }







}


