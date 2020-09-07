package lucku.baijunhan.alg.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class PrintZeroEvenOdd {

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        IntConsumer intConsumer = System.out::print;
        Thread zero = new Thread(() -> {
            try {
                zeroEvenOdd.zero(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread odd = new Thread(() -> {
            try {
                zeroEvenOdd.odd(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread even = new Thread(() -> {
            try {
                zeroEvenOdd.even(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        zero.start();
        odd.start();
        even.start();
    }


    static class ZeroEvenOdd {

        private final Lock lock = new ReentrantLock();
        private final Condition zeroCondition = lock.newCondition();
        private final Condition evenCondition = lock.newCondition();
        private final Condition oddCondition = lock.newCondition();


        private int n;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {

            lock.lock();
            try {
                for (int i = 1; i <= n; i++) {
                    printNumber.accept(0);
                    if ((i & 1) == 1)
                        oddCondition.signal();
                    else
                        evenCondition.signal();
                    zeroCondition.await();
                }
            } finally {
                lock.unlock();
            }

        }

        public void even(IntConsumer printNumber) throws InterruptedException {

            lock.lock();

            try {
                for (int i = 2; i <= n; i += 2) {
                    evenCondition.await();
                    printNumber.accept(i);
                    zeroCondition.signal();
                }
            } finally {
                lock.unlock();
            }

        }

        public void odd(IntConsumer printNumber) throws InterruptedException {

            lock.lock();
            try {
                for (int i = 1; i <= n; i += 2) {
                    oddCondition.await();
                    printNumber.accept(i);
                    zeroCondition.signal();
                }
            } finally {
                lock.unlock();
            }
        }

    }
}
