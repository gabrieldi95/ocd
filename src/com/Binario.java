package com;

import static com.Util.*;

public class Binario {

    int[] valor;
    boolean[] bool;

    Binario(int valor){
        this.valor = dec2bin(valor);
        this.bool = bin2bool(this.valor);
    }

    /**
     * dec2bin
     * Transforma um número decimal em binário
     * @param dec Número inteiro a ser convertido
     * @return Array de inteiros que representa um número binário, no menor tamanho possível
     */
    static int[] dec2bin(int dec) {

        // Descobre a quantidade de casas de bits para criar um array representando o numero
        // Utiliza o inteiro do logaritmo do número decimal na base 2 (inversa da exponencial) 
        int bitSize = (int) Math.ceil(Math.log((Math.abs(dec))/(Math.log(2))));

        // Adiciona-se duas unidades para manter o sinal-e-magnitude além de um complemento de 2
        int[] a = new int[bitSize + 2];

        // PS: NÃO TESTADO O UNDERFLOW - TESTE PARA PONTO FLUTUANTE E NUMEROS COM CASAS DECIMAIS
       
        int index = a.length-1;
        int modulo_do_Decimal = Math.abs(dec);

        // Popula os elementos em módulo, caso negativo será rodado o complemento de 2 no número positivo
        while ((modulo_do_Decimal > 0) && (index >= 0)) { 
            a[index] = modulo_do_Decimal % 2;
            modulo_do_Decimal = modulo_do_Decimal / 2;
            index--; 
        }

        // Se o número for negativo ele roda a função complemento de 2 que normaliza o número para o padrão de sinal-e-magnitude
        if (dec < 0) {
            a = complemento2(a);
        }

        return a;
    }
    
    /**
     * complemento1
     * Retorna o complemento de 1 de um número binário
     * @param num Array de inteiros que representa um número binário
     * @return Array de inteiros que representa um número binário, em complemento de 1, no menor tamanho possível
     */
    static int[] complemento1(int[] num) {
        int[] a = new int[num.length];       

        // Realiza a negação de todos os elementos
        for (int i = num.length - 1; i >= 0 ; i--) {
            if (num[i] == 0)  a[i] = 1;
            else { a[i] = 0; }
        }

        return a;
    }

     /**
     * complemento2
     * Retorna o complemento de 2 de um número binário
     * @param num Array de inteiros que representa um número binário sem complemento
     * @return Array de inteiros que representa um número binário, em complemento de 2, no menor tamanho possível
     */
    static int[] complemento2(int[] num) {

        int [] complemento1 = complemento1(num);
        boolean [] complemento1_boolean = bin2bool(complemento1);
        int [] add = dec2bin(1);
        boolean [] add_boolean = bin2bool(add);
        int [] complemento2 = soma(complemento1_boolean,add_boolean, false);

        return complemento2;
    }

    /**
     * bin2bool
     * @param num Array de inteiros que representa um número binário
     * @return Array de booleano que representa um número binário (para facilitar nas operações)
     */
    static boolean[] bin2bool(int[] num){
        boolean[] bool = new boolean[num.length];
        for (int i = 0; i < num.length; i++) {
            if(num[i] == 1) bool[i] = true;
            else bool[i] = false;
        }
        return bool;
    }

    /**
     * soma
     * @param a
     * @param b
     * @param over Flag que determina se a função ignora ou não o overflow. Se 'true', não ignora. (No complemento de 2 é necessário ignorar o overflow)
     * @return Array de inteiros que representa a soma de a+b
     */
    static int[] soma(boolean[] a, boolean[] b, boolean over) {
        // Carry in inicializado como 1
        boolean carryIn = false;

        // Caso 'a' e 'b' possuam tamanhos diferentes (de array), aumenta o tamnho do menor até ficar do mesmo tamanho
        if(isBigger(a, b)){
            b = normaliza(a, b);
        } else {
            a = normaliza(b, a);
        }

        // Inicializa array da resposta de a+b
        int[] res = new int[a.length];

        // Algoritmo da soma
        for (int i = res.length-1; i >= 0 ; i--) {
            if( (a[i] ^ b[i]) ^ carryIn){
                res[i] = 1;
            } else res[i] = 0;

            if( (!a[i] && (carryIn && b[i])) || (a[i] && (carryIn || b[i])) ){
                carryIn = true;
            } else carryIn = false;

        }

        // Caso haja overflow, aumenta o tamanho do array em 1
        int[] sobra = new int[res.length+1];
        if(carryIn && over){
            sobra[0] = 1;
            for (int i = 0; i < res.length; i++) {
                sobra[i+1] = res[i];
            }
            return sobra;
        }

        return res;
    }

    /**
     * sub
     * @param a
     * @param b
     * @return a-b
     */
    static int[] sub(boolean[] a, boolean[] b){

        // Normaliza o tamanho
        if(isBigger(a, b)){
            b = normaliza(a, b);
        }else {
            a = normaliza(b, a);
        }
        return soma(a, b, true);
    }

    static boolean[] booth(boolean[] x, boolean[] y){
        if(isBigger(x, y)){
            y = normaliza(x, y);
        }else {
            x = normaliza(y, x);
        }

        boolean[] a = new boolean[x.length+1];
        boolean[] b = new boolean[x.length+1];

        for(int i = 1; i<a.length; i++){
            a[i] = x[i-1];
            b[i] = y[i-1];
        }

        boolean[] res = new boolean[a.length];
        boolean Q = false;


        for (int i = 0; i < a.length; i++) {
            if (b[b.length - 1] == Q) {
                Q = b[b.length - 1];
                b = shiftR(b);
                b[0] = res[res.length - 1];
                res = shiftR(res);
            } else if (!Q) {
                res = bin2bool(sub(res, a));
                Q = b[b.length - 1];
                b = shiftR(b);
                b[0] = res[res.length - 1];
                res = shiftR(res);
            } else {
                res = bin2bool(soma(res, a, false));
                Q = b[b.length - 1];
                b = shiftR(b);
                b[0] = res[res.length - 1];
                res = shiftR(res);
            }
        }

        return concat(res, b);
    }


}
