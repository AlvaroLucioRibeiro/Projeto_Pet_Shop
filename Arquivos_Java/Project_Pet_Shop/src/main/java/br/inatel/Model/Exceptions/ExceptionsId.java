package br.inatel.Model.Exceptions;

/**
 * @author Alvaro Ribeiro, Laura Pivoto
 * @since 25/11/2022
 * Classe ExceptionsId que é chamada quando o usuário entra com algum dado incorreto,
 * ou seja, essa classe é chamada quando o usuário entra com letras envés de número...
 */
public class ExceptionsId  extends Exception {
    public ExceptionsId () {
        System.err.print("** Entre somente com números **");
        System.out.println("\n");
        System.out.print("(1 - Aukimia), (2 - Cats&Dogs) ou (3 - Pets&Cia) ");
    }
}