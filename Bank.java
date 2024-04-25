// Imports necessários
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Classe responsável por representar o banco e intermediar as transações entre contas
class Bank {
    private Lock lock = new ReentrantLock(); // Lock para garantir a sincronização das transações

    // Método para transferir dinheiro entre contas de forma segura
    public void transfer(Account from, Account to, double amount) {
        lock.lock(); // Bloqueia a execução para garantir a sincronização
        try {
            if (from.getBalance() >= amount) { // Verifica se a conta de origem tem saldo suficiente
                from.withdraw(amount); // Retira o dinheiro da conta de origem
                to.deposit(amount); // Deposita o dinheiro na conta de destino
                System.out.printf("Transferir: $%.2f da conta de origem %s para a conta de destino %s%n", amount, from.getName(), to.getName());
            } else {
                System.out.println("\nSaldo insuficiente para transferência.\n"); // Mensagem se não houver saldo suficiente
            }
        } finally {
            lock.unlock(); // Libera o bloqueio após a conclusão da transação
        }
    }
}
