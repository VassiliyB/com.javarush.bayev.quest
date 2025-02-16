package com.quest.service;

import com.quest.entity.Answer;
import com.quest.entity.Question;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

public class GameService {
    private final Map<Integer, Question> questions;

    public GameService(Map<Integer, Question> questions) {
        this.questions = questions;
    }

    public void processPlayerName(HttpServletRequest request, HttpSession session) {
        String playerName = request.getParameter("playerName");

        if (playerName != null && !playerName.isEmpty()) {
            session.setAttribute("playerName", playerName);
            session.setAttribute("currentQuestionId", 1);
            initializeGamesPlayed(session);
        }
    }

    private void initializeGamesPlayed(HttpSession session) {
        if (session.getAttribute("gamesPlayed") == null) {
            session.setAttribute("gamesPlayed", 0);
        }
    }

    public Question getCurrentQuestion(HttpSession session) {
        Integer currentQuestionId = (Integer) session.getAttribute("currentQuestionId");
        if (currentQuestionId == null) {
            currentQuestionId = 1;
            session.setAttribute("currentQuestionId", currentQuestionId);
        }
        return questions.get(currentQuestionId);
    }

    public Answer findSelectedAnswer(Question currentQuestion, String answerText) {
        if (currentQuestion == null || answerText == null) {
            return null;
        }

        for (Answer answer : currentQuestion.getAnswers()) {
            if (answer.getText().equals(answerText)) {
                return answer;
            }
        }
        return null;
    }

    public Integer getNextQuestionId(Answer selectedAnswer) {
        return (selectedAnswer != null) ? selectedAnswer.getNextQuestionId() : null;
    }

    public boolean isWin(Answer selectedAnswer) {
        return (selectedAnswer != null) && selectedAnswer.isWin();
    }

    public boolean isLose(Answer selectedAnswer) {
        return (selectedAnswer != null) && selectedAnswer.isLose();
    }

    public String getResultText(Answer selectedAnswer) {
        return (selectedAnswer != null) ? selectedAnswer.getResultText() : null;
    }

    public void resetGame(HttpSession session) {
        incrementGamesPlayed(session);
        session.setAttribute("currentQuestionId", 1);
    }

    private void incrementGamesPlayed(HttpSession session) {
        Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
        if (gamesPlayed != null) {
            session.setAttribute("gamesPlayed", gamesPlayed + 1);
        }
    }

    public boolean playerNameExists(HttpSession session) {

        return session.getAttribute("playerName") != null;
    }
}
