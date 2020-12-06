projekt nie jest skończony. zabrałem się do niego 2 dni przed terminem naiwnie wierząc, że umiem programować.
mam nauczkę.

# ppj-projekt01

Wszystkie zasady gry, cel jak i wielkość planszy opisuje wikipedia. Zaimplementuj
autorską wersję gry w Warcaby, w której gracze będą wykorzystywać wspólną
klawiaturę do wprowadzania posunięć.
Przyjmij że tworząc tą grę dla jednego gracza będziemy dysponowali tylko dwoma
zmiennymi typu long, których wewnętrzna struktura (rys. 1) dla każdego z elementów
gry zostanie opisana przez własny zestaw bitów (b x ) wyspecyfikowany jak następuje:
• b 2 ,b 1 ,b 0 - współrzędna X na planszy,
• b 5 ,b 4 ,b 3 - współrzędna Y na planszy,
• b 6 - kolor (0 - czarny, 1 - biały),
• b 7 - figura (0 - pion, 1 - damka),
• b 8 - stan (0 - zbity, 1 - w grze).

Aby gracze widzieli co dzieje się na planszy, po każdym ruchu należy wyrysować
planszę. W tym celu programujący dysponuje znakami unicode z tabelki 1:
2B1B
białe pole
2B1C
czarne pole
2659
biały pion
265F
czarny pion
2655
biała damka
265B czarna damka
