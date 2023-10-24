package org.example.dao.repository;

import org.example.dao.entity.EmployeeEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    @Override
    <S extends EmployeeEntity> List<S> findAll(Example<S> example);

    @Override
    Optional<EmployeeEntity> findById(Integer integer);
}
