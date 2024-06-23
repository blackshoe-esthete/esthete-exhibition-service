package com.blackshoe.esthete.repository;

import com.blackshoe.esthete.common.vo.ExhibitionAddressFilter;
import com.blackshoe.esthete.common.vo.ExhibitionPointFilter;
import com.blackshoe.esthete.dto.ExhibitionClusteringDto;
import com.blackshoe.esthete.entity.Exhibition;
import com.blackshoe.esthete.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExhibitionRepository extends JpaRepository<Exhibition,Long> {
    Page<Exhibition> findAll(Pageable pageable);

    Page<Exhibition> findByTitleContaining(String title, Pageable pageable);

    Optional<Exhibition> findByExhibitionId(UUID exhibitionId);

    default Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findTop10ByUserLocationGroupByState(ExhibitionPointFilter exhibitionPointFilter) {
        return findByUserLocationGroupByState(exhibitionPointFilter, PageRequest.of(0, 10));
    }

//    @Query(value = "SELECT new com.blackshoe.esthete.dto.ExhibitionClusteringDto$MarkedRegionGroupResponse(" +
//            "count(el), " +
//            "el.state, " +
//            "el.exhibition.thumbnailUrl) " +
//            "FROM ExhibitionLocation el " +
//            "WHERE el.latitude " +
//            "BETWEEN :#{#exhibitionPointFilter.latitude - #exhibitionPointFilter.latitudeDelta} " +
//            "AND :#{#exhibitionPointFilter.latitude + #exhibitionPointFilter.latitudeDelta} " +
//            "AND el.longitude " +
//            "BETWEEN :#{#exhibitionPointFilter.longitude - #exhibitionPointFilter.longitudeDelta} " +
//            "AND :#{#exhibitionPointFilter.longitude + #exhibitionPointFilter.longitudeDelta} " +
//            "GROUP BY el.state, el.exhibition.thumbnailUrl " +
//            "ORDER BY count(el) DESC")
//    Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findByUserLocationGroupByState(
//            @Param("exhibitionPointFilter") ExhibitionPointFilter exhibitionPointFilter, Pageable pageable);

    @Query(value = "SELECT new com.blackshoe.esthete.dto.ExhibitionClusteringDto$MarkedRegionGroupResponse(" +
            "count(el), " +
            "el.state, " +
            "(SELECT e.thumbnailUrl FROM ExhibitionLocation el2 " +
            "JOIN el2.exhibition e " +
            "WHERE el2.state = el.state " +
            "ORDER BY el2.exhibition.viewCount DESC LIMIT 1)) " +
            "FROM ExhibitionLocation el " +
            "WHERE el.latitude " +
            "BETWEEN :#{#exhibitionPointFilter.latitude - #exhibitionPointFilter.latitudeDelta} " +
            "AND :#{#exhibitionPointFilter.latitude + #exhibitionPointFilter.latitudeDelta} " +
            "AND el.longitude " +
            "BETWEEN :#{#exhibitionPointFilter.longitude - #exhibitionPointFilter.longitudeDelta} " +
            "AND :#{#exhibitionPointFilter.longitude + #exhibitionPointFilter.longitudeDelta} " +
            "GROUP BY el.state " +
            "ORDER BY count(el) DESC")
    Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findByUserLocationGroupByState(
            @Param("exhibitionPointFilter") ExhibitionPointFilter exhibitionPointFilter, Pageable pageable);



    default Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findTop10ByUserLocationGroupByCity(ExhibitionPointFilter exhibitionPointFilter) {
        return findByUserLocationGroupByCity(exhibitionPointFilter, PageRequest.of(0, 10));
    }

//    @Query(value = "SELECT new com.blackshoe.esthete.dto.ExhibitionClusteringDto$MarkedRegionGroupResponse(" +
//            "count(el), " +
//            "el.state, " +
//            "el.city, " +
//            "el.exhibition.thumbnailUrl) " +
//            "FROM ExhibitionLocation el " +
//            "WHERE el.latitude " +
//            "BETWEEN :#{#exhibitionPointFilter.latitude - #exhibitionPointFilter.latitudeDelta} " +
//            "AND :#{#exhibitionPointFilter.latitude + #exhibitionPointFilter.latitudeDelta} " +
//            "AND el.longitude " +
//            "BETWEEN :#{#exhibitionPointFilter.longitude - #exhibitionPointFilter.longitudeDelta} " +
//            "AND :#{#exhibitionPointFilter.longitude + #exhibitionPointFilter.longitudeDelta} " +
//            "GROUP BY el.state, el.city, el.exhibition.thumbnailUrl " +
//            "ORDER BY count(el) DESC")
//    Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findByUserLocationGroupByCity(
//            @Param("exhibitionPointFilter") ExhibitionPointFilter exhibitionPointFilter, Pageable pageable);

    @Query(value = "SELECT new com.blackshoe.esthete.dto.ExhibitionClusteringDto$MarkedRegionGroupResponse(" +
            "count(el), " +
            "el.state, " +
            "el.city, " +
            "(SELECT e.thumbnailUrl FROM ExhibitionLocation el2 " +
            "JOIN el2.exhibition e " +
            "WHERE el2.state = el.state AND el2.city = el.city " +
            "ORDER BY el2.exhibition.createdAt DESC LIMIT 1)) " +
            "FROM ExhibitionLocation el " +
            "WHERE el.latitude " +
            "BETWEEN :#{#exhibitionPointFilter.latitude - #exhibitionPointFilter.latitudeDelta} " +
            "AND :#{#exhibitionPointFilter.latitude + #exhibitionPointFilter.latitudeDelta} " +
            "AND el.longitude " +
            "BETWEEN :#{#exhibitionPointFilter.longitude - #exhibitionPointFilter.longitudeDelta} " +
            "AND :#{#exhibitionPointFilter.longitude + #exhibitionPointFilter.longitudeDelta} " +
            "GROUP BY el.state, el.city " +
            "ORDER BY count(el) DESC")
    Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findByUserLocationGroupByCity(
            @Param("exhibitionPointFilter") ExhibitionPointFilter exhibitionPointFilter, Pageable pageable);



    default Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findTop10ByUserLocationGroupByTown(ExhibitionPointFilter exhibitionLocationFilter){
        return findByUserLocationGroupByTown(exhibitionLocationFilter, PageRequest.of(0, 10));
    }

//    @Query(value = "SELECT new com.blackshoe.esthete.dto.ExhibitionClusteringDto$MarkedRegionGroupResponse(" +
//            "count(el), " +
//            "el.state, " +
//            "el.city, " +
//            "el.town, " +
//            "el.exhibition.thumbnailUrl) " +
//            "FROM ExhibitionLocation el " +
//            "WHERE el.latitude " +
//            "BETWEEN :#{#exhibitionPointFilter.latitude - #exhibitionPointFilter.latitudeDelta} " +
//            "AND :#{#exhibitionPointFilter.latitude + #exhibitionPointFilter.latitudeDelta} " +
//            "AND el.longitude " +
//            "BETWEEN :#{#exhibitionPointFilter.longitude - #exhibitionPointFilter.longitudeDelta} " +
//            "AND :#{#exhibitionPointFilter.longitude + #exhibitionPointFilter.longitudeDelta} " +
//            "GROUP BY el.state, el.city, el.town, el.exhibition.thumbnailUrl " +
//            "ORDER BY count(el) DESC")
//    Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findByUserLocationGroupByTown(
//            @Param("exhibitionPointFilter") ExhibitionPointFilter exhibitionPointFilter, Pageable pageable);

    @Query(value = "SELECT new com.blackshoe.esthete.dto.ExhibitionClusteringDto$MarkedRegionGroupResponse(" +
            "count(el), " +
            "el.state, " +
            "el.city, " +
            "el.town, " +
            "(SELECT e.thumbnailUrl FROM ExhibitionLocation el2 " +
            "JOIN el2.exhibition e " +
            "WHERE el2.state = el.state AND el2.city = el.city AND el2.town = el.town " +
            "ORDER BY el2.exhibition.createdAt DESC LIMIT 1)) " +
            "FROM ExhibitionLocation el " +
            "WHERE el.latitude " +
            "BETWEEN :#{#exhibitionPointFilter.latitude - #exhibitionPointFilter.latitudeDelta} " +
            "AND :#{#exhibitionPointFilter.latitude + #exhibitionPointFilter.latitudeDelta} " +
            "AND el.longitude " +
            "BETWEEN :#{#exhibitionPointFilter.longitude - #exhibitionPointFilter.longitudeDelta} " +
            "AND :#{#exhibitionPointFilter.longitude + #exhibitionPointFilter.longitudeDelta} " +
            "GROUP BY el.state, el.city, el.town " +
            "ORDER BY count(el) DESC")
    Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findByUserLocationGroupByTown(
            @Param("exhibitionPointFilter") ExhibitionPointFilter exhibitionPointFilter, Pageable pageable);



    //클러스터된 전시 리스트 가져오는 메소드
    @Query("SELECT new com.blackshoe.esthete.dto.ExhibitionClusteringDto$MarkedExhibitionsResponse(el.exhibition) " +
            "FROM ExhibitionLocation el " +
            "JOIN el.exhibition e " +
            "WHERE el.state = :#{#exhibitionAddressFilter.state} ")
    Page<ExhibitionClusteringDto.MarkedExhibitionsResponse> findAllByExhibitionLocationState(
            @Param("exhibitionAddressFilter") ExhibitionAddressFilter exhibitionAddressFilter, Pageable pageable);

    @Query("SELECT new com.blackshoe.esthete.dto.ExhibitionClusteringDto$MarkedExhibitionsResponse(el.exhibition) " +
            "FROM ExhibitionLocation el " +
            "JOIN el.exhibition e " +
            "WHERE el.state = :#{#exhibitionAddressFilter.state} " +
            "AND el.city = :#{#exhibitionAddressFilter.city} ")
    Page<ExhibitionClusteringDto.MarkedExhibitionsResponse> findAllByExhibitionLocationStateAndCity(
            @Param("exhibitionAddressFilter") ExhibitionAddressFilter exhibitionAddressFilter, Pageable pageable);

    @Query("SELECT new com.blackshoe.esthete.dto.ExhibitionClusteringDto$MarkedExhibitionsResponse(el.exhibition) " +
            "FROM ExhibitionLocation el " +
            "JOIN el.exhibition e " +
            "WHERE el.state = :#{#exhibitionAddressFilter.state} " +
            "AND el.city = :#{#exhibitionAddressFilter.city} " +
            "AND el.town = :#{#exhibitionAddressFilter.town} ")
    Page<ExhibitionClusteringDto.MarkedExhibitionsResponse> findAllByExhibitionLocationStateAndCityAndTown(
            @Param("exhibitionAddressFilter") ExhibitionAddressFilter exhibitionAddressFilter, Pageable pageable);

    @Query("SELECT e FROM ExhibitionTag et JOIN et.exhibition e JOIN et.tag t WHERE t.name = :tagName ORDER BY e.viewCount DESC")
    List<Exhibition> findTop6ByTagNameOrderByViewCountDesc(@Param("tagName") String tagName, Pageable pageable);

    @Query("SELECT e FROM Exhibition e ORDER BY e.viewCount DESC")
    List<Exhibition> findTop6ByOrderByViewCountDesc(Pageable pageable);

    @Query("SELECT e FROM ExhibitionTag et JOIN et.exhibition e JOIN et.tag t WHERE t.name = :tagName ORDER BY e.viewCount ASC")
    List<Exhibition> findTop6ByTagNameOrderByViewCountAsc(@Param("tagName") String tagName, Pageable pageable);

    @Query("SELECT e FROM Exhibition e ORDER BY e.viewCount ASC")
    List<Exhibition> findTop6ByOrderByViewCountAsc(Pageable pageable);

    Optional<List<Exhibition>> findAllByUser(User user);

    Boolean existsByUserId(Long userId);

    Boolean existsByUserAndExhibitionId(User user, UUID exhibitionId);

    Optional<Exhibition> findByUserAndExhibitionId(User user, UUID exhibitionId);

    @Query(value = "SELECT e FROM Exhibition e JOIN e.exhibitionLocation el " +
            "ORDER BY (6371 * acos(cos(radians(:latitude)) * cos(radians(el.latitude)) * cos(radians(el.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(el.latitude)))) ASC")
    List<Exhibition> findTop6NearestExhibitions(@Param("latitude") Double latitude, @Param("longitude") Double longitude, Pageable pageable);
}
