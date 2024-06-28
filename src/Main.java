import models.Bussines;
import models.HashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static HashTable<String, Bussines> hashTable1 = new HashTable<>();
    private static HashTable<String, Bussines> hashTable2 = new HashTable<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenú:");
            System.out.println("1. Insertar registro");
            System.out.println("2. Obtener registro");
            System.out.println("3. Cargar datos desde archivo");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    insertRecord(scanner);
                    break;
                case 2:
                    getRecord(scanner);
                    break;
                case 3:
                    loadFromFile();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    private static void insertRecord(Scanner scanner) {
        System.out.print("Ingresa la clave: ");
        String id = scanner.next();
        System.out.print("Ingresa el nombre: ");
        String name = scanner.next();
        System.out.print("Ingresa la dirección: ");
        String address = scanner.next();
        System.out.print("Ingresa la ciudad: ");
        String city = scanner.next();
        System.out.print("Ingresa el estado: ");
        String state = scanner.next();

        Bussines business = new Bussines(id, name, address, city, state);

        long startTime1 = System.nanoTime();
        hashTable1.put(id, business, 1);
        long endTime1 = System.nanoTime();
        System.out.println("Tiempo de inserción en HashTable1: " + (endTime1 - startTime1) + " ns");

        long startTime2 = System.nanoTime();
        hashTable2.put(id, business, 2);
        long endTime2 = System.nanoTime();
        System.out.println("Tiempo de inserción en HashTable2: " + (endTime2 - startTime2) + " ns");
    }

    private static void getRecord(Scanner scanner) {
        System.out.print("Ingresa la clave: ");
        String key = scanner.next();

        long startTime1 = System.nanoTime();
        Bussines value1 = hashTable1.get(key, 1);
        long endTime1 = System.nanoTime();
        System.out.println("Valor en HashTable1: " + value1 + " (Tiempo: " + (endTime1 - startTime1) + " ns)");

        long startTime2 = System.nanoTime();
        Bussines value2 = hashTable2.get(key, 2);
        long endTime2 = System.nanoTime();
        System.out.println("Valor en HashTable2: " + value2 + " (Tiempo: " + (endTime2 - startTime2) + " ns)");
    }

    private static void loadFromFile() {
        String line;
        String splitBy = ",";
        int id = 1;

        try (BufferedReader br = new BufferedReader(new FileReader("bussines.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] businessData = line.split(splitBy);
                String key = businessData[0];
                Bussines business = new Bussines(businessData[0], businessData[1], businessData[2], businessData[3], businessData[4]);

                long startTime1 = System.nanoTime();
                hashTable1.put(key, business, 1);
                long endTime1 = System.nanoTime();

                long startTime2 = System.nanoTime();
                hashTable2.put(key, business, 02);
                long endTime2 = System.nanoTime();

                System.out.println("[" + id + "] " + business);
                System.out.println("Tiempo de inserción en HashTable1: " + (endTime1 - startTime1) + " ns");
                System.out.println("Tiempo de inserción en HashTable2: " + (endTime2 - startTime2) + " ns");

                id++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
