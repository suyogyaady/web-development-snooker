package com.system.snooker.Repo;

import com.system.snooker.entity.Snooker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnookerRepo extends JpaRepository <Snooker, Integer>{
}
