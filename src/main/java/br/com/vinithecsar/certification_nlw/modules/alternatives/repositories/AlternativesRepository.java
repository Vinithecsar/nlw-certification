package br.com.vinithecsar.certification_nlw.modules.alternatives.repositories;

import br.com.vinithecsar.certification_nlw.modules.alternatives.entities.AlternativesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repositório para realizar operações de persistência e consultas na entidade {@link AlternativesEntity}.
 * Extende {@link JpaRepository} para fornecer métodos CRUD e consultas personalizadas.
 */
public interface AlternativesRepository extends JpaRepository<AlternativesEntity, UUID> {
}
