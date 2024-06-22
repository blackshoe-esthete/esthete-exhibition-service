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

    @Query(value = "SELECT new com.blackshoe.esthete.dto.ExhibitionClusteringDto$MarkedRegionGroupResponse(" +
            "count(el), " +
            "el.state, " +
            "el.exhibition.thumbnailUrl) " +
            "FROM ExhibitionLocation el " +
            "WHERE el.latitude " +
            "BETWEEN :#{#exhibitionPointFilter.latitude - #exhibitionPointFilter.latitudeDelta} " +
            "AND :#{#exhibitionPointFilter.latitude + #exhibitionPointFilter.latitudeDelta} " +
            "AND el.longitude " +
            "BETWEEN :#{#exhibitionPointFilter.longitude - #exhibitionPointFilter.longitudeDelta} " +
            "AND :#{#exhibitionPointFilter.longitude + #exhibitionPointFilter.longitudeDelta} " +
            "GROUP BY el.state, el.exhibition.thumbnailUrl " +
            "ORDER BY count(el) DESC")
    Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findByUserLocationGroupByState(
            @Param("exhibitionPointFilter") ExhibitionPointFilter exhibitionPointFilter, Pageable pageable);


    default Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findTop10ByUserLocationGroupByCity(ExhibitionPointFilter exhibitionPointFilter) {
        return findByUserLocationGroupByCity(exhibitionPointFilter, PageRequest.of(0, 10));
    }

    @Query(value = "SELECT new com.blackshoe.esthete.dto.ExhibitionClusteringDto$MarkedRegionGroupResponse(" +
            "count(el), " +
            "el.state, " +
            "el.city, " +
            "el.exhibition.thumbnailUrl) " +
            "FROM ExhibitionLocation el " +
            "WHERE el.latitude " +
            "BETWEEN :#{#exhibitionPointFilter.latitude - #exhibitionPointFilter.latitudeDelta} " +
            "AND :#{#exhibitionPointFilter.latitude + #exhibitionPointFilter.latitudeDelta} " +
            "AND el.longitude " +
            "BETWEEN :#{#exhibitionPointFilter.longitude - #exhibitionPointFilter.longitudeDelta} " +
            "AND :#{#exhibitionPointFilter.longitude + #exhibitionPointFilter.longitudeDelta} " +
            "GROUP BY el.state, el.city, el.exhibition.thumbnailUrl " +
            "ORDER BY count(el) DESC")
    Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findByUserLocationGroupByCity(
            @Param("exhibitionPointFilter") ExhibitionPointFilter exhibitionPointFilter, Pageable pageable);


    default Page<ExhibitionClusteringDto.MarkedRegionGroupResponse> findTop10ByUserLocationGroupByTown(ExhibitionPointFilter exhibitionLocationFilter){
        return findByUserLocationGroupByTown(exhibitionLocationFilter, PageRequest.of(0, 10));
    }

    @Query(value = "SELECT new com.blackshoe.esthete.dto.ExhibitionClusteringDto$MarkedRegionGroupResponse(" +
            "count(el), " +
            "el.state, " +
            "el.city, " +
            "el.town, " +
            "el.exhibition.thumbnailUrl) " +
            "FROM ExhibitionLocation el " +
            "WHERE el.latitude " +
            "BETWEEN :#{#exhibitionPointFilter.latitude - #exhibitionPointFilter.latitudeDelta} " +
            "AND :#{#exhibitionPointFilter.latitude + #exhibitionPointFilter.latitudeDelta} " +
            "AND el.longitude " +
            "BETWEEN :#{#exhibitionPointFilter.longitude - #exhibitionPointFilter.longitudeDelta} " +
            "AND :#{#exhibitionPointFilter.longitude + #exhibitionPointFilter.longitudeDelta} " +
            "GROUP BY el.state, el.city, el.town, el.exhibition.thumbnailUrl " +
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
  
    Optional<List<Exhibition>> findTop6ByOrderByViewCountDesc();

    Optional<List<Exhibition>> findTop6ByOrderByViewCountAsc();

    Optional<List<Exhibition>> findAllByUser(User user);

    Boolean existsByUserId(Long userId);

    Boolean existsByUserAndExhibitionId(User user, UUID exhibitionId);
}
