package br.edu.ifpb.barbeiro;

import java.util.LinkedList;
import java.util.List;

/**
 * A classe Barbearia simula o comportamento de uma barbearia com múltiplos barbeiros e clientes.
 * Ela gerencia a fila de clientes, as cadeiras disponíveis para espera e os barbeiros que cortam os cabelos dos clientes.
 */
public class Barbearia {
    private final int numeroCadeiras;
    private final int numeroBarbeiros;
    private int barbeirosDisponiveis;
    private List<Cliente> listaClientes; // clientes aguardando para cortar o cabelo

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
     * @author Pedroo722, ViniciusCavalcantePequneo
     */
    public void cortarCabelo(int barbeiroId) {
        Cliente cliente;

        // Sincroniza o acesso à lista de clientes para evitar condições de corrida
        synchronized (listaClientes) {
            // Se não houver clientes, o barbeiro aguarda
            while (listaClientes.isEmpty()) {
                System.out.println();
                System.out.println("O barbeiro " + barbeiroId
                        + " está esperando por clientes.");
                try {
                    // O barbeiro espera ser notificado quando um cliente chegar
                    listaClientes.wait();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            // Obtém o cliente da fila (primeiro da fila)
            cliente = (Cliente) ((LinkedList<?>) listaClientes).poll();

            System.out.println();
            System.out.println("O barbeiro " + barbeiroId
                    + " encontrou o cliente " + cliente.getClienteId()
                    + " na fila.");
        }

        long duracao = 0;
        try {
            // Decrementa o número de barbeiros disponíveis
            barbeirosDisponiveis--;

            System.out.println("O barbeiro " + barbeiroId
                    + " está cortando o cabelo do cliente "
                    + cliente.getClienteId() + ".");

            // duração do corte de cabelo (5 segundos)
            duracao = (long) (5000);
            Thread.sleep(duracao);

            // Após o corte, incrementa o número de barbeiros disponíveis
            barbeirosDisponiveis++;
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        // corte foi concluído
        System.out.println("O barbeiro " + barbeiroId
                + " terminou de cortar o cabelo do cliente "
                + cliente.getClienteId() + " em " + duracao
                + " milissegundos.");
    }

    /**
     * Método responsável por adicionar um cliente à lista de espera.
     * Se não houver cadeiras ou barbeiros disponíveis, o cliente sairá da barbearia.
     * 
     * @param cliente cliente que chegou à barbearia
     */
    public void adicionarCliente(Cliente cliente) {
        System.out.println();
        System.out.println("Cliente " + cliente.getClienteId()
                + " acabou de chegar.");

        // Sincroniza o acesso à lista de clientes para evitar condições de corrida
        synchronized (listaClientes) {
            // Sem cadeiras disponíveis, o cliente vai embora
            if (listaClientes.size() == numeroCadeiras) {
                System.out.println("Nenhuma cadeira disponível.");
                System.out.println("O cliente " + cliente.getClienteId()
                        + " deixou a barbearia.");
            } else if (barbeirosDisponiveis > 0) {
                // Se barbeiro disponível, o cliente é atendido
                ((LinkedList<Cliente>) listaClientes).offer(cliente);
                listaClientes.notify();
            } else {
                // Se barbeiro ocupado, o cliente senta-se na sala de espera
                ((LinkedList<Cliente>) listaClientes).offer(cliente);

                System.out.println("Todos os barbeiros estão ocupados."
                        + " O cliente " + cliente.getClienteId()
                        + " sentou-se na sala de espera.");

                // Notifica o barbeiro para verificar a fila
                if (listaClientes.size() == 1) {
                    listaClientes.notify();
                }
            }
        }
    }
}
