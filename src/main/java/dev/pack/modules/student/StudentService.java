package dev.pack.modules.student;

import dev.pack.modules.registration_batch.ChooseBatchDto;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.registration_batch.RegistrationBatchDto;

import java.util.List;

public interface StudentService {

    Student createStudent(Student bodyStudent, Integer idStudent);
    List<Student> getAll(); //developing

    RegistrationBatch chooseRegistrationBatch(ChooseBatchDto batchDto);

}
