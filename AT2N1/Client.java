import java.util.List;

// Classe que representa um cliente, que realiza compras em lojas
class Client extends Thread {
    private Account account; // Conta do cliente
    private List<Store> stores; // Lista de lojas disponíveis para compra
    private Bank bank; // Banco para transações

    // Construtor do cliente com a conta associada, lista de lojas e o banco
    public Client(String name, Account account, List<Store> stores, Bank bank) {
        super(name);
        this.account = account;
        this.stores = stores;
        this.bank = bank;
    }

    // Método para as ações do cliente
    public void run() {
        while (account.getBalance() > 0) { // Enquanto houver saldo na conta do cliente
            double amount = Math.random() < 0.5 ? 100 : 200; // Define o valor da compra
            Store randomStore = stores.get((int) (Math.random() * stores.size())); // Escolhe uma loja aleatória
            bank.transfer(account, randomStore.getAccount(), amount); // Realiza a transferência para a loja
            try {
                Thread.sleep(1000); // Espera um segundo antes da próxima compra
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Account getAccount() {
        return account;
    }
    
}
