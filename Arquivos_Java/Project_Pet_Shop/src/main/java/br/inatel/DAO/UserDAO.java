package br.inatel.DAO;

import br.inatel.Model.Animal;
import br.inatel.Model.Owner;
import br.inatel.Model.OwnerPetShop;
import br.inatel.Model.Petshop;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou


    //INSERT
    public void insertPetShop(Petshop petshop) {

        connectToDB();

        String sql = "INSERT INTO `Project_PetShop`.`PetShop` (Name, Address, City_Name, Number) values(?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, petshop.getName());
            pst.setString(2, petshop.getAddress());
            pst.setString(3, petshop.getCityName());
            pst.setString(4, petshop.getNumber());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
    }

    public void insertOwnerPT(OwnerPetShop ownerPT) {

        connectToDB();

        String sql = "INSERT INTO `Project_PetShop`.`OwnerPetShop` (NameOP, PetShop_idPetShop) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, ownerPT.getName());
            pst.setInt(2, ownerPT.getIdPetShop());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
    }

    public void insertOwnerPet(Owner owner) {

        connectToDB();

        String sql = "INSERT INTO `Project_PetShop`.`OwnerPet` (OwnerId, Name, Number, Address, Payment, PetShop_idPetShop) values(?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, Owner.getId());
            pst.setString(2, owner.getName());
            pst.setString(3, owner.getNumber());
            pst.setString(4, owner.getAddress());
            pst.setString(5, owner.getPayment());
            pst.setInt(6, 1);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
    }

    //esse pet é para o petshop1
    public boolean insertPet1(Animal animal) {

        connectToDB();

        String sql = "INSERT INTO `Project_PetShop`.`Animal` (idAnimal, Name, Age, Breed, Color, Sex, Animal_idPetShop, Category, Status, Weight) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, Animal.getId());
            pst.setString(2, animal.getName());
            pst.setInt(3, animal.getAge());
            pst.setString(4, animal.getBreed());
            pst.setString(5, animal.getColor());
            pst.setString(6, animal.getSex());
            pst.setInt(7, 1);
            pst.setString(8, animal.getCategory());
            pst.setInt(9, 0);
            pst.setFloat(10, animal.getWeight());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //esse pet é para o petshop1
    public boolean insertHave() {

        connectToDB();

        String sql = "INSERT INTO `Project_PetShop`.`OwnerPet_has_Animal` (OwnerPet_OwnerId, Animal_idAnimal) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, Owner.getId());
            pst.setInt(2, Animal.getId());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //UPDATE
    public boolean updateOwner(Owner owner, int num) {
        connectToDB();

        String sql = "UPDATE `Project_PetShop`.`OwnerPet` SET PetShop_idPetShop = ? where Name = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(2, owner.getName());
            pst.setInt(1, num);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean updatePets(Animal animal, int num) {
        connectToDB();

        String sql = "UPDATE `Project_PetShop`.`Animal` SET Animal_idPetShop = ? where Name = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(2, animal.getName());
            pst.setInt(1, num);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean updatePetStatus(Animal animal) {
        connectToDB();

        String sql = "UPDATE `Project_PetShop`.`Animal` SET Status = ? where Status = ? ";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(2, 0);
            pst.setInt(1, 1);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //SELECT
    public ArrayList<Animal> selectPets() {
        ArrayList<Animal> animal = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM `Project_PetShop`.`Animal`";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de pets atendidos: ");

            while (rs.next()) {

                //String category, String name, int age, String breed, String color, String sex, float weight, int status

                Animal animalAux = new Animal(rs.getString("Category"),rs.getString("Name"), rs.getInt("Age"),
                        rs.getString("Breed"),rs.getString("Color"), rs.getString("Sex"),
                        rs.getFloat("Weight"), rs.getInt("Status")  );

                System.out.println("Nome = " + animalAux.getName());
                System.out.println("Categoria = " + animalAux.getCategory());
                System.out.println("Sexo = " + animalAux.getSex());
                System.out.println("Raça = " + animalAux.getBreed());
                System.out.println("Idade = " + animalAux.getAge());
                System.out.println("--------------------------------");

                animal.add(animalAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return animal;
    }

    //DELETE
    public boolean deletePet() {
        connectToDB();
        String sql = "DELETE FROM `Project_PetShop`.`Animal` where  Status = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, 1 );
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //DELETE
    public boolean deleteOwner() {
        connectToDB();
        String sql = "DELETE FROM `Project_PetShop`.`OwnerPet` where  OwnerId != ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, 0);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

}
