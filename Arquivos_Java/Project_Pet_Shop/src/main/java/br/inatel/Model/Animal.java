package br.inatel.Model;

/**
 * @author Alvaro Ribeiro, Laura Pivoto
 * @since 05/11/2022
 * Classe Animal é uma classe mãe para as Classes
 * Gato e Cachorro, armazena os valores padrões
 */

public class Animal {

    private String category;
    private String name;
    private int age;
    private String breed;
    private String color;
    private String sex;
    private int status;
    private float weight;
    private static int id = 0;

    /**
     * Este construtor é mandatório para se receber os parâmetros do Animal
     * Utilizado com veemência nas classes Gato e Cachorro
     * @param category Entrar com categoria do Animal
     * @param name Entrar com o nome do Animal
     * @param age Entrar com a idade do Animal
     * @param breed Entrar com a raça do Animal
     * @param color Entrar com a cor do Animal
     * @param sex Entrar com o sexo do Animal
     * @param weight Entrar com o peso do Animal
     */
    public Animal(String category, String name, int age, String breed, String color, String sex, float weight, int status) {
        id++;
        this.category = category;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.color = color;
        this.sex = sex;
        this.weight = weight;
        this.status = status;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }
    public int getAge() {return age; }
    public String getCategory() {
        return category;
    }
    public String getBreed() {
        return breed;
    }
    public String getColor() {
        return color;
    }
    public String getSex() {
        return sex;
    }
    public float getWeight() {
        return weight;
    }
    public static int getId() {
        return id;
    }
}