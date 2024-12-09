
package br.edu.ifpb.barbeiro;

import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        int numCadeiras = 5;  // Número de cadeiras na sala de espera
        Barbeiro barbeiro = new Barbeiro(numCadeiras);

        // Cria e inicia o barbeiro em uma thread
        Thread barbeiroThread = new Thread(new Barbearia(barbeiro));
        barbeiroThread.start();

        // Simula a chegada de clientes
        for (int i = 1; i <= 10; i++) {
            final int clienteId = i;

            // Cria e inicia o cliente em uma nova thread
            Thread clienteThread = new Thread(() -> {
                System.out.println();
                System.out.println("Cliente " + clienteId + " chegou na barbearia.");
                barbeiro.chegarCliente();
                System.out.println();
                System.out.println("Cliente " + clienteId + " foi atendido pelo barbeiro.");
            });

            clienteThread.start();

            try {
                // Simula a chegada de clientes a cada 1 segundo
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Erro ao aguardar a chegada do cliente: " + e);
            }
        }
    }
}
