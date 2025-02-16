package com.quest.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonReader {
    public static Map<Integer, Question> readQuestionsFromJson(String resourcePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<Integer, Question> questionsMap = new HashMap<>();

        try(InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }

            JsonNode rootNode = mapper.readTree(inputStream);
            JsonNode questionsNode = rootNode.get("questions");

            if (questionsNode.isArray()) {
                for (JsonNode questionNode : questionsNode) {
                    Question question = parseQuestion(questionNode);
                    questionsMap.put(question.getId(), question);
                }
            }

        }

        return questionsMap;
    }

    private static Question parseQuestion(JsonNode questionNode) {
        int id = questionNode.get("id").asInt();
        String text = questionNode.get("text").asText();
        List<Answer> answers = parseAnswers(questionNode.get("answers"));

        return new Question(id, text, answers);
    }

    private static List<Answer> parseAnswers(JsonNode answersNode) {
        List<Answer> answersList = new ArrayList<>();

        if (answersNode.isArray()) {
            for (JsonNode answerNode : answersNode) {
                String text = answerNode.get("text").asText();
                Integer nextQuestionId = answerNode.has("nextQuestionId")
                        && !answerNode.get("nextQuestionId").isNull()
                        ? answerNode.get("nextQuestionId").asInt()
                        : null;
                boolean isWin = answerNode.has("isWin")
                        && answerNode.get("isWin").asBoolean(false);
                boolean isLose = answerNode.has("isLose")
                        && answerNode.get("isLose").asBoolean(false);
                String resultText = answerNode.has("resultText")
                        ? answerNode.get("resultText").asText()
                        : null;

                answersList.add(new Answer(text, nextQuestionId, isWin, isLose, resultText));
            }
        }
        return answersList;
    }
}
