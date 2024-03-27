/*
 * Copyright 2024 Hochschule Luzern - Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Uebungen_AD.week5.n1.balls;

/**
 * Description of class Ball
 */
public class Ball implements Runnable {

    private final Circle circle;
    private final int size;
    private final int offset;
    private final int speed;

    public Ball(final int size, final int xPos, final int yPos, String color, final int speed) {
        this.size = size;
        this.circle = new Circle(size/2, xPos, yPos, color);
        this.offset = 2;
        this.speed = speed;
    }

    @Override
    public void run() {
        this.circle.makeVisible();

        //move balls down and make them pop
        while (circle.getY() < 300){
            this.circle.moveVertical(speed);
        }


        //ball pop
        int currentDiameter = circle.getDiameter();
        while (circle.getDiameter() > 0){
            this.circle.changeSize(circle.getDiameter()-20);
        }
        this.circle.makeInvisible();
    }
}
