package Converter.observer;

import java.util.LinkedList;
import java.util.List;

public class Pulisher {

    //field
    private List<Subriber> subribers = new LinkedList<>();

    //method
    public void subribe(Subriber subriber){
        subribers.add(subriber);
    }

    public void unSubcribe(Subriber subriber){
        subribers.remove(subriber);
    }

    public void notifySubcribers(){
        for(Subriber subriber : subribers){
            subriber.update();
        }
    }
}
