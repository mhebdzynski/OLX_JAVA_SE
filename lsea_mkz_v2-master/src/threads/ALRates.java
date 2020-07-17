package threads;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
/**
 * Class that is used just to override the add method, so we can synchronize it in multithreading and we create it object in LSEA
 *
 * @author Patryk Dunajewski
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ALRates {

    /***list-> List of bikes and their rates(private, RateArr)*/
    private List<RateArr> list;

    /** Override of the add method, so it can be synchronized in multithreading
     * @param r-> List of bikes and their rates(private, RateArr)
     */
    public synchronized void add(RateArr r){
        list.add(r);
    }
}
