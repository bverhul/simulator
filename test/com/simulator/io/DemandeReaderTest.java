package com.simulator.io;

import com.simulator.sd.Demande;
import com.simulator.sd.Demandes;
import com.simulator.sd.Sommet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DemandeReaderTest {

    @Test
    void demandeRead() throws IOException {
        List<Sommet> liste = TopologyReader.getSommets("test/com/simulator/io/test_topologie.txt");
        System.out.println(liste.toString());
        Demandes demandes = DemandeReader.DemandeRead("test/com/simulator/io/test_demandes.txt",liste);
        List<Demande> l_demandes = demandes.getDemandeList();
        System.out.println(demandes.toString());
        // todo : les tests unitaires
        assertEquals(6,l_demandes.size());
        /* verifier individuellement les 6 demandes */
        l_demandes.stream().filter(p->p.type.equals("B"));
    }
}