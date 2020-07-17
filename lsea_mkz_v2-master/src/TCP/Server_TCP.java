package TCP;

import lombok.NoArgsConstructor;
import lsea.LSEA;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used for TCP server.<br>
 * It contains only basic constructor and method that sends data to the server.<br>
 * NoArgsConstructor  is generated automatically by lombok.
 *
 * @author Kamil Rutkowski
 *
 */
@NoArgsConstructor
public class Server_TCP {

    /**
     * method that enables server receiving data from the client via TCP protocol<br>
     * after receiving the data, server prints it on the screen
     * @param prof list of profiles - it is necessary to let server know how many object will be send to it
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void TCP_receive(List<LSEA> prof) throws IOException, ClassNotFoundException {

        ServerSocket server = new ServerSocket(2022);
        Socket client = server.accept();
        System.out.println("connected");

        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream()); // get the output stream of client.
        ObjectInputStream in = new ObjectInputStream(client.getInputStream());    // get the input stream of client.

        List<LSEA> profiles = new ArrayList<LSEA>();

        for(int i=0; i<prof.size(); i++) {
            profiles.add((LSEA)in.readObject());
            System.out.println(profiles.get(i).toString());
        }

        in.close();
        out.close();

        client.close();
        server.close();
    }
}
