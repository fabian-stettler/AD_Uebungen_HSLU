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

import java.util.Random;

/**
 * Demonstration von Bällen.
 */
public class DemoBalls {

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final String[] color = {"red", "black", "blue", "yellow", "green", "magenta"};

        Ball ball1 = new Ball(600, 10, 10, color[2]);

        Ball[] ballArray = new Ball[1000];
        Random rand = new Random();

        for (int i =0 ; i < 1000; i++){
            //create random circles
            int randomPosX = rand.nextInt((600));
            int randomSize = rand.nextInt(30 - 10) + 10;
            ballArray[i] = new Ball(randomSize, randomPosX, 0, color[i%5]);
        }

        for (int i =0 ; i < 1000; i++){
            Thread.startVirtualThread(ballArray[i]::run);
        }


    }


}
