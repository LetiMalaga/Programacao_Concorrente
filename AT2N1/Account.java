// Nenhum import necessário

// Classe que representa uma conta bancária
class Account {
    private String name; // Nome da conta
    private double balance; // Saldo da conta

    // Construtor da conta com nome e saldo inicial
    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    // Getter para o nome da conta
    public String getName() {
        return name;
    }

    // Getter para o saldo da conta
    public double getBalance() {
        return balance;
    }

    // Método para depositar dinheiro na conta
    public void deposit(double amount) {
        balance += amount; // Adiciona dinheiro à conta
    }

    // Método para sacar dinheiro da conta
    public void withdraw(double amount) {
        balance -= amount; // Retira dinheiro da conta
    }

    // Método para transferir dinheiro para outra conta
    public void transfer(Account destination, double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount; // Deduz o valor transferido do saldo da conta de origem
            destination.deposit(amount); // Adiciona o valor transferido ao saldo da conta de destino
            System.out.printf("Transferiu $%.2f para a conta de %s%n", amount, destination.getName());
        } else {
            System.out.println("Transferência não autorizada: saldo insuficiente.");
        }
    }
}
