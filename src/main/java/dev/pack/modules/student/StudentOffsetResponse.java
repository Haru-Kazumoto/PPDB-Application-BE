package dev.pack.modules.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.pack.modules.lookup.Lookup;
import dev.pack.modules.registration_batch.RegistrationBatch;
import dev.pack.modules.student_logs.StudentLogs;
import dev.pack.modules.student_payments.StudentPayments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentOffsetResponse {

    @JsonProperty("offset_data")
    private StudentLogs studentLogs;

    @JsonProperty("registration_batch")
    private RegistrationBatch registrationBatch;

    @JsonProperty("current_state")
    private StudentLogs currentState;

    @JsonProperty("student")
    private Student student;

    @JsonProperty("major")
    private Lookup major;

    @JsonProperty("payment_status")
    private StudentPayments studentPayments;

}
