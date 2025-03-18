package com.sd.laborator;
import org.graalvm.polyglot.*;

import java.util.*;

public class Tema1 {

    private static String RToUpper(String token){

        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        String resultString = token.toUpperCase();
        polyglot.close();

        return resultString;
    }
    private static int SumCRC(String token){
        String subToken=token;
        if(token.length()>1)
            subToken = token.substring(1, token.length() - 1);
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();

        String script = "coefficients = [1, 2, 3, 5, 7, 11];\n" +
                "checksum = 0;\n" +
                "for i, ch in enumerate('" + subToken + "'):\n" +
                "    checksum += coefficients[i % len(coefficients)] * (ord(ch) ** (i % 6));\n" +
                "checksum = checksum % 10000;  # Limităm suma de control la 10000\n" +
                "checksum";

        Value result = polyglot.eval("python", script);
        int resultInt = result.asInt();
        polyglot.close();
        return resultInt;
    }

    public static void main(String[] args) {
        Context polyglot = Context.create("js", "Python");

        Value array = polyglot.eval("js", "[\"If\",\"we\",\"run\",\"the\",\"java\",\"command\",\"included\",\"in\",\"GraalVM\",\"we\",\"will\",\"be\",\"automatically\",\"using\",\"the\",\"Graal\",\"JIT\",\"compiler\",\"no\",\"extra\",\"configuration\",\"is\",\"needed\"]");

        Map<Integer, List<String>> crcGroups = new HashMap<>();

        for (int i = 0; i < array.getArraySize(); i++) {
            String element = array.getArrayElement(i).asString();
            String upper = RToUpper(element);
            int crc = SumCRC(upper);


            crcGroups.putIfAbsent(crc, new ArrayList<>());
            crcGroups.get(crc).add(upper);
        }


        for (Map.Entry<Integer, List<String>> entry : crcGroups.entrySet()) {
            System.out.println("Suma de control " + entry.getKey() + " -> " + entry.getValue());
        }

        polyglot.close();
    }
}

