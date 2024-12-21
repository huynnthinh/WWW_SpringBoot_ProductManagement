package iuh.fit.se.repositories;

import iuh.fit.se.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value = "select * from product where code like %:ma% and category_id  in (select id from category where name like %:ten%) and is_active= :active",nativeQuery = true)
    public List<Product> findByCodeAndNameCategory(@Param("ma") String code,@Param("ten") String name,@Param("active") boolean active);
}
