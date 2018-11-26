package br.com.neolog.serialization;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class SerializationConfiguration
{

    @Bean
    @Autowired
    public ObjectMapper objectMapper()
    {
        return new ObjectMapper()
            .addMixIn( HibernateProxy.class, HibernateProxyMixIn.class );
    }

    private interface HibernateProxyMixIn
        extends
            HibernateProxy
    {
        @Override
        @JsonIgnore
        LazyInitializer getHibernateLazyInitializer();
    }
}
