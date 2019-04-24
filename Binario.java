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
     * @param dec Núemro inteiro a ser convertido
     * @return Array de inteiros que representa um número binário, no menor tamanho possível
     */
    static int[] dec2bin(int dec){

        int bitSize = 1;
        double maxSize = 1;

        // Descobre a quantidade de bits para criar o array
        while(maxSize < dec){
            maxSize = maxSize + Math.pow(2, bitSize);
            bitSize++;
        }

        int[] a = new int[bitSize];

        int index = a.length-1;
        // Popula o array
        while(dec > 0 && index >= 0){
            a[index] = dec % 2;
            dec = dec / 2;
            index--;
        }

        return a;
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
        boolean ci = false;

        // Caso 'a' e 'b' possuam tamanhos diferentes (de array), aumenta o tamnho do menor até ficar do mesmo tamanho
        if(isBigger(a, b)){
            b = normaliza(a, b);
        }else {
            a = normaliza(b, a);
        }

        // Inicializa array da resposta de a+b
        int[] res = new int[a.length];

        // Algoritmo da soma
        for (int i = res.length-1; i >= 0 ; i--) {
            if( (a[i] ^ b[i]) ^ ci){
                res[i] = 1;
            }else res[i] = 0;
            if( (!a[i] && (ci && b[i])) || (a[i] && (ci || b[i])) ){
                ci = true;
            }else ci = false;
        }

        // Caso haja overflow, aumenta o tamanho do array em 1
        int[] sobra = new int[res.length+1];
        if(ci && over){
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

        return soma(a, negativo(b), false);
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
