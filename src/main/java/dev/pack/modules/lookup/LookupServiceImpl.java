package dev.pack.modules.lookup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LookupServiceImpl implements LookupService{
    private final LookupRepository lookupRepository;

    @Override
    public List<Lookup> findByValue(String value) {
        return this.lookupRepository.getByValue(value);
    }
}
