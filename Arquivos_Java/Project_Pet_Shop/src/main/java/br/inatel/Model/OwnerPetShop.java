package br.inatel.Model;
/**
 * @author Alvaro Ribeiro, Laura Pivoto
 * @since 05/11/2022
 * Classe Dono do Pet-Shop onde guardará os valores do objeto Dono do Pet-Shop
 */

public class OwnerPetShop {

    private String Name;
    private int idPetShop;

    /**
     * Construtor mandatório para receber os parâmetros do Dono do Pet-Shop
     * @param name Entrar com o nome do Dono do Pet-Shop
     * @param idPetShop Entrar com o id do Pet-Shop
     */
    public OwnerPetShop(String name, int idPetShop) {
        Name = name;
        this.idPetShop = idPetShop;
    }

    // Getters
    public String getName() {
        return Name;
    }
    public int getIdPetShop() {
        return idPetShop;
    }
}
