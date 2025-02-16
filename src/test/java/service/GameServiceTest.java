package service;

import com.quest.entity.Answer;
import com.quest.entity.Question;
import com.quest.service.GameService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameServiceTest {
    private GameService gameService;
    private Map<Integer, Question> questions;

    @BeforeEach
    void setUp() {
        questions = new HashMap<>();

        List<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer("Принять вызов", 2, false, false, null));
        answers1.add(new Answer("Отклонить вызов", null, false, true, "Ты отклонил вызов. Поражение"));

        Question question1 = new Question(1, "Ты потерял память. Принять вызов НЛО?", answers1);
        questions.put(1, question1);

        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("Подняться на мостик", 4, false, false, null));
        answers2.add(new Answer("Отказаться подниматься на мостик", null, false, true, "Ты не пошел на переговоры. Поражение"));

        Question question2 = new Question(2, "Ты принял вызов. Поднимаешься на мостик к капитану?", answers2);
        questions.put(2, question2);

        gameService = new GameService(questions);
    }

    @Test
    void testProcessPlayerName() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("playerName")).thenReturn("TestPlayer");

        gameService.processPlayerName(request, session);

        Mockito.verify(session).setAttribute("playerName", "TestPlayer");
        Mockito.verify(session).setAttribute("currentQuestionId", 1);
    }

    @Test
    void testGetCurrentQuestion() {
        HttpSession session = mock(HttpSession.class);

        when(session.getAttribute("currentQuestionId")).thenReturn(1);

        Question question = gameService.getCurrentQuestion(session);

        assertNotNull(question);
        assertEquals(1, question.getId());
    }

    @Test
    void testFindSelectedAnswer() {
        Question question = questions.get(1);

        Answer answer = gameService.findSelectedAnswer(question, "Принять вызов");

        assertNotNull(answer);
        assertEquals("Принять вызов", answer.getText());
    }

    @Test
    void testGetNextQuestionId() {
        Answer answer = new Answer("Принять вызов", 2, false, false, null);

        Integer nextQuestionId = gameService.getNextQuestionId(answer);

        assertEquals(2, nextQuestionId);
    }

    @Test
    void testIsWin() {
        Answer answer = new Answer("Победа", null, true, false, "Вы победили!");

        boolean isWin = gameService.isWin(answer);

        assertTrue(isWin);
    }

    @Test
    void testIsLose() {
        Answer answer = new Answer("Проигрыш", null, false, true, "Вы проиграли!");

        boolean isLose = gameService.isLose(answer);

        assertTrue(isLose);
    }

    @Test
    void testGetResultText() {
        Answer answer = new Answer("Победа", null, true, false, "Вы победили!");

        String resultText = gameService.getResultText(answer);

        assertEquals("Вы победили!", resultText);
    }

    @Test
    void testPlayerNameExists() {
        HttpSession session = mock(HttpSession.class);

        when(session.getAttribute("playerName")).thenReturn("TestPlayer");

        boolean playerNameExists = gameService.playerNameExists(session);

        assertTrue(playerNameExists);
    }
}
