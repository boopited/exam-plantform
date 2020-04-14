package demo.examinationContext;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ExaminationTest {

    private final String teacherId = "teacher-";
    private final BlankQuiz quiz1 = new BlankQuiz("Question A", "Answer A", 25);
    private final BlankQuiz quiz2 = new BlankQuiz("Question B", "Answer B", 25);
    private final BlankQuiz quiz3 = new BlankQuiz("Question C", "Answer C", 30);
    private final BlankQuiz quiz4 = new BlankQuiz("Question D", "Answer D", 20);
    private final BlankQuiz quiz5 = new BlankQuiz("Question E", "Answer E", 15);
    private final BlankQuiz quiz6 = new BlankQuiz("Question F", "Answer F", 10);
    private final BlankQuiz quiz7 = new BlankQuiz("Question G", "Answer G", 10);

    private final BlankQuiz[] score100count5 = {quiz1, quiz3, quiz4, quiz5, quiz6};
    private final BlankQuiz[] score100count4 = {quiz1, quiz2, quiz3, quiz4};
    private final BlankQuiz[] score115count5 = {quiz1, quiz2, quiz3, quiz4, quiz5};
    private final BlankQuiz[] score85count5 = {quiz3, quiz4, quiz5, quiz6, quiz7};

    private long duration;
    private Date dueTime;
    private ArrayList<BlankQuiz> quizzes = new ArrayList();
    private Examination examination;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        duration = TimeUnit.HOURS.toSeconds(2);
        dueTime = new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(5));
    }

    @After
    public void tearDown() {
        quizzes.clear();
    }

    @Test
    public void test_validParams_shouldReturnExamination() {
        quizzes.addAll(Arrays.asList(score100count5));
        final Paper paper = new Paper(quizzes);
        Examination examination = Examination.create(paper, teacherId, duration, dueTime);
        Assert.assertNotNull("Valid params should create examination", examination);
        Assert.assertEquals(examination.getDueTime(), dueTime);
        Assert.assertEquals(examination.getDuration(), duration);
        Assert.assertEquals(examination.getPaper(), paper);
        Assert.assertEquals(examination.getTeacherId(), teacherId);
    }

    @Test
    public void test_lessThan5BlankQuizzes_shouldThrowException() {
        quizzes.addAll(Arrays.asList(score100count4));
        final Paper paper = new Paper(quizzes);
        exception.expect(IllegalArgumentsException.class);
        exception.expectMessage("Paper invalid");
        Examination.create(paper, teacherId, duration, dueTime);
    }

    @Test
    public void test_moreThan5BlankQuizzes_shouldThrowException() {
        quizzes.addAll(Arrays.asList(score115count5));
        final Paper paper = new Paper(quizzes);
        exception.expect(IllegalArgumentsException.class);
        exception.expectMessage("Paper invalid");
        Examination.create(paper, teacherId, duration, dueTime);
    }

    @Test
    public void test_lessThan100Points_shouldThrowException() {
        quizzes.addAll(Arrays.asList(score85count5));
        final Paper paper = new Paper(quizzes);
        exception.expect(IllegalArgumentsException.class);
        exception.expectMessage("Paper invalid");
        Examination.create(paper, teacherId, duration, dueTime);
    }

    @Test
    public void test_moreThan100Points_shouldThrowException() {
        quizzes.addAll(Arrays.asList(score115count5));
        final Paper paper = new Paper(quizzes);
        exception.expect(IllegalArgumentsException.class);
        exception.expectMessage("Paper invalid");
        Examination.create(paper, teacherId, duration, dueTime);
    }

    @Test
    public void test_durationMoreThan2Hours_shouldThrowException() {
        quizzes.addAll(Arrays.asList(score100count5));
        final Paper paper = new Paper(quizzes);
        duration = TimeUnit.HOURS.toSeconds(3);
        exception.expect(IllegalArgumentsException.class);
        exception.expectMessage("Duration should be positive, and less than 2 hours");
        Examination.create(paper, teacherId, duration, dueTime);
    }

    @Test
    public void test_durationIsZero_shouldThrowException() {
        quizzes.addAll(Arrays.asList(score100count5));
        final Paper paper = new Paper(quizzes);
        duration = -1;
        exception.expect(IllegalArgumentsException.class);
        exception.expectMessage("Duration should be positive, and less than 2 hours");
        Examination.create(paper, teacherId, duration, dueTime);
    }

    @Test
    public void test_durationIsNegative_shouldThrowException() {
        quizzes.addAll(Arrays.asList(score100count5));
        final Paper paper = new Paper(quizzes);
        duration = -1;
        exception.expect(IllegalArgumentsException.class);
        exception.expectMessage("Duration should be positive, and less than 2 hours");
        Examination.create(paper, teacherId, duration, dueTime);
    }

    @Test
    public void test_dueDateIsEarlierThanNow_shouldThrowException() {
        quizzes.addAll(Arrays.asList(score100count5));
        final Paper paper = new Paper(quizzes);
        dueTime = new Date(System.currentTimeMillis() - 1);
        exception.expect(IllegalArgumentsException.class);
        exception.expectMessage("Due date should be later than now");
        Examination.create(paper, teacherId, duration, dueTime);
    }
}
