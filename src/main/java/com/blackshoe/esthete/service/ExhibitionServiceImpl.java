package com.blackshoe.esthete.service;

import com.blackshoe.esthete.common.vo.ExhibitionAddressFilter;
import com.blackshoe.esthete.common.vo.ExhibitionAddressSearchType;
import com.blackshoe.esthete.common.vo.ExhibitionLocationGroupType;
import com.blackshoe.esthete.common.vo.ExhibitionPointFilter;
import com.blackshoe.esthete.dto.ExhibitionClusteringDto;
import com.blackshoe.esthete.dto.MainHomeDto;
import com.blackshoe.esthete.dto.SearchExhibitionDto;
import com.blackshoe.esthete.entity.*;
import com.blackshoe.esthete.exception.*;
import com.blackshoe.esthete.repository.*;
import com.blackshoe.esthete.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ExhibitionServiceImpl implements ExhibitionService{
    private final ExhibitionRepository exhibitionRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final ExhibitionTagRepository exhibitionTagRepository;
    private final ViewRepository viewRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final JwtUtil jwtUtil;

    @Override
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
    public Page<SearchExhibitionDto.SearchAuthorResponse> searchAllAuthor(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "followerCount"));
        Page<User> allUser = userRepository.findAll(pageRequest);

        return allUser.map(author -> SearchExhibitionDto.SearchAuthorResponse.builder()
                .photographerId(author.getUserId())
                .photographerName(author.getNickname())
                .photographerIntroduction(author.getIntroduce())
                .profileImgUrl(author.getProfileUrl().getCloudfrontUrl())
                .supportCount(author.getFollowerCount())
                .build());
    }

    @Override
    public Page<SearchExhibitionDto.SearchAuthorResponse> searchAuthor(String authorKeyword, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "followerCount"));
        Page<User> findAuthorList = userRepository.findByNicknameContaining(authorKeyword, pageRequest);

        return findAuthorList.map(author -> SearchExhibitionDto.SearchAuthorResponse.builder()
                .photographerId(author.getUserId())
                .photographerName(author.getNickname())
                .photographerIntroduction(author.getIntroduce())
                .profileImgUrl(author.getProfileUrl().getCloudfrontUrl())
                .supportCount(author.getFollowerCount())
                .build());
    }

    @Override
    public Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> getTop10ByUserLocationGroupBy(ExhibitionPointFilter exhibitionLocationFilter, ExhibitionLocationGroupType exhibitionLocationGroupType){
       Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> markedRegionGroupResponse;

       switch (exhibitionLocationGroupType){
           case STATE:
               markedRegionGroupResponse = exhibitionRepository.findTop10ByUserLocationGroupByState(exhibitionLocationFilter);
               return markedRegionGroupResponse;
           case CITY:
               markedRegionGroupResponse = exhibitionRepository.findTop10ByUserLocationGroupByCity(exhibitionLocationFilter);
               return markedRegionGroupResponse;
           case TOWN:
               markedRegionGroupResponse = exhibitionRepository.findTop10ByUserLocationGroupByTown(exhibitionLocationFilter);
               return markedRegionGroupResponse;
           default:
               throw new ExhibitionException(ExhibitionErrorResult.INVALID_LOCATION_GROUP_TYPE);
       }
    }

    @Override
    public Page<ExhibitionClusteringDto.MarkedExhibitionsResponse> readByAddress(ExhibitionAddressFilter exhibitionAddressFilter, Integer page, Integer size, Sort sortBy){
        Pageable pageable = PageRequest.of(page, size, sortBy);

        Page<ExhibitionClusteringDto.MarkedExhibitionsResponse> markedExhibitionsResponse;

        ExhibitionAddressSearchType exhibitionAddressSearchType = exhibitionAddressFilter.getSearchType();

        switch (exhibitionAddressSearchType) {
            case STATE:
                markedExhibitionsResponse = exhibitionRepository.findAllByExhibitionLocationState(exhibitionAddressFilter, pageable);
                return markedExhibitionsResponse;
            case CITY:
                markedExhibitionsResponse = exhibitionRepository.findAllByExhibitionLocationStateAndCity(exhibitionAddressFilter, pageable);
                return markedExhibitionsResponse;
            case TOWN:
                markedExhibitionsResponse = exhibitionRepository.findAllByExhibitionLocationStateAndCityAndTown(exhibitionAddressFilter, pageable);
                return markedExhibitionsResponse;
            default:
                throw new ExhibitionException(ExhibitionErrorResult.INVALID_ADDRESS_FILTER);
        }
    }
  
    // 개인 추천 전시회 조회 메서드
    @Override
    public List<MainHomeDto.ExhibitionResponse> getRecommendExhibitions(String authorizationHeader) {
        List<Exhibition> exhibitions = exhibitionRepository.findTop6ByOrderByViewCountDesc()
                .orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITIONS));
        if (exhibitions.size() < 6) {
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

    // 소외 전시회 조회 메서드
    @Override
    public List<MainHomeDto.ExhibitionResponse> getIsolationExhibitions() {
        List<Exhibition> exhibitions = exhibitionRepository.findTop6ByOrderByViewCountAsc()
                .orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITIONS));
        if (exhibitions.size() < 6) {
            throw new ExhibitionException(ExhibitionErrorResult.FAIL_TO_GET_SIX_EXHIBITIONS);
        }
        return MainHomeDto.ExhibitionResponse.of(exhibitions);
    }

    // 태그 선택 전시회 조회 메서드
    @Override
    public List<MainHomeDto.ExhibitionResponse> getExhibitionsByTag(String tagName) {
        Tag tag = tagRepository.findByName(tagName)
                .orElseThrow(() -> new TagException(TagErrorResult.NOT_FOUND_TAG));

        List<ExhibitionTag> exhibitionTags = exhibitionTagRepository.findAllByTag(tag)
                .orElseThrow(() -> new TagException(TagErrorResult.NOT_FOUND_EXHIBITION_TAGS));

        List<Exhibition> exhibitions = new ArrayList<>();
        for (ExhibitionTag exhibitionTag : exhibitionTags) {
            exhibitions.add(exhibitionTag.getExhibition());
        }
        // 추후 추천 알고리즘 적용 예정, 현재는 단순히 태그가 포함되어 있으면 반환
        return MainHomeDto.ExhibitionResponse.of(exhibitions);
    }

    // 주변 전시회 조회 메서드
    @Override
    public List<MainHomeDto.ExhibitionResponse> getNearByExhibitions(String authorizationHeader, Double longitude, Double latitude) {
        if (Math.abs(longitude) > 180) {
            throw new ExhibitionException(ExhibitionErrorResult.INVALID_LONGITUDE);
        }
        if (Math.abs(latitude) > 90) {
            throw new ExhibitionException(ExhibitionErrorResult.INVALID_LATITUDE);
        }
        List<Exhibition> exhibitions = exhibitionRepository.findTop6NearestExhibitions(latitude, longitude, PageRequest.of(0, 6));
        return MainHomeDto.ExhibitionResponse.of(exhibitions);
    }

    // 선호 작가 조회 메서드
    @Override
    public List<MainHomeDto.AuthorResponse> getPreferAuthors(String authorizationHeader) {
        List<User> users;
        if (Objects.isNull(authorizationHeader)) {
            users = userRepository.findRandom6Users();
        } else {
            User user = jwtUtil.getUserFromHeader(authorizationHeader);
            List<Like> likes = likeRepository.findAllByUserId(user.getUserId());

            Map<User, Integer> map = new HashMap<>();
            List<User> existUsers = userRepository.findAll();
            for (User existUser : existUsers) {
                if (!existUser.getUserId().equals(user.getUserId())) {
                    map.put(existUser, 0);
                }
            }
            likes.forEach(like -> {
                Exhibition exhibition = exhibitionRepository.findByExhibitionId(like.getExhibitionId())
                        .orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITION));
                User exhibitionUser = exhibition.getUser();
                map.put(exhibitionUser, map.get(exhibitionUser) + 1);
            });
            // 좋아요를 많이 누른 작가 6명을 뽑아냄
            users = map.entrySet().stream()
                    .sorted(Map.Entry.<User, Integer>comparingByValue().reversed())
                    .limit(6)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
        return MainHomeDto.AuthorResponse.of(users);
    }

    // 전시회 상세 조회 메서드
    @Override
    public MainHomeDto.ExhibitionDetailResponse getExhibitionDetails(String authorizationHeader, String exhibitionId) {
        Exhibition exhibition = exhibitionRepository.findByExhibitionId(UUID.fromString(exhibitionId))
                .orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITION));

        if (!Objects.isNull(authorizationHeader)) {
            User user = jwtUtil.getUserFromHeader(authorizationHeader);
            // 조회수++
            exhibition.increaseViewCount();
            exhibitionRepository.save(exhibition);
            View view = View.builder()
                    .exhibition(exhibition)
                    .user(user)
                    .build();
            viewRepository.save(view);
        }
        return MainHomeDto.ExhibitionDetailResponse.of(exhibition);
    }

    // 댓글 전체 조회 메서드
    @Override
    public List<MainHomeDto.CommentResponse> getAllComments(String exhibitionId) {
        Exhibition exhibition = exhibitionRepository.findByExhibitionId(UUID.fromString(exhibitionId))
                .orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITION));
        List<Comment> comments = commentRepository.findAllByExhibition(exhibition);
        return comments.stream()
                .map(comment -> {
                    User user = userRepository.findByUserId(comment.getUserId())
                            .orElseThrow(() -> new UserException(UserErrorResult.NOT_FOUND_USER));
                    return MainHomeDto.CommentResponse.of(comment, user);
                })
                .collect(Collectors.toList());
    }

    // 댓글 등록 메서드
    @Override
    public void addComments(String authorizationHeader, MainHomeDto.CommentRequest commentRequest) {
        User user = jwtUtil.getUserFromHeader(authorizationHeader);
        Exhibition exhibition = exhibitionRepository.findByExhibitionId(UUID.fromString(commentRequest.getExhibitionId()))
                .orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITION));
        if (commentRequest.getContent().length() > 50) {
            throw new ExhibitionException(ExhibitionErrorResult.CONTENT_OVER_LIMIT_LENGTH);
        }
        if (exhibitionRepository.existsByUserId(user.getId())) {
            throw new ExhibitionException(ExhibitionErrorResult.CANNOT_COMMENT_ON_OWN_POST);
        }
        Comment comment = Comment.builder()
                .exhibition(exhibition)
                .userId(user.getUserId())
                .content(commentRequest.getContent())
                .build();
        commentRepository.save(comment);
    }

    // 댓글 좋아요 등록 메서드
    @Override
    public void addLikeToComment(String authorizationHeader, String commentId) {
        User user = jwtUtil.getUserFromHeader(authorizationHeader);
        List<Exhibition> exhibitions = exhibitionRepository.findAllByUser(user)
                .orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITIONS));

        // 해당 유저의 전시회에 달린 댓글인지 확인
        Comment comment = null;
        UUID exhibitionId = null;
        for (Exhibition exhibition : exhibitions) {
            Optional<Comment> optionalComment = exhibition.getComments().stream()
                    .filter(c -> c.getCommentId().equals(UUID.fromString(commentId)))
                    .findFirst();
            if (optionalComment.isPresent()) {
                comment = optionalComment.get();
                exhibitionId = exhibition.getExhibitionId();
                break;
            }
        }
        if (Objects.isNull(comment)) {
            throw new ExhibitionException(ExhibitionErrorResult.IS_NOT_USERS_COMMENT);
        }
        if (comment.getIsLike()) {
            throw new ExhibitionException(ExhibitionErrorResult.IS_ALREADY_LIKED);
        }

        comment.addLike();
        commentRepository.save(comment);
        Like like = Like.builder()
                .userId(user.getUserId())
                .exhibitionId(exhibitionId)
                .build();
        likeRepository.save(like);
    }

    // 댓글 좋아요 취소 메서드
    @Override
    public void removeLikeToComment(String authorizationHeader, String commentId) {
        User user = jwtUtil.getUserFromHeader(authorizationHeader);
        List<Exhibition> exhibitions = exhibitionRepository.findAllByUser(user)
                .orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_EXHIBITIONS));

        // 해당 유저의 전시회에 달린 댓글인지 확인
        Comment comment = null;
        UUID exhibitionId = null;
        for (Exhibition exhibition : exhibitions) {
            Optional<Comment> optionalComment = exhibition.getComments().stream()
                    .filter(c -> c.getCommentId().equals(UUID.fromString(commentId)))
                    .findFirst();
            if (optionalComment.isPresent()) {
                comment = optionalComment.get();
                exhibitionId = exhibition.getExhibitionId();
                break;
            }
        }
        if (Objects.isNull(comment)) {
            throw new ExhibitionException(ExhibitionErrorResult.IS_NOT_USERS_COMMENT);
        }
        if (!comment.getIsLike()) {
            throw new ExhibitionException(ExhibitionErrorResult.IS_NOT_LIKED);
        }

        comment.removeLike();
        commentRepository.save(comment);
        Like like = likeRepository.findByUserIdAndExhibitionId(user.getUserId(), exhibitionId)
                .orElseThrow(() -> new ExhibitionException(ExhibitionErrorResult.NOT_FOUND_LIKE));
        likeRepository.delete(like);
    }
}