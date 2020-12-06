import java.util.Scanner;
import java.lang.Math;

public
    class S23605P01 {

    //definiowanie zmiennych w obrębie klasy, dla łatwego dostępu dla metod
    static Scanner in = new Scanner(System.in);
    static long czarne_piony_1 = 0;
    static long czarne_piony_2 = 0;
    static long biale_piony_1 = 0;
    static long biale_piony_2 = 0;

    public static void main(String[] args){



        System.out.print("Gracz pierwszy (białe) - podaj imię: ");
        String gracz1 = in.next();
        System.out.print("Gracz drugi (czarne) - podaj imię: ");
        String gracz2 = in.next();

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

        //rozpoczyna gracz 1, nie zmieniamy gracza, przeciwnik jest czarny
        int kolej_gracza = 1;
        boolean zmiana_gracza = true;
        int kolor_przeciwnika = 0;

        //pętla gry
        while(true) {

            //sprawdzić czy gracz ma ruch do wykonania i czy ma obowiązkowe bicie
            //jak nie ma ruchu, koniec gry, wygrywa drugi gracz

            //sprawdzamy czy jest mus bicia
            boolean mus_bicia = false;
            boolean mozliwy_ruch = false;

            //zmiana gracza (jeśli nie ma powtórzenia bicia)
            if(!zmiana_gracza){
                //określenie koloru przeciwnika (do bić)
                switch(kolej_gracza) {
                    case 1:
                        kolej_gracza = 2;
                        kolor_przeciwnika = 1;
                        break;
                    case 2:
                        kolej_gracza = 1;
                        kolor_przeciwnika = 0;
                        break;
                }
            }

            //loop dla każdego piona. TODO weryfikacja damek
            for(int i=0; i<7; i++){
                //jeśli jest mus bicia, możemy przerwać pętlę - na pewno jest ruch
                if(mus_bicia) break;
                int pion_1, pion_2;

                //sprawdzamy oba piony jednoczesnie (z obu longów)
                if(kolej_gracza == 1){
                    pion_1 = (int)(biale_piony_1 >> (9*i) & 0b111111111);
                    pion_2 = (int)(biale_piony_2 >> (9*i) & 0b111111111);
                } else {
                    pion_1 = (int)(czarne_piony_1 >> (9*i) & 0b111111111);
                    pion_2 = (int)(czarne_piony_2 >> (9*i) & 0b111111111);
                }

                //weryfikacja dla pion_1
                //sprawdzamy stan piona
                if((pion_1 >> 8 & 0b1) != 0){
                    //wyciągamy pole
                    int pole_piona_1 = pion_1 & 0b111111;
                    int wspx_piona = pole_piona_1 & 0b111;
                    int wspy_piona = (pole_piona_1 >> 3) & 0b111;

                    //sprawdzamy czy pion
                    if((pion_1 >> 7 & 0b1) == 0){
                        //sprawdzamy czy bicie dla piona
                        int sprawdzane_pole;
                        int wartosc_sprawdzanego_pola;

                        //sprawdzamy lewy skos w gore
                        //sprawdzamy, czy nie wychodzi poza plansze
                        if(!((wspy_piona+1 > 7) | (wspx_piona-1<0))){
                            sprawdzane_pole = ((wspy_piona+1) << 3) + wspx_piona-1;
                            wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);

                            if(wartosc_sprawdzanego_pola == -1){
                                //jesli pole puste, mamy mozliwy ruch
                                mozliwy_ruch = true;
                            } else {
                                //sprawdzamy czy kolor piona zajmujacego miejsce jest kolorem przeciwnika
                                if((wartosc_sprawdzanego_pola >> 5 & 0b1) == kolor_przeciwnika){
                                    //sprawdzamy czy kolejne pole za nim jest puste.
                                    if(!((wspy_piona+2 > 7) | (wspx_piona-2<0))) {
                                        sprawdzane_pole = ((wspy_piona + 2) << 3) + wspx_piona - 2;
                                        wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);
                                        if(wartosc_sprawdzanego_pola == -1){
                                            //mamy mus bicia
                                            mus_bicia = true;
                                        }
                                    }
                                }
                            }
                        }

                        //sprawdzamy lewy skos w dol
                        //sprawdzamy, czy nie wychodzi poza plansze
                        if(!((wspy_piona-1 < 0) | (wspx_piona-1<0))){
                            sprawdzane_pole = ((wspy_piona-1) << 3) + wspx_piona-1;
                            wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);

                            if(wartosc_sprawdzanego_pola == -1){
                                //jesli pole puste, mamy mozliwy ruch
                                mozliwy_ruch = true;
                            } else {
                                //sprawdzamy czy kolor piona zajmujacego miejsce jest kolorem przeciwnika
                                if((wartosc_sprawdzanego_pola >> 5 & 0b1) == kolor_przeciwnika){
                                    //sprawdzamy czy kolejne pole za nim jest puste.
                                    if(!((wspy_piona-2 < 0) | (wspx_piona-2<0))) {
                                        sprawdzane_pole = ((wspy_piona - 2) << 3) + wspx_piona - 2;
                                        wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);
                                        if(wartosc_sprawdzanego_pola == -1){
                                            //mamy mus bicia
                                            mus_bicia = true;
                                        }
                                    }
                                }
                            }
                        }

                        //sprawdzamy prawy skos w gore
                        //sprawdzamy, czy nie wychodzi poza plansze
                        if(!((wspy_piona+1 > 7) | (wspx_piona+1 > 7))){
                            sprawdzane_pole = ((wspy_piona+1) << 3) + wspx_piona+1;
                            wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);

                            if(wartosc_sprawdzanego_pola == -1){
                                //jesli pole puste, mamy mozliwy ruch
                                mozliwy_ruch = true;
                            } else {
                                //sprawdzamy czy kolor piona zajmujacego miejsce jest kolorem przeciwnika
                                if((wartosc_sprawdzanego_pola >> 5 & 0b1) == kolor_przeciwnika){
                                    //sprawdzamy czy kolejne pole za nim jest puste.
                                    if(!((wspy_piona+2 > 7) | (wspx_piona+2 > 7))) {
                                        sprawdzane_pole = ((wspy_piona + 2) << 3) + wspx_piona + 2;
                                        wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);
                                        if(wartosc_sprawdzanego_pola == -1){
                                            //mamy mus bicia
                                            mus_bicia = true;
                                        }
                                    }
                                }
                            }
                        }

                        //sprawdzamy prawy skos w dol
                        //sprawdzamy, czy nie wychodzi poza plansze

                        if(!((wspy_piona-1 < 0) | (wspx_piona+1 > 7))){
                            sprawdzane_pole = ((wspy_piona-1) << 3) + wspx_piona+1;
                            wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);

                            if(wartosc_sprawdzanego_pola == -1){
                                //jesli pole puste, mamy mozliwy ruch
                                mozliwy_ruch = true;
                            } else {
                                //sprawdzamy czy kolor piona zajmujacego miejsce jest kolorem przeciwnika
                                if((wartosc_sprawdzanego_pola >> 5 & 0b1) == kolor_przeciwnika){
                                    //sprawdzamy czy kolejne pole za nim jest puste.
                                    if(!((wspy_piona+2 > 7) | (wspx_piona+2 > 7))) {
                                        sprawdzane_pole = ((wspy_piona - 2) << 3) + wspx_piona + 2;
                                        wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);
                                        if(wartosc_sprawdzanego_pola == -1){
                                            //mamy mus bicia
                                            mus_bicia = true;
                                        }
                                    }
                                }
                            }
                        }


                    } else {
                        //sprawdzamy czy bicie dla damki
                        //TODO
                    }

                }

                //to samo dla pion_2, sprawdzamy stan
                if((pion_2 >> 8 & 0b1) != 0){
                    //wyciągamy pole
                    int pole_piona_2 = pion_2 & 0b111111;
                    int wspx_piona = pole_piona_2 & 0b111;
                    int wspy_piona = (pole_piona_2 >> 3) & 0b111;

                    //sprawdzamy czy pion
                    if((pion_2 >> 7 & 0b1) == 0){
                        //sprawdzamy czy bicie dla piona
                        int sprawdzane_pole;
                        int wartosc_sprawdzanego_pola;

                        //sprawdzamy lewy skos w gore
                        //sprawdzamy, czy nie wychodzi poza plansze
                        if(!((wspy_piona+1 > 7) | (wspx_piona-1<0))){
                            sprawdzane_pole = ((wspy_piona+1) << 3) + wspx_piona-1;
                            wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);

                            if(wartosc_sprawdzanego_pola == -1){
                                //jesli pole puste, mamy mozliwy ruch
                                mozliwy_ruch = true;
                            } else {
                                //sprawdzamy czy kolor piona zajmujacego miejsce jest kolorem przeciwnika
                                if((wartosc_sprawdzanego_pola >> 5 & 0b1) == kolor_przeciwnika){
                                    //sprawdzamy czy kolejne pole za nim jest puste.
                                    if(!((wspy_piona+2 > 7) | (wspx_piona-2<0))) {
                                        sprawdzane_pole = ((wspy_piona + 2) << 3) + wspx_piona - 2;
                                        wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);
                                        if(wartosc_sprawdzanego_pola == -1){
                                            //mamy mus bicia
                                            mus_bicia = true;
                                        }
                                    }
                                }
                            }
                        }

                        //sprawdzamy lewy skos w dol
                        //sprawdzamy, czy nie wychodzi poza plansze
                        if(!((wspy_piona-1 < 0) | (wspx_piona-1<0))){
                            sprawdzane_pole = ((wspy_piona-1) << 3) + wspx_piona-1;
                            wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);

                            if(wartosc_sprawdzanego_pola == -1){
                                //jesli pole puste, mamy mozliwy ruch
                                mozliwy_ruch = true;
                            } else {
                                //sprawdzamy czy kolor piona zajmujacego miejsce jest kolorem przeciwnika
                                if((wartosc_sprawdzanego_pola >> 5 & 0b1) == kolor_przeciwnika){
                                    //sprawdzamy czy kolejne pole za nim jest puste.
                                    if(!((wspy_piona-2 < 0) | (wspx_piona-2<0))) {
                                        sprawdzane_pole = ((wspy_piona - 2) << 3) + wspx_piona - 2;
                                        wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);
                                        if(wartosc_sprawdzanego_pola == -1){
                                            //mamy mus bicia
                                            mus_bicia = true;
                                        }
                                    }
                                }
                            }
                        }

                        //sprawdzamy prawy skos w gore
                        //sprawdzamy, czy nie wychodzi poza plansze
                        if(!((wspy_piona+1 > 7) | (wspx_piona+1 > 7))){
                            sprawdzane_pole = ((wspy_piona+1) << 3) + wspx_piona+1;
                            wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);

                            if(wartosc_sprawdzanego_pola == -1){
                                //jesli pole puste, mamy mozliwy ruch
                                mozliwy_ruch = true;
                            } else {
                                //sprawdzamy czy kolor piona zajmujacego miejsce jest kolorem przeciwnika
                                if((wartosc_sprawdzanego_pola >> 5 & 0b1) == kolor_przeciwnika){
                                    //sprawdzamy czy kolejne pole za nim jest puste.
                                    if(!((wspy_piona+2 > 7) | (wspx_piona+2 > 7))) {
                                        sprawdzane_pole = ((wspy_piona + 2) << 3) + wspx_piona + 2;
                                        wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);
                                        if(wartosc_sprawdzanego_pola == -1){
                                            //mamy mus bicia
                                            mus_bicia = true;
                                        }
                                    }
                                }
                            }
                        }

                        //sprawdzamy prawy skos w dol
                        //sprawdzamy, czy nie wychodzi poza plansze
                        if(!((wspy_piona-1 < 0) | (wspx_piona+1 > 7))){
                            sprawdzane_pole = ((wspy_piona-1) << 3) + wspx_piona+1;
                            wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);

                            if(wartosc_sprawdzanego_pola == -1){
                                //jesli pole puste, mamy mozliwy ruch
                                mozliwy_ruch = true;
                            } else {
                                //sprawdzamy czy kolor piona zajmujacego miejsce jest kolorem przeciwnika
                                if((wartosc_sprawdzanego_pola >> 5 & 0b1) == kolor_przeciwnika){
                                    //sprawdzamy czy kolejne pole za nim jest puste.
                                    if(!((wspy_piona+2 > 7) | (wspx_piona+2 > 7))) {
                                        sprawdzane_pole = ((wspy_piona - 2) << 3) + wspx_piona + 2;
                                        wartosc_sprawdzanego_pola = pobierz_zawartosc_pola(sprawdzane_pole);
                                        if(wartosc_sprawdzanego_pola == -1){
                                            //mamy mus bicia
                                            mus_bicia = true;
                                        }
                                    }
                                }
                            }
                        }


                    } else {
                        //sprawdzamy czy bicie dla damki
                        //TODO
                    }

                }

            }

            //wydrukować planszę, poinformować o kolejce
            wydrukuj_plansze();
            switch (kolej_gracza) {
                case 1:
                    System.out.println("Kolej gracza pierwszego (białe). Powodzenia " + gracz1 + "!");
                    break;
                case 2:
                    System.out.println("Kolej gracza drugiego (czarne). Powodzenia " + gracz2 + "!");
                    break;
            }
            //informacja o możliwości ruchu/musie bicia
            if(mus_bicia){
                System.out.println("Masz bicie, które musisz wykonać");
                //dodatkowo konieczne będzie powtórzenie ruchu (już po wykonaniu bicia)
                zmiana_gracza = true;
            } else if(mozliwy_ruch){
                System.out.println("Masz możliwy ruch do wykoniania");
            }

            //pobrać ruch zaproponowany przez gracza
            System.out.println("Który pion chcesz podnieść?");
            int pole_startowe = pobierz_wspolrzedne();
            System.out.println("Gdzie chcesz go przesunąć?");
            int pole_docelowe = pobierz_wspolrzedne();

            //sprawdzić czy ruch jest dozwolony (uwzględnić obowiązkowe bicie)

            //pobieramy zawartość wybranych pól
            int wybrany_pion = pobierz_zawartosc_pola(pole_startowe);
            int zawartosc_pola_docelowego = pobierz_zawartosc_pola(pole_docelowe);


            //sprawdzamy czy to nie puste pole
            if(wybrany_pion != -1){
                //sprawdzamy czy to nie pion przeciwnika
                if((wybrany_pion >> 6 & 0b1) != kolor_przeciwnika){
                    //warunek dla piona
                    if((wybrany_pion >> 7 & 0b1) == 0){
                        //pole docelowe jest puste, a gracz nie ma musu bicia
                        if(zawartosc_pola_docelowego == -1 & !mus_bicia){
                            //sprawdzamy czy dlugosc ruchu wynosi 1x1 i czy jest we wlasciwym kierunku

                            //kolej białych, ruszamy się w dół
                            if(kolej_gracza == 1){
                                //spradzenie przemieszczenia względem osi y
                                switch((pole_docelowe >> 3 & 0b111) - (pole_startowe >> 3 & 0b111)){
                                    case -1:
                                        //prawidłowy ruch, sprawdzanie względem osi x
                                        switch((pole_docelowe & 0b111) - (pole_startowe & 0b111)){
                                            case -1, 1:
                                                //wykonanie ruchu na puste pole
                                                for(int i=0; i<7; i++){
                                                    //szukamy piona w longach
                                                    if((((biale_piony_1 >> (9*i)) & 0b111111) == pole_startowe)) {
                                                        //'wciskamy' wartosc nowego pola
                                                        biale_piony_1 = biale_piony_1 - ((long)pole_startowe << (i*9));
                                                        biale_piony_1 = biale_piony_1 + ((long)pole_docelowe << (i*9));
                                                        // po ruchu na puste pole gracz kończy ruch.
                                                        // TODO dodać mechanike zmiany w damke
                                                        zmiana_gracza = false;
                                                    }
                                                    if((((biale_piony_2 >> (9*i)) & 0b111111) == pole_startowe)){
                                                        biale_piony_2 = biale_piony_2 - ((long)pole_startowe << (i*9));
                                                        biale_piony_2 = biale_piony_2 + ((long)pole_docelowe << (i*9));
                                                        // po ruchu na puste pole gracz kończy ruch.
                                                        // TODO dodać mechanike zmiany w damke
                                                        zmiana_gracza = false;
                                                    }
                                                }
                                                break;
                                            default:
                                                System.out.println("Zbyt długi ruch po współrzędnej X jak na piona");
                                                zmiana_gracza = true;
                                                break;
                                        }
                                        break;
                                    case 1:
                                        System.out.println("Próbujesz ruszyć się w złą stronę");
                                        zmiana_gracza = true;
                                        break;
                                    default:
                                        System.out.println("Zbyt długi ruch po współrzędnej Y jak na piona");
                                        zmiana_gracza = true;
                                        break;
                                }
                            }

                            //kolej czarnych, ruszamy się w górę
                            if(kolej_gracza == 2){
                                //spradzenie przemieszczenia względem osi y
                                switch((pole_docelowe >> 3 & 0b111) - (pole_startowe >> 3 & 0b111)){
                                    case 1:
                                        //prawidłowy ruch, sprawdzanie względem osi x
                                        switch((pole_docelowe & 0b111) - (pole_startowe & 0b111)){
                                            case -1, 1:
                                                //wykonanie ruchu na puste pole
                                                for(int i=0; i<7; i++){
                                                    //szukamy piona w longach
                                                    if((((czarne_piony_1 >> (9*i)) & 0b111111) == pole_startowe)) {
                                                        //'wciskamy' wartosc nowego pola
                                                        czarne_piony_1 = czarne_piony_1 - ((long)pole_startowe << (i*9));
                                                        czarne_piony_1 = czarne_piony_1 + ((long)pole_docelowe << (i*9));
                                                        // po ruchu na puste pole gracz kończy ruch.
                                                        // TODO dodać mechanike zmiany w damke
                                                    }
                                                    if((((czarne_piony_2 >> (9*i)) & 0b111111) == pole_startowe)){
                                                        czarne_piony_2 = czarne_piony_2 - ((long)pole_startowe << (i*9));
                                                        czarne_piony_2 = czarne_piony_2 + ((long)pole_docelowe << (i*9));
                                                        // po ruchu na puste pole gracz kończy ruch.
                                                        // TODO dodać mechanike zmiany w damke
                                                    }
                                                }
                                                break;
                                            default:
                                                System.out.println("Zbyt długi ruch po współrzędnej X jak na piona");
                                                zmiana_gracza = true;
                                                break;
                                        }
                                        break;
                                    case -1:
                                        System.out.println("Próbujesz ruszyć się w złą stronę");
                                        zmiana_gracza = true;
                                        break;
                                    default:
                                        System.out.println("Zbyt długi ruch po współrzędnej Y jak na piona");
                                        zmiana_gracza = true;
                                        break;
                                }
                            }
                        } else if (mus_bicia){
                            System.out.println("Wybrany ruch nie jest biciem.");
                            zmiana_gracza = true;
                        } else {
                            //TODO dla bicia piona
                        }
                    } else {
                        //TODO dla damki
                    }
                } else {
                    System.out.println("Wybrany przez Ciebie pion to pion przeciwnika!");
                    zmiana_gracza = true;
                }
            } else {
                System.out.println("Wybrane przez Ciebie pole nie zawiera piona. Spróbuj ponownie.");
                zmiana_gracza = true;
            }


            //wykonać ruch lub powtórzyć pętlę dla gracza


            //TODO remisy
        }
    }


    public static void wydrukuj_plansze(){
        //wsp y
        System.out.println("  1 2 3 4 5 6 7 8");
        for(int i=7; i>=0; i--){
            //wsp x
            System.out.print((i+1)+" ");
            for(int j=0; j<=7; j++){
                int obecne_pole = i;
                obecne_pole = (obecne_pole << 3) + j;
                int zawartosc_pola = pobierz_zawartosc_pola(obecne_pole);
                wydrukuj_pole(zawartosc_pola, obecne_pole);
            }
            System.out.println((i+1));
        }
        System.out.println("  1 2 3 4 5 6 7 8");
    }

    public static void wydrukuj_pole(int zawartosc_pola, int pole){

        //puste pole
        if(zawartosc_pola == -1){
            //jeśli suma wsp x i y jest parzysta, pole jest czarne
            switch( ((pole&0b111) + ((pole >> 3)&0b111)) %2){
                case 0:
                    System.out.print("\u2B1C "); //czarne pole
                    break;
                case 1:
                    System.out.print("\u2B1B "); //białe pole
                    break;
            }
        } else {
            //sprawdzenie figury
            switch(zawartosc_pola>>6 & 0b111) {
                case 0b110:
                    System.out.print("\u265B "); //czarna damka
                    break;
                case 0b100:
                    System.out.print("\u265F "); //czarny pion
                    break;
                case 0b111:
                    System.out.print("\u2655 "); //biała damka
                    break;
                case 0b101:
                    System.out.print("\u2659 "); //biały pion
                    break;
                //zostaje zbity pion. jego nie drukujemy, szukamy dalej piona o tych współrzędnych, który jest w grze.
            }
        }
    }

    public static int pobierz_wspolrzedne(){
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

    public static int pobierz_zawartosc_pola(int pole){

        for(int i=0; i<7; i++){
            //sprawdzamy na kazdej zmiennej czy pole sie zgadza i czy pion jest w grze
            if((((czarne_piony_1 >> (9*i)) & 0b111111) == pole) & (((czarne_piony_1 >> (9*i)) & 0b100000000) > 0))
                return (int)((czarne_piony_1 >> (9*i)) & 0b111111111);
            if((((czarne_piony_2 >> (9*i)) & 0b111111) == pole) & (((czarne_piony_2 >> (9*i)) & 0b100000000) > 0))
                return (int)((czarne_piony_2 >> (9*i)) & 0b111111111);
            if((((biale_piony_1 >> (9*i)) & 0b111111) == pole) & (((biale_piony_1 >> (9*i)) & 0b100000000) > 0))
                return (int)((biale_piony_1 >> (9*i)) & 0b111111111);
            if((((biale_piony_2 >> (9*i)) & 0b111111) == pole) & (((biale_piony_2 >> (9*i)) & 0b100000000) > 0))
                return (int)((biale_piony_2 >> (9*i)) & 0b111111111);
        }

        //pole puste
        return -1;
    }

    public static boolean czy_bicie_pion(int pion){


        return false;
    }
}
