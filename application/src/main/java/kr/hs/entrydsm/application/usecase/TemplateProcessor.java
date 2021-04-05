package kr.hs.entrydsm.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TemplateProcessor {

    private final TemplateEngine templateEngine;

    public String process(String template, Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);
        return templateEngine.process(template, context);
    }
}
