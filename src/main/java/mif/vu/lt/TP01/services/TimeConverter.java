package mif.vu.lt.TP01.services;

import mif.vu.lt.TP01.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class TimeConverter implements Serializable {

    public int minsToHours(int time) {
        if(time > 200)
            return time / 60;
        else
            return time;
    }

}
