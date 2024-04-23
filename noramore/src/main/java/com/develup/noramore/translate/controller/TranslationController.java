package com.develup.noramore.translate.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TranslationController {

    @PostMapping("translate.do")
    @ResponseBody
    public String translate(@RequestParam("text") String text, @RequestParam("targetLang") String targetLang) {
        final String apiUrl = "https://api-free.deepl.com/v2/translate";
        final String apiKey = "76619abd-f2e4-4a93-9335-6df2f0f22f64:fx";

        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?auth_key=%s&text=%s&target_lang=%s", apiUrl, apiKey, text, targetLang);
        
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }
}
