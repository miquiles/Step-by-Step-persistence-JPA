# PersistenceJPA
 
## JPA - Java Persistence API

Representar com classe JAVA as tabelas do banco de dados relacional (SQL, ORACLE,POSTGRE)



Anotações basicas de mapeamento:


@Entity

@Table

@Id

@Column

Criando um CRUD com JPA

1 - Crie um projeto Maven

2 - Add as dependencias no POM.XML 

<dependencies>

<dependency>
   		<groupId> org.hibernate</groupId>
  		 <artifactId>hibernate-core</artifactId>
  		 <version>5.4.2.Final</version>
</dependency>

   <dependency>
 		  <groupId> mysql</groupId>
 		  <artifactId>mysql-connector-java</artifactId>
   <version>8.0.16</version>
   </dependency>


</dependencies>

Configurando a conexão com o banco de dados:

1- No caminho src/main/resources crie uma pasta chamada META-INF, após crie um arquivo chamado persistence.xml.

<?xml version="1.0" encoding="UTF-8"?>
<persistence version="1.0" xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_1_0.xsd">


   <persistence-unit name="Clientes-PU" transaction-type="RESOURCE_LOCAL">
       <properties>

           <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" />
           <property name="hibernate.connection.show_sql" value="true" />
           <property name="hibernate.hbm2ddl.auto" value="update" />
           <property name="javax.persistence.jdbc.user" value="root"/>
           <property name="javax.persistence.jdbc.password" value="12345"/>
           <property name="hibernate.connection.url" value="jdbc:mysql://localhost:3306/cadastrocliente?useTimezone=true&amp;serverTimezone=UTC" />

       </properties>
   </persistence-unit>
</persistence>


















2 - Criar o banco de dados e mapeie.

	2.1 - Abra o shell e crie as tabelas do banco e insira os dados para teste
	
* create database cadastrocliente;

* use cadastrocliente;

* create table cliente (id bigint not null auto_increment, nome varchar(100) not null, primary key (id));

* insert into cliente (nome) values ('Tv teste');

 ![banco](https://github.com/miquiles/Step-by-Step-persistence-JPA/blob/master/banco.png)








3 - Criando uma classe referencia 

	2.2 - Crie um pacote chamado Model dentro do pacote src/JAVA
	2.3 - Crie uma classe Cliente.
	2.4 - Crie os atributos e encapsule
	2.5 - Crie equals e hashCode apenas para chave primária.

















4 - Crie Anotações

@Entity na classe Cliente // Referencía a classe para mapear uma tabela

@Id apenas na chave primária (id)

@Table (name = “db_cliente”) // Quando o nome da classe é diferente da tabela no banco

@Column (name=”cli_id”) // configura o nome das colunas.


	package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // quem vai gerar a primarykey será o banco de dados
    private Integer Id;


    private String nome;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Id.equals(cliente.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}












5 - Testando nossa configuração

	4.1 Crie um pacote Cadastro cliente
	
	4.2 Crie uma classe de testes chamada “Exemplo”
	
	4.3 Crie o método MAIN
	
	4.4 Insira os dados da fábrica:

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









