/*
 * Copyright (C) 2016 Iyad Kuwatly
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

package com.example;

import java.util.Random;

// Jokes url: http://www.short-funny.com/
public class JokesFactory {
    String jokes[] = {
            "Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all.",
            "Why does it suck to be a penguin? Because even when you get angry, you still look cute.",
            "I’ve always thought my neighbors were quite nice people. But then they put a password on their Wi-Fi.",
            "Bus driver to passenger: Don’t you want to sit down?\nPassenger: No, I am in a hurry."
            };

    public String getJoke() {
        return jokes[new Random().nextInt(jokes.length)];
    }


}
