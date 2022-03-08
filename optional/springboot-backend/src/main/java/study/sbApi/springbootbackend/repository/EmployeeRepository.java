package study.sbApi.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.sbApi.springbootbackend.model.Employee;

//jpaRepo takes entity class and type of the primary key
//does not require @Repository because JpaRepository has it already
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
