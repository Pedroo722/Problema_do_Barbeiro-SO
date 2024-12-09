package br.edu.ifpb.barbeiro;

public class Cliente implements Runnable {
    private final Barbeiro barbeiro;

    public Cliente(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    @Override
    public void run() {
        barbeiro.chegarCliente();
    }
}
