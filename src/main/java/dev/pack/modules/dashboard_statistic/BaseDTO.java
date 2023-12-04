package dev.pack.modules.dashboard_statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseDTO {

    private Integer id;
    private String name;
    private Integer countStudent;

    //TODO : ITUNG SISWA YANG MENDAFTAR DARI JALUR PENDAFTARAN
}
