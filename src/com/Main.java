package com;

import static com.Util.*;

public class Main {

    public static void main(String[] args) {
        // Rotina de Testes
        //testes(5, -2); // Positivo e Negativo - mínimo 4 bits - OK
         testes(1, -2); // Positivo e Negativo - AXIOMA mínimo
        /* testes(13, 20); // Positivo e Positivo
        testes(-15, -17); // Negativo e Negativo
        testes(-20, 35); // Negativo e Positivo */
    }
    
    public static void testes(int x, int y) {
        Binario a = new Binario(x);
        Binario b = new Binario(y);

        // Soma
        System.out.println("*********SOMA*********");
        System.out.println("X: " + x + " Y: " + y);
        int soma = x + y;
        System.out.println("Saída dos Binários:");
        System.out.print("X: ");
        Util.printInt(a.valor);
        System.out.print("Y: ");
        Util.printInt(b.valor);
        System.out.print("R: ");
        printInt(Binario.soma(a.bool, b.bool, false));
        System.out.println("Conta: " + bin2dec(Binario.soma(a.bool, b.bool, false)));
        System.out.println("Esperado: " + soma);        
        System.out.println("**********************" + "\n");
       

        //Subtração
        System.out.println("*********SUBTRACAO*********");
        System.out.println("X: " + x + " Y: " + y);
        int sub = x - y;
        System.out.println("Esperado: " + sub + ", Conta: " + bin2dec(Binario.sub(a.bool, b.bool)));
        System.out.println("Saída dos Binários:");
        System.out.print("A: ");
        Util.printInt(a.valor);
        System.out.print("B: ");
        Util.printInt(b.valor);
        System.out.print("R: ");
        printInt(Binario.sub(a.bool, b.bool));
        System.out.println("**********************" + "\n");      

        //Multiplicação

        //Divisão

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
