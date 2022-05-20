package de.meclab.simulator_grid.environment;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.tobject.ObjectMatrix2D;
import cern.colt.matrix.tobject.impl.SparseObjectMatrix2D;
import de.meclab.simulator_grid.entities.IEntity;
import org.jblas.DoubleMatrix;

import java.util.ArrayList;
import java.util.List;

public class CEnvironment implements IEnvironment
{
    ObjectMatrix2D m_grid;
    List<IEntity> m_entitis;

    /**
     * constructor
     */
    public CEnvironment()
    {
        m_grid = new SparseObjectMatrix2D( 100, 100 );
        m_entitis = new ArrayList<>();
    }

    /**
     * constructor
     *
     * @param p_rows rows
     * @param p_columns columns
     */
    public CEnvironment( final int p_rows, final int p_columns )
    {
        m_grid = new SparseObjectMatrix2D( p_rows, p_columns );
        m_entitis = new ArrayList<>();
    }

    @Override
    public void put( List< IEntity > p_entitis )
    {
        m_entitis.addAll( p_entitis );
        m_entitis.parallelStream().forEach( e -> m_grid.set( ( int ) e.position().get( 0 ), ( int ) e.position().get( 1 ), e ) );
    }

    @Override
    public void execute()
    {
        if ( m_entitis.isEmpty() )
            throw new RuntimeException( "environment has no entities" );

        m_entitis.parallelStream().forEach( e -> {
            try
            {
                e.call();
            }
            catch ( Exception ex )
            {
                ex.printStackTrace();
            }
        } );
    }

    @Override
    public synchronized void remove( IEntity p_entity )
    {
        IEntity l_entity = ( IEntity ) m_grid.getQuick( ( int ) p_entity.position().get( 0 ), ( int ) p_entity.position().get( 1 ) );
        if ( l_entity.equals( p_entity ) )
        {
            m_grid.set( ( int ) l_entity.position().get( 0 ), ( int ) l_entity.position().get( 1 ), null );
            m_entitis.remove( l_entity );
        }
    }

    public synchronized IEntity move(IEntity p_entity, DoubleMatrix p_position )
    {
        final DoubleMatrix1D l_position = new DenseDoubleMatrix1D( p_position.toArray() );

        final IEntity l_entity = ( IEntity ) m_grid.getQuick( ( int ) l_position.get( 0 ), ( int ) l_position.get( 1 ) );

        if ( l_entity != p_entity )
        {
            if ( l_entity != null )
                return l_entity;

            m_grid.set( ( int ) l_position.get( 0 ), ( int ) l_position.get( 1 ), p_entity );
            m_grid.set( ( int ) p_entity.position().get( 0 ), ( int ) p_entity.position().get( 1 ), null );
        }

        p_entity.position( p_position );

        return p_entity;
    }

    @Override
    public List< IEntity > entities()
    {
        return m_entitis;
    }

    @Override
    public boolean positionoutgrid( DoubleMatrix p_position )
    {
        return p_position.get( 0 ) >= m_grid.rows() || p_position.get( 1 ) >= m_grid.columns() || p_position.get( 0 ) < 0 || p_position.get( 1 ) < 0;
    }
}
