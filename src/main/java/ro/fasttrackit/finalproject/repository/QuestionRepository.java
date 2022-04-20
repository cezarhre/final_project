package ro.fasttrackit.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.finalproject.model.Question;
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
