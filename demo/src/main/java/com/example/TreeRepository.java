package com.example;
import org.springframework.data.jpa.repository.JpaRepository;

interface TreeRepository extends JpaRepository<TreeEntity, Long> {
}
