package br.inatel;

import java.util.*;
import java.util.regex.*;

import br.inatel.Model.*;
import br.inatel.Model.Exceptions.*;
import br.inatel.DAO.UserDAO;

/**
 * @author Alvaro Ribeiro, Laura Pivoto
 * @since 05/11/2022
 * Classe Principal para desenvolvimento
 */

public class Main {
    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        // Scanner para entradas
        Scanner input = new Scanner(System.in);
        // Variáveis auxiliares - Dono
        String name;
        String end = null;
        String payment;
        String toTake;
        String number;
        float StatusPayment = 0;

        // Variáveis auxiliares - Pet
        String petName;
        String petBreed;
        String petColor;
        String petSex;
        String petCategory = null;
        String petNumber = "Sim";
        int petAge;
        int petShopId = 0;
        float petWeight;

        // Variável auxiliar para escolha de banho ou tosa
        String aux;

        //Variável auxiliar para criação do pet
        Animal petok = null;

        /*-------------------------------------------DADOS DO PET-SHOP------------------------------------------------*/
        /**
         * Criando os Pet-Shops fixos
         * Entrada com o nome, cidade, endereço e telefone
         */
        Petshop auxP = new Petshop("aux", "aux", "aux", "aux");

        Petshop P1 = new Petshop("Aukimia", "Pouso Alegre", "Rua das flores, 100, Bairro Primavera",
                "(35) 3422-1580");
        Petshop P2 = new Petshop("Cats&Dogs", "Santa Rita do Sapucaí", "Rua Americana, 65," +
                " Bairro Centro",
                "(35) 3488-1177");
        Petshop P3 = new Petshop("Pets&Cia", "Santa Rita do Sapucaí", "Rua Alvorada, 76," +
                " Bairro Nova Cidade",
                "(35) 99988-7667");


        dao.insertPetShop(auxP);
        dao.insertPetShop(P1);
        OwnerPetShop laura = new OwnerPetShop("Laura Pivoto", 1);
        dao.insertOwnerPT(laura);
        dao.insertPetShop(P2);
        OwnerPetShop alvaro = new OwnerPetShop("Alvaro Lúcio", 2);
        dao.insertOwnerPT(alvaro);
        dao.insertPetShop(P3);
        OwnerPetShop renzo = new OwnerPetShop("RenZo Kuken", 3);
        dao.insertOwnerPT(renzo);
        /*-------------------------------------------DADOS DO DONO----------------------------------------------------*/

        /**
         * Entrada de Dados:
         * 1º Entrada com Nome, que deverá obedecer uma ordem de mais de 3 letras e somente letras
         * 2º Entre com o número de Telefone, que deverá obedecer ao padrão DDD9Número
         * 3º Entre com o pagamento, que deverá ser débito, crédito ou dinheiro
         *      Cada método de pagamento terá um desconto no final, débito 5%, dinheiro 10% e crédito valor integral
         * 4º Entre se precisa buscar, se precisar buscar será cobrada uma taxa de 15 reais
         *      Se sim, deverá entrar com o endereço. Se não, o sistema avança para próxima etapa que será cadastro do
         *      pet
         */

        System.out.println("---------------------------------------------");
        System.out.println("ENTRE COM OS SEUS DADOS");

