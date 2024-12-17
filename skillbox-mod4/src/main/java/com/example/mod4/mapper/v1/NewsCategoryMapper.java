package com.example.mod4.mapper.v1;

import com.example.mod4.model.NewsCategory;
import com.example.mod4.web.model.NewsCategoryRequest;
import com.example.mod4.web.model.NewsCategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class NewsCategoryMapper {
    public NewsCategory requestToNewsCategory(NewsCategoryRequest request){
        NewsCategory newsCategory = new NewsCategory();
        if(request.getName() !=null){
            newsCategory.setName(request.getName());
        }
        return newsCategory;
    }

    public NewsCategoryResponse newsCategoryToResponse(NewsCategory newsCategory){
        NewsCategoryResponse response = new NewsCategoryResponse();
        response.setId(newsCategory.getId());
        response.setName(newsCategory.getName());
        return response;
    }


}
