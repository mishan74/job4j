package ru.job4j.condition;

/**
 *Class Point описывает точку в двоичной системе координат.
 *@author Mikhail Rozdin
 *@since 05.12.2018
 */
public class Point {
    private final int x;
    private final int y;

    /**
     * Конструктор, устанавливающий координаты точки.
     * @param x координата оси X.
     * @param y координата оси Y.
     */
    public  Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод, считающий расстояние до другой точки.
     * @param that точка, до которой надо считать расстояние.
     * @return расстояние между текущей и переданной точками.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2)
        );
    }

    public static void main(String[] args) {
        Point a = new Point(-5, 2);
        Point b = new Point(-2, 5);

        System.out.println("x1 = " + a.x);
        System.out.println("y1 = " + a.y);
        System.out.println("x2 = " + b.x);
        System.out.println("y2 = " + b.y);

        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками А и В : " + result);
    }
}
