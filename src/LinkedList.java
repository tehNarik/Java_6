import java.util.*;

public class LinkedList implements List<Car> {
    private Node head;
    private int size;

    // Перший пустий конструктор
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Другий конструктор з аргументом одного елементу
    public LinkedList(Car data) {
        this();
        add(data);
    }

    // Третій конструктор, який приймає готовий список
    public LinkedList(List<Car> carList) {
        this(); // Викликаємо пустий конструктор для ініціалізації
        for (Car car : carList) {
            add(car); // Додаємо кожен елемент зі списку в наш однозв'язний список
        }
    }

    // Клас Node - елемент однозв'язного списку
    private class Node {
        public Car data;
        public Node next;

        public Node(Car data) {
            this.data = data;
        }
    }

    // Метод для додавання елемента в кінець списку
    @Override
    public boolean add(Car data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode; // Якщо список порожній, новий вузол стає головним
        } else {
            Node currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode; // Додаємо новий вузол в кінець
        }
        this.size++;
        return true;
    }

    // Метод для вставки елемента в заданий індекс
    @Override
    public void add(int index, Car element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Індекс за межами списку");
        }
        Node newNode = new Node(element);
        if (index == 0) {
            newNode.next = head;
            head = newNode; // Додаємо новий елемент на початок
        } else {
            Node currentNode = head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode; // Додаємо новий вузол у середину
        }
        this.size++;
    }

    // Метод для видалення елемента зі списку
    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Car)) {
            return false;
        }
        Car data = (Car) o;
        Node currentNode = head;
        Node previousNode = null;

        while (currentNode != null) {
            if (currentNode.data.equals(data)) { // Використовуємо equals для порівняння об'єктів
                if (currentNode == head) {
                    head = currentNode.next; // Якщо видаляється головний елемент
                } else {
                    previousNode.next = currentNode.next; // Пропускаємо вузол
                }
                this.size--;
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return false; // Елемент не знайдено
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // Перевірка на null
        if (c == null) {
            throw new NullPointerException("Колекція не повинна бути null");
        }

        // Проходимо через всі елементи колекції
        for (Object obj : c) {
            // Перевіряємо, чи міститься кожен об'єкт у списку
            if (!contains(obj)) {
                return false; // Повертаємо false, якщо хоча б один елемент відсутній
            }
        }
        return true; // Якщо всі елементи присутні, повертаємо true
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true; // Перевірка на ідентичність
        }
        if (!(o instanceof List)) {
            return false; // Перевірка, чи o - це список
        }
        List<?> otherList = (List<?>) o; // Перетворення об'єкта у список

        if (this.size() != otherList.size()) {
            return false; // Перевірка розміру списків
        }

        // Порівнюємо елементи
        for (int i = 0; i < this.size(); i++) {
            Object thisElement = this.get(i); // Отримуємо елемент з поточного списку
            Object otherElement = otherList.get(i); // Отримуємо елемент з іншого списку
            if (!Objects.equals(thisElement, otherElement)) {
                return false; // Якщо елементи не рівні, повертаємо false
            }
        }

        return true; // Якщо всі елементи рівні, повертаємо true
    }


    // Метод для видалення елемента за індексом
    @Override
    public Car remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Індекс за межами списку");
        }
        Node currentNode = head;
        if (index == 0) {
            head = currentNode.next; // Видаляємо головний елемент
        } else {
            Node previousNode = null;
            for (int i = 0; i < index; i++) {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            previousNode.next = currentNode.next; // Пропускаємо вузол
        }
        this.size--;
        return currentNode.data;
    }

    @Override
    public int indexOf(Object o) {
        // Перевірка, чи об'єкт є типом Car
        if (!(o instanceof Car)) {
            return -1; // Повертаємо -1, якщо об'єкт не є типом Car
        }
        Car data = (Car) o; // Приведення об'єкта до типу Car
        Node currentNode = head; // Починаємо з голови списку
        int index = 0; // Індекс для відстеження позиції

        // Проходимо через всі вузли списку
        while (currentNode != null) {
            if (currentNode.data.equals(data)) {
                return index; // Повертаємо індекс, якщо знайшли елемент
            }
            currentNode = currentNode.next; // Переходимо до наступного вузла
            index++; // Збільшуємо індекс
        }
        return -1; // Повертаємо -1, якщо елемент не знайдено
    }

    @Override
    public int lastIndexOf(Object o) {
        // Перевірка, чи об'єкт є типом Car
        if (!(o instanceof Car)) {
            return -1; // Повертаємо -1, якщо об'єкт не є типом Car
        }
        Car data = (Car) o; // Приведення об'єкта до типу Car
        Node currentNode = head; // Починаємо з голови списку
        int index = 0; // Індекс для відстеження позиції
        int lastIndex = -1; // Ініціалізуємо з -1 для позначення, що елемент не знайдено

        // Проходимо через всі вузли списку
        while (currentNode != null) {
            if (currentNode.data.equals(data)) {
                lastIndex = index; // Оновлюємо lastIndex, якщо дані збігаються
            }
            currentNode = currentNode.next; // Переходимо до наступного вузла
            index++; // Збільшуємо індекс
        }
        return lastIndex; // Повертаємо останній знайдений індекс або -1, якщо не знайдено
    }


    // Метод для отримання елемента за індексом
    @Override
    public Car get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Даного індексу не існує");
        }
        Node currentNode = head;
        for (int currentIndex = 0; currentIndex < index; currentIndex++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }
    // Метод для отримання першого елемента списку
    public Car getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список порожній, неможливо отримати перший елемент.");
        }
        return head.data; // Повертаємо дані головного вузла
    }

    // Метод для отримання останнього елемента списку
    public Car getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Список порожній, неможливо отримати останній елемент.");
        }
        Node currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next; // Переміщаємося до останнього вузла
        }
        return currentNode.data; // Повертаємо дані останнього вузла
    }
    // Метод для створення нового списку з елементами у зворотному порядку
    public LinkedList reversed() {
        LinkedList reversedList = new LinkedList(); // Створюємо новий пустий список
        Node currentNode = head;

        while (currentNode != null) {
            reversedList.add(0, currentNode.data); // Додаємо елемент на початок нового списку
            currentNode = currentNode.next; // Переміщаємося до наступного вузла
        }

        return reversedList; // Повертаємо новий список у зворотному порядку
    }

    // Метод для модифікації елемента за індексом
    @Override
    public Car set(int index, Car element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Індекс за межами списку");
        }
        Node currentNode = head;
        for (int currentIndex = 0; currentIndex < index; currentIndex++) {
            currentNode = currentNode.next;
        }
        Car oldData = currentNode.data;
        currentNode.data = element; // Оновлюємо дані
        return oldData;
    }

    // Метод для отримання розміру списку
    @Override
    public int size() {
        return this.size;
    }

    // Метод для перевірки, чи список порожній
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Метод для перевірки, чи містить список вказаний елемент
    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Car)) {
            return false;
        }
        Car data = (Car) o;
        Node currentNode = head;

        while (currentNode != null) {
            if (currentNode.data.equals(data)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    // Метод для очищення списку
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    // Метод для отримання ітератора
    @Override
    public Iterator<Car> iterator() {
        return new Iterator<Car>() {
            private Node currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public Car next() {
                Car data = currentNode.data;
                currentNode = currentNode.next;
                return data;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size]; // Створюємо масив потрібного розміру
        Node currentNode = head; // Починаємо з голови списку

        for (int i = 0; i < size; i++) {
            array[i] = currentNode.data; // Заповнюємо масив елементами списку
            currentNode = currentNode.next; // Переходимо до наступного елемента
        }

        return array; // Повертаємо масив
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // Якщо наданий масив недостатньої довжини, створюємо новий масив
            @SuppressWarnings("unchecked") // Попередження про типізацію
            T[] newArray = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
            return toArray(newArray); // Заповнюємо новий масив
        }

        Node currentNode = head; // Починаємо з голови списку
        for (int i = 0; i < size; i++) {
            a[i] = (T) currentNode.data; // Заповнюємо наданий масив елементами списку
            currentNode = currentNode.next; // Переходимо до наступного елемента
        }

        if (a.length > size) {
            a[size] = null; // Додаємо null, якщо масив довший за список
        }

        return a; // Повертаємо заповнений масив
    }


    // Інші методи List
    @Override
    public ListIterator<Car> listIterator() {
        throw new UnsupportedOperationException("listIterator не реалізовано");
    }

    @Override
    public ListIterator<Car> listIterator(int index) {
        throw new UnsupportedOperationException("listIterator не реалізовано");
    }

    @Override
    public List<Car> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Індекс за межами списку");
        }
        LinkedList sublist = new LinkedList();
        Node currentNode = head;
        for (int i = 0; i < toIndex; i++) {
            if (i >= fromIndex) {
                sublist.add(currentNode.data);
            }
            currentNode = currentNode.next;
        }
        return sublist;
    }

    @Override
    public boolean addAll(Collection<? extends Car> c) {
        boolean modified = false;
        for (Car car : c) {
            if (add(car)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Car> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Індекс за межами списку");
        }
        boolean modified = false;
        for (Car car : c) {
            add(index++, car);
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            if (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Node currentNode = head;
        Node previousNode = null;

        while (currentNode != null) {
            if (!c.contains(currentNode.data)) {
                if (currentNode == head) {
                    head = currentNode.next; // Якщо видаляється головний елемент
                } else {
                    previousNode.next = currentNode.next; // Пропускаємо вузол
                }
                this.size--;
                modified = true;
            } else {
                previousNode = currentNode;
            }
            currentNode = currentNode.next;
        }
        return modified;
    }

}
