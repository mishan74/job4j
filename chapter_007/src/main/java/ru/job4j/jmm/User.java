package ru.job4j.jmm;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class User {
    private final String name;
    private int salary;

    public User(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Для начала мы создали класс User, который имеет имя и зарплату
     * Затем 2 класса, имплементирующие runnable, которые принимают в себя
     * объект User, затем увеличивают этому User зарплату на 500.
     * Далее мы объявляем потоки, с аргументами этих классов и запускаем их методом start()
     * Причем запускаем их в потоке main, т.е. по сути у нас 3 потока (в 2х инкрементируется зарплата,
     * в третьем она выводится на экран). И так как все 3 потока работают ассинхронно,
     * мы не знаем какой из них завершится первый
     *
     * В данном примере существуют сразу 2 проблемы:
     * 1 Проблема - Если метод main завершится раньше, чем отработал какой либо из потоков, то мы получим
     * неверную зарплату, тк. main считает ту зарплату, которая изменится в дальнейшем., но даже если
     * инкрементирующий поток выполняется после считывания зарплаты методом майн, это не гарантирует
     * корректной операции из за проблемы 2:
     * 2 Проблема - У нас 2 метода, изменяюхих состояние объекта. Данная программа не предусматривает
     * синхронного изменения зарплаты, а значит оба инкремментирующих метода могут изменить зарплату не
     * последовательно, а оба взять изначальную зарплату, скопировать ее в свой стек памяти, и не зависимо от того
     * завершился ли первый инкремментирующий метод или нет, инкремментировать свое! число из своего! локального стека
     *
     * Метод майн выводит разные значения на экран:
     *
     * 1500
     * 2000
     * 2500
     *
     */
    public static void main(String[] args) throws InterruptedException {
        User user = new User("Gorgy", 1500);
        Thread thread1 = new Thread(new IncrementSallary(user));
        Thread thread2 = new Thread(new IncrementSallary(user));

        thread1.start();
        thread2.start();

        Thread.sleep(10);
        System.out.printf(
                "Name = %s; %sSalary = %s%s",
                user.getName(),
                System.lineSeparator(),
                user.getSalary(),
                System.lineSeparator()
        );
    }
}


class IncrementSallary implements Runnable {
    private final User user;

    public IncrementSallary(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        this.user.setSalary(this.user.getSalary() + 500);
    }
}