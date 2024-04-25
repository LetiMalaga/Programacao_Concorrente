// Imports Necessários
import java.util.List;

// Classe que representa uma loja, que recebe pagamentos de clientes e paga funcionários
class Store {
    private String name; // Nome da loja
    private Account account; // Conta da loja

    // Construtor da loja com nome e conta associada
    public Store(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    // Getter para a conta da loja
    public Account getAccount() {
        return account;
    }

    // Método para pagar os funcionários da loja
    public void payEmployees(List<Employee> employees) {
        double totalPayment = employees.size() * 1400; // Calcula o total dos salários
        if (account.getBalance() >= totalPayment) { // Verifica se há saldo suficiente na conta da loja
            for (Employee employee : employees) {
                account.withdraw(1400); // Retira o salário da conta da loja
                employee.receivePayment(); // Paga o salário ao funcionário
                System.out.printf("Loja %s pagou $1400 para o funcionário %s%n", name, employee.getName());
            }
        } else {
            System.out.println("\nA loja não possui saldo suficiente para pagar o funcionário.\n");
        }
    }
}
