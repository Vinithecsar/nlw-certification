package br.com.vinithecsar.certification_nlw.modules.students.useCases;

import br.com.vinithecsar.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.vinithecsar.certification_nlw.modules.questions.repositories.QuestionRepository;
import br.com.vinithecsar.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.vinithecsar.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import br.com.vinithecsar.certification_nlw.modules.students.entities.AnswersCertificationsEntity;
import br.com.vinithecsar.certification_nlw.modules.students.entities.CertificationStudentEntity;
import br.com.vinithecsar.certification_nlw.modules.students.entities.StudentEntity;
import br.com.vinithecsar.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import br.com.vinithecsar.certification_nlw.modules.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Caso de uso para processar as respostas de certificação dos estudantes.
 */
@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    /**
     * Executa o processamento das respostas de certificação de um estudante.
     *
     * @param dto O objeto DTO contendo as respostas do estudante.
     * @return A entidade {@link CertificationStudentEntity} de certificação do estudante criada.
     * @throws Exception se o estudante já tiver uma certificação.
     */
    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {

        var hasCertification = this.verifyIfHasCertificationUseCase.execute(new VerifyHasCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if(hasCertification) {
            throw new Exception("Você já tirou sua certificação!");
        }

        // Buscar as alternativas das perguntas
        // - Correta ou Incorreta
        List<QuestionEntity> questionEntityList = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionAnswers().stream().forEach(questionAnswer -> {
            var questionEntity = questionEntityList
                    .stream()
                    .filter(question -> question.getId().equals(questionAnswer.getQuestionId()));

            var findCorrectAlternative = questionEntity.findFirst().get().getAlternatives()
                    .stream().filter(alternative -> alternative.isCorrect()).findFirst().get();

            if(findCorrectAlternative.getId().equals(questionAnswer.getAlternativeId())) {
                questionAnswer.setCorrect(true);
                correctAnswers.incrementAndGet();
            } else {
                questionAnswer.setCorrect(false);
            }

            var answersCertificationsEntity = AnswersCertificationsEntity
            .builder()
            .answerId(questionAnswer.getAlternativeId())
            .questionId(questionAnswer.getQuestionId())
            .isCorrect(questionAnswer.isCorrect())
            .build();

            answersCertifications.add(answersCertificationsEntity);
        });
        // Verificar se existe student pelo email
        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentId;
        if(student.isEmpty()){
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentId = studentCreated.getId();
        } else {
            studentId = student.get().getId();
        }

        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
                .technology(dto.getTechnology())
                .studentId(studentId)
                .grade(correctAnswers.get())
                .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answerCertification -> {
            answerCertification.setCertificationId(certificationStudentEntity.getId());
            answerCertification.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationsEntities(answersCertifications);

        certificationStudentRepository.save(certificationStudentEntity);

        return certificationStudentCreated;
        // Salvar as informações da certificação
    }
}
