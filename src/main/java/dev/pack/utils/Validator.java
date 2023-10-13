package dev.pack.utils;

import dev.pack.exception.ErrorSoftDelete;
import dev.pack.exception.IllegalDateTime;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class Validator {

    public void dateValidate(@NonNull Date startDate, @NonNull Date endDate){
        if(startDate.equals(endDate) || startDate.after(endDate) || endDate.before(startDate)){
            throw new IllegalDateTime("Pembuatan waktu invalid.");
        }

        long timeDifference = endDate.getTime() - startDate.getTime();
        long daysDifference = TimeUnit.MILLISECONDS.toDays(timeDifference);
        if(daysDifference < 1) throw new IllegalDateTime("Minimal tenggat waktu adalah 1 hari.");
    }

    public boolean isDataHasDeleted(Date deletedAt){
        boolean data = deletedAt != null;
        if(data) throw new ErrorSoftDelete("Data has been deactivate");
        return true;
    }

}
