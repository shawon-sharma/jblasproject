package de.meclab.simulator_grid.core;

/**
 * simulator class, used to simulate a predefined routine (simulating task). The simulating
 * task should be instance of <code>{@link Simulatable}</code> interface, then the simulator
 * will call the instance's <code>simulate</code> function inside the simulation loop.
 *
 * the simulation loop is controlled by a predefined timestep in the configuration file
 * or used the default one in case of missing.
 */
public class CSimulator implements ISimulator
{
    /**
     * default step time in ms (supposed to perform ~59 FPS)
     */
    private static final long DEFAULT_TIME_STEP = 17;
    /**
     * simulation task
     */
    private final Simulatable m_task;
    /**
     * time step in ms
     */
    private final long m_timestep;
    /**
     * flag to activate/deactivate current simulation
     */
    private boolean m_active;

    /**
     * constructor
     *
     * @param p_task     simulation task instance
     * @param p_timestep time step in ms
     */
    public CSimulator( Simulatable p_task, long p_timestep )
    {
        m_task = p_task;
        m_timestep = p_timestep < 1 ? DEFAULT_TIME_STEP : p_timestep;
        m_active = true;
    }

    @Override
    public void simulate()
    {
        if ( m_task != null )
            simulationloop();
    }

    /**
     * simulation loop method, controls the timing mechanism
     */
    private void simulationloop()
    {
        while ( m_active )
        {
            try
            {
                Thread.sleep( m_timestep );
                m_task.simulate();
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ISimulator activate()
    {
        m_active = true;
        return this;
    }

    @Override
    public ISimulator deactivate()
    {
        m_active = false;
        return this;
    }

    @Override
    public boolean active()
    {
        return m_active;
    }
}
