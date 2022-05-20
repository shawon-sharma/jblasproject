package de.meclab.simulator_grid.common;

import org.jblas.DoubleMatrix;

import java.util.Random;

public enum EDirection {

    RIGHT(0,1),
    RIGHTUP(-1,1),
    UP(-1,0),
    LEFTUP(-1,-1),
    LEFT(0,-1),
    LEFTDOWN(1,-1),
    DOWN(1,0),
    RIGHTDOWN(1,1);

    private final DoubleMatrix m_directionmatrix;

    EDirection(int x, int y)
    {
        m_directionmatrix = new DoubleMatrix(new double[]{x,y});
    }

    public DoubleMatrix nextposition(DoubleMatrix p_position, double p_speed) {
        return p_position.add(
                        m_directionmatrix.mul(p_speed));

    }

    public static EDirection random()
    {
        return values()[new Random().nextInt(values().length)];
    }
}
