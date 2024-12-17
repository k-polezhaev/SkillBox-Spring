package com.example.mod4.aop;

import com.example.mod4.configuration.AppConfiguration;
import com.example.mod4.exception.NotPermitException;
import com.example.mod4.service.CommentService;
import com.example.mod4.service.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorCheckAspect {

    private final NewsService newsService;
    private final CommentService commentService;
    private final AppConfiguration appConfiguration;

    public AuthorCheckAspect(NewsService newsService, CommentService commentService, AppConfiguration appConfiguration) {
        this.newsService = newsService;
        this.commentService = commentService;
        this.appConfiguration = appConfiguration;
    }

    @Around("@annotation(AuthorCheck)")
    public Object authorCheck(ProceedingJoinPoint pjp) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        var pathVar = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long pathVarId = Long.valueOf(pathVar.get("id"));

        Long currentUserId = appConfiguration.currentUserId;

        Long authorId = switch (pjp.getSignature().getDeclaringTypeName()) {
            case "com.example.news.service.impl.NewsServiceImpl" ->
                    newsService.findById(pathVarId).getUser().getId();
            case "com.example.news.service.impl.CommentServiceImpl" ->
                    commentService.findById(pathVarId).getUser().getId();
            default -> null;
        };

        if (!currentUserId.equals(authorId)) {
            throw new NotPermitException("Not Permitted!");
        }

        try {
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }


}
