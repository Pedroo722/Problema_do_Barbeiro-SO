package br.edu.ifpb.barbeiro;

import java.util.LinkedList;
import java.util.List;

/**
 * A classe Barbearia simula o comportamento de uma barbearia com múltiplos barbeiros e clientes.
 * Ela gerencia a fila de clientes, as cadeiras disponíveis para espera e os barbeiros que cortam os cabelos dos clientes.
 *
 * @author Pedroo722, ViniciusCavalcantePequeno
 */
public class Barbearia {
    private final int numeroCadeiras;
    private final int numeroBarbeiros;
    private int barbeirosDisponiveis;
    private final List<Cliente> listaClientes; // clientes aguardando para cortar o cabelo

    /**
     * Construtor da classe Barbearia.
     * Inicializa o número de barbeiros, o número de cadeiras e a lista de clientes.
     *
     * @param numeroBarbeiros número de barbeiros na barbearia
     * @param numeroCadeiras número de cadeiras para a sala de espera
     */
    public Barbearia(int numeroBarbeiros, int numeroCadeiras) {
        this.numeroCadeiras = numeroCadeiras;
        listaClientes = new LinkedList<>();
        this.numeroBarbeiros = numeroBarbeiros;
        barbeirosDisponiveis = this.numeroBarbeiros;
    }

    /**
     * Método responsável por simular o corte de cabelo de um cliente.
     * Um barbeiro espera por um cliente, realiza o corte e depois libera a vaga.
     *
     * @param barbeiroId identificador do barbeiro que vai cortar o cabelo
     */
    public void cortarCabelo(int barbeiroId) {
        while (true) {
            Cliente cliente;

            synchronized (listaClientes) {
                // Barbeiro dorme se não houver clientes
                while (listaClientes.isEmpty()) {
                    System.out.println();
                    System.out.println("O barbeiro " + barbeiroId + " está dormindo enquanto espera por clientes.");
                    try {
                        listaClientes.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Interrupção: " + e.getMessage());
                    }
                }

                // Barbeiro pega o próximo cliente da fila
                cliente = ((LinkedList<Cliente>) listaClientes).poll();
                System.out.println();
                System.out.println("O barbeiro " + barbeiroId + " encontrou o cliente " + cliente.getClienteId() + " na fila.");
                barbeirosDisponiveis--;
            }

            // Simula o corte de cabelo
            try {
                System.out.println("O barbeiro " + barbeiroId + " está cortando o cabelo do cliente " + cliente.getClienteId() + ".");
                Thread.sleep(5000); // 5 segundos para o tempo de corte
                System.out.println("O barbeiro " + barbeiroId + " terminou de cortar o cabelo do cliente " + cliente.getClienteId() + ".");
            } catch (InterruptedException e) {
                System.out.println("Interrupção: " + e.getMessage());
            }

            synchronized (listaClientes) {
                barbeirosDisponiveis++;
                listaClientes.notifyAll(); // Notifica possíveis clientes que estavam esperando
                // acordando o barbeiro caso esteja dormindo
            }
        }
    }

    /**
     * Método responsável por adicionar um cliente à lista de espera.
     * Se não houver cadeiras ou barbeiros disponíveis, o cliente sairá da barbearia.
     *
     * @param cliente cliente que chegou à barbearia
     */
    public void adicionarCliente(Cliente cliente) {
        System.out.println("\nCliente " + cliente.getClienteId() + " acabou de chegar.");

        synchronized (listaClientes) {
            if (listaClientes.size() == numeroCadeiras) {
                System.out.println();
                System.out.println("Nenhuma cadeira disponível. O cliente " + cliente.getClienteId() + " deixou a barbearia.");
            } else {
                listaClientes.add(cliente);
                System.out.println("O cliente " + cliente.getClienteId() + " sentou-se na sala de espera.");
                listaClientes.notify(); // Notifica os barbeiros para atender o cliente
                // acordando o barbeiro caso ele esteja dormindo
            }
        }
    }
}