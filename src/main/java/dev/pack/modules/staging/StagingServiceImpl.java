package dev.pack.modules.staging;

import dev.pack.modules.auth.AuthenticationService;
import dev.pack.modules.enums.FormPurchaseType;
import dev.pack.modules.registration_batch.RegistrationBatchRepository;
import dev.pack.modules.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StagingServiceImpl implements StagingService {

    private final StagingRepository stagingRepository;
    private final AuthenticationService authenticationService;
    private final RegistrationBatchRepository registrationBatchRepo;

    @Override
    public List<GetAllStaging> getAllByStudent(FormPurchaseType type) {
        User user = this.authenticationService.decodeJwt();

        if( Objects.equals(user.getRole_id().getRole_name(), "Admin") ) {
            throw new BadCredentialsException("only user can access");
        }

        return this.stagingRepository.findStudentOffset(user.getStudent().getId(),type == FormPurchaseType.PEMBELIAN ? 0 : 1);
    }
}
