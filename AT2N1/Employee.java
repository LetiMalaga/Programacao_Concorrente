// Nenhum import necessário

// Classe que representa um funcionário, que recebe salário da loja e investe uma parte
class Employee extends Thread {
    private String name; // Nome do funcionário
    private Account paymentAccount; // Conta do salário
    private Account investmentAccount; // Conta de investimento

    // Construtor do funcionário com nome e contas associadas
    public Employee(String name, Account paymentAccount, Account investmentAccount) {
        super(name);
        this.name = name;
        this.paymentAccount = paymentAccount;
        this.investmentAccount = investmentAccount;
    }

    // Método para receber o salário e investir uma parte
    public void receivePayment() {
        double salary = 1400; // Salário fixo do funcionário
        double investmentAmount = salary * 0.2; // Calcula o valor do salário a ser investido (20%)
        
        paymentAccount.deposit(salary); // Deposita o salário na conta do funcionário
        investmentAccount.deposit(investmentAmount); // Investe uma parte do salário na conta de investimento
        
        System.out.printf("%s recebeu o salário de: R$%.2f%n", name, salary);
        System.out.printf("R$%.2f investidos na conta de investimento de %s%n", investmentAmount, name);
    }

    public Account getPaymentAccount() {
        return paymentAccount;
    }
    
    public Account getInvestmentAccount() {
        return investmentAccount;
    }

    @Override
    public void run() {
        receivePayment(); // Simula o recebimento do salário ao iniciar a thread do funcionário
    }
}
