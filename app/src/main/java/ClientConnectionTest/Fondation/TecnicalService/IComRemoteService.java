package ClientConnectionTest.Fondation.TecnicalService;


import org.dto.DTO;

import java.io.IOException;



/**
 * Created by gioele on 04/03/16.
 */
public interface IComRemoteService {

    Object RichiediAlServer(DTO dto, String indirizzo, Integer porta) throws IOException;


}
