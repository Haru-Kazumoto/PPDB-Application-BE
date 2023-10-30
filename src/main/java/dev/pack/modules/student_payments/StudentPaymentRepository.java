package dev.pack.modules.student_payments;

import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentPaymentRepository extends JpaRepository<StudentPayments, Integer> {

    @Query("""
        select s from StudentPayments s where s.type = :type and s.student = :student
""")
    Optional<StudentPayments> findStudentPaymentStatusByType(Student student, FormPurchaseType type);

}
