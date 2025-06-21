package com.dentaku.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
// Springがこのクラスをコントローラーとして認識するためのアノテーション
// アノテーションに;は不要

public class CalculatorController {
    @GetMapping("/calculator")
    public String calculator() {
        return "calculator"; // calculator.htmlを返す
    }
    // GetMappingは()内のURLに対して、calculatorメソッドを呼び出す
    // calculatorメソッドは、calculator.htmlを返す
    // .htmlは省略可能

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("num1") int num1,
            @RequestParam("num2") int num2,
            @RequestParam("operator") String operator,
            Model model) {
        // @PostMappingは、HTTPからのPOSTリクエストを処理するためのアノテーション
        // @RequestParam（パラメータ）は、HTMLフォームから送信されたパラメータを受け取るためのアノテーション
        // num1, num2, operatorは、HTMLフォームから送信されたパラメータの名前
        // Modelは、HTMLにデータを渡すための箱をSpringからもらっている（importが必要）

        int result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;

                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    result = 0; // ゼロ除算の処理
                }
                break;
            }
                model.addAttribute("result", result);
                //modelにresltを格納
                return "calculator";
                // @Controllerアノテーションが付いているクラスのメソッドは、Stringを返すと
                // Springがその文字列をテンプレートエンジンに渡して、HTMLを生成する
                // return "xxx" を、/resources/templates/xxx.html

        
    }
}
