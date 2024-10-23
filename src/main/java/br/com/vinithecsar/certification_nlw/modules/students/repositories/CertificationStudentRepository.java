package br.com.vinithecsar.certification_nlw.modules.students.repositories;

import br.com.vinithecsar.certification_nlw.modules.students.entities.CertificationStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repositório para gerenciar operações de acesso a dados relacionadas às certificações dos estudantes.
 * Extende {@link JpaRepository} para fornecer métodos CRUD e consultas personalizadas.
 */
@Repository
public interface CertificationStudentRepository extends JpaRepository<CertificationStudentEntity, UUID> {

    /**
     * Busca as certificações de um estudante com base no email e na tecnologia.
     *
     * @param email      O endereço de e-mail do estudante.
     * @param technology A tecnologia da certificação.
     * @return Uma lista de certificações do tipo {@link CertificationStudentEntity} que correspondem ao estudante e à tecnologia especificados.
     */
    @Query("SELECT c FROM certifications c INNER JOIN c.studentEntity std WHERE std.email = :email AND c.technology = :technology")
    List<CertificationStudentEntity> findByStudentEmailAndTechnology(String email, String technology);

    /**
     * Busca as 10 melhores certificações ordenadas pela nota em ordem decrescente.
     *
     * @return Uma lista das 10 certificações do tipo {@link CertificationStudentEntity} com as notas mais altas.
     */
    @Query("SELECT c from certifications c ORDER BY c.grade DESC LIMIT 10")
    List<CertificationStudentEntity> findTop10ByOrderByGradeDesc();
}
