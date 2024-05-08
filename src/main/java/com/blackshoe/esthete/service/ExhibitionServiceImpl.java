package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.SearchExhibitionDto;
import com.blackshoe.esthete.entity.Exhibition;
import com.blackshoe.esthete.entity.User;
import com.blackshoe.esthete.repository.ExhibitionRepository;
import com.blackshoe.esthete.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExhibitionServiceImpl implements ExhibitionService{
    private final ExhibitionRepository exhibitionRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public Page<SearchExhibitionDto.SearchExhibitionResponse> searchAllExhibition(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "viewCount"));
        Page<Exhibition> findAllList = exhibitionRepository.findAll(pageRequest);

        return findAllList.map(exhibition -> SearchExhibitionDto.SearchExhibitionResponse.builder()
                .exhibitionId(exhibition.getExhibitionId())
                .exhibitionTitle(exhibition.getTitle())
                .photographerName(exhibition.getUser().getNickname())
                .thumbnailImgUrl(exhibition.getCloudfrontUrl())
                .viewCount(exhibition.getViewCount())
                .build());
    }

    @Override
    @Transactional
    public Page<SearchExhibitionDto.SearchExhibitionResponse> searchExhibition(String exhibitionKeyword, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "viewCount"));
        Page<Exhibition> findByKeywordList = exhibitionRepository.findByTitleContaining(exhibitionKeyword, pageRequest);

        return findByKeywordList.map(exhibition -> SearchExhibitionDto.SearchExhibitionResponse.builder()
                .exhibitionId(exhibition.getExhibitionId())
                .exhibitionTitle(exhibition.getTitle())
                .photographerName(exhibition.getUser().getNickname())
                .thumbnailImgUrl(exhibition.getCloudfrontUrl())
                .viewCount(exhibition.getViewCount())
                .build());
    }


}
