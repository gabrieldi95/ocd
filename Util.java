package com;

public class Util {

    static void print(boolean[] arr){
        /**
         * print
         * Printa na tela um númro binário
         * @param Array booleano que representa um número binário
         */
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]) System.out.print(1);
            else System.out.print(0);
        }
        System.out.println("");
    }

    /**
     * printInt
     * Printa na tela um númro binário
     * @param arr de inteiros (1 ou 0) que representa um número binário
     */
    static void printInt(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("");
    }

    /**
     * negativo
     * @param num Array de booleano que representa um número binário
     * @return O complemento de 2 da entrada
     */
    static boolean[] negativo(boolean[] num){
        boolean[] neg = new boolean[num.length];
        for (int i = 0; i < neg.length; i++) {
            neg[i] = !num[i];
        }
        for (int i = neg.length-1; i >= 0 ; i--) {
            if(neg[i]) neg[i] = false;
            else{
                neg[i] = true;
                break;
            }
        }
        return neg;
    }


    /**
     * isBigger
     * @param a
     * @param b
     * @return True se a>b, false se a<=b
     */
    static boolean isBigger(boolean[] a, boolean[] b){
        boolean arr[];
        if(a.length > b.length) return true;
        else return false;
    }


    /**
     * normaliza
     * @param a
     * @param b
     * @return b do mesmo tamanho de a
     */
    static boolean[] normaliza(boolean[] a, boolean[] b){
        //Aumenta o tamanho de b para o tamanho de a

        boolean[] arr = new boolean[a.length];
        int index = arr.length-1;

        for (int i = b.length-1; i >= 0 ; i--) {
            arr[index] = b[i];
            index--;
        }
        return arr;
    }


    static boolean[] shiftR(boolean[] num){
        boolean[] res = new boolean[num.length];
        for(int i = res.length-1; i > 0; i--){
            res[i] = num[i-1];
        }
        res[0] = num[0];
        return res;
    }

    static boolean[] concat(boolean[] a, boolean[] b){
        boolean[] res = new boolean[a.length + b.length];
        for(int i = 0; i < a.length; i++){
            res[i] = a[i];
            res[i+a.length] = b[i];
        }

        return res;
    }

}
