package concurrency.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainClient {

    public static void main(String[] args) {
        /**
         * Java Concurrency in Practice by Brian Goetz (Author), Tim Peierls (Author),
         * Joshua Bloch (Author), Joseph Bowbeer (Author), David Holmes (Author), Doug Lea (Author)
         *
         Класс ConcurrentHashMap — это хешированный ассоциативный массив
         Мар, аналогичный хеш-массиву HashMap, но использующий другую замковую стратегию.
         Вместо синхронизации каждого метода на общем замке
         и ограничения доступа одним потоком за раз он использует замковое
         расщепление на полосы (lock striping), расширяющее
         возможности совместного доступа к ассоциативному массиву. Оно обеспечивает
         конкурентность между читающими потоками, между читателями и писателями и между писателями.
         Результатом является высокая пропускная способность в рамках конкурентного доступа с небольшим
         штрафом на производительность для однопоточного доступа.
         Также класс ConcurrentHashMap предоставляет итераторы, которые не выдают исключение
         ConcurrentModificationException, являются не быстро
         отказывающими, а слабо непротиворечивыми (weakly consistent). Они
         допускают конкурентное выполнение изменений, перебирают элементы
         в том порядке, в каком они существовали при конструировании итератора,
         и могут (не обязательно) отражать изменения в коллекцию.
         */
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        /**
         * if in the meantime some other thread adds or removes an element from the list,
         * that modification is making a fresh copy of the data that will be used in any further data lookup from that list.
         * it particularly useful in cases when we are iterating over it more often than we are modifying it
         * If adding elements is a common operation in our scenario, then CopyOnWriteArrayList won’t be a good choice –
         * because the additional copies will definitely lead to sub-par performance.
         */
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
    }
}
