// Imports necessários
import java.util.ArrayList;
import java.util.List;

// Classe principal que inicia o sistema bancário
public class Main {
    
    // Imprime os valores finais das contas
    public static void printFinalBalances(List<Account> accounts) {
        System.out.println("Saldos Finais:\n");
        for (Account account : accounts) {
            System.out.printf("%s: $%.2f%n", account.getName(), account.getBalance());
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank(); // Cria o banco

        Account bankAccount = new Account("Banco", Double.POSITIVE_INFINITY); // Cria a conta do banco com saldo ilimitado

        // Cria contas para as lojas
        Account store1Account = new Account("Loja 1", 0);
        Account store2Account = new Account("Loja 2", 0);

        // Cria lista de lojas
        List<Store> stores = new ArrayList<>();
        stores.add(new Store("Loja 1", store1Account));
        stores.add(new Store("Loja 2", store2Account));

        // Cria contas para os funcionários
        Account[] employee1Accounts = {store1Account, new Account("Investimento Funcionário 1", 0)};
        Account[] employee2Accounts = {store1Account, new Account("Investimento Funcionário 2", 0)};
        Account[] employee3Accounts = {store2Account, new Account("Investimento Funcionário 3", 0)};
        Account[] employee4Accounts = {store2Account, new Account("Investimento Funcionário 4", 0)};

        // Cria lista de funcionários
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Funcionário 1", employee1Accounts[0], employee1Accounts[1]));
        employees.add(new Employee("Funcionário 2", employee2Accounts[0], employee2Accounts[1]));
        employees.add(new Employee("Funcionário 3", employee3Accounts[0], employee3Accounts[1]));
        employees.add(new Employee("Funcionário 4", employee4Accounts[0], employee4Accounts[1]));

        // Inicializa as threads dos funcionários
        for (Employee employee : employees) {
            employee.start();
        }

        // Cria clientes e inicia suas threads
        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Account clientAccount = new Account("Cliente " + i, 1000); // Cria uma conta para cada cliente com saldo inicial de R$ 1000
            Client client = new Client("Cliente " + i, clientAccount, stores, bank); // Cria uma instância de cliente
            clients.add(client); // Adiciona o cliente à lista
            client.start(); // Inicia a thread do cliente
        }

        // Aguarda a finalização das threads dos clientes
        for (Client client : clients) {
            try {
                client.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Paga os funcionários após todos os clientes terem terminado suas compras
        for (Store store : stores) {
            store.payEmployees(employees); // Paga os funcionários de cada loja
        }

        // Cria uma lista de todas as contas no sistema
        List<Account> allAccounts = new ArrayList<>();
        allAccounts.add(bankAccount);
        allAccounts.add(store1Account);
        allAccounts.add(store2Account);
        for (Employee employee : employees) {
            allAccounts.add(employee.getPaymentAccount());
            allAccounts.add(employee.getInvestmentAccount());
        }
        for (Client client : clients) {
            allAccounts.add(client.getAccount());
        }

        // Chama a função que imprime os valores finais de cada conta
        printFinalBalances(allAccounts);
    }
}
