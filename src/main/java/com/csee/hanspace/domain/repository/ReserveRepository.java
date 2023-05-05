package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.ReserveRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReserveRepository extends JpaRepository<ReserveRecord, Long> {
    @Query("select r from ReserveRecord r " +
            "left join fetch r.savedUserInfo " +
            "where r.savedUserInfo.id = :savedUserInfoId " +
            "and r.regular = false ")
    List<ReserveRecord> findAllBySavedUserInfoId(@Param("savedUserInfoId") Long savedUserInfoId);

//    @Query("select r from ReserveRecord r " +
//            "left join fetch r.savedUserInfo " +
//            "where r.savedUserInfo.id = :savedUserInfoId " +
//            "and r.regular = true ")
//    List<ReserveRecord> findAllRegularBySavedUserInfoId(@Param("savedUserInfoId") Long savedUserInfoId);

    @Query("SELECT r FROM ReserveRecord r LEFT JOIN FETCH r.savedUserInfo s WHERE s.id = :savedUserInfoId AND r.regular = true ORDER BY r.regularId, r.date ASC")
    List<ReserveRecord> findAllRegularBySavedUserInfoId(@Param("savedUserInfoId") Long savedUserInfoId);

//    @Query("select r from ReserveRecord r " +
//            "where r.regularId = :regularId " +
//            "order by r.id desc Limit 1"
//    )
    ReserveRecord findTop1ByRegularId(Long regularId);

    @Query("select r from ReserveRecord r " +
            "where r.regularId = :regularId " +
            "and r.regular = true ")
    List<ReserveRecord> findAllByRegularId(@Param("regularId") Long regularId);

//    @Query("select r from ReserveRecord  r " + "join fetch r.timeRecordList t " + "where r.site.id = :siteId")
//    List<ReserveRecord> findAllReserveBySiteId(Long siteId);
    List<ReserveRecord> findAllReserveBySiteId(Long siteId);

    @Query("select MAX (regularId) from ReserveRecord where id = :siteId")
    Long findCurrentRegularId(Long siteId);

    List<ReserveRecord> findBySiteIdAndRegularId(Long siteId, Long regularId);

    ReserveRecord findBySiteIdAndId(Long siteId, Long reserveId);

    @Modifying
    @Query("Update ReserveRecord r set r.deleted = true where r.regularId = :regularId and r.deleted = false")
    void deleteAllByRegularId(Long regularId);

}
