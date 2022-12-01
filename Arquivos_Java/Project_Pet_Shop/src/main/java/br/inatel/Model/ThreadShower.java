package br.inatel.Model;

import java.util.concurrent.TimeUnit;
/**
 * @author Alvaro Ribeiro, Laura Pivoto
 * @since 10/11/2022
 * Classe ThreadShower onde será realizada a contagem do início até
 * o final do banho e alertará que o Pet está pronto
 */
public class ThreadShower extends Thread {
    // Função run que irá fazer a atualização constante de quantos minutos falta para o banho ficar pronto
    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.println("O banho ficará pronto em: " + i + " minutos...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("O banho está pronto");
    }
}