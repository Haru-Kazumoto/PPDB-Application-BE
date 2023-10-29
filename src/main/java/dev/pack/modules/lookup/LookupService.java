package dev.pack.modules.lookup;

import java.util.List;

public interface LookupService {

    List<Lookup> findByValue(String value);

}
