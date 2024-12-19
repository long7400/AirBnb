package com.airbnb.repository;

import com.airbnb.dto.response.home.HomestayDTO;
import com.airbnb.entity.Homestay;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HomestayRepository extends JpaRepository<Homestay, Long> {

    @Query(value = """
            with destination as (
                select st_transform(st_setsrid(st_makepoint(:longitude, :latitude), 4326), 3857) as geom
            )
            select id, name, description, images, bedrooms, vh.night_amount, vh.total_amount, address, longitude, latitude
            from destination d, homestay hs inner join
            (
                select h.id, avg(ha.price) as night_amount, sum(ha.price) as total_amount
                 from destination d,
                      homestay h
                 join homestay_availability ha on h.id = ha.homestay_id
                 where st_dwithin(st_transform(st_setsrid(st_makepoint(h.longitude, h.latitude), 4326), 3857), d.geom, :radius)
                   and h.guests >= :guests
                   and ha.date between :checkinDate and :checkoutDate
                   and ha.status = :status
                 group by h.id
                 having count(ha.date) = :nights
            ) as vh using (id)
            order by hs.geom <-> d.geom
            """, nativeQuery = true)
    List<HomestayDTO> searchHomestay(@Param("longitude") Double longitude,
                                     @Param("latitude") Double latitude,
                                     @Param("radius") Double radius,
                                     @Param("checkinDate") LocalDate checkinDate,
                                     @Param("checkoutDate") LocalDate checkoutDate,
                                     @Param("nights") Integer nights,
                                     @Param("guests") Integer guests,
                                     @Param("status") String status);

    @Query(value = " SELECT h FROM Homestay h WHERE h.id = :id  AND h.isDelete = FALSE ")
    Optional<Homestay> findByIdAndNotDelete(@Param("id") @NotNull Long id);
}
