package com.github.lothar.katas.tennis.score;

public interface Score<T extends Score<?>> extends Comparable<T> {

    T next();
}
