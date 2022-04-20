package ro.fasttrackit.finalproject.ui;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.fasttrackit.finalproject.model.Question;
import ro.fasttrackit.finalproject.model.QuestionFormData;
import ro.fasttrackit.finalproject.model.Result;
import ro.fasttrackit.finalproject.service.QuestionService;
import java.util.List;

@Controller
public class QuestionController {
    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("questions")
    public String viewHomePage(Model model){
        return findPaginated(1, model);
    }

    @GetMapping("/final")
    public String getQuestionPage(Model model){
        model.addAttribute("questions", service.getAll());
        return "questions";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionFormData questionFormData, RedirectAttributes redirectAttributes){
        Result result = service.getResult(questionFormData);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/result";
    }
    @GetMapping("result")
    public String result(){
        return "result";
    }

    @GetMapping("/showNewQuestionForm")
    public String showNewQuestionForm(Model model) {
        Question question = new Question();
        model.addAttribute("questions", question);
        return "newQuestion";
    }

    @PostMapping("/saveQuestion")
    public String saveQuestion(@ModelAttribute("questions") Question question) {
        service.saveQuestion(question);
        return "redirect:/questions";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Question question = service.getQuestionById(id);
        model.addAttribute("questions", question);
        return "updateQuestion";
    }

    @GetMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable(value = "id") int id) {
        this.service.deleteQuestionById(id);
        return "redirect:/questions";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,Model model) {
        int pageSize = 5;
        Page<Question> page = service.findPaginated(pageNo, pageSize);
        List<Question> questionsList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("questionsList", questionsList);
        return "list";
    }
}
