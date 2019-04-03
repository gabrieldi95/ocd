package com;

public class Main {

    public static void main(String[] args) {




        Binario b = new Binario(10);
        Binario c = new Binario(30);
        print(b.valor);
        print(c.valor);
        int[] soma = c.soma(b);
        print(soma);



    }

    public static void print(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("");
    }

}
