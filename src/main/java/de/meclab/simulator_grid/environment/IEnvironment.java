package de.meclab.simulator_grid.environment;

import de.meclab.simulator_grid.entities.IEntity;
import org.jblas.DoubleMatrix;

import java.util.List;

/**
 * environment interface
 */
public interface IEnvironment {

    /**
     * put entities in environment
     *
     * @param p_entitis list of entities
     */
    void put( List<IEntity> p_entitis );

    /**
     * execute the environment, i.e. run all entities
     */
    void execute();

    /**
     * remove entity
     *
     * @param p_entity entity
     */
    void remove( IEntity p_entity );

    /**
     * move entity to new position
     *
     * @param p_entity entity
     * @param p_position new position
     *
     * @return entity instance
     */
    IEntity move( IEntity p_entity, DoubleMatrix p_position );

    /**
     * returns list of entities
     *
     * @return entities
     */
    List<IEntity> entities();

    /**
     * check if position is out of grid
     *
     * @param p_position position
     *
     * @return true if position out grid; otherwise false
     */
    boolean positionoutgrid( DoubleMatrix p_position );
}
