package br.edu.ifpb.barbeiro;

/**
 * A classe GeradorCliente é responsável por simular a chegada de novos clientes à barbearia.
 * Ela estende a classe Thread e, ao ser executada, cria e inicia novas instâncias da classe Cliente.
 *
 * O comportamento da classe consiste em gerar clientes de forma contínua, com intervalos de tempo
 * aleatórios de até 4 segundos, para simular a chegada de clientes de maneira imprevisível.
 *
 * @author ViniciusCavalcantePequeno
 */
public class GeradorCliente extends Thread {
    private final Barbearia barbearia;
    private static int contadorId = 1; // Contador estático para IDs progressivos

    /**
     * Construtor da classe GeradorCliente.
     * Inicializa a instância de GeradorCliente com a referência para a barbearia onde os clientes serão registrados.
     *
     * @param barbearia a instância da barbearia onde os clientes serão atendidos
     */
    public GeradorCliente(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    /**
     * Método executado pela thread para gerar clientes de forma contínua.
     * A cada ciclo, um novo cliente é criado, iniciado como uma thread e adicionado à fila de espera da barbearia.
     * Entre a criação de cada cliente, a thread pausa por um tempo aleatório de até 4 segundos.
     */
    @Override
    public void run() {
        while (contadorId < 11) {
            Cliente cliente = new Cliente(barbearia);
            cliente.setClienteId(getNovoId());
            cliente.start(); // automaticamente realiza o run() do cliente

            try {
                // tempo aleatório entre 0 e 4 segundos, até o próximo cliente chegar
                Thread.sleep((long) (Math.random() * 4000));
            } catch (InterruptedException interruptedException) {
                System.out.println(interruptedException);
            }
        }
    }

    private synchronized int getNovoId() {
        return contadorId++;
    }
}