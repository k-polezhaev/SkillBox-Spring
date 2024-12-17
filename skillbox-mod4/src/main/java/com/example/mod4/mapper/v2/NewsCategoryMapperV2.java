package com.example.mod4.mapper.v2;

import com.example.mod4.model.NewsCategory;
import com.example.mod4.web.model.NewsCategoryRequest;
import com.example.mod4.web.model.NewsCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsCategoryMapperV2 {
    NewsCategory requestToNewsCategory(NewsCategoryRequest request);
    NewsCategoryResponse newsCategoryToResponse(NewsCategory newsCategory);
}
