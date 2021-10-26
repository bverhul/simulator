package com.simulator.io;

import com.simulator.sd.Sommet;
import com.simulator.sd.Terminal;
import com.simulator.sd.Topologie;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopologyReaderTest {

    @Test
    void topologyReader() throws IOException {
        Topologie topologie = TopologyReader.TopologyRead("test/com/simulator/io/test_topologie.txt");
        List<Terminal> liste = topologie.terminals;
        System.out.println(topologie);
        assertTrue(topologie.canMove(liste.get(0),liste.get(1)));
        assertTrue(topologie.canMove(liste.get(1),liste.get(2)));
        assertTrue(topologie.canMove(liste.get(2),liste.get(0)));

        assertFalse(topologie.canMove(liste.get(1),liste.get(0)));
        assertFalse(topologie.canMove(liste.get(2),liste.get(1)));
        assertFalse(topologie.canMove(liste.get(0),liste.get(2)));
    }
}