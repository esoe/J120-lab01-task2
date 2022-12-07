package ru.molokoin;

import java.util.Iterator;

/**
 * Класс содержащий инструменты управления списком и данными в нем
 * @param <T>
 */
public class SimplyGenericList<T> implements Iterable<T> {
    /**
     * Поле элемента списка, хранит данные о первом элементе
     */
    private Node<T> head;
    /**
     * Поле элемента списка, хранит данные о последнем элементе
     */
    private Node<T> tail;
    /**
     * остаток не доделаной конфигурации,<p>
     * Хранит данные о передаваемом в список типе данных,<p>
     * предполагалось с помощью него реализовывать применение различных реализаций методов,<p>
     * в завиимости от типов передаваемых пользователем даных.
     */
    Class<?> clazz;//для определения класса, с которым пользователь использует список

    /**
     * Конструктор списка, принимающий в качестве параметра тип данных, передаваемых пользователем в список.
     * @param clazz
     */
    public SimplyGenericList(Class<?> clazz){
        this.clazz = clazz;
    }
    /**
     * @return the head
     */
    public Node<T> getHead() {
        return head;
    }
    /**
     * @param head the head to set
     */
    public void setHead(Node<T> head) {
        this.head = head;
    }
    /**
     * @return the tail
     */
    public Node<T> getTail() {
        return tail;
    }
    /**
     * @param tail the tail to set
     */
    public void setTail(Node<T> tail) {
        this.tail = tail;
    }
    /**
     * Добавление элемента в начало списка
     * @param data
     */
    public void addFirst(T data){
        if(head == null){
            head = new Node<T>();
            head.data = (T)data;
            tail = head;
        }else{
            Node<T> tmp = new Node<T>();
            tmp.data = (T)data;
            tmp.next = head;
            head = tmp;
        }
    }
    /**
     * Добавление нового элемента в конец списка
     */
    public void addLast(T data){
        if(head == null){
            addFirst((T)data);
        }else{
            Node<T> tmp = new Node<T>();
            tmp.data = (T)data;
            tail.next = tmp;
            tail = tmp;
        }
    }
    /**
     * удаляет первый элемент списка и возвращает его данные
     * @return
     * TODO вдруг список пустой
     * TODO вдруг список содержит только один элемент
     */
    public T executeHead(){
        T tmp = null;
        tmp = (T)head.data;
        head = head.next;
        return tmp;
    }
    /**
     * Удаление последнего элемента списка
     * @return
     */
    public T executeTail(){
        T tmp = (T)tail.data;//готовы писать данные удаляемого элемента
        Node<T> bufNode = head;
        //у предпоследнего элемента удаляем ссылку на tail
        while (bufNode.next != tail){
            bufNode = bufNode.next;
        }
        bufNode.next = null;
        return tmp;
    }
    /**
     * Преобразование массива в односвязный список
     * @param array
     * @return
     * TODO в перспективе сделать преобразование массива
     * */
    public SimplyGenericList<T> arrayToList(T[] array){
        SimplyGenericList<T> sl = new SimplyGenericList<T>(clazz);
        return sl;
    }
    /**
     * вывод в консоль всех элементов списка, по порядку
     */
    public void printAll(){
        Node<T> temp = head;
        while (temp != null){
            printNode(temp);
            temp = temp.next;
        }
    }
    /**
     * Вывод в консоль данных указанной ноды
     * @param node
     */
    public void printNode(Node<T> node){
        if (node == null){
            System.out.println(">>> " + null);

        }else{
            System.out.println(">>> " + (T)node.data);
        }
    }
    public void deleteNodeByValue(T value){
        //обработка случая, когда первый элемент списка подлежит удалению
        while (head.data == (T)value){
            head = head.next;
        }
        //первый элемент списка точно не подлежит удалению
        Node<T> buf = new Node<T>();
        buf = head;
        while(buf.next != null){
            if (buf.next.data != (T)value){
                buf = buf.next;
            }else{
                buf.next = buf.next.next;
            }
        }
    }
    /**
     * не понятно почему не работает,
     * оператор "+" не предусмотрен для объектов класса Т
     * как предусмотреть ???
     * можно попробовать сделать еще один свитч по типу данных, передаваемых пользователем в список,
     * но поля в списке всеравно окажутся <T> и к ним не понятно как получать доступ.
     * с агрегированием аналогичная ситуация
     * @param use
     * @param value
     */

