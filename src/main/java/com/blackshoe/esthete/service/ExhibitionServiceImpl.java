package com.blackshoe.esthete.service;

import com.blackshoe.esthete.dto.MainHomeDto;
import com.blackshoe.esthete.dto.SearchExhibitionDto;
import com.blackshoe.esthete.entity.Exhibition;
import com.blackshoe.esthete.entity.User;
import com.blackshoe.esthete.exception.ExhibitionErrorResult;
import com.blackshoe.esthete.exception.ExhibitionException;
import com.blackshoe.esthete.repository.ExhibitionRepository;
import com.blackshoe.esthete.repository.UserRepository;
import com.blackshoe.esthete.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExhibitionServiceImpl implements ExhibitionService{
    private final ExhibitionRepository exhibitionRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public Page<SearchExhibitionDto.SearchExhibitionResponse> searchAllExhibition(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "viewCount"));
        Page<Exhibition> findAllList = exhibitionRepository.findAll(pageRequest);

        return findAllList.map(exhibition -> SearchExhibitionDto.SearchExhibitionResponse.builder()
                .exhibitionId(exhibition.getExhibitionId())
                .exhibitionTitle(exhibition.getTitle())
                .photographerName(exhibition.getUser().getNickname())
                .thumbnailImgUrl(exhibition.getThumbnailUrl())
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
                .thumbnailImgUrl(exhibition.getThumbnailUrl())
                .viewCount(exhibition.getViewCount())
                .build());
    }

    @Override
    @Transactional
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

    // 개인 추천 전시회 조회 메서드
    @Override
    @Transactional
    public List<MainHomeDto.ExhibitionResponse> getRecommendExhibitions(String authorizationHeader) {
        List<Exhibition> exhibitions = exhibitionRepository.findTop6ByOrderByViewCountDesc();
        if (Objects.isNull(exhibitions) || exhibitions.size() < 6) {
            throw new ExhibitionException(ExhibitionErrorResult.FAIL_TO_GET_SIX_EXHIBITIONS);
        }
        if (!Objects.isNull(authorizationHeader)) {
            User user = jwtUtil.getUserFromHeader(authorizationHeader);
            // 추후 기존 회원은 추천 알고리즘을 통해 받는 식으로 변경할 예정
            return MainHomeDto.ExhibitionResponse.of(exhibitions);
        } else {
            return MainHomeDto.ExhibitionResponse.of(exhibitions);
        }
    }
}