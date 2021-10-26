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
        Leg leg1 = l_leg.get(0);
        assertEquals(1,leg1.duree);
        assertEquals(topologie.sommets.get(0),leg1.start);
        assertEquals(topologie.sommets.get(1),leg1.end);
        assertEquals("l1",leg1.name);
        Leg leg2 = l_leg.get(1);
        assertEquals(1,leg2.duree);
        assertEquals(topologie.sommets.get(1),leg2.start);
        assertEquals(topologie.sommets.get(2),leg2.end);
        assertEquals("l2",leg2.name);
        // todo : vérifications des autres champs moins sensibles
        System.out.println("Service = "+s);
    }
}