    public void math(UseMath use, T value){
        try{
            switch (use){
                case SUM :{
                    Node<T> buf = head;
                    if (buf.data instanceof String){
                        /**
                         * работает быстрее, потомучто под капотом static поля,<p>
                         * обработка идет в  стеке
                         */
                        StringBuilder mid = new StringBuilder(buf.toString());
                        while (buf.next != null){
                            mid =  mid.append(buf.next.toString())  ;
                            buf = buf.next;
                        }
                        System.out.println("SUM-String: " + mid);
                    }
                    /**
                     * тоже работает
                     */
                    // if (buf.data instanceof String){
                    //     String mid = buf.toString();
                    //     while (buf.next != null){
                    //         mid = "" +mid + buf.next.toString();
                    //         buf = buf.next;
                    //     }
                    //     System.out.println("SUM-String: " + mid);
                    // }

                    //по той же причине mid объявлен как int
                    if (buf.data instanceof Integer){
                        int mid = buf.toInteger();
                        while (buf.next != null){
                            mid = mid + buf.next.toInteger();
                            buf = buf.next;
                        }
                        System.out.println("SUM-INTEGER: " + mid);
                    }
                    
                    break;
                }
                case INCREASE : {
                    Node<T> buf = head;
                    if (buf.data instanceof Number){
                        Integer midl = buf.toInteger() + (Integer)value;
                        System.out.println("(Integer)mid :" + midl);
                        buf.data = (T)midl;
                        while (buf.next != null){
                            midl = buf.next.toInteger() + (Integer)value;
                            buf.next.data = (T)midl;
                            buf = buf.next;
                        }
                    }
                    if (buf.data instanceof String){
                        String midl = buf.toString() + (String)value;
                        System.out.println("(String)mid :" + midl);
                        buf.data = (T)midl;
                        while (buf.next != null){
                            midl = buf.next.toString() + (String)value;
                            buf.next.data = (T)midl;
                            buf = buf.next;
                        }
                    }
                    break;
                }
                case DECREASE : {
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Метод интерфейса Iterable, рализует функционирование итератора,
     * возможность перебора списка циклом foreach
     */
    @Override
    public Iterator<T> iterator(){
        return new SimplyGenericListIterator<T>(head);
    }
    public int size() {
        int index = 1;
        Node<T> current = head;
        while (current.next != null){
            current = current.next;
            index++;
        }
        return index;
    }

    /**
     * Класс, объекты которого являются элементами списка (узлы / ноды / node) в которых хранятся основные данные и ссылка на следующий элемент списка. 
     * не понятно, почему создаются объекты, если класс статический ...
     */
    public static class Node<T>{
        private T data;
        private Node<T> next;

        public Integer toInteger(){
            if (data instanceof Integer){
                return (Integer)data;
            }else{
                throw new IllegalArgumentException("IllegalArgumentException: даные не относятся к типу Integer");
            }
        }
        public String toString(){
            if (data instanceof String){
                return (String)data;
            }else{
                throw new IllegalArgumentException("IllegalArgumentException: даные не относятся к типу String");
            }
        }
    }

    /**
     * Класс, устанавливающий методы рализации интерфейса Iterator,
     * устанавливает методы, испльзуемые при реализации метода iterator() интерфейса Iterable
     * !! скопировал рализацию с практики, на много проще моей получилась .. 
     */
    public static class SimplyGenericListIterator<T> implements Iterator<T> {
        Node<T> nextNode;

        /**
         * Конструктор итератора, передаем в него головную ноду.
         * @param nextNode
         */
        public SimplyGenericListIterator(Node<T> nextNode) {
            this.nextNode = nextNode;
        }
        @Override
        public boolean hasNext() {
            if(nextNode!=null) return true;
            return false;
        }
        @Override
        public T next() {
            T value = (T)nextNode.data;
            nextNode = nextNode.next;
            return value;
        }
    }

}
