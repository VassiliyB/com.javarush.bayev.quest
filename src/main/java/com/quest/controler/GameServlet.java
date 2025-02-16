package com.quest.controler;

import com.quest.entity.JsonReader;
import com.quest.entity.Question;
import com.quest.service.GameService;
import com.quest.util.IpConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "GameServlet", value = "/game")
public class GameServlet extends HttpServlet {
    private Map<Integer, Question> questions;
    private GameService gameService;

    @Override
    public void init() throws ServletException {
        try {
            questions = JsonReader.readQuestionsFromJson("questions.json");
            gameService = new GameService(questions);
        } catch (IOException e) {
            throw new ServletException("Error loading questions from JSON", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String ipAddress = req.getRemoteAddr();
        ipAddress = IpConverter.convertIpv6ToIpv4(ipAddress);

        req.setAttribute("ipAddress", ipAddress);

        Question currentQuestion = gameService.getCurrentQuestion(session);

        if (currentQuestion == null) {
            resp.getWriter().println("Вопрос не найден");
            return;
        }

        req.setAttribute("questionText", currentQuestion.getText());
        req.setAttribute("answers", currentQuestion.getAnswers());

        req.getRequestDispatcher("/jsp/game.jsp").forward(req, resp);
    }
}
