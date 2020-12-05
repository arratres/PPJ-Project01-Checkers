import java.util.Scanner;

public class main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args){

//        System.out.printf("Gracz1 (białe) - podaj imię: ");
//        String gracz1 = in.next();
//        System.out.printf("Gracz2 (czarne) - podaj imię: ");
//        String gracz2 = in.next();

        long czarne_piony_1 = 0;
        long czarne_piony_2 = 0;
        long biale_piony_1 = 0;
        long biale_piony_2 = 0;

        //rozdanie czarnych
        //współrzędna y
        for(int i=0; i<=2; i++){
            //współrzędna x
            for(int j=0; j<=7; j++){
                //czarne pole, wstawiamy pionek
                if((i+j)%2==0){
                    //czy pierwszy long ma jeszcze miejsce?
                    if(czarne_piony_1 >> 46 == 0){
                        //dodanie informacji o czarnym pionie w grze
                        czarne_piony_1 = czarne_piony_1 << 3;
                        czarne_piony_1 += 0b100;
                        //dodanie informacji o wsp y
                        czarne_piony_1 = czarne_piony_1 << 3;
                        czarne_piony_1 += i;
                        //dodanie informacji o wsp x
                        czarne_piony_1 = czarne_piony_1 << 3;
                        czarne_piony_1 += j;
                    } else {
                        //dodanie informacji o czarnym pionie w grze
                        czarne_piony_2 = czarne_piony_2 << 3;
                        czarne_piony_2 += 0b100;
                        //dodanie informacji o wsp y
                        czarne_piony_2 = czarne_piony_2 << 3;
                        czarne_piony_2 += i;
                        //dodanie informacji o wsp x
                        czarne_piony_2 = czarne_piony_2 << 3;
                        czarne_piony_2 += j;
                    }
                }
            }
        }

        //rozdanie białych
        //współrzędna y
        for(int i=5; i<=7; i++){
            //współrzędna x
            for(int j=0; j<=7; j++){
                //czarne pole, wstawiamy pionek
                if((i+j)%2==0){
                    //czy pierwszy long ma jeszcze miejsce?
                    if(biale_piony_1 >> 46 == 0){
                        //dodanie informacji o czarnym pionie w grze
                        biale_piony_1 = biale_piony_1 << 3;
                        biale_piony_1 += 0b101;
                        //dodanie informacji o wsp y
                        biale_piony_1 = biale_piony_1 << 3;
                        biale_piony_1 += i;
                        //dodanie informacji o wsp x
                        biale_piony_1 = biale_piony_1 << 3;
                        biale_piony_1 += j;
                    } else {
                        //dodanie informacji o czarnym pionie w grze
                        biale_piony_2 = biale_piony_2 << 3;
                        biale_piony_2 += 0b101;
                        //dodanie informacji o wsp y
                        biale_piony_2 = biale_piony_2 << 3;
                        biale_piony_2 += i;
                        //dodanie informacji o wsp x
                        biale_piony_2 = biale_piony_2 << 3;
                        biale_piony_2 += j;
                    }
                }
            }
        }



        int kolej_gracza = 1;

