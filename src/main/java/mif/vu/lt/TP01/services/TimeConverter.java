package mif.vu.lt.TP01.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class TimeConverter implements ITimeConverter, Serializable {

    public int minsToHours(int time) {
        if(time > 200)
            return time / 60;
        else
            return time;
    }

}
