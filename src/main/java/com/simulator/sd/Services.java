package com.simulator.sd;

import java.util.ArrayList;
import java.util.List;

public class Services {
    private List<Service> l_service;

    public Services(){
        this.l_service = new ArrayList<>();
    }

    public boolean addService(Service service){
        return l_service.add(service);
    }

    public List<Service> getL_service() {
        return l_service;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Services{");
        sb.append("l_service=").append(l_service);
        sb.append('}');
        return sb.toString();
    }
}
