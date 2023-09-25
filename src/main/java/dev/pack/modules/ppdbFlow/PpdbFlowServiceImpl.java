package dev.pack.modules.ppdbFlow;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.modules.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PpdbFlowServiceImpl implements PpdbFlowService{

    private final PpdbFlowRepository ppdbFlowRepository;
    private final UserRepository userRepository;

    @Override
    public PpdbFlow createFlow(PpdbFlow bodyCreate) {
        this.ppdbFlowRepository
                .findByTitle(bodyCreate.getTitle())
                .ifPresent(title -> {
                    throw new DuplicateDataException("Title has already exists.");
                });

        return this.ppdbFlowRepository.save(bodyCreate);
    }

    @Override
    public List<PpdbFlow> getAllFlowByUserId(Integer userId) {
        var idUser = this.userRepository
                .findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        return this.ppdbFlowRepository.findAllByUserId(idUser.getId());
    }

    @Override
    public List<PpdbFlow> getAll() {
        return this.ppdbFlowRepository.findAll();
    }

    @Override
    public PpdbFlow updateFlow(Integer id, PpdbFlow bodyUpdate) {
        PpdbFlow data = this.ppdbFlowRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        this.ppdbFlowRepository
                .findByTitle(bodyUpdate.getTitle())
                .ifPresent(title -> {
                    throw new DuplicateDataException("Title has already exists.");
                });

        data.setTitle(bodyUpdate.getTitle());
        data.setContent(bodyUpdate.getContent());

        return data;
    }
}
