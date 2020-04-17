package demo.answerSheetContext.domain.model;

import demo.common.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
class AnswerSheet implements Entity<AnswerSheet> {

    private final String id;
    private final String studentId;
    private final String examinationId;
    private final ArrayList<Answer> answers;
    private int score;

    @Override
    public boolean sameIdentityAs(AnswerSheet other) {
        return id.equals(other.getId());
    }

    public static AnswerSheet assign(String id, String studentId, String examinationId, List<Answer> answers) {
        return new AnswerSheet(id, studentId, examinationId, new ArrayList<>(answers), 0);
    }

    public void submit(String id, String answer, int score) {
        for (Answer it : answers) {
            if (it.getId().equals(id)) {
                it.submit(answer, score);
                break;
            }
        }
    }

    public void handIn() {
        review();
    }

    private void review() {
        answers.forEach(answer -> score += answer.getScore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerSheet that = (AnswerSheet) o;

        if (score != that.score) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(studentId, that.studentId)) return false;
        if (!Objects.equals(examinationId, that.examinationId)) return false;
        return Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (examinationId != null ? examinationId.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }
}
