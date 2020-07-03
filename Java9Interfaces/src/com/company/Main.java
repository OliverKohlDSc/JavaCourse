package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	    // Process API changes
        // java.lang.ProcessHandle
        // java.lang.ProcessHandle.Info

        ProcessHandle currentProcess = ProcessHandle.current();
        // getPid();
        System.out.println("Current process id = " + currentProcess.pid());


        // Java 7
        // ABRM (Automatic Better Resource Management) - Try-With-Resources

        try {
            ARM_v9();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void ARM_v7() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/Volumes/Data/SynologyDrive/Code/Oliver/Kurse/Java Basis II/Code/Java9Interfaces/src/com/company/in.txt"));
        try (BufferedReader reader2 = reader) {
            System.out.println(reader2.readLine());
        }
    }

    static void ARM_v9() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/Volumes/Data/SynologyDrive/Code/Oliver/Kurse/Java Basis II/Code/Java9Interfaces/src/com/company/in.txt"));
        try (reader) {
            System.out.println(reader.readLine());
        }
    }
}
