package crackingTheCodingInterview;

import java.util.LinkedList;

/**
 * Author by darcy
 * Date on 17-7-8 上午11:32.
 * Description:
 *
 * 构造一个对动物类型的封装, 但是需要存储一个代表进入收容中心的时间戳,
 * 源代码中给出的order, private long time = System.currentTimeMillis(); 存储一个这样的字段也可以.
 */

abstract class Animal {
    private int order;

    protected String name;
    public Animal(String n) {
        name = n;
    }

    public abstract String name();

    public void setOrder(int ord) {
        order = ord;
    }

    public int getOrder() {
        return order;
    }

    public boolean isOlderThan(Animal a) {
        return this.order < a.getOrder();
    }
}

class Cat extends Animal {
    public Cat(String n) {
        super(n);
    }

    public String name() {
        return "Cat: " + name;
    }
}

class Dog extends Animal {
    public Dog(String n) {
        super(n);
    }

    public String name() {
        return "Dog: " + name;
    }
}

public class P837_CatDogAsylum2 {

    LinkedList<Dog> dogs = new LinkedList<Dog>();
    LinkedList<Cat> cats = new LinkedList<Cat>();

    // 收容中心维护一个order.
    private int order = 0;

    public void enqueue(Animal a) {
        a.setOrder(order);
        order++;
        if (a instanceof Dog) {
            dogs.addLast((Dog) a);
        } else if (a instanceof Cat) {
            cats.addLast((Cat)a);
        }
    }

    public Animal dequeueAny() {
        if (dogs.size() == 0) {
            return dequeueCats();
        } else if (cats.size() == 0) {
            return dequeueDogs();
        }
        Dog dog = dogs.peek();
        Cat cat = cats.peek();
        if (dog.isOlderThan(cat)) {
            return dogs.poll();
        } else {
            return cats.poll();
        }
    }

    public Animal peek() {
        if (dogs.size() == 0) {
            return cats.peek();
        } else if (cats.size() == 0) {
            return dogs.peek();
        }
        Dog dog = dogs.peek();
        Cat cat = cats.peek();
        if (dog.isOlderThan(cat)) {
            return dog;
        } else {
            return cat;
        }
    }

    public int size() {
        return dogs.size() + cats.size();
    }

    public Dog dequeueDogs() {
        return dogs.poll();
    }

    public Dog peekDogs() {
        return dogs.peek();
    }

    public Cat dequeueCats() {
        return cats.poll();
    }

    public Cat peekCats() {
        return cats.peek();
    }

}
