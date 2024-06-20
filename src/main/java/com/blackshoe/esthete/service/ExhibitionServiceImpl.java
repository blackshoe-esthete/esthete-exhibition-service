package com.blackshoe.esthete.service;

import com.blackshoe.esthete.common.vo.ExhibitionAddressFilter;
import com.blackshoe.esthete.common.vo.ExhibitionAddressSearchType;
import com.blackshoe.esthete.common.vo.ExhibitionLocationGroupType;
import com.blackshoe.esthete.common.vo.ExhibitionPointFilter;
import com.blackshoe.esthete.dto.ExhibitionClusteringDto;
import com.blackshoe.esthete.dto.SearchExhibitionDto;
import com.blackshoe.esthete.entity.Exhibition;
import com.blackshoe.esthete.entity.User;
import com.blackshoe.esthete.exception.ExhibitionErrorResult;
import com.blackshoe.esthete.exception.ExhibitionException;
import com.blackshoe.esthete.repository.ExhibitionRepository;
import com.blackshoe.esthete.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    @Transactional // 팔
    public Page<SearchExhibitionDto.SearchAuthorResponse> searchAllAuthor(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "supportCount"));
        Page<User> allUser = userRepository.findAll(pageRequest);

        return allUser.map(author -> SearchExhibitionDto.SearchAuthorResponse.builder()
                .photographerId(author.getUserId())
                .photographerName(author.getNickname())
                .photographerIntroduction(author.getIntroduce())
                .supportCount(author.getSupportCount())
                .build());
    }

    @Override
    @Transactional
    public Page<SearchExhibitionDto.SearchAuthorResponse> searchAuthor(String authorKeyword, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "supportCount"));
        Page<User> findAuthorList = userRepository.findByNicknameContaining(authorKeyword, pageRequest);

        return findAuthorList.map(author -> SearchExhibitionDto.SearchAuthorResponse.builder()
                .photographerId(author.getUserId())
                .photographerName(author.getNickname())
                .photographerIntroduction(author.getIntroduce())
                .supportCount(author.getSupportCount())
                .build());
    }

}
