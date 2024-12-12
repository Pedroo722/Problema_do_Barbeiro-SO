package br.edu.ifpb.barbeiro;

/**
 * A classe Barbeiro representa um barbeiro que trabalha na barbearia.
 * Cada barbeiro é uma thread que executa o processo de cortar o cabelo dos clientes.
 *
 * O barbeiro começa "dormindo" e, assim que um cliente está disponível, ele começa
 * a cortar o cabelo até que não haja mais clientes esperando.
 *
 * @author Pedroo722
 */
public class Barbeiro extends Thread {
    private final Barbearia barbearia;
    private final int barbeiroId;

    /**
     * Construtor da classe Barbeiro.
     * Inicializa a barbearia onde o barbeiro vai trabalhar e o seu identificador.
     *
     * @param barbearia instância da barbearia onde o barbeiro irá trabalhar
     * @param barbeiroId identificador único para o barbeiro
     */
    public Barbeiro(Barbearia barbearia, int barbeiroId) {
        this.barbearia = barbearia;
        this.barbeiroId = barbeiroId;
    }

    /**
     * Método responsável por executar a lógica do barbeiro. O barbeiro começa "dormindo"
     * e fica esperando por clientes na barbearia. Quando um cliente chega, ele corta o cabelo
     * e repete o processo indefinidamente.
     */
    @Override
    public void run() {
        try {
            // Simula o barbeiro "dormindo", aguardando um cliente
            System.out.println("O barbeiro está dormindo...");
            Thread.sleep(1000); // Dorme por 1 segundo antes de começar o processo
        } catch (InterruptedException interruptedException) {
            System.out.println(interruptedException);
        }

        // O barbeiro entra em um loop infinito, onde ele corta cabelo de clientes
        while (true) {
            barbearia.cortarCabelo(barbeiroId);
        }
    }
}