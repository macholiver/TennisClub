package service.config;

import dto.CourtDTO;
import dto.CustomerDTO;
import dto.ReservationDTO;
import dto.SurfaceDTO;
import entity.*;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import config.PersistenceSampleApplicationContext;

/**
 * Dozer configuration for mapping
 *
 * @author Oliver Mach
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackages = "service")
public class ServiceConfiguration {

	@Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();		
		dozer.addMapping(new DozerCustomConfig());
		return dozer;
	}

	public class DozerCustomConfig extends BeanMappingBuilder {
	    @Override
	    protected void configure() {
			mapping(Reservation.class, ReservationDTO.class);
			mapping(Court.class, CourtDTO.class);
			mapping(Customer.class, CustomerDTO.class);
			mapping(Surface.class, SurfaceDTO.class);
	    }
	}
}

