package dev.pack.modules.registration_batch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationBatchServiceImpl implements RegistrationBatchService{
    @Override
    public RegistrationBatch store(RegistrationBatch bodyCreate) {
        return null;
    }

    @Override
    public List<RegistrationBatch> index() {
        return null;
    }

    @Override
    public RegistrationBatch update(Integer id, RegistrationBatch bodyUpdate) {
        return null;
    }
}
