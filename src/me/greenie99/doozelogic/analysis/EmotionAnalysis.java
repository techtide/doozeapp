/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.greenie99.doozelogic.analysis;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
// import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author greenie
 */
public class EmotionAnalysis {
    
    public static AnalysisResults response;
    
    public static void main(String[] args) {
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                "116ecd04-fea9-4f54-bcd5-49b39ecf5f1a",
                "Wat0UHqCdb8R"
        );

        System.out.println("Enter the emotions that you feel right now: \n");
        Scanner scanner = new Scanner(System.in);

        String textToAnalyze = scanner.nextLine();
        
        List<String> targets = new ArrayList<>();
        
        targets = getTargetListAllWords(textToAnalyze);
        
        /*SentimentOptions sentiment = new SentimentOptions.Builder()
                .build(); */

        EmotionOptions emotion= new EmotionOptions.Builder()
            .targets(targets)
            .build();

        KeywordsOptions keywords = new KeywordsOptions.Builder()
                .sentiment(Boolean.TRUE)
                .emotion(Boolean.TRUE)
                .limit(7)   
                .build();
        
        Features features = new Features.Builder()
                .emotion(emotion)
                .keywords(keywords)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .html("<p>" + textToAnalyze + "</p>")
                .features(features)
                .build();
        
        response = service
                .analyze(parameters)
                .execute();
    
        System.out.println(response);
    }
    
    public static List<String> getTargetListAllWords(String sentence) {
        return Arrays.asList(sentence.split(" "));
    }
    
    public static List<String> promptWatsonForKeywords(String sentence) {
        
        // discard
        
        return null;
    }
}
