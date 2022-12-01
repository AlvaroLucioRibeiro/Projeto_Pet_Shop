package br.inatel.Model;

/**
 * @author Alvaro Ribeiro, Laura Pivoto
 * @since 05/11/2022
 * Classe Pet-Shop onde ficará armazenado os dados do mesmo
 * Guarda as funções de Banho, Tosa ou Vacina
 */

public class Petshop {
    private String name;
    private String cityName;
    private String address;
    private String number;
    private static long chegadaGenerate;
    private long chegada;

    /**
     * Este construtor é mandatório para se receber os parâmetros do Pet-Shop de forma fixa
     * @param name Entrar com o nome do Pet-Shop
     * @param cityName Entrar com a cidade que se encontra o Pet-Shop
     * @param address Entrar com o endereço que se encontra o Pet-Shop
     * @param number Entrar com o telefone do Pet-Shop
     */
    public Petshop(String name, String cityName, String address, String number) {
        this.name = name;
        this.cityName = cityName;
        this.address = address;
        this.number = number;
        // Gerando ordem de chegada
        this.chegadaGenerate++;
        this.chegada = chegadaGenerate;
    }

    /**
     * Declarando as classes de Thread de Banho e Tosa
     * Thread de Banho chamada TS
     * Thread de Tosa chamada TC
     */
    ThreadShower TS = new ThreadShower();
    ThreadClipping TC = new ThreadClipping();


    // Função void que irá chamar a ThreadClipping
    public void clipping() {
        TC.run();
    }

    // Função void que irá chamar a ThreadShower
    public void shower() {
        TS.run();
    }

    // Função void que apresenta a frase de vacina aplicada
    public void vacine() {
        System.out.println("Vacina aplicada com sucesso!");
    }

    public String getName() {
        return name;
    }
    public String getCityName() {
        return cityName;
    }
    public String getAddress() {
        return address;
    }
    public String getNumber() {
        return number;
    }
}
