package demo.examinationContext.domain.model;

import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
public class Examination {
    private String id;
    private Paper paper;
    private String teacherId;
    private long duration;
    private Date dueTime;

    private Examination(Paper paper, String teacher, long duration, Date dueTime) {
        this.id = new Date().toString();
        this.paper = paper;
        this.teacherId = teacher;
        this.duration = duration;
        this.dueTime = new Date(dueTime.getTime());
    }

    private static boolean validatePaper(Paper paper) {
        final List<BlankQuiz> blankQuizzes = paper.getBlankQuizzes();
        int size = blankQuizzes.size();
        if (size < 5 || size > 20) return false;

        int totalScore = 0;
        for (int i = 0; i < size; i++) {
            BlankQuiz quiz = blankQuizzes.get(i);
            totalScore += quiz.getScore();
            if (blankQuizzes.lastIndexOf(quiz) != i) {
                return false;
            }
        }
        if (totalScore != 100) {
            return false;
        }

        return true;
    }

    public static Examination create(Paper paper, String teacher, long duration, Date dueTime) {
        if (!validatePaper(paper)) {
            throw new IllegalArgumentsException("Paper invalid");
        }
        if (duration <= 0 || duration > TimeUnit.HOURS.toSeconds(2)) {
            throw new IllegalArgumentsException("Duration should be positive, and less than 2 hours");
        }
        if (dueTime.before(new Date())) {
            throw new IllegalArgumentsException("Due date should be later than now");
        }
        return new Examination(paper, teacher, duration, dueTime);
    }
}
