package com.sd.laborator;
import org.graalvm.polyglot.*;

class Polyglot {

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
                "for i, ch in enumerate('" + subToken + "'):\n" +  // Folosim subToken în loc de token
                "    checksum += coefficients[i % len(coefficients)] * (ord(ch) ** (i % 6));\n" +
                "checksum = checksum % 10000;  # Limităm suma de control la 10000\n" +  // Aplicăm modulo 10000
                "checksum";

        Value result = polyglot.eval("python", script);
        int resultInt = result.asInt();
        polyglot.close();
        return resultInt;
    }





    public static void main(String[] args) {

        Context polyglot = Context.create("js", "Python");
        Value array = polyglot.eval("js", "[\"If\",\"we\",\"run\",\"the\",\"java\",\"command\",\"included\",\"in\",\"GraalVM\",\"we\",\"will\",\"be\",\"automatically\",\"using\",\"the\",\"Graal\",\"JIT\",\"compiler\",\"no\",\"extra\",\"configuration\",\"is\",\"needed\",\"I\",\"will\",\"use\",\"the\",\"time\",\"command\",\"to\",\"get\",\"the\",\"real\",\"wall\",\"clock\",\"elapsed\",\"time\",\"it\",\"takes\",\"to\",\"run\",\"the\",\"entire\",\"program\",\"from\",\"start\",\"to\",\"finish\",\"rather\",\"than\",\"setting\",\"up\",\"a\",\"complicated\",\"micro\",\"benchmark\",\"and\",\"I\",\"will\",\"use\",\"a\",\"large\",\"input\",\"so\",\"that\",\"we\",\"arent\",\"quibbling\",\"about\",\"a\",\"few\",\"seconds\",\"here\",\"or\",\"there\",\"The\",\"large.txt\",\"file\",\"is\",\"150\",\"MB\"];");
        for (int i = 0; i < array.getArraySize();i++){
            String element = array.getArrayElement(i).asString();
            String upper = RToUpper(element);
            int crc = SumCRC(upper);
            System.out.println(upper + " -> " + crc);
        }

        polyglot.close();
    }
}

