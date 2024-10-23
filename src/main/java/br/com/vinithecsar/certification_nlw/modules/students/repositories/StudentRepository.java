package br.com.vinithecsar.certification_nlw.modules.students.repositories;

import br.com.vinithecsar.certification_nlw.modules.students.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repositório para gerenciar operações de acesso a dados relacionadas aos estudantes.
 * Extende {@link JpaRepository} para fornecer métodos CRUD e consultas personalizadas.
 */
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {

    /**
     * Busca um estudante pelo seu endereço de e-mail.
     *
     * @param email O endereço de e-mail do estudante.
     * @return Um {@link Optional} contendo o estudante encontrado, ou vazio se nenhum estudante corresponder ao e-mail fornecido.
     */
    Optional<StudentEntity> findByEmail(String email);
}
