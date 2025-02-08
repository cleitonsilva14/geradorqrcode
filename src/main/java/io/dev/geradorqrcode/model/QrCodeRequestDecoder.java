package io.dev.geradorqrcode.model;


import org.springframework.web.multipart.MultipartFile;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class QrCodeRequestDecoder {

    private MultipartFile qrCode;

}
