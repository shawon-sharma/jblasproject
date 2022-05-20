package de.meclab.simulator_grid.entities;

import de.meclab.simulator_grid.common.EDirection;
import de.meclab.simulator_grid.environment.IEnvironment;
import org.jblas.DoubleMatrix;

import java.time.LocalDateTime;

/**
 * car class
 */
public class CCar extends IBaseEntity
{
    /**
     * car speed in cell/s
     */
    private double m_speed;
    /**
     * current position
     */
    private DoubleMatrix m_position;
    /**
     * current direction
     */
    private EDirection m_direction;
    /**
     * environment
     */
    private IEnvironment m_environment;

    /**
     * constructor
     *  @param p_speed speed
     * @param p_position starting position
     * @param p_environment environment instance
     */
    public CCar(double p_speed, DoubleMatrix p_position, IEnvironment p_environment )
    {
        m_speed = p_speed;
        m_position = p_position;
        m_direction = EDirection.RIGHT;
        m_environment = p_environment;
    }

    @Override
    public Object call() throws Exception
    {
        DoubleMatrix l_nextposition = m_direction.nextposition( m_position, m_speed );

        if ( m_environment.positionoutgrid( l_nextposition ) )
            return this;

        m_environment.move( this, l_nextposition );
        System.out.println( "time: " + LocalDateTime.now().toLocalTime());
        System.out.println( "position: " + m_position + ", speed: " + m_speed );
        return this;
    }

    @Override
    public void speed( double p_speed )
    {
        m_speed = p_speed;
    }

    @Override
    public double speed()
    {
        return m_speed;
    }

    @Override
    public void position( DoubleMatrix p_position )
    {
        //changing here @shawon Sharma

        m_position.put( 0, p_position.get( 0 ) );
        m_position.put( 1, p_position.get( 1 ) );
    }

    @Override
    public DoubleMatrix position()
    {
        return m_position;
    }

    @Override
    public EDirection direction()
    {
        return m_direction;
    }
}