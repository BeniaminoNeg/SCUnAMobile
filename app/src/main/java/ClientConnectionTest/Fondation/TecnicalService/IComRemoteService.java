package ClientConnectionTest.Fondation.TecnicalService;


import java.io.IOException;

import Util.DTO;

/**
 * Created by gioele on 04/03/16.
 */
public interface IComRemoteService {

    Object RichiediAlServer(DTO dto, String indirizzo, Integer porta) throws IOException;


}
