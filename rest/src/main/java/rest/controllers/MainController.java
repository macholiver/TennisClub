package rest.controllers;

import rest.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Rest controller for root
 *
 * @author Oliver Mach
 */
@RestController
public class MainController {
	/**
	 *
	 * @return resource uris
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final Map<String, String> getResources() {
		return Map.of("reservations_uri", ApiUris.ROOT_URI_RESERVATIONS,
				"courts_uri", ApiUris.ROOT_URI_COURTS);
	}
}
