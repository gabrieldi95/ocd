package com;

import static com.Util.*;

public class Main {

    public static void main(String[] args) {

        //testes(13, 17);
        Binario a = new Binario(10);
        Binario b = new Binario(5);
        //print(Binario.sub(a.bool, b.bool));
        print(Binario.booth(a.bool, b.bool));



    }

    public static  void testes(int x, int y){
        Binario a = new Binario(x);
        for (int i = y; i > 0; i--) {
            Binario b = new Binario(i);
            System.out.println("Soma de " + 13 + "+" + i);
            int soma = 13 + i;
            System.out.println("Correto: " + soma + ", conta: " + bin2dec(Binario.soma(a.bool, b.bool, true)));
            printInt(Binario.soma(a.bool, b.bool, true));

            System.out.println("Subtração de " + 13 + "-" + i);
            int sub = 13 - i;
            System.out.println("Correto: " + sub + ", conta: " + bin2dec(Binario.sub(a.bool, b.bool)));
            printInt(Binario.sub(a.bool, b.bool));
            System.out.println("\n");
        }
    }

    public static int bin2dec(int[] bin){
        int res = 0;
        for(int i = bin.length-1; i >= 0; i--){
            if(bin[i] == 1){
                res += Math.pow(2, (double) bin.length-i-1);
            }
        }
        return res;
    }

}
