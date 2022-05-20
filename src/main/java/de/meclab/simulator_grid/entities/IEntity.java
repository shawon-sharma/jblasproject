package de.meclab.simulator_grid.entities;

import de.meclab.simulator_grid.common.EDirection;
import org.jblas.DoubleMatrix;

import java.util.concurrent.Callable;

/**
 * entity interface
 */
public interface IEntity extends Callable
{
    /**
     * set a speed
     *
     * @param p_speed speed
     */
    void speed( double p_speed );

    /**
     * return speed value of the entity
     *
     * @return speed
     */
    double speed();

    /**
     * set a new position
     *
     * @param p_position position instance
     */
    void position( DoubleMatrix p_position );

    /**
     * returns current position of the entity
     *
     * @return position instance
     */
    DoubleMatrix position();

    /**
     * return current movement direction
     *
     * @return direction instance
     */
    EDirection direction();
}
