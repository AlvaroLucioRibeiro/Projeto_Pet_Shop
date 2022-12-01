package br.inatel.Model.Exceptions;

/**
 * @author Alvaro Ribeiro, Laura Pivoto
 * @since 25/11/2022
 * Classe ExceptionsAge que é chamada quando o usuário entra com algum dado incorreto,
 * ou seja, essa classe é chamada quando o usuário entra com letras envés de números...
 */
public class ExceptionsAge extends Exception {
    public ExceptionsAge() {
        System.err.print("** Entre somente com números **");
        System.out.println("\n");
        System.out.print("Entre com a idade do seu Pet: ");
    }
}

