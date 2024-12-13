package br.edu.ifpb.barbeiro;

/**
 * A classe App é a classe principal que inicia a simulação de uma barbearia.
 * Ela configura o número de barbeiros e cadeiras, cria uma instância da barbearia,
 * inicializa os barbeiros e gera clientes aleatórios que chegam para cortar o cabelo.
 *
 * A classe simula a interação entre os barbeiros e os clientes na barbearia.
 * Cada barbeiro trabalha como uma thread independente e a geração de clientes também ocorre em paralelo.
 *
 * O número de barbeiros e cadeiras pode ser facilmente configurado, e os clientes chegam de forma
 * aleatória para sentar nas cadeiras e esperar para serem atendidos pelos barbeiros.
 *
 * @author ViniciusCavalcantePequeno
 */
public class App {
    public static void main(String[] args) {
        int numeroBarbeiros = 1;
        int numeroCadeiras = 5;

        Barbearia barbearia = new Barbearia(numeroBarbeiros, numeroCadeiras);

        // Cria e inicializa os barbeiros, cada barbeiro é uma thread
        for (int i = 1; i <= numeroBarbeiros; i++) {
            // Cria uma instância de Barbeiro para cada barbeiro configurado
            Barbeiro barbeiro = new Barbeiro(barbearia, i);
            // Inicia a execução do barbeiro como uma thread
            barbeiro.start(); // automaticamente realiza o run() do barbeiro
        }

        // Cria e inicializa a geração de clientes, que será executada como uma thread
        GeradorCliente clientes = new GeradorCliente(barbearia);
        // Inicia a execução do gerador de clientes
        clientes.start(); // automaticamente realiza o run() do gerador de clientes
    }
}