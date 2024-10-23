package br.com.vinithecsar.certification_nlw.modules.students.controllers;

import br.com.vinithecsar.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.vinithecsar.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import br.com.vinithecsar.certification_nlw.modules.students.useCases.StudentCertificationAnswersUseCase;
import br.com.vinithecsar.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos estudantes,
 * incluindo a verificação de certificação e o envio de respostas de certificação.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    /**
     * Verifica se o estudante já possui uma certificação.
     *
     * @param verifyHasCertificationDTO objeto que contém as informações necessárias para verificar a certificação.
     * @return uma mensagem informando se o usuário já fez a prova ou se pode fazê-la.
     */
    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
        if (this.verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO)) {
            return "Usuário já fez a prova";
        }
        return "Usuário pode fazer a prova";
    }

    /**
     * Envia as respostas do estudante para a certificação.
     *
     * @param studentCertificationAnswerDTO objeto que contém as respostas do estudante.
     * @return uma resposta HTTP com o resultado do processamento das respostas.
     */
    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
        try {
            var result = this.studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
            return ResponseEntity.ok().body(result);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
