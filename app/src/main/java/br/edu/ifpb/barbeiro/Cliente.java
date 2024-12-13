package br.edu.ifpb.barbeiro;

/**
 * A classe Cliente representa um cliente que chega à barbearia para cortar o cabelo.
 * Cada cliente é uma thread que simula a ação de ir à barbearia, onde ele é adicionado à fila de espera
 * para ser atendido por um barbeiro.
 *
 * O cliente possui um identificador único e, ao executar a thread, ele é adicionado à fila da barbearia
 * para aguardar o atendimento do barbeiro.
 *
 * @author ViniciusCavalcantePequeno
 */
public class Cliente extends Thread {
    private final Barbearia barbearia;
    private long clienteId;

    /**
     * Construtor da classe Cliente.
     * Inicializa a barbearia onde o cliente deseja cortar o cabelo.
     *
     * @param barbearia instância da barbearia onde o cliente vai cortar o cabelo
     */
    public Cliente(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Método que simula a chegada do cliente à barbearia.
     * O cliente é adicionado à fila da barbearia para aguardar o atendimento.
     */
    private void irCortarCabelo() {
        barbearia.adicionarCliente(this);
    }

    /**
     * Método que executa a thread do cliente. Quando o cliente é iniciado pelo 'start',
     * ele chama o método `irCortarCabelo()` para se adicionar à fila da barbearia.
     */
    @Override
    public void run() {
        irCortarCabelo();
    }
}
