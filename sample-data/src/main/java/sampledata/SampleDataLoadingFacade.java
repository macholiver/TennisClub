package sampledata;

import java.io.IOException;

/**
 * Populates database with sample data.
 *
 * @author Oliver Mach
 */
public interface SampleDataLoadingFacade {

    void loadData() throws IOException;
}
