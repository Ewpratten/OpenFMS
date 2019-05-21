package OpenFMS;

import java.lang.Thread;

import OpenFMS.field.Arena;

public class CLI {
    Arena arena;

    public CLI(Arena arena) {
        this.arena = arena;
    }
    
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Do nothing
        }
        
        System.out.println("+-------------- OpenFMS Console -----------------+");
        System.out.println("| To control the field, use the various avalible |\n"
                          +"|        commands in the OpenFMS Console.        |\n"
                          +"|                                                |\n"
                          +"| To view these commands, use the \"help\" command |");
        System.out.println("+------------------------------------------------+");

        while (true) {
            
        }
    }
}