package br.com.vinithecsar.certification_nlw.modules.alternatives.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vinithecsar.certification_nlw.modules.alternatives.dto.AlternativesCreateDTO;
import br.com.vinithecsar.certification_nlw.modules.alternatives.dto.AlternativesResultDTO;
import br.com.vinithecsar.certification_nlw.modules.alternatives.useCases.CreateAlternativeUseCase;

/**
 * Controlador REST responsável por gerenciar as alternativas.
 * Este controlador fornece endpoints para a criação de alternativas
 * e mapeia as requisições HTTP para os casos de uso correspondentes.
 */
@RestController
@RequestMapping("/alternatives")
public class AlternativeController {

    @Autowired 
    private CreateAlternativeUseCase createAlternativeUseCase;

    /**
     * Cria uma nova alternativa com os dados fornecidos no corpo da requisição.
     *
     * @param dto objeto contendo os dados da alternativa a ser criada.
     * @return um objeto {@link AlternativesResultDTO} contendo os dados da alternativa criada.
     * @throws Exception se ocorrer um erro durante o processamento da requisição.
     */
    @PostMapping()
    public AlternativesResultDTO create(@RequestBody AlternativesCreateDTO dto) throws Exception {
        return this.createAlternativeUseCase.execute(dto);
    }
}
