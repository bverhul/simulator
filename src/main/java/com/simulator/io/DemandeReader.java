package com.simulator.io;

import com.simulator.sd.Demande;
import com.simulator.sd.Demandes;
import com.simulator.sd.Terminal;

import java.io.*;
import java.util.List;

public class DemandeReader {
    /**
     * Permet de lire les demandes à partir d'un fichier texte
     * @param file
     * @param l_terminals
     * @return
     * @throws IOException
     */
    public static Demandes DemandeRead(String file, List<Terminal> l_terminals) throws IOException {
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
                        args[4],Integer.parseInt(args[5]),Integer.parseInt(args[6]), l_terminals));
                line = bufReader.readLine();
            }
            bufReader.close();
        }else{
            throw new FileNotFoundException();
        }
        return demandes;
    }
}
