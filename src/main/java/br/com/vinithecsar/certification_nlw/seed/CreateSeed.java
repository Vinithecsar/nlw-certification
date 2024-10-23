package br.com.vinithecsar.certification_nlw.seed;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Classe responsável por executar um script SQL para popular o banco de dados.
 */
public class CreateSeed {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Construtor que inicializa o JdbcTemplate com a fonte de dados fornecida.
     *
     * @param dataSource A fonte de dados a ser usada pelo JdbcTemplate.
     */
    public CreateSeed(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Método principal que configura a fonte de dados e inicia o processo de seed.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5434/pg_nlw");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");

        CreateSeed createSeed = new CreateSeed(dataSource);
        createSeed.run(args);
    }

    /**
     * Executa o seed chamando o método para executar o arquivo SQL especificado.
     */
    public void run(String ...args) {
        executeSqlFile("src/main/resources/create.sql");
    }

    /**
     * Lê e executa um arquivo SQL fornecido no caminho especificado.
     *
     * @param filePath O caminho do arquivo SQL a ser executado.
     */
    private void executeSqlFile(String filePath) {
        try {
            String sqlScript = new String(Files.readAllBytes(Paths.get(filePath)));

            jdbcTemplate.execute(sqlScript);

            System.out.println("Seed realizado com sucesso");
        } catch(IOException e) {
            System.out.println("Erro ao executar arquivo " + e.getMessage());
        }
    }
}
