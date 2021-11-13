package com.simulator.io;

import com.simulator.sd.*;
import com.simulator.state.BargeS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ServiceReader {
    public static Services ServicesRead(String file, Topologie topologie) throws IOException {
        Services services = new Services();
        List<Terminal> l_terminal = topologie.terminals;
        File f = new File(file);
        if(f.exists()) {
            FileReader inFileStream = new FileReader(f);
            BufferedReader bufReader = new BufferedReader(inFileStream);

            String line = bufReader.readLine();/* pour retirer l'entête */
            line = bufReader.readLine();
            while(line != null) {
                String[] args = line.split(";");
                System.out.println("Args: "+Arrays.toString(args)+",size="+args.length);
                /* todo : lire les args */
                Optional<Terminal> optn_depart_s = l_terminal.stream().filter(e->e.getName().equals(args[1])).findFirst();
                Optional<Terminal> optn_arrivee_s = l_terminal.stream().filter(e->e.getName().equals(args[2])).findFirst();
                if(!optn_arrivee_s.isPresent() || !optn_depart_s.isPresent())throw new NullPointerException();
                /* paramètre leg */
                List<Leg> list_leg = new ArrayList<>();
                String[] legs_name = args[3].split(",");
                String[] chemin_leg_name = args[4].split(",");
                String[] duree_leg_name = args[5].split(",");
                for(int i = 0 ; i < legs_name.length ; i++){
                   String[] startEnd_chemin = chemin_leg_name[i].split("-");
                   Optional<Terminal> optn_depart_leg = l_terminal.stream().filter(e->e.getName().equals(startEnd_chemin[0])).findFirst();
                   Optional<Terminal> optn_arrivee_leg = l_terminal.stream().filter(e->e.getName().equals(startEnd_chemin[1])).findFirst();
                   if(!optn_depart_leg.isPresent() || !optn_arrivee_leg.isPresent())throw new NullPointerException();
                   list_leg.add(new Leg(optn_depart_leg.get(),optn_arrivee_leg.get(),legs_name[i],Integer.parseInt(duree_leg_name[i])));
                }
                /* paramètre stops */
                // todo
                String[] stops_start = args[10].split(",");
                String[] stops_end = args[11].split(",");
                Map<Terminal,Integer> map_stops_start = new HashMap<>(),map_stops_stop = new HashMap<>();
                Arrays.stream(stops_start).forEach(stop_start->{
                    String[] args2 = stop_start.split(":");
                    Optional<Terminal> optn_sommet = l_terminal.stream().filter(e->e.getName().equals(args2[0])).findFirst();
                    if(!optn_sommet.isPresent())throw new NullPointerException();
                    map_stops_start.put(optn_sommet.get(),Integer.parseInt(args2[1]));
                });
                Arrays.stream(stops_end).forEach(stop_start->{
                    String[] args2 = stop_start.split(":");
                    Optional<Terminal> optn_sommet = l_terminal.stream().filter(e->e.getName().equals(args2[0])).findFirst();
                    if(!optn_sommet.isPresent())throw new NullPointerException();
                    map_stops_stop.put(optn_sommet.get(),Integer.parseInt(args2[1]));
                });
                /* --- barges --- */
                /* todo : récupérer les autres champs */
                /* par défaut, une barge */
                List<Barge> bargeList = new ArrayList<>();bargeList.add(new Barge(BargeS.EN_ATTENTE,1,5d,topologie.terminals.get(0)));

                /* ajout du service */
                Service service = new Service(
                        Integer.parseInt(args[0]), optn_depart_s.get(), optn_arrivee_s.get(),
                        Integer.parseInt(args[6]),Integer.parseInt(args[7]),Integer.parseInt(args[8]),
                        Integer.parseInt(args[9]),
                        map_stops_start,map_stops_stop,list_leg,bargeList
                );
                services.addService(service);

                bargeList.get(0).setService(service);
                line = bufReader.readLine();
            }
            bufReader.close();
        }
        return services;
    }
}
