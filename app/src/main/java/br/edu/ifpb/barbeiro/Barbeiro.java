package br.edu.ifpb.barbeiro;

import java.util.concurrent.Semaphore;

public class Barbeiro {
    // Semáforos
    private final Semaphore semaforoBarbeiro;   // Controla se o barbeiro está dormindo ou trabalhando
    private final Semaphore semaforoSalaEspera;  // Controla as cadeiras na sala de espera
    private final Semaphore semaforoCliente;    // Controla o cliente acordando o barbeiro

    public Barbeiro(int numCadeiras) {
        this.semaforoBarbeiro = new Semaphore(0); // barbeiro começa dormindo
        this.semaforoSalaEspera = new Semaphore(numCadeiras); // salas disponíveis
        this.semaforoCliente = new Semaphore(0);
    }

    // Método para o barbeiro cortar cabelo
    public void cortarCabelo() {
        try {
            // O barbeiro espera até que um cliente o acorde
            semaforoCliente.acquire();

            System.out.println(Thread.currentThread().getName() + " está cortando o cabelo.");

            // Simula o tempo de corte de cabelo (2 segundos)
            Thread.sleep(2000);

            System.out.println(Thread.currentThread().getName() + " terminou de cortar o cabelo.");

            // O barbeiro termina e libera a cadeira na sala de espera
            semaforoSalaEspera.release();
        } catch (InterruptedException e) {
			System.out.println("Erro: " + e);
        }
    }

    // Método para o cliente chegar na barbearia
    public void chegarCliente() {
        try {
            // O cliente tenta entrar na sala de espera
            if (semaforoSalaEspera.tryAcquire()) {
                System.out.println(Thread.currentThread().getName() + " entrou na sala de espera.");

                // O cliente acorda o barbeiro
                semaforoCliente.release();
                // O cliente espera o barbeiro começar o corte
                semaforoBarbeiro.acquire();
            } else {
                // Se não há cadeira, o cliente vai embora
                System.out.println(Thread.currentThread().getName() + " foi embora, sem lugar para esperar.");
            }
        } catch (InterruptedException e) {
			System.out.println("Erro: " + e);
        }
    }

    // Getter para o semáforo cliente
    public Semaphore getSemaforoCliente() {
        return semaforoCliente;
    }
}
