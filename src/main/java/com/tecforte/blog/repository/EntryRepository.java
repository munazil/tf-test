package com.tecforte.blog.repository;
import com.tecforte.blog.domain.Blog;
import com.tecforte.blog.domain.Entry;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Spring Data  repository for the Entry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
	
	@Transactional
	@Modifying
	@Query("delete from Entry e where e.title like %:str1% or e.content like %:str1% or e.title like %:str2% or e.content like %:str2%")
	void deleteByTitleLikeOrContentLike(@Param("str1") String str1, @Param("str2") String str2);
	
	@Transactional
	@Modifying
	@Query("delete from Entry e where e.blog.id = :id and e.title like %:str1% or e.content like %:str1%")
	void deleteByBlogIdWithTitleLikeOrContentLike(@Param("id") Long id, @Param("str1") String str1);

}
