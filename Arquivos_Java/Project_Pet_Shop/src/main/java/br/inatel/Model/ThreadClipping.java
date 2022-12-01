package br.inatel.Model;

import java.util.concurrent.TimeUnit;

/**
 * @author Alvaro Ribeiro, Laura Pivoto
 * @since 10/11/2022
 * Classe ThreadClipping onde será realizada a contagem do início até
 * o final da tosa e alertará que o Pet está pronto
 */

public class ThreadClipping extends Thread {

    // Função run que irá fazer a atualização constante de quantos minutos falta para a tosa ficar pronta
    @Override
    public void run() {
        for (int i = 15; i > 0; i--) {
            System.out.println("A Tosa ficará pronto em: " + i + " minutos...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("A tosa está pronta");
    }
}