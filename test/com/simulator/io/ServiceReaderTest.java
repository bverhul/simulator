package com.simulator.io;

import com.simulator.sd.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceReaderTest {

    @Test
    void servicesRead() throws IOException {
        Topologie topologie = TopologyReader.TopologyRead("test/com/simulator/io/test_topologie.txt");
        System.out.println(topologie.sommets.toString());
        Services services = ServiceReader.ServicesRead("test/com/simulator/io/test_services.txt",topologie);
        assertEquals(1,services.getL_service().size());

        Service s = services.getL_service().get(0);
        /* vérification des champs */
        assertEquals(1,s.getId());
        assertEquals("u1",s.getDepart().getName());
        assertEquals("u3",s.getArrivee().getName());
        /* vérification des legs */
        List<Leg> l_leg = s.getList_leg();
        assertEquals(2,l_leg.size());
    }
}