package io.dev.geradorqrcode.controller;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import io.dev.geradorqrcode.model.QrCodeRequestDecoder;
import io.dev.geradorqrcode.model.QrCodeRequestGenerator;
import io.dev.geradorqrcode.model.QrCodeResponse;
import io.dev.geradorqrcode.service.QrCodeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MissingMatrixVariableException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/qrcode")
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @PostMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public void generateQrCode(@Valid @RequestBody QrCodeRequestGenerator requestGenerator, HttpServletResponse response) throws MissingRequestValueException, IOException, WriterException {

        if(requestGenerator == null || requestGenerator.getText() == null || requestGenerator.getText().isBlank()) {
            throw new MissingRequestValueException("Text is required");
        }
        qrCodeService.generateQrCode(requestGenerator.getText(), response.getOutputStream());
        response.getOutputStream().flush();

    }

    @PostMapping(value = "/decode")
    public QrCodeResponse decodeQr(@RequestParam(name = "qrCode") MultipartFile qrCode) throws IOException, NotFoundException {
        String qrCodeString = qrCodeService.decodeQrCode(qrCode.getBytes());
        return new QrCodeResponse(qrCodeString);
    }

}
