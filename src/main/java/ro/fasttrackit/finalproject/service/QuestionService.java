package ro.fasttrackit.finalproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.fasttrackit.finalproject.model.Question;
import ro.fasttrackit.finalproject.model.QuestionFormData;
import ro.fasttrackit.finalproject.model.Result;
import ro.fasttrackit.finalproject.repository.QuestionRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository repository;
    private final Result result;

    public QuestionService(QuestionRepository repository, Result result) {
        this.repository = repository;
        this.result = result;
    }

    public List<Question> getAll(){
        return repository.findAll();
    }

    public void saveQuestion(Question questions) {
        this.repository.save(questions);
    }

    public Result getResult(QuestionFormData questionFormData) {
        result.setTotalCorrect(0);
        result.setTotalWrong(0);
        for(Question q: questionFormData.getQuestions()) {
            if(Objects.equals(q.getCorrectAnswer(), q.getChoose()))
                result.setTotalCorrect(result.getTotalCorrect() + 1);
            else
                result.setTotalWrong(result.getTotalWrong() + 1);
        }
        return result;
    }

    public Question getQuestionById(int id) {
        Optional<Question> optional = repository.findById(id);
        Question question;
        if (optional.isPresent()) {
            question = optional.get();
        } else {
            throw new RuntimeException("Question not found for id: " + id);
        }
        return question;
    }

    public void deleteQuestionById(int id) {
        this.repository.deleteById(id);
    }

    public Page<Question> findPaginated (int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.repository.findAll(pageable);
    }
}
