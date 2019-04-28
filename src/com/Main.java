package com;

import static com.Util.*;

public class Main {

    public static void main(String[] args) {
        // Rotina de Testes
        // testes(5, -2); // Positivo e Negativo - mínimo 4 bits - SOMA OK - Subtração Errada
        //testes(-2, 1); // Positivo e Negativo - AXIOMA mínimo - SOMA OK - Subtração Errada
        // Pendente Subtração e Underflow
        //testes(13, 20); // Positivo e Positivo - SOMA OK 
        testes(-15, -17); // Negativo e Negativo - SOMA OK
        //testes(-15, -18); // Negativo e Negativo Overflow - SOMA ERRADA 
        // testes(-20, 35); // Negativo e Positivo  - SOMA OK
    }
    
    public static void testes(int x, int y) {
        Binario a = new Binario(x);
        Binario b = new Binario(y);

        // Soma
       /* System.out.println("*********SOMA*********");
        System.out.println("X: " + x + " Y: " + y);
        int soma = x + y;
        System.out.println("Saída dos Binários:");
        System.out.print("X: ");
        Util.printInt(a.valor);
        System.out.print("Y: ");
        Util.printInt(b.valor);
        System.out.print("R: ");
        printInt(Binario.soma(a.bool, b.bool, true));
        System.out.println("Conta: " + bin2dec(Binario.soma(a.bool, b.bool, true)));
        System.out.println("Esperado: " + soma);        
        System.out.println("**********************" + "\n"); */
    
        //Subtração
        System.out.println("*********SUBTRACAO*********");
        System.out.println("X: " + x + " Y: " + y);
        int sub = x - y;
        System.out.println("Saída dos Binários:");
        System.out.print("A: ");
        Util.printInt(a.valor);
        System.out.print("B: ");
        Binario c = new Binario(17);
        Util.printInt(c.valor);
        System.out.print("R: ");
        
        printInt(Binario.sub(a.bool, c.bool));
        System.out.println("Conta: " + bin2dec(Binario.sub(a.bool, b.bool)));
        System.out.println("Esperado: " + sub);
        System.out.println("**********************" + "\n");      

        //Multiplicação

        //Divisão

    }

    public static int bin2dec(int[] bin) {
        int res = 0;
        boolean negativo = false;

        // Se o número for negativo faz o complemento de 2
        // Ativa a flag que é numero negativo para após o complemento de 2 mudar o sinal
        if (bin[0] == 1) {
            negativo = true;
            bin = Binario.complemento2(bin);
        }  

        // Cálculo do valor decimal do número
        for (int i = bin.length - 1; i >= 0; i--) {
            if (bin[i] == 1) {      
                res += Math.pow(2, (double) bin.length - i - 1);
            } 
        }
        if (negativo = true) {return -res;}
        return res;
    }

}