        while(true){

            //sprawdzić czy gracz ma ruch do wykonania i czy ma obowiązkowe bicie
            //czy ma ruch - czy ma pion, który może się jakkolwiek ruszyć lub bić
            //jak nie ma ruchu, koniec gry, wygrywa drugi gracz

            //wydrukować planszę, poinformować o kolejce
            wydrukuj_plansze(czarne_piony_1, czarne_piony_2, biale_piony_1, biale_piony_2);
            System.out.println("Kolej gracza "+kolej_gracza);


            //pobrać ruch zaproponowany przez gracza
            System.out.println("Który pion chcesz podnieść?");
            int wybrany_pion = wybierz_pole();
            System.out.println("Gdzie chcesz go przesunąć?");
            int pole_docelowe = wybierz_pole();

            switch(kolej_gracza){
                case 1:
                    break;
                case 2:
                    break;
            }

            //sprawdzić czy ruch jest dozwolony (uwzględnić obowiązkowe bicie)

            //wykonać ruch lub powtórzyć pętlę dla gracza

        }
    }

    public static void wydrukuj_plansze(long czarne_piony_1, long czarne_piony_2, long biale_piony_1, long biale_piony_2){
        //wsp y
        System.out.println("  1 2 3 4 5 6 7 8");
        for(int i=7; i>=0; i--){
            //wsp x
            System.out.print((i+1)+" ");
            for(int j=0; j<=7; j++){
                int obecne_pole = i;
                obecne_pole = (obecne_pole << 3) + j;
                boolean wydrukowane = false;

                //spr czarne_piony_1
                wydrukowane = wydrukuj_pion(czarne_piony_1, obecne_pole);

                //spr czarne_piony_2
                if(wydrukowane == false){
                    wydrukowane = wydrukuj_pion(czarne_piony_2, obecne_pole);
                } else continue; //pole wydrukowane, idziemy dalej

                // spr biale_piony_1
                if(wydrukowane == false){
                    wydrukowane = wydrukuj_pion(biale_piony_1, obecne_pole);
                } else continue; //pole wydrukowane, idziemy dalej

                // spr biale_piony_2
                if(wydrukowane == false){
                    if(wydrukowane == false){
                        wydrukowane = wydrukuj_pion(biale_piony_2, obecne_pole);
                    } else continue; //pole wydrukowane, idziemy dalej
                } else continue; //pole wydrukowane, idziemy dalej

                //żadna figura nie była wydrukowana - puste pole
                if(wydrukowane == false){
                    switch((i+j)%2){
                        case 0:
                            System.out.print("\u2B1C "); //czarne pole
                            break;
                        case 1:
                            System.out.print("\u2B1B "); //białe pole
                            break;
                    }
                }
            }
            //koniec linii, drukujemy linebreak
            System.out.println((i+1));
        }
        System.out.println("  1 2 3 4 5 6 7 8");
    }

    public static boolean wydrukuj_pion(long piony, int pole){
        boolean wydrukowane = false;
        for(int k=0; k<6; k++){

            //ostatnie 6 bitów - pozycja piona == obecne pole
            if(((piony >> (9*k) & 0b111111)) == pole){

                //sprawdzenie figury
                switch( (int)((piony >> (9*k)) >>6) & 0b111) {
                    case 0b110:
                        System.out.print("\u265B "); //czarna damka
                        wydrukowane = true;
                        break;
                    case 0b100:
                        System.out.print("\u265F "); //czarny pion
                        wydrukowane = true;
                        break;
                    case 0b111:
                        System.out.print("\u2655 "); //biała damka
                        wydrukowane = true;
                        break;
                    case 0b101:
                        System.out.print("\u2659 "); //biały pion
                        wydrukowane = true;
                        break;
                    //zostaje zbity pion. jego nie drukujemy, szukamy dalej piona o tych współrzędnych, który jest w grze.
                }
            }
        }
        return wydrukowane;
    }

    public static int wybierz_pole(){
        int wybrane_pole = 0;

        //pobranie wsp. y
        while(true){
            try{
                System.out.print("Podaj współrzędną Y (1-8): ");
                int podana_wartosc = in.nextInt();

                if(podana_wartosc > 0 & podana_wartosc < 9){
                    wybrane_pole = podana_wartosc-1;
                    wybrane_pole = wybrane_pole << 3;
                    break;
                } else {
                    System.out.println("Podana wartość nie mieści się w przedziale 1-8. Spróbuj ponownie.");
                }
            } catch(Exception e){
                System.out.println("Podana wartości nie jest liczbą całkowitą. Spróbuj ponownie.");
                in.nextLine();
                continue;
            }
        }

        //pobranie wsp. x
        while(true){
            try{
                System.out.print("Podaj współrzędną X (1-8): ");
                int podana_wartosc = in.nextInt();

                if(podana_wartosc > 0 & podana_wartosc < 9){
                    wybrane_pole += podana_wartosc-1;
                    break;
                } else {
                    System.out.println("Podana wartość nie mieści się w przedziale 1-8. Spróbuj ponownie.");
                }
            } catch(Exception e){
                System.out.println("Podana wartości nie jest liczbą całkowitą. Spróbuj ponownie.");
                in.nextLine();
                continue;
            }
        }
        return wybrane_pole;
    }

}
