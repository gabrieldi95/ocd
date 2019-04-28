package com;

import static com.Util.*;

public class Main {

    public static void main(String[] args) {

        // testes(13, 17);
        /*
         * Binario a = new Binario(10); Binario b = new Binario(5);
         */
        // print(Binario.sub(a.bool, b.bool));
        // print(Binario.booth(a.bool, b.bool));

        testes(1, -1);
        testes(13, 20);
        testes(-15, -17);
        testes(-20, 35);
    }

    public static void testes(int x, int y) {
        Binario a = new Binario(x);
        Binario b = new Binario(y);

        // Soma
        System.out.println("Soma de X:" + x + " + Y:" + y);
        int soma = x + y;
        System.out.println("Correto: " + soma + ", conta: " + bin2dec(Binario.soma(a.bool, b.bool, true)));
        printInt(Binario.soma(a.bool, b.bool, true));

        //Subtração
        System.out.println("Subtração de X:" + x + " + Y:" + y);
        int sub = x - y;
        System.out.println("Correto: " + sub + ", conta: " + bin2dec(Binario.sub(a.bool, b.bool)));
        printInt(Binario.sub(a.bool, b.bool));
        System.out.println("\n");

    }

    public static int bin2dec(int[] bin) {
        int res = 0;
        for (int i = bin.length - 1; i >= 0; i--) {
            if (bin[i] == 1) {
                res += Math.pow(2, (double) bin.length - i - 1);
            }
        }
        return res;
    }

}
