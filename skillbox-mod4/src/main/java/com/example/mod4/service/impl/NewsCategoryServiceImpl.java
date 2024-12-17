package com.example.mod4.service.impl;

import com.example.mod4.model.NewsCategory;
import com.example.mod4.repository.NewsCategoryRepository;
import com.example.mod4.service.NewsCategoryService;
import com.example.mod4.exception.EntityNotFoundException;

import com.example.mod4.web.model.PageFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsCategoryServiceImpl implements NewsCategoryService {

    private final NewsCategoryRepository newsCategoryRepository;

    @Override
    public List<NewsCategory> findAll(PageFilter filter) {
        return newsCategoryRepository.findAll(
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())
        ).getContent();
    }

    @Override
    public NewsCategory findById(Long id) {
        return newsCategoryRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException(MessageFormat.format("NewsCategory with id {0} not found!", id)));
    }

    @Override
    public NewsCategory create(NewsCategory newsCategory) {
        return newsCategoryRepository.save(newsCategory);
    }

    @Override
    public NewsCategory update(NewsCategory newsCategory) {
        NewsCategory existedCategory = findById(newsCategory.getId());
//        if(existedCategory == null){
//            throw new EntityNotFoundException(MessageFormat.format("NewsCategory with id {0} not found!", newsCategory.getId()));
//        }
        existedCategory.setName(newsCategory.getName());
        return newsCategoryRepository.save(existedCategory);
    }

    @Override
    public void deleteById(Long id) {
        newsCategoryRepository.deleteById(id);

    }
}
