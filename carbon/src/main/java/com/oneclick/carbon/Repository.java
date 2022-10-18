package com.oneclick.carbon;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneclick.carbon.Resource;

public interface Repository extends JpaRepository<Resource, Long> {
 //   List<Resource> findByPublished(boolean published);

   // List<Resource> findByTitleContaining(String title);
}
