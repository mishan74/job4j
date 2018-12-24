package ru.job4j.pseudo;


 /**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 * Класс прорисовки фигур в псевдо графике.
 */

public class Paint {
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args)  {
        Paint paint = new Paint();
        paint.draw(new Square());
        paint.draw(new Triangle());
    }
}
