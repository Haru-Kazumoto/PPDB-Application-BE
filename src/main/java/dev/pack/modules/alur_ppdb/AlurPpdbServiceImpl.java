package dev.pack.modules.alur_ppdb;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.DuplicateDataException;
import dev.pack.exception.ErrorSoftDelete;
import dev.pack.modules.data.user.User;
import dev.pack.modules.data.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AlurPpdbServiceImpl implements AlurPpdbService {

    private final AlurPpdbRepository alurPpdbRepository;
    private final UserRepository userRepository;

    @Override
    public AlurPpdb createFlow(AlurPpdb bodyCreate) {
        this.alurPpdbRepository.findByTitle(bodyCreate.getTitle())
                .ifPresent((title) -> {
                    throw new DuplicateDataException("Title has exists.");
                });

        User user = this.userRepository.findById(bodyCreate.getUser_id())
                .orElseThrow(() -> new DataNotFoundException("Id user not found."));

        bodyCreate.setUser_id(bodyCreate.getUser_id());
        bodyCreate.setUserId(user);

        return this.alurPpdbRepository.save(bodyCreate);
    }

    @Override
    public List<AlurPpdb> getAllFlowByUserId(Integer userId) {
        var idUser = this.userRepository
                .findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        return this.alurPpdbRepository.findAllByUserId(idUser.getId());
    }

    @Override
    public List<AlurPpdb> getAll() {
        return this.alurPpdbRepository.findAll();
    }

    @Override
    public AlurPpdb updateFlow(Integer id, AlurPpdb bodyUpdate) {
        AlurPpdb data = this.alurPpdbRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Id not found."));

        this.alurPpdbRepository
                .findByTitle(bodyUpdate.getTitle())
                .ifPresent(title -> {
                    throw new DuplicateDataException("Title has already exists.");
                });

        data.setTitle(bodyUpdate.getTitle());
        data.setContent(bodyUpdate.getContent());

        return data;
    }

    @Override
    public void hardDeleteById(Integer id) {
        //NOT RETURNING YET
    }

    @Override
    public void softDeleteById(Integer id) {
        User data = this.userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Id not found."));
        if(data.getDeletedAt() != null) throw new ErrorSoftDelete("Data has been deactive.");

        this.alurPpdbRepository.softDeleteById(id);
    }

    @Override
    public void deleteDataHasDeleted() {

    }
}
