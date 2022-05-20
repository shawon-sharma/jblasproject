package de.meclab.simulator_grid.core;

/**
 * Functional interface <code>{@link Simulatable}</code> for simulation tasks.
 * Any simulating routine should implement <code>simulate</code> method.
 */
@FunctionalInterface
public interface Simulatable
{
    /**
     * Method encapsulates the simulating task
     */
    void simulate();
}
