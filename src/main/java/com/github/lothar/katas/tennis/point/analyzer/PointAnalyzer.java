package com.github.lothar.katas.tennis.point.analyzer;

import com.github.lothar.katas.tennis.Player;
import com.github.lothar.katas.tennis.point.Point;

public interface PointAnalyzer {
    Point pointFor(Player player);
}
