package de.meclab.simulator_grid.common;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * configuration class used to help parsing and loading YAML file using snakeyaml
 * @see <a href={https://www.baeldung.com/java-snake-yaml}></a>
 */
public class CConfiguration
{
    /**
     * configuration instance
     */
    public static final CConfiguration INSTANCE = new CConfiguration();
    /**
     * configuration map
     */
    private final Map< String, Object > m_configuration = new ConcurrentHashMap<>();

    /**
     * constructor
     */
    private CConfiguration()
    {

    }

    /**
     * load configuration
     *
     * @param p_filepath configuration file path (YAML)
     *
     * @return configuration instance
     */
    public final CConfiguration load( String p_filepath )
    {
        try (
            InputStream l_input = CConfiguration.class.getResourceAsStream( p_filepath )
        )
        {
            Map< String, Object > l_config = new Yaml().load( l_input );
            if ( l_config != null )
                m_configuration.putAll( l_config );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }

        return this;
    }

    /**
     * get configurations
     *
     * @return configurations
     */
    public Map< String, Object > getall()
    {
        return m_configuration;
    }

    /**
     * returns single config element
     *
     * @param p_key element path (nodes)
     *
     * @return desired config
     */
    public Object get( String... p_key )
    {
        return nested( m_configuration, p_key );
    }

    /**
     * extract nested nodes
     *
     * @param p_configmap  configuration map
     * @param p_key element path (nodes)
     *
     * @return configuration object
     */
    @SuppressWarnings("unchecked")
    private Object nested( final Map< String, Object > p_configmap, final String... p_key )
    {
        if ( ( p_key == null ) || ( p_key.length == 0 ) )
            throw new RuntimeException( "path is not correct" );

        final Object l_value = p_configmap.get( p_key[0] );
        return ( p_key.length == 1 ) || ( l_value == null )
                ? l_value : nested( ( Map< String, Object > ) l_value, p_key[1] );
    }
}
