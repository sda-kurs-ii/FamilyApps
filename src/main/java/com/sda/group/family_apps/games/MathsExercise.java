package com.sda.group.family_apps.games;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Random;

@Controller
public class MathsExercise {

    ExerciseDTO dTo;
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
        dTo = new ExerciseDTO();
        while (dTo.getZadania().size() < count) {
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
                dTo.getZadania().add(exercise);
            }
        }
        model.addAttribute("listaZadan", dTo);
        return "mathsExercise";
    }

    @PostMapping(value = "/mathsExerciseCheck")
    String check(Model model, @ModelAttribute(value = "zadania") ExerciseDTO zadania) {
        int result = 0;
        for (int i = 0; i < zadania.getZadania().size(); i++) {
            if (dTo.getZadania().get(i).getResult().equals(zadania.getZadania().get(i).getUserResult())) {
                result = result + 1;
            }
        }
        model.addAttribute("finalResult", result);
//        model.addAttribute("listaZadan", dTo);
        return "mathsExercise";
    }
}