        // Entrar com o nome
        // While caso o nome seja menor que 3 ou caso entre com número, ambas entradas serão consideradas inválidas
        System.out.print("Entre com seu nome: ");
        name = String.valueOf(input.nextLine());
        Pattern namePattern = Pattern.compile("[A-Za-z]{3,}");
        Matcher matchName = namePattern.matcher(name);
        boolean matchFound = matchName.find();
        while (!matchFound) {
            try {
                System.err.print("Utilize somente letras e maior que 3! Entre novamente com seu nome: ");
                name = String.valueOf(input.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("Não foi possível entender seu nome");
            }
            namePattern = Pattern.compile("[A-Za-z]{3,}");
            matchName = namePattern.matcher(name);
            matchFound = matchName.find();
        }


        // Entrar com o número de telefone
        // Seguindo o padrão DDD9Número, caso não obedeça ao padrão entra no while
        System.out.print("Entre com o número de telefone: ");
        number = String.valueOf(input.nextLine());
        Pattern numberPattern = Pattern.compile("^([0-9]{2}9[0-9]{8})$");
        Matcher numberMatcher = numberPattern.matcher(number);
        boolean numberFound = numberMatcher.find();
        while (!numberFound) {
            try {
                System.out.print("Entre com 11 Dígitos (DDD)9 + Número: ");
                number = String.valueOf(input.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("Erro, numero inválido");
            } catch (NumberFormatException e) {
                System.out.println("Entre somente com números!");
            }
            numberPattern = Pattern.compile("^([0-9]{2}9[0-9]{8})$");
            numberMatcher = numberPattern.matcher(number);
            numberFound = numberMatcher.find();
        }

        // Entrar com o método de pagamento
        // Entradas permitidas somente dinheiro, crédito e débito, caso contrário entra no while até uma opção válida
        // ser selecionada pelo cliente
        System.out.print("Qual o método de pagamento (débito, crédito ou dinheiro): ");
        payment = input.nextLine();
        while (!payment.equalsIgnoreCase("dinheiro") & !payment.equalsIgnoreCase("Dinheiro") &
                !payment.equalsIgnoreCase("Débito") & !payment.equalsIgnoreCase("débito") &
                !payment.equalsIgnoreCase("Crédito") & !payment.equalsIgnoreCase("crédito")) {
            System.out.print("Entre com o pagamento (débito, crédito ou dinheiro): ");
            payment = input.nextLine();
        }

        // Pergunta se precisar buscar, caso seja sim, entra no if e pergunta endereço, caso seja não só avança
        // Caso nenhuma das opções seja selecionada, entrará no while para (selecione) uma opção válida
        System.out.print("Precisa que busque? ");
        toTake = input.nextLine();
        while (!toTake.equalsIgnoreCase("sim") & !toTake.equalsIgnoreCase("Sim") &
                !toTake.equalsIgnoreCase("não") & !toTake.equalsIgnoreCase("não")) {
            System.out.print("Digite sim ou não ");
            toTake = input.nextLine();
        }

        Owner d1 = new Owner(Owner.getId(), name, number, "não especificado", payment, 1);

        if (toTake.equals("sim") || toTake.equals("Sim")) {
            System.out.print("Entre com o Endereço: ");
            end = input.nextLine();
            d1 = new Owner(Owner.getId(), name, number, end, payment, 1);
            StatusPayment += 15;
            dao.insertOwnerPet(d1);
        } else {
            dao.insertOwnerPet(d1);
        }

        /*-----------------------------------------DADOS DO ANIMAL----------------------------------------------------*/
        /**
         * Entrada de Dados do Pet:
         * 1º Entre com a categoria: cachorro ou Gato... Qualquer outra categoria não será aceito
         * 2º Entre com o nome
         * 3º Entre com a raça
         * 4º Entre com a cor
         * 5º Entre com o sexo: Macho ou Fêmea
         * 6º Entre com a idade: Até 25 anos
         * 7º Entre com o Peso
         */

        // While iniciado em sim para dar início ao loop
        while (petNumber.equals("Sim") || (petNumber.equals("sim"))) {
            System.out.println("---------------------------------------------");
            System.out.println("ENTRE COM OS DADOS DO SEU PET");

            // Entrada da categoria do Pet
            System.out.print("Seu pet é um Cachorro ou um Gato? ");
            petCategory = input.nextLine();
            while (!petCategory.equalsIgnoreCase("Cachorro") & !petCategory.equalsIgnoreCase("cachorro") &
                    !petCategory.equalsIgnoreCase("Gato") & !petCategory.equalsIgnoreCase("gato")) {
                System.out.print("Não atendemos esse tipo de Pet... Entre com Cachorro ou Gato: ");
                petCategory = input.nextLine();
            }

            // Entre com o nome do seu pet, o nome deve ter mais de 3 letras
            System.out.print("Entre com o nome do seu Pet: ");
            petName = input.nextLine();
            Pattern namePetPattern = Pattern.compile("[A-Za-z]{3,}");
            Matcher matchNamePet = namePetPattern.matcher(petName);
            boolean matchFoundPet = matchNamePet.find();
            while (!matchFoundPet) {
                try {
                    System.err.print("Utilize somente letras e maior que 3! Entre novamente com nome do seu Pet: ");
                    petName = input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Não foi possível entender o nome do seu pet");
                }
                namePetPattern = Pattern.compile("[A-Za-z]{3,}");
                matchNamePet = namePetPattern.matcher(petName);
                matchFoundPet = matchNamePet.find();
            }

            // Entre com a raça do seu pet, a raça deve ter mais de 3 letras possíveis
            System.out.print("Entre com a raça do seu Pet: ");
            petBreed = input.nextLine();
            Pattern petBreedPattern = Pattern.compile("[A-Za-z]{3,}");
            Matcher matchBreed = petBreedPattern.matcher(petBreed);
            boolean matchFoundBreed = matchBreed.find();
            while (!matchFoundBreed) {
                try {
                    System.err.print("Utilize somente letras e maior que 3! Entre novamente com a raça do seu Pet: ");
                    petBreed = input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Não foi possível entender a raça do seu pet");
                }
                petBreedPattern = Pattern.compile("[A-Za-z]{3,}");
                matchBreed = petBreedPattern.matcher(petName);
                matchFoundBreed = matchBreed.find();
            }

            // Entre com a cor do seu pet, a cor deve possuir mais de 3 letras
            System.out.print("Entre com a cor do seu Pet: ");
            petColor = input.nextLine();
            Pattern petColorPattern = Pattern.compile("[A-Za-z]{3,}");
            Matcher matchColor = petColorPattern.matcher(petColor);
            boolean matchFoundColor = matchColor.find();
            while (!matchFoundColor) {
                try {
                    System.err.print("Utilize somente letras e maior que 3! Entre novamente com a cor do seu Pet: ");
                    petColor = input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Não foi possível entender a cor do seu pet");
                }
                petColorPattern = Pattern.compile("[A-Za-z]{3,}");
                matchColor = petColorPattern.matcher(petColor);
                matchFoundColor = matchColor.find();
            }

            // Entrando com o sexo do Pet (Aceito somente Macho ou Fêmea, do contrário entra no loop)
            System.out.print("Entre com o sexo do seu Pet: ");
            petSex = input.nextLine();
            while (!petSex.equalsIgnoreCase("Macho") & !petSex.equalsIgnoreCase("Fêmea") &
                    !petSex.equalsIgnoreCase("macho") & !petSex.equalsIgnoreCase("fêmea")) {
                System.out.print("Entre com Macho ou Fêmea: ");
                petSex = input.nextLine();
            }

            // Entrando com o peso do pet (Aceito somente números diferente de 0, do contrário entra no loop)
            System.out.print("Entre com o peso do seu Pet: ");
            petWeight = 0;
            while (petWeight == 0) {
                try {
                    petWeight = Float.parseFloat(input.nextLine());
                } catch (NumberFormatException e) {
                    new ExceptionsWeight();
                }
            }

            petAge = 0;
            // Entrando com a idade do pet (Aceito somente números inteiros diferente de zero e menor que 26 (idade do
            // animal mais velho ja registrado), do contrário entra no loop)
            System.out.print("Entre com a idade do seu Pet: ");
            while (petAge == 0 || petAge > 25) {
                try {
                    petAge = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    new ExceptionsAge();
                }
            }

            // Sim, para novo cadastro, não para finalizar e avançar
            System.out.print("Deseja cadastrar um novo pet? ");
            petNumber = input.nextLine();

            // Criando objeto animal e adicionando ele no arraylist do dono
            petok = new Animal(petCategory, petName, petAge, petBreed, petColor, petSex, petWeight, 0);
            //inserindo na tabela de pets
            dao.insertPet1(petok);
            d1.lista.add(petok);

            //inserindo na tabela de relacionamento m-n
            dao.insertHave();

        }
        /*--------------------------------------ESCOLHENDO O PLANO E PET-SHOP-----------------------------------------*/
        /**
         * Seleciona o Pet-Shop que deseja, sendo as opções:
         * 1º Aukimia, 2º Cats&Dogs, 3º Pets&Cia e qualquer outra entrada inválida
         * Após a entrada escolher entre Tosa, Banho ou Vacina, qualquer outra opção será desconsiderada
         * Após a seleção entrará o processo escolhido, assim que ficar pronto printará a mensagem
         * e sistema será encerrado e apareça o valor total que será pago
         * A Tosa custará R$55,00; O banho custará R$45,00; A Vacina custará R$60,00.
         */

        System.out.println("---------------------------------------------");
        System.out.println("Qual Pet-Shop você quer levar? ");
        System.out.print("(1 - Aukimia), (2 - Cats&Dogs) ou (3 - Pets&Cia) ");

        // entrada somente com números, do contrário entra no loop
        while (petShopId == 0) {
            try {
                petShopId = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                new ExceptionsId();
            }
        }
        while (petShopId != 0) {
            switch (petShopId) {
                // Após escolha do Pet-Shop entra no case de cada um com as opções: Vacina, tosa ou banho...
                case 1:
                    System.out.println("---------------------------------------------");
                    System.out.println("SEJA BEM VINDO A AUKIMIA");

                    //tirando o dono e os pets do pet-shop aux
                    dao.updateOwner(d1, 2);
                    for (int i = 0; i < d1.lista.size(); i++) {
                        dao.updatePets(d1.lista.get(i), 2);
                    }
                    //Atendimento no pet-shop
                    for (int i = 0; i < d1.lista.size(); i++) {
                        System.out.println("O " + d1.lista.get(i).getName() + " vai ser atendido");
                        System.out.print("Deseja Banho, Tosa ou Vacina? ");
                        aux = input.nextLine();
                        // Entra no loop caso não seja Banho, Tosa ou Vacina
                        while (!aux.equalsIgnoreCase("vacina") & !aux.equalsIgnoreCase("Vacina") &
                                !aux.equalsIgnoreCase("banho") & !aux.equalsIgnoreCase("Banho") &
                                !aux.equalsIgnoreCase("tosa") & !aux.equalsIgnoreCase("Tosa")) {
                            System.out.println("Não possuimos este serviço...");
                            System.out.print("Deseja Banho, Tosa ou Vacina? ");
                            aux = input.nextLine();
                        }
                        if (aux.equals("Banho") || aux.equals("banho")) {
                            dao.updatePetStatus(d1.lista.get(i));
                            P1.shower();
                            StatusPayment += 45.00F;
                        } else if (aux.equals("Tosa") || aux.equals("tosa")) {
                            P1.clipping();
                            dao.updatePetStatus(d1.lista.get(i));
                            StatusPayment += 55.00F;
                        } else if (aux.equals("Vacina") || aux.equals("vacina")) {
                            if (petCategory.equals("cachorro") || petCategory.equals("Cachorro")) {
                                System.out.println("AAAAAAAAAAAUUUUUUUUUUUUUUUU");
                            } else if (petCategory.equals("gato") || petCategory.equals("Gato")) {
                                System.out.println("MIAUUUUUUUUUUUUUUUUUUUUUUU");
                            }
                            P1.vacine();
                            dao.updatePetStatus(d1.lista.get(i));
                            StatusPayment += 60.00F;
                        }
                    }
                    break;
                case 2:
                    System.out.println("---------------------------------------------");
                    System.out.println("SEJA BEM VINDO A CATS&DOGS");

                    dao.updateOwner(d1, 3);
                    for (int i = 0; i < d1.lista.size(); i++) {
                        dao.updatePets(d1.lista.get(i), 3);
                    }

                    for (int i = 0; i < d1.lista.size(); i++) {
                        System.out.println("O " + d1.lista.get(i).getName() + " vai ser atendido");
                        System.out.print("Deseja Banho, Tosa ou Vacina? ");
                        aux = input.nextLine();
                        // Entra no loop caso não seja Banho, Tosa ou Vacina
                        while (!aux.equalsIgnoreCase("vacina") & !aux.equalsIgnoreCase("Vacina") &
                                !aux.equalsIgnoreCase("banho") & !aux.equalsIgnoreCase("Banho") &
                                !aux.equalsIgnoreCase("tosa") & !aux.equalsIgnoreCase("Tosa")) {
                            System.out.println("Não possuimos este serviço...");
                            System.out.print("Deseja Banho, Tosa ou Vacina? ");
                            aux = input.nextLine();
                        }
                        if (aux.equals("Banho") || aux.equals("banho")) {
                            dao.updatePetStatus(d1.lista.get(i));
                            P2.shower();
                            StatusPayment += 45.00F;
                        } else if (aux.equals("Tosa") || aux.equals("tosa")) {
                            dao.updatePetStatus(d1.lista.get(i));
                            P2.clipping();
                            StatusPayment += 55.00F;
                        } else if (aux.equals("Vacina") || aux.equals("vacina")) {
                            if (petCategory.equals("cachorro") || petCategory.equals("Cachorro")) {
                                System.out.println("AAAAAAAAAAAUUUUUUUUUUUUUUUU");
                            } else if (petCategory.equals("gato") || petCategory.equals("Gato")) {
                                System.out.println("MIAUUUUUUUUUUUUUUUUUUUUUUU");
                            }
                            P2.vacine();
                            dao.updatePetStatus(d1.lista.get(i));
                            StatusPayment += 60.00F;
                        }
                    }
                    break;
                case 3:
                    System.out.println("---------------------------------------------");
                    System.out.println("SEJA BEM VINDO A PETS&CIA");

                    dao.updateOwner(d1, 4);
                    for (int i = 0; i < d1.lista.size(); i++) {
                        dao.updatePets(d1.lista.get(i), 4);
                    }

                    for (int i = 0; i < d1.lista.size(); i++) {
                        System.out.println("O " + d1.lista.get(i).getName() + " vai ser atendido");
                        System.out.print("Deseja Banho, Tosa ou Vacina? ");
                        aux = input.next();
                        // Entra no loop caso não seja Banho, Tosa ou Vacina
                        while (!aux.equalsIgnoreCase("vacina") & !aux.equalsIgnoreCase("Vacina") &
                                !aux.equalsIgnoreCase("banho") & !aux.equalsIgnoreCase("Banho") &
                                !aux.equalsIgnoreCase("tosa") & !aux.equalsIgnoreCase("Tosa")) {
                            System.out.println("Não possuimos este serviço...");
                            System.out.print("Deseja Banho, Tosa ou Vacina? ");
                            aux = input.nextLine();
                        }
                        if (aux.equals("Banho") || aux.equals("banho")) {
                            dao.updatePetStatus(d1.lista.get(i));
                            P3.shower();
                            StatusPayment += 45.00F;
                        } else if (aux.equals("Tosa") || aux.equals("tosa")) {
                            P3.clipping();
                            dao.updatePetStatus(d1.lista.get(i));
                            StatusPayment += 55.00F;
                        } else if (aux.equals("Vacina") || aux.equals("vacina")) {
                            if (petCategory.equals("cachorro") || petCategory.equals("Cachorro")) {
                                System.out.println("AAAAAAAAAAAUUUUUUUUUUUUUUUU");
                            } else if (petCategory.equals("gato") || petCategory.equals("Gato")) {
                                System.out.println("MIAUUUUUUUUUUUUUUUUUUUUUUU");
                            }
                            P3.vacine();
                            dao.updatePetStatus(d1.lista.get(i));
                            StatusPayment += 60.00F;
                        }
                    }
                    break;
                default:
                    System.out.println("Entrada invalida");
            }
            break;
        }
        /*-----------------------------FINALIZANDO O SISTEMA E GERANDO PAGAMENTO--------------------------------------*/
        /**
         * Após finalizar o atendimento, será encerrado o sistema e será emitido o total a ser pago
         * Se o método de pagamento for dinheiro tem 10% de desconto, se for débito 5% de desconto
         * e se for crédito é realizado o pagamento integral...
         * Após isso o programa se encerrará
         */
        // Encerrando o sistema e Gerando o pagamento com o total de desconto de cada método de pagamento
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Sistema encerrado com sucesso");
        System.out.println("O total da sua compra foi: ");
        if (payment.equals("Dinheiro") || payment.equals("dinheiro")) {
            StatusPayment = (StatusPayment * 9.0F) / 10.0F;
            System.out.print("O total foi de: R$" + StatusPayment + " com 10% de desconto");
            if (toTake.equals("sim") || toTake.equals("Sim")) {
                System.out.println(" incluso a taxa de buscar o pet...");
            }
        } else if (payment.equals("Débito") || payment.equals("débito")) {
            StatusPayment = (StatusPayment * 95) / 100;
            System.out.print("O total foi de: R$" + StatusPayment + " com 5% de desconto");
            if (toTake.equals("sim") || toTake.equals("Sim")) {
                System.out.println(" incluso a taxa de buscar o pet...");
            }
        } else if (payment.equals("Crédito") || payment.equals("crédito")) {
            System.out.print("O total foi de: R$" + StatusPayment);
            if (toTake.equals("sim") || toTake.equals("Sim")) {
                System.out.println(" incluso a taxa de buscar o pet...");
            }
        }
        dao.selectPets();
        dao.deletePet();
        dao.deleteOwner();
    }
}