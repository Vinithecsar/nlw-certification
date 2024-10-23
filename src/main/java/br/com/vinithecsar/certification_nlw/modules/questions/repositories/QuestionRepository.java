package br.com.vinithecsar.certification_nlw.modules.questions.repositories;

import br.com.vinithecsar.certification_nlw.modules.questions.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repositório para realizar operações de persistência e consultas na entidade {@link QuestionEntity}.
 * Extende {@link JpaRepository} para fornecer métodos CRUD e consultas personalizadas.
 */
public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {

    /**
     * Busca uma lista de questões com base na tecnologia fornecida.
     *
     * @param technology o nome da tecnologia pela qual as questões serão filtradas.
     * @return uma lista de {@link QuestionEntity} que correspondem à tecnologia especificada.
     */
    List<QuestionEntity> findByTechnology(String technology);
}
