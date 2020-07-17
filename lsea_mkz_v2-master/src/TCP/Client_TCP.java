package TCP;

import lombok.NoArgsConstructor;
import lsea.LSEA;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 * Class used for TCP client.<br>
 * It contains only basic constructor and method that sends data to the server.<br>
 * NoArgsConstructor is generated automatically by lombok.
 *
 * @author Kamil Rutkowski
 *
 */
@NoArgsConstructor
public class Client_TCP {

    /**
     * method that enables client sending data to the server via TCP protocol
     * @param prof list of profiles that will be send to the server
     * @throws UnknownHostException
     * @throws IOException
     */
    public void TCP_send(List<LSEA> prof) throws UnknownHostException, IOException {

        Socket client = new Socket ("localhost", 2022);
        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(client.getInputStream());

        for(int i=0; i<prof.size(); i++) {
            out.writeObject(prof.get(i));
            out.flush();
        }

        in.close();
        out.close();
        client.close();
    }
}


