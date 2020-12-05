import java.util.Scanner;

public class main {

    public static void main(String[] args){

//        Scanner in = new Scanner(System.in);
//        System.out.printf("Gracz1 (czarne) - podaj imię: ");
//        String gracz1 = in.next();
//        System.out.printf("Gracz2 (białe) - podaj imię: ");
//        String gracz2 = in.next();

        long czarne_piony_1 = 0;
        long czarne_piony_2 = 0;
        long biale_piony_1 = 0;
        long biale_piony_2 = 0;

        //rozdanie czarnych
        //współrzędna y
        for(int i=5; i<=7; i++){
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
        for(int i=0; i<=2; i++){
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

        //wsp x
        for(int i=7; i>=0; i--){
            //wsp y
            for(int j=0; j<=7; j++){
                int obecne_pole = i;
                obecne_pole = (obecne_pole << 3) + j;
                boolean wydrukowane = false;

                //spr czarne_piony_1
                for(int k=0; k<6; k++){
                    //ostatnie 6 bitów - pozycja piona == obecne pole
                    if(((czarne_piony_1 >> (9*k) & 0b111111)) == obecne_pole){
                        //sprawdzenie figury (kolor czarny)
                        switch( (int)((czarne_piony_1 >> (9*k)) >>6) & 0b111){
                            case 0b110:
                                System.out.print("\u265B"); //czarna damka
                                wydrukowane = true;
                                break;
                            case 0b100:
                                System.out.print("\u265F"); //czarny pion
                                wydrukowane = true;
                                break;
                            //zostaje zbity pion. jego nie drukujemy, szukamy dalej piona o tych współrzędnych, który jest w grze.
                        }

                    }
                }

                //spr czarne_piony_2
                if(wydrukowane == false){
                    for(int k=0; k<6; k++){
                        //ostatnie 6 bitów - pozycja piona == obecne pole
                        if(((czarne_piony_2 >> (9*k) & 0b111111)) == obecne_pole){
                            //sprawdzenie figury (kolor czarny)
                            switch( (int)((czarne_piony_2 >> (9*k)) >>6) & 0b111) {
                                case 0b110:
                                    System.out.print("\u265B"); //czarna damka
                                    wydrukowane = true;
                                    break;
                                case 0b100:
                                    System.out.print("\u265F"); //czarny pion
                                    wydrukowane = true;
                                    break;
                                //zostaje zbity pion. jego nie drukujemy, szukamy dalej piona o tych współrzędnych, który jest w grze.
                            }
                        }
                    }
                } else continue; //pole wydrukowane, idziemy dalej

                // spr biale_piony_1
                if(wydrukowane == false){
                    for(int k=0; k<6; k++){
                        //ostatnie 6 bitów - pozycja piona == obecne pole
                        if(((biale_piony_1 >> (9*k) & 0b111111)) == obecne_pole){
                            //sprawdzenie figury (kolor biały)
                            switch( (int)((biale_piony_1 >> (9*k)) >>6) & 0b111) {
                                case 0b111:
                                    System.out.print("\u2655"); //biała damka
                                    wydrukowane = true;
                                    break;
                                case 0b101:
                                    System.out.print("\u2659"); //biały pion
                                    wydrukowane = true;
                                    break;
                                //zostaje zbity pion. jego nie drukujemy, szukamy dalej piona o tych współrzędnych, który jest w grze.
                            }
                        }
                    }
                } else continue; //pole wydrukowane, idziemy dalej

                // spr biale_piony_2
                if(wydrukowane == false){
                    for(int k=0; k<6; k++){
                        //ostatnie 6 bitów - pozycja piona == obecne pole
                        if(((biale_piony_2 >> (9*k) & 0b111111)) == obecne_pole){
                            //sprawdzenie figury (kolor biały)
                            switch( (int)((biale_piony_2 >> (9*k)) >>6) & 0b111) {
                                case 0b111:
                                    System.out.print("\u2655"); //biała damka
                                    wydrukowane = true;
                                    break;
                                case 0b101:
                                    System.out.print("\u2659"); //biały pion
                                    wydrukowane = true;
                                    break;
                                //zostaje zbity pion. jego nie drukujemy, szukamy dalej piona o tych współrzędnych, który jest w grze.
                            }
                        }
                    }
                } else continue; //pole wydrukowane, idziemy dalej

                //żadna figura nie była wydrukowana - puste pole
                if(wydrukowane == false){
                    switch((i+j)%2){
                        case 0:
                            System.out.print("\u2B1C"); //czarne pole
                            break;
                        case 1:
                            System.out.print("\u2B1B"); //białe pole
                            break;
                    }
                }
            }
            //koniec linii, drukujemy linebreak
            System.out.println();
        }


//        long test = 0b1100001001;
//        test = test & 0b1111111000;
//        test = test | 0b0000000010;

//
//
//        for(int i=0; i<8; i++){
//            for(int j=0; j<8; j++){
//
//            }
//        }

//        System.out.println("\u2b1b");
//        System.out.println("\u2b1c");
//        System.out.println("\u2659");
//        System.out.println("\u2659");
//        System.out.println("\u265F");
//        System.out.println("\u2655");
//        System.out.println("\u265B");
    }
}
