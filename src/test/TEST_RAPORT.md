# Raport z testów

## Testowana aplikacja
- Nazwa aplikacji: Foodie
- Testowana wersja: commit #2324a8125721d8560ff8773c51d81801439ca913
- Autor: Piotr Leżański
- Krótki opis: Foodie to aplikacja do rezerwowania stolików w restauracjach, bez konieczności bezpośredniego kontaktu 
z obsługą lokalu.
- Technologie: Java (21), Spring Boot, Vaadin (24.2.2)

## Proces testowania
### Metoda testowania
Zdecydowałem się na podjęcie metody testowania dynamicznego, opartego na ryzyku. Skupiłem się na 
identyfikowaniu, projektowaniu i wykonaniu testów na obszarach, które są uważane za ryzykowne dla projektu.
Pomogło mi to skoncentrować się na tych obszarach, które są kluczowe dla powodzenia projektu i mogą zawierać 
największe niebezpieczeństwo dla jego powodzenia.

Proces można podzielić na takie kroki:
1. **Identyfikacja Ryzyka**:
W pierwszym kroku zidentyfikowałem potencjalne ryzyka związane z projektem. 
2. **Priorytetyzacja Ryzyka**:
Po zidentyfikowaniu ryzyk, oszacowałem je według ich wpływu na projekt. Te o większym wpływie i prawdopodobieństwie wystąpienia są uznawane za bardziej ryzykowne.
3. **Projektowanie Testów**:
Na podstawie zidentyfikowanych ryzyk zaprojektowałem testy, które skoncentrowane są na weryfikowaniu obszarów związanych z ryzykiem. 
4. **Wykonanie Testów**:
Testy dynamiczne oparte na ryzyku obejmują wykonanie testów zgodnie z opracowanym planem. 
5. **Monitorowanie i Raportowanie**:
Działania są monitorowane pod kątem skuteczności w identyfikowaniu i minimalizowaniu ryzyk. 
Wyniki testów są raportowane z uwzględnieniem obszarów ryzyka.

### Typy testów
Głównym typem zastosowanych testów są testy czarnoskrzynkowe funkcjonalne.  
Stworzone zostały na bazie wiedzy testera oraz dokumentacji zawartej w kodzie (ale nie bazowane na samym kodzie).

### Metryki (miary) wykorzystane w procesie testowym
- ilość napisanych przypadków testowych
- ilość wykonanych testów manualnych
- ilość przypadków testowych zakończonych wynikiem negatywnym
- ilość wykrytych defektów
- ilość naprawionych defektów

## Zrealizowane testy

### Testy jednostkowe
Testy jednostkowe zostały użyte do sprawdzenia prawidłowości systemu dokonywania rezerwacji.

| nazwa                              | opis                                      | wynik    |
|------------------------------------|-------------------------------------------|----------|
| testConfiguringButtons             | prawidłowe zalogowanie                    | PASS ✅   |
| testFillFormWithRestaurant         | błędne dane                               | PASS ✅   |
| testMakeReservationButtonClicked   | próba zrobienia rezerwacji                | PASS ✅ |
| testCancelReservationButtonClicked | próba anulowania rezerwacji               |    PASS ✅     |
| testFilterByValidName              | filtrowanie listy po poprawnej nazwie     | PASS ✅   |
| testFilterByInvalidName            | filtrowanie listy po niepoprawnej nazwie  | PASS ✅   |
| testFilterByValidCity              | filtrowanie listy po poprawnym mieście    | PASS ✅   |
| testFilterByInvalidCity            | filtrowanie listy po niepoprawnym mieście | PASS ✅   |

### Testy integracyjne

| nazwa   | opis                                       | wynik    |
|---------|--------------------------------------------|----------|
| testFormInvisibleByDefault | formularz niewidoczny domyślnie            | PASS ✅ |
| testFormVisibleWhenRestaurantSelected | formularz widoczny po wybraniu restauracji | PASS ✅ |

### Testy end-to-end
Zostały wykonane dwa testy sprawdzające proces logowania.

| nazwa              | opis                   | wynik  |
|--------------------|------------------------|--------|
| loginAsValidUser   | prawidłowe zalogowanie | PASS ✅ |
| loginAsInvalidUser | błędne dane            | PASS ✅ |

# Podsumowanie
W przeprowadzonych testach jednostkowych i integracyjnych nie wykryto żadnych poważnych błędów czy niezgodności. 
Aplikacja zdaje egzamin pod względem funkcjonalności rezerwacyjnych, rekomendacyjnych i integracyjnych.


