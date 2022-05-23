package mif.vu.lt.TP01.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;

@Alternative
@ApplicationScoped
public class TimeConverterAlt implements ITimeConverter, Serializable {

    public int minsToHours(int time) {
        if(time > 250)
            return time / 60;
        else
            return time;
    }

}
