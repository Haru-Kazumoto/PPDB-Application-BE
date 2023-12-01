package dev.pack.modules.student;

import dev.pack.modules.enums.FormPurchaseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadPaymentDto {
    MultipartFile file;
    String amount;
    String payment_method;
    String bank_name;
    String bank_account;
    String bank_user;


    FormPurchaseType type;
}