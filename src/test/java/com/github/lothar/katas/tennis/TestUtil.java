package com.github.lothar.katas.tennis;

import static java.util.stream.IntStream.range;

public class TestUtil {

    public static void repeat(int times, Runnable runnable) {
        range(0, times).forEach(i -> runnable.run());
    }

}
