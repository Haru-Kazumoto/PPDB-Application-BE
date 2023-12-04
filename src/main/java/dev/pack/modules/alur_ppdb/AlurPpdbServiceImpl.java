package dev.pack.modules.alur_ppdb;

import dev.pack.exception.DataNotFoundException;
import dev.pack.exception.ErrorSoftDelete;
import dev.pack.modules.enums.Grade;
import dev.pack.modules.user.User;
import dev.pack.modules.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.pack.constraint.ErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AlurPpdbServiceImpl implements AlurPpdbService {

    private final AlurPpdbRepository alurPpdbRepository;
    private final UserRepository userRepository;

    @Override
    public AlurPpdb createFlow(AlurPpdb bodyCreate) {
        return this.alurPpdbRepository.save(bodyCreate);
    }

    @Override
    public List<AlurPpdb> getAll() {
        return this.alurPpdbRepository.findAll();
    }

    @Override
    public AlurPpdb updateFlow(Integer id, AlurPpdb bodyUpdate) {
        AlurPpdb data = this.alurPpdbRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(PPDB_FLOW_ID_NOT_FOUND));

        data.setTitle(bodyUpdate.getTitle());
        data.setContent(bodyUpdate.getContent());

        return data;
    }

    @Override
    public void hardDeleteById(Integer id) {
        this.alurPpdbRepository.deleteById(id);
    }

    @Override
    public void softDeleteById(Integer id) {
        User data = this.userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(USER_ID_NOT_FOUND));
        if(data.getDeletedAt() != null) throw new ErrorSoftDelete("Data has been deactive.");

        this.alurPpdbRepository.softDeleteById(id);
    }

    @Override
    public void deleteDataHasDeleted() {

    }
}
