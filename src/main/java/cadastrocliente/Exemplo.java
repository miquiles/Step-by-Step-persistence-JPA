package cadastrocliente;

import model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Exemplo {

    public static void main(String[] args) {

        // instanciando uma fabrica.
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Clientes-PU");

        // gerente de entidades.
        EntityManager entityManager = entityManagerFactory.createEntityManager();



        /*Buscando no banco pelo id.
        Cliente cliente = entityManager.find(Cliente.class, 1);
        System.out.println(cliente.getId());
        System.out.println(cliente.getNome());
        */



        /* Inserindo dados
        Cliente cliente = new Cliente();
        cliente.setNome("TV Amadora");
        entityManager.getTransaction().begin(); // transação para testar a operação.
        entityManager.persist(cliente); // persistir os dados no banco
        entityManager.getTransaction().commit();
        */




        /* Remover registros
        Cliente cliente = entityManager.find(Cliente.class, 1);
        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
        */



        /*Atualizar registros
        Cliente cliente = entityManager.find(Cliente.class,8);
        entityManager.getTransaction().begin();
        cliente.setNome("TV Brasil");
        entityManager.getTransaction().commit();
        */




        /* Usar merge para gerenciar os registros.

        Cliente cliente = new Cliente();
        cliente.setId(1); //precisa retirar o generatetype em exemplo.
        cliente.setNome("Tv Cultura");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
        */


        //Vão nascer e vão ser fechadas no final do metodo Main.
         entityManager.close();
         entityManagerFactory.close();





    }
}
