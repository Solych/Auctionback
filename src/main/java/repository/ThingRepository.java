package repository;

import model.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface ThingRepository extends JpaRepository<Thing, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM auctionSchema.THING WHERE OWNER_ID = ?1")
    List<Thing> findAllByOwnerId(Integer userId);

    @Query(nativeQuery = true, value = "SELECT * FROM auctionSchema.THING WHERE TIME_FOR_SELLING > ?1 LIMIT 24")
    List<Thing> findRandom24(Date now);

    @Query(nativeQuery = true, value =
            "SELECT * FROM auctionSchema.thing WHERE TIME_FOR_SELLING > ?1 AND CATEGORY_ID = ?2 LIMIT ?3, 24")
    List<Thing> findByCategoryPageable(Date now, Integer categoryId, Integer page);

    @Query(nativeQuery = true, value = "SELECT * FROM auctionSchema.THING WHERE THING.THING_ID = ?1")
    Thing findByThingId(Integer thingId);

    @Query(nativeQuery = true, value = "SELECT * FROM auctionSchema.THING where auctionSchema.THING.NAME LIKE ?1 AND TIME_FOR_SELLING > ?2")
    List<Thing> getThingsByNameLike(String name, Date now);

    @Query(nativeQuery = true, value = "SELECT * FROM auctionSchema.THING ORDER BY RAND() LIMIT 1")
    Thing getRandomIdOfThing();

    @Query(nativeQuery = true, value = "SELECT MAX(PRICE) as price FROM auctionSchema.FACT_OVERRIDE WHERE THING_ID = ?1")
    Integer getLastPrice(Integer thingId);

    @Query(nativeQuery = true, value = "SELECT * FROM auctionSchema.THING WHERE THING.TIME_FOR_SELLING < ?1 AND MESSAGE IS NULL")
    List<Thing> getExpired(Date now);

    @Query(nativeQuery = true, value = "SELECT * FROM THING INNER JOIN auctionSchema.FACT_OVERRIDE ON FACT_OVERRIDE.THING_ID = THING.THING_ID WHERE FACT_OVERRIDE.BUYER_ID = ?1")
    List<Thing> getUserBets(Integer userId);
}
