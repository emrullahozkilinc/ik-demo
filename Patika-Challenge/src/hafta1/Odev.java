package hafta1;

import java.util.Scanner;

public class Odev {
    public static void main(String[] args){
        int fiyat;
        double kdvDahilFiyat;
        Scanner fiyatScanner=new Scanner(System.in);
        System.out.println("fiyati giriniz: ");
        fiyat=fiyatScanner.nextInt();
        if(fiyat>0&fiyat<1000) {
            kdvDahilFiyat=fiyat*1.18;
            System.out.println("Ürünün kdv dahil fiyati: " + kdvDahilFiyat);
        }else {
            kdvDahilFiyat=fiyat*1.08;
            System.out.println("Ürünün kdv dahil fiyati: " + kdvDahilFiyat);
        }
    }
}
