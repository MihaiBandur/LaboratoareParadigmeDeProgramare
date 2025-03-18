package com.sd.laborator;

import org.graalvm.polyglot.*;

import java.util.List;

class Exercitiul3 {

    // Funcția Python care generează o listă de 20 de numere întregi
    private static List<Integer> generateRandomList() {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();

        // Script Python pentru a genera lista de 20 de numere aleatorii
        String pythonScript =
                "import random\n" +
                        "random_list = [random.randint(1, 100) for _ in range(20)]\n" +
                        "random_list";

        // Executăm scriptul Python și obținem lista
        Value result = polyglot.eval("python", pythonScript);

        // Convertim rezultatul într-o listă de Integer
        List<Integer> randomList = result.as(List.class);

        // Închidem contextul Polyglot
        polyglot.close();

        return randomList;
    }

    // Funcția JavaScript care afișează lista de numere întregi
    private static void displayList(List<Integer> list) {
        // Creăm un context Polyglot pentru JavaScript
        Context polyglot = Context.create("js");

        // Convertim lista de numere întregi într-un șir pentru a o transmite către JavaScript
        String listAsString = list.toString();

        // Script JavaScript pentru a afișa lista
        String javascriptScript = "console.log('Lista generată:', " + listAsString + ");";

        // Executăm scriptul JavaScript
        polyglot.eval("js", javascriptScript);

        // Închidem contextul Polyglot
        polyglot.close();
    }

    // Funcția Ruby care sortează lista, elimină primele și ultimele 20% și calculează media aritmetică
    private static double calculateAverage(List<Integer> list) {
        // Creăm un context Polyglot pentru Ruby
        Context polyglot = Context.create("ruby");

        // Convertim lista de numere întregi într-un șir pentru a o transmite către Ruby
        String listAsString = list.toString();

        // Script Ruby pentru a sorta lista, elimina 20% din primele și ultimele elemente și calculează media
        String rubyScript =
                "numbers = " + listAsString + "\n" +
                        "numbers.sort!\n" +
                        "n = numbers.length\n" +
                        "trimmed_numbers = numbers[(n*0.2).to_i..(n*0.8).to_i - 1]\n" +
                        "average = trimmed_numbers.sum / trimmed_numbers.size.to_f\n" +
                        "average";

        // Executăm scriptul Ruby și obținem media
        Value result = polyglot.eval("ruby", rubyScript);

        // Obținem media ca rezultat
        double average = result.asDouble();

        // Închidem contextul Polyglot
        polyglot.close();

        return average;
    }

    // Funcția main
    public static void main(String[] args) {
        // Generăm lista de 20 de numere aleatorii folosind Python
        List<Integer> randomList = generateRandomList();

        // Afișăm lista folosind JavaScript
        displayList(randomList);

        // Calculăm media folosind Ruby
        double average = calculateAverage(randomList);

        // Afișăm rezultatul
        System.out.println("Media după eliminarea primelor și ultimelor 20% de elemente: " + average);
    }
}
