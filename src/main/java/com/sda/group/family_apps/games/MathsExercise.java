package com.sda.group.family_apps.games;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Random;

@Controller
public class MathsExercise {

    ExerciseDTO exerciseDTO;
    Random random = new Random();

    @GetMapping(value = "/logicGames")
    String showLogicGamesMenu() {
        return "logicGames";
    }

    @GetMapping(value = "/mathsExercise")
    String showMathsExercise() {
        return "mathsExercise";
    }

    @PostMapping(value = "/mathsExercise")
    String getExercises(Model model,
                        @ModelAttribute(value = "type") String exerciseType,
                        @ModelAttribute(value = "count") Integer count,
                        @ModelAttribute(value = "zakres") Integer maxZakres,
                        @ModelAttribute(value = "minZakres") Integer minZakres) {
        exerciseDTO = new ExerciseDTO();
        prepareExercises(model, exerciseType, count, maxZakres, minZakres);
        model.addAttribute("listaZadan", exerciseDTO);
        return "mathsExercise";
    }

    @PostMapping(value = "/mathsExerciseCheck")
    String check(Model model, @ModelAttribute(value = "zadania") ExerciseDTO zadania) {
        int result = 0;
        for (int i = 0; i < zadania.getZadania().size(); i++) {
            if (exerciseDTO.getZadania().get(i).getResult().equals(zadania.getZadania().get(i).getUserResult())) {
                result = result + 1;
            }
        }
        model.addAttribute("finalResult", result);
        return "mathsExercise";
    }

    private void prepareExercises(Model model, @ModelAttribute("type") String exerciseType, @ModelAttribute("count") Integer count, @ModelAttribute("zakres") Integer maxZakres, @ModelAttribute("minZakres") Integer minZakres) {
        while (exerciseDTO.getZadania().size() < count) {
            Exercise exercise = new Exercise();
            exercise.setFirstNumber(random.nextInt(maxZakres));
            exercise.setSecondNumber(random.nextInt(maxZakres));
            switch (exerciseType) {
                case "add":
                    exercise.setResult(exercise.getFirstNumber() + exercise.getSecondNumber());
                    model.addAttribute("sign", " + ");
                    break;
                case "subtract":
                    exercise.setResult(exercise.getFirstNumber() - exercise.getSecondNumber());
                    model.addAttribute("sign", " - ");
                    break;
                case "divide":
                    if (exercise.getSecondNumber() != 0) {
                        exercise.setFirstNumber(exercise.getFirstNumber() * exercise.getSecondNumber());
                        exercise.setResult(exercise.getFirstNumber() / exercise.getSecondNumber());
                    }
                    model.addAttribute("sign", " : ");
                    break;
                case "multiply":
                    exercise.setResult(exercise.getFirstNumber() * exercise.getSecondNumber());
                    model.addAttribute("sign", " x ");
                    break;
            }

            if (exercise.getResult() != null
                    && exercise.getResult() <= maxZakres
                    && exercise.getResult() >= minZakres
                    && exercise.getFirstNumber()<=maxZakres) {
                exerciseDTO.getZadania().add(exercise);
            }
        }
    }
}
