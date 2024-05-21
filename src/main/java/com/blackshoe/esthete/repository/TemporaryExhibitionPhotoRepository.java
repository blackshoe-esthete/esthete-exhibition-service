//package com.blackshoe.esthete.repository;
//
//import com.blackshoe.esthete.entity.TemporaryExhibition;
//import com.blackshoe.esthete.entity.TemporaryExhibitionPhoto;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface TemporaryExhibitionPhotoRepository extends JpaRepository<TemporaryExhibitionPhoto,Long> {
//    Optional<List<TemporaryExhibitionPhoto>> findAllByTemporaryExhibition(TemporaryExhibition temporaryExhibition);
//
//    Boolean existsAllByTemporaryExhibition(TemporaryExhibition findTemporaryExhibition);
//}