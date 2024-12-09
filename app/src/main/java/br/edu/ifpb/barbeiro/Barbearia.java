package br.edu.ifpb.barbeiro;

public class Barbearia implements Runnable {
    private final Barbeiro barbeiro;

    public Barbearia(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // O barbeiro espera que um cliente o acorde
                barbeiro.getSemaforoCliente().acquire();
                barbeiro.cortarCabelo();
            } catch (InterruptedException e) {
                System.out.println("Erro: " + e);
            }
        }
    }
}
