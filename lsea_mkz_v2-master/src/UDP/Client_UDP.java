package UDP;

import lombok.NoArgsConstructor;
import lsea.LSEA;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

/**
 * Basic class for UDP client. There is only one method and constructor in this class.<br>
 * This method is sending the list of profiles to sever UDP.<br>
 * NoArgsConstructor is generated automatically by lombok.
 *
 * @author Patryk Dunajewski
 *
 */
@NoArgsConstructor
public class Client_UDP {

    /**
     * This method is sending profiles list to server(UDP).
     * @param lsea list of profiles
     * @throws IOException
     */
    public void send_UDP(List<LSEA>lsea) throws IOException{

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        for(int i=0; i<lsea.size(); i++){
            oos.writeObject(lsea.get(i));
        }
        byte[] ba = baos.toByteArray();

        DatagramSocket ds = new DatagramSocket();
        InetAddress ia = InetAddress.getLocalHost();
        DatagramPacket dp = new DatagramPacket(ba, ba.length, ia,9997);

        ds.send(dp);
        ds.close();
        oos.close();
        baos.close();
    }
}
