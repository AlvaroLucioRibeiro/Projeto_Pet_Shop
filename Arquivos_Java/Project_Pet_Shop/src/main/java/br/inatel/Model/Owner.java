package br.inatel.Model;

import java.util.*;
/**
 * @author Alvaro Ribeiro, Laura Pivoto
 * @since 05/11/2022
 * Classe Dono onde guardará os valores do objeto Dono
 * onde ficará guardado em um ArrayList os pets do Dono
 */

public class Owner {
    private String name;
    private String number;
    private String address;
    private String payment;
    private int idPetShop;
    private static int id = 0;

    // Arraylist no qual será utilizado para guardar os pets do Dono
    public List<Animal> lista = new ArrayList<>();

    /**
     * Este construtor é mandatório para se receber os parâmetros do Dono
     * @param id Id auto-incremental
     * @param name Entrar com o nome do Dono
     * @param number Entrar com o telefone do Dono
     * @param address Entrar com o endereço do Dono
     * @param payment Entrar com o método de pagamento do Dono
     * @param idPetShop Recebe o id do Pet-Shop
     */
    public Owner(int id, String name, String number, String address, String payment, int idPetShop) {
        id++;
        this.name = name;
        this.number = number;
        this.address = address;
        this.payment = payment;
        this.idPetShop = idPetShop;
        this.id = id;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }
    public String getAddress() {
        return address;
    }
    public String getPayment() {
        return payment;
    }
    public static int getId() {
        return id;
    }

}
