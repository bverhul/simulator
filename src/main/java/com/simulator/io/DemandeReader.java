package com.simulator.io;

import com.simulator.sd.Demande;
import com.simulator.sd.Demandes;
import com.simulator.sd.Sommet;

import java.io.*;
import java.util.List;

public class DemandeReader {
    /**
     * Permet de lire les demandes à partir d'un fichier texte
     * @param file
     * @param l_sommets
     * @return
     * @throws IOException
     */
    public static Demandes DemandeRead(String file, List<Sommet> l_sommets) throws IOException {
        File f = new File(file);
        Demandes demandes = new Demandes();
        if(f.exists()) {
            FileReader inFileStream = new FileReader(f);
            BufferedReader bufReader = new BufferedReader(inFileStream);

            String line = bufReader.readLine();/* pour retirer l'entête */
            line = bufReader.readLine();
            while(line != null) {
                String[] args = line.split(";");
                demandes.addDemande(new Demande(args[0],args[1],Integer.parseInt(args[2]),Integer.parseInt(args[3]),
                        args[4],Integer.parseInt(args[5]),Integer.parseInt(args[6]), l_sommets));
                line = bufReader.readLine();
            }
            bufReader.close();
        }
        return demandes;
    }
}
