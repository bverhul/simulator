package com.simulator.io;

import com.simulator.sd.Sommet;
import com.simulator.sd.Topologie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TopologyReader {
    /**
     * Retourne la liste des sommets d'une topologie
     * @param file
     * @return
     * @throws IOException
     */
    public static List<Sommet> getSommets(String file) throws IOException {
        File f = new File(file);
        List<Sommet> liste = new ArrayList<>();
        if(f.exists()){
            FileReader inFileStream = new FileReader(f);
            BufferedReader bufReader = new BufferedReader(inFileStream);

            String line = bufReader.readLine();
            while(line != null){
                String[] args = line.split(";");
                /* vérifie si il existe déjà le sommet dans la liste */
                Optional<Sommet> s_a = liste.stream().filter(s->s.getName().equals(args[0])).findFirst();
                Optional<Sommet> s_b = liste.stream().filter(s->s.getName().equals(args[1])).findFirst();
                /* ajoute le sommet si il n'existe pas dans la liste */
                if(!s_a.isPresent())liste.add(new Sommet(args[0]));
                if(!s_b.isPresent())liste.add(new Sommet(args[1]));
                line = bufReader.readLine();
            }
            bufReader.close();
        }
        return liste;
    }

    public static Topologie TopologyRead(String file) throws IOException {
        return TopologyReader.TopologyRead(file,TopologyReader.getSommets(file));
    }
    private static Topologie TopologyRead(String file, List<Sommet> list) throws IOException {
        File f = new File(file);
        Topologie topologie = new Topologie(list);
        if(f.exists()){
            FileReader inFileStream = new FileReader(f);
            BufferedReader bufReader = new BufferedReader(inFileStream);

            String line = bufReader.readLine();
            while(line != null){
                String[] args = line.split(";");
                Optional<Sommet> s_a = list.stream().filter(s->s.getName().equals(args[0])).findFirst();
                Optional<Sommet> s_b = list.stream().filter(s->s.getName().equals(args[1])).findFirst();

                if(!s_a.isPresent() || !s_b.isPresent())throw new NullPointerException();
                /* création de l'arc */
                topologie.setArc(s_a.get(),s_b.get());
                line = bufReader.readLine();
            }

            bufReader.close();
        }
        return topologie;
    }
}
