class Wallet {

    private int balance;

    Wallet(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void transferTo(Wallet target, int amount) {

        Wallet first = this;
        Wallet second = target;

        // Consistent lock ordering prevents deadlock
        if (System.identityHashCode(first) >
                System.identityHashCode(second)) {

            first = target;
            second = this;
        }

        synchronized (first) {
            synchronized (second) {

                if (balance >= amount) {
                    balance -= amount;
                    target.balance += amount;

                    System.out.println(
                        "Transferred Rs." + amount
                    );
                }
            }
        }
    }
}

public class WalletTransferDemo {

    public static void main(String[] args)
            throws InterruptedException {

        Wallet wallet1 = new Wallet(5000);
        Wallet wallet2 = new Wallet(5000);

        Thread student1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                wallet1.transferTo(wallet2, 10);
            }
        });

        Thread student2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                wallet2.transferTo(wallet1, 10);
            }
        });

        student1.start();
        student2.start();

        student1.join();
        student2.join();

        System.out.println("Wallet 1 Balance: "
                + wallet1.getBalance());

        System.out.println("Wallet 2 Balance: "
                + wallet2.getBalance());

        System.out.println("Total Balance: "
                + (wallet1.getBalance()
                + wallet2.getBalance()));
    }
}