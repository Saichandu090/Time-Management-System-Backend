package com.tms.time_management_system.repository;

import com.tms.time_management_system.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session,Integer>
{

}
