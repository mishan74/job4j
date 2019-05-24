package ru.job4j.threads;

import javafx.scene.shape.Rectangle;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final int limitX;
    private final int limitY;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    @Override
    public void run() {
        int deltaX = 1;
        int deltaY = 1;
        boolean endWallX = false;
        boolean endWallY = false;
        int t = 1;
        while (!Thread.currentThread().isInterrupted()) {
            if ((!endWallX && (this.rect.getX() + rect.getWidth() >= limitX))
                    || (endWallX && (this.rect.getX() + rect.getWidth() <= 0))
            ) {
                deltaX = deltaX > 0 ? (int) ((Math.random() + 1) * -1)
                        : (int) ((Math.random() * 1) + 1);
                endWallX = !endWallX;
            }
            if ((!endWallY && ((this.rect.getY()) + rect.getHeight() >= limitY))
                    || (endWallY && (this.rect.getY() + rect.getHeight() <= 0))
            ) {
                deltaY = deltaY > 0 ? (int) ((Math.random() + 1) * -1)
                        : (int) ((Math.random() * 1) + 1);
                endWallY = !endWallY;
            }

            this.rect.setX(this.rect.getX() + deltaX);
            this.rect.setY(this.rect.getY() + deltaY);

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
