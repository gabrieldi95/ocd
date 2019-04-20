package com;

public class Binario {

    int[] valor;
    boolean[] bool;

    Binario(int valor){
        this.valor = dec2bin(valor);
        this.bool = bin2bool(this.valor);
    }



    static int[] dec2bin(int dec){

        int bitSize = 1;
        double maxSize = 1;

        // Descobre a quantidade de bits
        while(maxSize < dec){
            maxSize = maxSize + Math.pow(2, bitSize);
            bitSize++;
        }

        int[] a = new int[bitSize];

        int index = a.length-1;
        while(dec > 0 && index >= 0){
            a[index] = dec % 2;
            dec = dec / 2;
            index--;
        }

        return a;
    }


    static boolean[] bin2bool(int[] num){
        boolean[] bool = new boolean[num.length];
        for (int i = 0; i < num.length; i++) {
            if(num[i] == 1) bool[i] = true;
            else bool[i] = false;
        }
        return bool;
    }




    int[] soma(Binario num) {
        boolean ci = false;

        int[] res;

        boolean[] a;
        boolean[] b;

        if(isBigger(this.bool, num.bool)){
            b = normaliza(this.bool, num.bool);
            a = this.bool;
        }else {
            a = normaliza(num.bool, this.bool);
            b = num.bool;
        }

        res = new int[a.length];

        for (int i = res.length-1; i >= 0 ; i--) {
            if( (a[i] ^ b[i]) ^ ci){
                res[i] = 1;
            }else res[i] = 0;
            if( (!a[i] && (ci && b[i])) || (a[i] && (ci || b[i])) ){
                ci = true;
            }else ci = false;
        }

        // Caso haja overflow
        int[] sobra = new int[res.length+1];
        if(ci){
            sobra[0] = 1;
            for (int i = 0; i < res.length; i++) {
                sobra[i+1] = res[i];
            }
            return sobra;
        }

        return res;
    }

    boolean[] negativo(){
        boolean[] neg = new boolean[this.valor.length];
        for (int i = 0; i < neg.length; i++) {
            neg[i] = !this.bool[i];
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



    boolean isBigger(boolean[] a, boolean[] b){
        boolean arr[];
        if(a.length > b.length) return true;
        else return false;
    }



    boolean[] normaliza(boolean[] a, boolean[] b){
        //Aumenta o tamanho de b para o tamanho de a

        boolean[] arr = new boolean[a.length];
        int index = arr.length-1;

        for (int i = b.length-1; i >= 0 ; i--) {
            arr[index] = b[i];
            index--;
        }
        return arr;
    }

    static void print(boolean[] arr){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]) System.out.print(1);
            else System.out.print(0);
        }
        System.out.println("");
    }
    static void printInt(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("");
    }

}
