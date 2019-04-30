/*
    EP I - OCD - 2019/1S
    Danilo Nunes Davanso NUSP 7650926 T.04
    Gabriel Di Pardi Arruda NUSP 8921610 T.04
*/

package com;

import static com.Util.*;

public class Main {

    public static void main(String[] args) {
        // Rotina de Testes Básica
        /* testes(-1, 2); // Positivo e Negativo - SOMA SUB BIN2DEC - OK
        testes(2, 1); // Positivo e Positivo -  SOMA SUB BIN2DEC - OK
        testes(-2, -1); // Positivo e Negativo - SOMA SUB BIN2DEC - OK*/
        testes(-15, -18); // Negativo e Negativo - SOMA SUB BIN2DEC - OK Overflow - 
        
        // Pendente Underflow
        // Testes extras
        // testes(13, 20); // Positivo e Positivo 
        // testes(-15, -17); // Negativo e Negativo 
        // testes(-20, 35); // Negativo e Positivo 
        // testes(23, -12);
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
        printInt(Binario.soma(a.bool, b.bool));
        System.out.println("Esperado em R: " + soma);  
        System.out.println("Conversão Bin2Dec de R: " + bin2dec(Binario.soma(a.bool, b.bool)));     
        System.out.println("**********************" + "\n");
    
        //Subtração
        System.out.println("*********SUBTRACAO*********");
        System.out.println("X: " + x + " Y: " + y);
        int sub = x - y;
        System.out.println("Saída dos Binários:");
        System.out.print("A: ");
        Util.printInt(a.valor);
        System.out.print("B: ");
        Util.printInt(b.valor);
        System.out.print("R: ");
        Binario b2 = new Binario(-y);
        printInt(Binario.sub(a.bool, b2.bool));
        System.out.println("Esperado em R: " + sub);
        System.out.println("Conversão Bin2Dec de R: " + bin2dec(Binario.sub(a.bool, b2.bool)));    
        System.out.println("**********************" + "\n");      

        //Multiplicação

        //Divisão

    }

    public static int bin2dec(int[] bin) {
        int res = 0;
        boolean negativo = false;

        // Representação de um número negativo após inserção de um binário em complemento de 2 
        // O número negativo faz o complemento de 2 novamente para desfazer a representação sem sinal-e-magnitude e voltar para o padrão binário clássico
        // Ativa a flag que é numero negativo para no retorno voltar o número com sinal trocado após o complemento de 2 mudar o sinal
        if (bin[0] == 1) {
            negativo = true;
            bin = Binario.complemento2(bin);
        }  

        // Cálculo do valor decimal do número positivo
        for (int i = bin.length - 1; i > 0; i--) {
            if (bin[i] == 1) {      
                res += Math.pow(2, (double) bin.length - i - 1);
            } 
        }
        if (negativo == true) {
            return -res;
        } else return res;
    }

}
