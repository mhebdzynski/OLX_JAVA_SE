package UDP;
import lombok.NoArgsConstructor;
import lsea.LSEA;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic class for UDP server. There is only one method and constructor in this class.<br>
 * This method is getting list of profiles from the client UDP and writing down info about those profiles.<br>
 * NoArgsConstructor is generated automatically by lombok.
 *
 * @author Patryk Dunajewski
 *
 */
@NoArgsConstructor
public class Server_UDP {

    /**
     * Method that is setting sever and getting data from the client(UDP) and than printing it.
     * @param profiles list with all profiles
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void receiveUDP(List<LSEA>profiles) throws ClassNotFoundException, IOException {

        List<LSEA>lsea = new ArrayList<LSEA>();
        DatagramSocket ds = new DatagramSocket(9997);
        byte[] b = new byte [profiles.size()*128];
        DatagramPacket dp = new DatagramPacket(b,b.length);
        ds.receive(dp);

        byte[] data = dp.getData();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInput oi = new ObjectInputStream(bais);

        for(int i =0; i<profiles.size(); i++) {
            lsea.add((LSEA) oi.readObject());
            lsea.get(i).getInfo();
        }

        oi.close();
        bais.close();
        ds.close();
    }
}
